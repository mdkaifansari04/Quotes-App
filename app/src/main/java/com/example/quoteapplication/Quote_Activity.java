package com.example.quoteapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class Quote_Activity extends AppCompatActivity {

    Button shareButton;
    TextView quoteText;
    String data = "kaif";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getQuote();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quote);

        shareButton = findViewById(R.id.shareButton);
        quoteText = findViewById(R.id.tvQuote);

        shareButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_TEXT, data);
                startActivity(intent);
            }
        });
    }

    public void getQuote(){
        try {
            System.out.println("Test 3");
            URL url = new URL("https://api.api-ninjas.com/v1/quotes?category=happiness");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestProperty("accept", "application/json");
            connection.setRequestProperty("X-Api-Key", "NOOo0F+WUVL5TlWw==WzrIzEjnqgaWb2lV");
            InputStream responseStream = connection.getInputStream();

            ObjectMapper mapper = new ObjectMapper();
            JsonNode root = mapper.readTree(responseStream);

            // Extract properties from the JSON response
            String quote = root.path(0).path("quote").asText();
            String author = root.path(0).path("author").asText();
            String category = root.path(0).path("category").asText();

            quoteText.setText(quote);


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}