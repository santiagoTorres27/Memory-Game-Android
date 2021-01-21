package com.dam.mymemorygame;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Chronometer;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {

    Chronometer cronometro;
    TextView tvIntentos;
    TextView tvAciertos;
    ImageView ivAcierto;
    ImageView iv11, iv12, iv13, iv14, iv21, iv22, iv23, iv24, iv31, iv32, iv33, iv34, iv41, iv42, iv43, iv44;

    ArrayList<ImageView> cartas;
    ArrayList<Integer> imagenes;
    int contadorJugada = 0;
    //Almacena en un array el ID de la imagen para comprobar si se ha seleccionado la misma
    int cartasPar[];
    // Almacena en un array las posiciones de las 2 cartas seleccionadas
    int posiciones[];
    int aciertos = 0;
    int intentos = 0;
    boolean hayAcierto = false;
    boolean running;

    Animation myanim;
    Animation myanim2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cronometro = findViewById(R.id.cronometro);
        tvIntentos = findViewById(R.id.tvIntentos);
        tvAciertos = findViewById(R.id.tvAciertos);
        ivAcierto = findViewById(R.id.ivAcierto);

        cartas = new ArrayList<ImageView>();
        imagenes = new ArrayList<Integer>();

        cartasPar = new int[2];
        posiciones = new int[2];

        inicializarCartas();
        cargarArrayCartas();
        cargarImagenes();
        iniciarCronometro();

        Collections.shuffle(imagenes);

        myanim = AnimationUtils.loadAnimation(this, R.anim.acierto);
        myanim2 = AnimationUtils.loadAnimation(this, R.anim.anim_puntuacion);

        for (int i = 0; i < imagenes.size(); i++) {
            int pos = i;

            cartas.get(i).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    cartas.get(pos).setBackgroundResource(imagenes.get(pos));
                    cartas.get(pos).setTag(1);
                    cartas.get(pos).setEnabled(false);

                    cartasPar[contadorJugada] = imagenes.get(pos);  // Almacena el ID de la imagen
                    posiciones[contadorJugada] = pos;               // Almacena la posición
                    contadorJugada++;

                    if (contadorJugada == 2) {
                        intentos++;

                        tvIntentos.setText(String.valueOf(intentos));
                        tvIntentos.startAnimation(myanim2);

                        if (cartasPar[0] == cartasPar[1]) {
                            aciertos++;
                            hayAcierto = true;

                            if (aciertos == 8) {
                                juegoTerminado();
                            }

                            tvAciertos.startAnimation(myanim2);
                            tvAciertos.setText(String.valueOf(aciertos));
                            cartas.get(posiciones[0]).setClickable(false);
                            cartas.get(posiciones[1]).setClickable(false);

                        } else {
                            cartas.get(posiciones[0]).setTag(0);
                            cartas.get(posiciones[1]).setTag(0);
                            cartas.get(posiciones[0]).setClickable(true);
                            cartas.get(posiciones[1]).setClickable(true);

                            habilitarCartas(false);
                        }

                        mostrarEmoticon();

                        Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                limpiarCartas();
                                habilitarCartas(true);
                            }
                        }, 1000);

                        contadorJugada = 0;
                        hayAcierto = false;
                    }
                }
            });
        }
    }

    private void iniciarCronometro() {
        if (!running) {
            cronometro.setBase(SystemClock.elapsedRealtime());
            cronometro.start();
            running = true;
        }
    }

    private void mostrarEmoticon() {
        ivAcierto.setVisibility(View.VISIBLE);
        if (hayAcierto) {
            ivAcierto.setBackgroundResource(R.drawable.acierto);

        } else {
            ivAcierto.setBackgroundResource(R.drawable.fallo);
        }
        ivAcierto.startAnimation(myanim);
        ivAcierto.setVisibility(View.INVISIBLE);
    }


    private void juegoTerminado() {
        String tiempo = cronometro.getText().toString();
        Intent i = new Intent(this, ResultadoActivity.class);
        i.putExtra("INTENTOS", intentos);
        i.putExtra("TIEMPO", tiempo);
        startActivity(i);
    }

    private void limpiarCartas() {
        int flag;
        for (int i = 0; i < 16; i++) {
            flag = (Integer) cartas.get(i).getTag();
            if (flag == 0) {
                cartas.get(i).setBackgroundResource(R.drawable.card);
            } else if (flag == 1) {
                cartas.get(i).setEnabled(false);
            }
        }
    }

    private void habilitarCartas(boolean b) {
        for (ImageView carta : cartas) {
            carta.setEnabled(b);
        }
    }


    private void cargarImagenes() {
        imagenes.add(R.drawable.img1);
        imagenes.add(R.drawable.img1);
        imagenes.add(R.drawable.img2);
        imagenes.add(R.drawable.img2);
        imagenes.add(R.drawable.img3);
        imagenes.add(R.drawable.img3);
        imagenes.add(R.drawable.img4);
        imagenes.add(R.drawable.img4);
        imagenes.add(R.drawable.img5);
        imagenes.add(R.drawable.img5);
        imagenes.add(R.drawable.img6);
        imagenes.add(R.drawable.img6);
        imagenes.add(R.drawable.img7);
        imagenes.add(R.drawable.img7);
        imagenes.add(R.drawable.img8);
        imagenes.add(R.drawable.img8);
    }

    private void cargarArrayCartas() {
        cartas.add(iv11);
        cartas.add(iv12);
        cartas.add(iv13);
        cartas.add(iv14);
        cartas.add(iv21);
        cartas.add(iv22);
        cartas.add(iv23);
        cartas.add(iv24);
        cartas.add(iv31);
        cartas.add(iv32);
        cartas.add(iv33);
        cartas.add(iv34);
        cartas.add(iv41);
        cartas.add(iv42);
        cartas.add(iv43);
        cartas.add(iv44);

        for (ImageView carta : cartas) {
            carta.setBackgroundResource(R.drawable.card);
            carta.setTag(0);
        }
    }

    private void inicializarCartas() {
        iv11 = findViewById(R.id.iv11);
        iv12 = findViewById(R.id.iv12);
        iv13 = findViewById(R.id.iv13);
        iv14 = findViewById(R.id.iv14);
        iv21 = findViewById(R.id.iv21);
        iv22 = findViewById(R.id.iv22);
        iv23 = findViewById(R.id.iv23);
        iv24 = findViewById(R.id.iv24);
        iv31 = findViewById(R.id.iv31);
        iv32 = findViewById(R.id.iv32);
        iv33 = findViewById(R.id.iv33);
        iv34 = findViewById(R.id.iv34);
        iv41 = findViewById(R.id.iv41);
        iv42 = findViewById(R.id.iv42);
        iv43 = findViewById(R.id.iv43);
        iv44 = findViewById(R.id.iv44);
    }
}