package com.example.quoteapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    Button startButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startButton = findViewById(R.id.startButton);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Quote_Activity.class);
                try {
                    System.out.println("Test 3");
                    URL url = new URL("https://api.api-ninjas.com/v1/quotes?category=happiness");
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestProperty("accept", "application/json");
                    connection.setRequestProperty("X-Api-Key", "NOOo0F+WUVL5TlWw==WzrIzEjnqgaWb2lV");
                    InputStream responseStream = connection.getInputStream();

                    ObjectMapper mapper = new ObjectMapper();
                    JsonNode root = mapper.readTree(responseStream);

                    String quote = root.path(0).path("quote").asText();
                    String author = root.path(0).path("author").asText();
                    String category = root.path(0).path("category").asText();
                    System.out.println("Quote : "+ quote);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                startActivity(intent);
            }
        });

    }
}