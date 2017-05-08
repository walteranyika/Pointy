package com.walter.pointend;

import android.content.Intent;
import android.content.SharedPreferences;
import android.location.Address;
import android.location.Geocoder;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.TextHttpResponseHandler;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;
import cz.msebera.android.httpclient.Header;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.inputNames)
    EditText inputNames;
    @BindView(R.id.inputPhone)
    EditText inputPhone;
    @BindView(R.id.progressBarSend)
    ProgressBar progress;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

    }

    public void save(View view) {
        String names = inputNames.getText().toString().trim();
        String phone = inputPhone.getText().toString().trim();

        if (names.isEmpty() || phone.isEmpty() || names.length() < 8 || phone.length() < 8) {
            Toast.makeText(this, "Fill in all the fields with valid values", Toast.LENGTH_SHORT).show();
        } else {
            if (isNetworkAvailable()) {
                register_content(names, phone);
            } else {
                Toast.makeText(this, "Your device is not connected to the internet. Kindly check your settings", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void register_content(final String names, final String phone) {
        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams params = new RequestParams();
        params.put("names", names);
        params.put("phone", phone);
        if (!names.isEmpty() && !phone.isEmpty()) {
            progress.setVisibility(View.VISIBLE);
            client.post(Constants.URL_REGISTER, params, new TextHttpResponseHandler() {
                @Override
                public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                    Log.e("SERVER", responseString);
                    Toast.makeText(MainActivity.this, "Failed To Connect To The Server. Try Again", Toast.LENGTH_SHORT).show();
                    progress.setVisibility(View.INVISIBLE);
                }

                @Override
                public void onSuccess(int statusCode, Header[] headers, String responseString) {
                    Log.d("SERVER", responseString);
                    progress.setVisibility(View.INVISIBLE);
                    try {
                        JSONObject obj = new JSONObject(responseString);
                        String resp = obj.getString("response");
                        if (resp.toLowerCase().contains("success")) {
                            Toast.makeText(MainActivity.this, resp, Toast.LENGTH_SHORT).show();
                            //TODO Navigate
                            SharedPreferences.Editor editor = getSharedPreferences("settings", MODE_PRIVATE).edit();
                            editor.putString("names", names);
                            editor.putString("phone", phone);
                            editor.putBoolean("status", true);
                            editor.commit();
                            startActivity(new Intent(MainActivity.this, DestinationActivity.class));
                            finish();
                        } else if (resp.toLowerCase().contains("already")) {
                            Toast.makeText(MainActivity.this, resp, Toast.LENGTH_SHORT).show();
                        }
                    } catch (JSONException e) {
                        Toast.makeText(MainActivity.this, "An error occurred while processing the registration", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        } else {
            Toast.makeText(this, "Fill in all the values", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

}
