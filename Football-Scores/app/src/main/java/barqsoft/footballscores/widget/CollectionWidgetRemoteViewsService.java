package barqsoft.footballscores.widget;

import android.annotation.TargetApi;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Binder;
import android.os.Build;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import barqsoft.footballscores.DatabaseContract;

/**
 * Created by Dillon Connolly on 10/30/2015.
 * This is a work in progress. This code is not ready yet.
 */
@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class CollectionWidgetRemoteViewsService extends RemoteViewsService {
    public static final String LOG_TAG = CollectionWidgetRemoteViewsService.class.getSimpleName();
    private static final String[] SCORES_COLUMNS = {
            DatabaseContract.SCORES_TABLE  + "." + DatabaseContract.scores_table._ID,
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

    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        return new RemoteViewsFactory() {
            private Cursor data = null;

            @Override
            public void onCreate() {

            }

            @Override
            public void onDataSetChanged() {
                if(data != null){
                    data.close();
                }
                // Personal Notes:
                // This method is called by the launcher (because it is a widget). Since the content provider
                // is not exported, the caller identity needs to be cleared and restored. This will make the
                // calls use the football scores' process id and permissions.
                final long identityToken = Binder.clearCallingIdentity();
                Uri uriWithDate = DatabaseContract.scores_table.buildScoreWithDate();
                data = getContentResolver().query(
                        uriWithDate,
                        SCORES_COLUMNS,
                        null,
                        null,
                        DatabaseContract.scores_table.DATE_COL + "ASC"
                        );
                Binder.restoreCallingIdentity(identityToken);
            }

            @Override
            public void onDestroy() {
                if(data!=null){
                    data.close();
                    data = null;
                }
            }

            @Override
            public int getCount() {
                return data == null ? 0 : data.getCount();
            }

            @Override
            public RemoteViews getViewAt(int position) {
                // This is where I need to fill in all the data that I obtained from the table.
                return null;
            }

            // Need to implement setRemoteContentDescription

            @Override
            public RemoteViews getLoadingView() {
                // return new RemoteViews(getPackageName(), R.layout.widget_list_item);
                return null;
            }

            @Override
            public int getViewTypeCount() {
                return 1;
            }

            @Override
            public long getItemId(int position) {
                if(data.moveToPosition(position)){
                    return data.getLong(INDEX_TABLE_ID);
                }
                return position;
            }

            @Override
            public boolean hasStableIds() {
                return true;
            }
        };
    }
}
