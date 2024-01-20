package com.example.quoteapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Quote_Activity extends AppCompatActivity {

    Button shareButton;
    TextView tvQuote, tvType, tvAuthor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quote);

        shareButton = findViewById(R.id.shareButton);
        tvQuote = findViewById(R.id.tvQuote);
        tvType = findViewById(R.id.tvType);
        tvAuthor = findViewById(R.id.tvAuthor);

        Intent intent = getIntent();
        String quote = intent.getStringExtra("quote");
        String author = intent.getStringExtra("author");

        tvQuote.setText(quote);
        tvAuthor.setText(author);

        shareButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_TEXT, tvQuote.getText());
                startActivity(intent);
            }
        });
    }

}