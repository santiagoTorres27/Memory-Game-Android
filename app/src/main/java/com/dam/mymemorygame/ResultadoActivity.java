package com.dam.mymemorygame;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ResultadoActivity extends AppCompatActivity {

    TextView tvIntentos;
    TextView tvTiempo;
    ImageView circulo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultado);

        tvIntentos = findViewById(R.id.tvIntentos);
        tvTiempo = findViewById(R.id.tvTiempo);
        circulo = findViewById(R.id.circulo);

        Animation rotation = AnimationUtils.loadAnimation(this, R.anim.rotation);
        circulo.startAnimation(rotation);

        int intentos = getIntent().getIntExtra("INTENTOS", 0);
        String tiempo = getIntent().getStringExtra("TIEMPO");

        tvIntentos.setText(String.valueOf(intentos));
        tvTiempo.setText(tiempo);

    }

    public void reiniciar(View view) {
        Intent next = new Intent(this, MainActivity.class);
        next.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        next.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(next);
    }
}