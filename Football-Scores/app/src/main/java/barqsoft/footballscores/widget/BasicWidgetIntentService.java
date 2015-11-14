package barqsoft.footballscores.widget;

import android.annotation.TargetApi;
import android.app.IntentService;
import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.RemoteViews;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import barqsoft.footballscores.DatabaseContract;
import barqsoft.footballscores.MainActivity;
import barqsoft.footballscores.R;
import barqsoft.footballscores.Utilities;

/**
 * Created by Dillon Connolly on 10/30/2015.
 * Basic IntentService class for the basic widget. It gets a cursor containing data from the API call
 * and fills in the widget data. A description is added to the widget based on the device version.
 */
public class BasicWidgetIntentService extends IntentService {

    public static final String LOG_TAG = BasicWidgetIntentService.class.getSimpleName();
    private static final String[] SCORES_COLUMNS = {
            DatabaseContract.SCORES_TABLE + "." + DatabaseContract.scores_table._ID,
            DatabaseContract.scores_table.LEAGUE_COL,
            DatabaseContract.scores_table.DATE_COL,
            DatabaseContract.scores_table.TIME_COL,
            DatabaseContract.scores_table.HOME_COL,
            DatabaseContract.scores_table.AWAY_COL,
            DatabaseContract.scores_table.HOME_GOALS_COL,
            DatabaseContract.scores_table.AWAY_GOALS_COL
    };
    // If the projection is changed these indices must change to match.
    private static final int INDEX_TABLE_ID = 0;
    private static final int INDEX_LEAGUE_COL = 1;
    private static final int INDEX_DATE_COL = 2;
    private static final int INDEX_TIME_COL = 3;
    private static final int INDEX_HOME_COL = 4;
    private static final int INDEX_AWAY_COL = 5;
    private static final int INDEX_HOME_GOALS_COL = 6;
    private static final int INDEX_AWAY_GOALS_COL = 7;

    public BasicWidgetIntentService() {
        super("BasicWidgetIntentService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(this);
        int[] appWidgetIds = appWidgetManager.getAppWidgetIds(new ComponentName(this, BasicWidgetProvider.class));
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date todayDate = new Date();

        Uri baseContentUri = DatabaseContract.BASE_CONTENT_URI;
        String sortOrder = DatabaseContract.scores_table.TIME_COL + " ASC";
        String selection = DatabaseContract.scores_table.DATE_COL + " = ?";
        String selArg = dateFormat.format(todayDate);
        Log.v(LOG_TAG, "selection: "+ selection);

        // TODO: Figure out why this query is ignoring my selection param

        Cursor data = getContentResolver().query(
                baseContentUri,
                SCORES_COLUMNS,
                selection,
                new String[]{selArg},
                sortOrder
        );

        if (data == null) {
            return;
        }
        if (!data.moveToFirst()) {
            data.close();
            Log.v(LOG_TAG,"data was empty");
            return;
        }
        // TODO: remove this work around when the selection bug is fixed.
        while(!data.getString(INDEX_DATE_COL).equals(selArg)){
            data.moveToNext();
            if(data.isAfterLast()){
                handleNoData(appWidgetManager,appWidgetIds);
                data.close();
                return;
            }
        }

        // TODO: add more data to the widget based on what looks good
            Context context = getApplicationContext();
            String homeTeam = data.getString(INDEX_HOME_COL);
            String awayTeam = data.getString(INDEX_AWAY_COL);
            int homeScore = data.getInt(INDEX_HOME_GOALS_COL);
            int awayScore = data.getInt(INDEX_AWAY_GOALS_COL);
            String description = homeTeam + " playing " + awayTeam;
            String score = Utilities.formatScoreForWidget(homeScore, awayScore);
            String gameTime = Integer.toString(data.getInt(INDEX_TIME_COL));
            data.close();


        // Loop for all widgets
        for (int appWidgetId : appWidgetIds) {
            int defaultWidth = getResources().getDimensionPixelSize(R.dimen.defaultWidgetWidth);
            int widgetWidth = getWidgetWidth(appWidgetManager, appWidgetId);
            int largeWidth = getResources().getDimensionPixelSize(R.dimen.defaultWidgetLargeWidth);
            int layoutId;
            if (widgetWidth >= largeWidth) {
                // layoutId = R.layout.widget_basic_large;
                layoutId = R.layout.widget_basic;
            } else if (widgetWidth >= defaultWidth) {
                layoutId = R.layout.widget_basic;
            } else {
                layoutId = R.layout.widget_basic;
                // layoutId = R.layout.widget_basic_small;
            }
            // RemoteView for updating the current widget
            RemoteViews views = new RemoteViews(context.getPackageName(), layoutId);
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH_MR1){
                setRemoteContentDescription(views, description);
            }

            // Now add data to the RemoteView
            views.setViewVisibility(R.id.widget_no_data, View.GONE);
            views.setViewVisibility(R.id.widget, View.VISIBLE);
            views.setTextViewText(R.id.widget_home_team, homeTeam);
            views.setTextViewText(R.id.widget_away_team, awayTeam);
            views.setTextViewText(R.id.widget_score, score);
            views.setTextViewText(R.id.widget_game_time, gameTime);

            // Intent to launch MainActivity when the widget is clicked
            Intent launchIntent = new Intent(context, MainActivity.class);
            PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, launchIntent, 0);
            views.setOnClickPendingIntent(R.id.widget, pendingIntent);

            appWidgetManager.updateAppWidget(appWidgetId, views);
            //Log.v(LOG_TAG, "date = " + date);
        }
    }

    private void handleNoData(AppWidgetManager appWidgetManager,int[] appWidgetIds){
        for(int appWidgetId: appWidgetIds){
            int defaultWidth = getResources().getDimensionPixelSize(R.dimen.defaultWidgetWidth);
            int widgetWidth = getWidgetWidth(appWidgetManager, appWidgetId);
            int largeWidth = getResources().getDimensionPixelSize(R.dimen.defaultWidgetLargeWidth);
            int layoutId;
            String description = getString(R.string.widget_no_data);
            Context context = getApplicationContext();

            if (widgetWidth >= largeWidth) {
                // layoutId = R.layout.widget_basic_large;
                layoutId = R.layout.widget_basic;
            } else if (widgetWidth >= defaultWidth) {
                layoutId = R.layout.widget_basic;
            } else {
                layoutId = R.layout.widget_basic;
                // layoutId = R.layout.widget_basic_small;
            }
            // RemoteView for updating the current widget
            RemoteViews views = new RemoteViews(context.getPackageName(), layoutId);
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH_MR1){
                setRemoteContentDescription(views, description);
            }

            views.setViewVisibility(R.id.widget_no_data, View.VISIBLE);
            views.setViewVisibility(R.id.widget, View.GONE);
            // Intent to launch MainActivity when the widget is clicked
            Intent launchIntent = new Intent(context, MainActivity.class);
            PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, launchIntent, 0);
            views.setOnClickPendingIntent(R.id.widget_no_data, pendingIntent);

            appWidgetManager.updateAppWidget(appWidgetId, views);
        }
    }

    private int getWidgetWidth(AppWidgetManager appWidgetManager, int appWidgetId) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN) {
            return getResources().getDimensionPixelSize(R.dimen.defaultWidgetWidth);
        }
        return getWidgetWidthFromOptions(appWidgetManager, appWidgetId);
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    private int getWidgetWidthFromOptions(AppWidgetManager appWidgetManager, int appWidgetId) {
        Bundle options = appWidgetManager.getAppWidgetOptions(appWidgetId);
        if (options.containsKey(AppWidgetManager.OPTION_APPWIDGET_MIN_WIDTH)) {
            int minWidth = options.getInt(AppWidgetManager.OPTION_APPWIDGET_MIN_WIDTH);
            DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
            return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, minWidth, displayMetrics);
        }
        return getResources().getDimensionPixelSize(R.dimen.defaultWidgetWidth);
    }

    @TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH_MR1)
    private void setRemoteContentDescription(RemoteViews views, String description) {
        views.setContentDescription(R.id.widget, description);
    }
}