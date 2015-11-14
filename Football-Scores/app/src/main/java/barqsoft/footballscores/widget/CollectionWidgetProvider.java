package barqsoft.footballscores.widget;

import android.annotation.TargetApi;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

/**
 * Created by Dillon Connolly on 10/30/2015.
 * This is the provider for a scrollable Today's games widget.
 * This is a work in progress. This code is not ready yet.
 */
@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class CollectionWidgetProvider extends AppWidgetProvider {
    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        super.onUpdate(context, appWidgetManager, appWidgetIds);
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);
    }
}
