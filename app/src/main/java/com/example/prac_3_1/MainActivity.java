package com.example.prac_3_1;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.google.android.material.textfield.TextInputEditText;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private TextInputEditText urlEditText;
    private TextInputEditText locationEditText;
    private TextInputEditText messageEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        urlEditText = findViewById(R.id.url_edit_text);
        locationEditText = findViewById(R.id.location_edit_text);
        messageEditText = findViewById(R.id.message_edit_text);

        Button openUrlButton = findViewById(R.id.open_url_button);
        Button openLocationButton = findViewById(R.id.open_location_button);
        Button shareMessageButton = findViewById(R.id.share_message_button);

        openUrlButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = urlEditText.getText().toString();
                if (!url.isEmpty()) {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                    startActivity(intent);
                }
            }
        });

        openLocationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String location = locationEditText.getText().toString();
                if (!location.isEmpty()) {
                    Uri gmmIntentUri = Uri.parse("geo:0,0?q=" + Uri.encode(location));
                    Intent intent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                    intent.setPackage("com.google.android.apps.maps");

                    if (intent.resolveActivity(getPackageManager()) != null) {
                        startActivity(intent);
                    } else {
                        Intent fallbackIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                        startActivity(fallbackIntent);
                    }
                }
            }
        });

        shareMessageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String message = messageEditText.getText().toString();
                if (!message.isEmpty()) {
                    Intent shareIntent = new Intent();
                    shareIntent.setAction(Intent.ACTION_SEND);
                    shareIntent.putExtra(Intent.EXTRA_TEXT, message);
                    shareIntent.setType("text/plain");
                    startActivity(Intent.createChooser(shareIntent, "Share message via"));
                }
            }
        });
    }
}
