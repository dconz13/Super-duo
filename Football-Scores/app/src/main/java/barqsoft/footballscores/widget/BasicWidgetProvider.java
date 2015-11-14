package barqsoft.footballscores.widget;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;

import barqsoft.footballscores.service.myFetchService;

/**
 * Created by Dillon Connolly on 10/30/2015.
 */
public class BasicWidgetProvider extends AppWidgetProvider {
    @Override
    public void onReceive(@NonNull Context context, @NonNull Intent intent) {
        super.onReceive(context, intent);
        //if(myFetchService.ACTION_DATA_UPDATED.equals(intent.getAction())){
            context.startService(new Intent(context, BasicWidgetIntentService.class));
        //}
        Log.v("Widget provider","onReceive called");
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        context.startService(new Intent(context, BasicWidgetIntentService.class));
        Log.v("Widget provider", "onUpdate called");
    }

    @Override
    public void onAppWidgetOptionsChanged(Context context, AppWidgetManager appWidgetManager, int appWidgetId, Bundle newOptions) {
        context.startService(new Intent(context, BasicWidgetIntentService.class));
        Log.v("Widget provider", "onAppWidgetOptionsChanged called");
    }
}
