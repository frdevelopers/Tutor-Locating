package com.farhanramzan.tutorlocating;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView registerBtn = findViewById(R.id.registerBtn);
        ImageView aboutBtn = findViewById(R.id.aboutBtn);

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent lIntent = new Intent(MainActivity.this,registration.class);
                MainActivity.this.startActivity(lIntent);
            }
        });

        aboutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent lIntent = new Intent(MainActivity.this,about_us.class);
                MainActivity.this.startActivity(lIntent);
            }
        });

    }
}
