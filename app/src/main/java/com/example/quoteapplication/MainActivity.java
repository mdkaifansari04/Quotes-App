package com.example.quoteapplication;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    Button startButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startButton = findViewById(R.id.startButton);

        OkHttpClient client = new OkHttpClient();
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (Build.VERSION.SDK_INT > 9) {
                Log.d("test", "test 1");
                    StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
                    StrictMode.setThreadPolicy(policy);
                }

                if (Build.VERSION.SDK_INT > 9) {
                    Log.d("test","test2");
                    StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
                    StrictMode.setThreadPolicy(policy);
                }
                Log.d("test", "test3");
                Request request = new Request.Builder()
                        .url("https://api.api-ninjas.com/v1/quotes?category=success")
                        .header("X-Api-Key", "H4gFrmOcPmKunvNTj6EP4QIaDqnftQTAcI3TSl3K")
                        .build();

                try (Response response = client.newCall(request).execute()) {

                    String res = response.body().string();

                    String quote = res.split("quote")[1].split(":")[1].split("\\.")[0].replace("\"", "");
                    String author = res.split("author")[1].split(":")[1].split(",")[0].replace("\"", "");

                    Log.d("test", "authoer" +author);
                    Intent intent = new Intent(MainActivity.this, Quote_Activity.class);
                    intent.putExtra("quote", quote);
                    intent.putExtra("author", author);
                    startActivity(intent);


                } catch (IOException e) {
                    Log.e("test", "Error in network request", e);
                }
            }
        });
    }

}