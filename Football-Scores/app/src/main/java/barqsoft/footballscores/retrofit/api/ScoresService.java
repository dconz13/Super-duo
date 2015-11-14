package barqsoft.footballscores.retrofit.api;

/**
 * Created by Dillon Connolly on 11/4/2015.
 *
 * This is the get function for retrofit. More get or set methods can be added as needed. Make sure
 * to add the respective model files to the model folder when adding different API calls.
 */

import retrofit.Callback;
import barqsoft.footballscores.retrofit.model.*;
import retrofit.http.GET;
import retrofit.http.Query;

public interface ScoresService {

    @GET("/alpha/fixtures")
    public void getDataForDate(@Query("timeFrame") String timeFrame, Callback<Data> footballScoreDataCallback);
}
