package com.walter.pointend;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.TextHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class AdminActivity extends AppCompatActivity {
   ListView list;
    CustomAdapter adapter;
    ArrayList<Item> data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
    }

    private void fetch_data() {
        AsyncHttpClient client = new AsyncHttpClient();
        client.post(Constants.URL_LOCATION_FETCH,new TextHttpResponseHandler() {
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                Log.e("SERVER", responseString);
                Toast.makeText(getApplicationContext(), "Failed To Upload Location Details To The Server. Try Again", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                Log.d("SERVER", responseString);
                try {
                    JSONArray array=new JSONArray(responseString);
                    for (int x =0; x<array.length(); x++){
                       JSONObject obj = array.getJSONObject(x);
                        //names, address, longitude, latitude, time,date;
                        String names =obj.getString("names");
                        String  address = obj.getString("address");
                        String longitude = obj.getString("longitude");
                        String latitude =obj.getString("latitude");
                        String  time =obj.getString("time");
                        String date = obj.getString("date");

                        /*Item item =new Item();
                        item.names =names; item.address=address; item.longitude=longitude; item.latitude=latitude; item.time=time; item.date=date;
                        data.add(item);*/
                    }
                    adapter.notifyDataSetChanged();

                } catch (JSONException e) {
                    Toast.makeText(getApplicationContext(), "Error while processing response from server", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }


    public void add_another(View view) {
        startActivity(new Intent(this, DestinationActivity.class));
        finish();
    }
}
