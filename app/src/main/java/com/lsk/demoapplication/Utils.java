package com.lsk.demoapplication;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by ASUS on 2016/10/29.
 */
public interface Utils {

    Tets test=new Tets();

    @GET("book/search")
    Call<Tets> getBookSearchResponse(@Query("q") String q,
                                     @Query("tag") String tag,
                                     @Query("start") int start,
                                     @Query("count") int  count);

}
