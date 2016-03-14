package id.tech.util;

import java.util.List;


/**
 * Created by RebelCreative-A1 on 14/03/2016.
 */
import id.tech.POJO.OlxOutlet;
import retrofit.Callback;
import retrofit.http.GET;


public interface Test_RestAdapter {

    @GET("/get.php?kind=outlet")
    void getOutlet(Callback<OlxOutlet> cb);
}
