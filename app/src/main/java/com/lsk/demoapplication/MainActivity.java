package com.lsk.demoapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String Url="https://api.douban.com/v2/";

        Retrofit retrofit=new Retrofit.Builder().baseUrl(Url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        //调用retrofit 的静态方法create(指定接口类型的类模板)得到实现接口的子类对象
        Utils utils = retrofit.create(Utils.class);

        //指定JSON解析完毕后数据封装到哪个实体类对应的Call对象中
        final Call<Tets> call = utils.getBookSearchResponse("小王子", null, 0, 3);


        //同步请求
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    //将请求提交到服务器端并得到服务器的返回结果对应的响应对象
                    Response<Tets> response= call.execute();

                    //通过响应对象得到封装后塞完数据的实体对象
                    Tets bookSearchResponse = response.body();

                    System.out.println("bookSearchResponse=" + bookSearchResponse);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
