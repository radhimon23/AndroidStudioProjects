package com.example.gauravkapadiya.home.Utils;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Soham on 5/4/2016.
 */
public class geturl {
    OkHttpClient client = new OkHttpClient();

    public JSONObject makeHttpRequestpost(String url, ArrayList<param> a1) {
        InputStream is = null;
        JSONObject jObj = null;
        String json = "";
        JSONArray jArr = null;

        try {
            json = new geturl().run(url,a1);
            Log.d("json", json);

        } catch (Exception e) {
            Log.e("Buffer Error", "Error converting result " + e.toString());
        }

        try {
            jObj = new JSONObject(json);
        } catch (JSONException e) {
            Log.e("JSON Parser", "Error parsing data " + e.toString());
        }


        return jObj;

    }

   public String run(String url) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .build();

        Response response = client.newCall(request).execute();
        return response.body().string();
    }



    public String run(String url,ArrayList<param> a1) throws IOException {

        FormBody.Builder  formBody = new FormBody.Builder();

        for (int i =0;i<a1.size();i++)
        {
            formBody.add(a1.get(i).name,a1.get(i).value);
        }
        FormBody f1 = formBody.build();

        Request request = new Request.Builder()
                .url(url)
                .post(f1)
                .build();

        Response response = client.newCall(request).execute();
        return response.body().string();
    }
}