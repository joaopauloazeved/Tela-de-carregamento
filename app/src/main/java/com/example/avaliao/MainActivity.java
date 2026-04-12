package com.example.avaliao;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        Button butaoentrar = findViewById(R.id.Button_iniciar);
        Button butaosair = findViewById(R.id.button_sair);

        butaoentrar.setOnClickListener(this);
        butaosair.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.Button_iniciar) {
            Intent intent = new Intent(this, TaskActivity.class);
            startActivity(intent);
        } else if (v.getId() == R.id.button_sair) {
            finish();
        }
    }
}