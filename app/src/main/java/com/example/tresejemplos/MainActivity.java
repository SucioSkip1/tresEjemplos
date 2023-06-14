package com.example.tresejemplos;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    Handler handler = new Handler();
    TextView Tv1, Tv2, Tv3;
    int time = 0;
    int rate = 100;
    Timer timer, timer2, timer3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Tv1 = findViewById(R.id.tv1);
        Tv2 = findViewById(R.id.tv2);
        Tv3 = findViewById(R.id.tv3);
        int factor1 = 1;
        int factor2 = 2;
        int factor3 = 3;
        timer = new Timer("Temporizador");
        Tarea tarea = new Tarea(Tv1, factor1);
        timer.scheduleAtFixedRate(tarea, 0, rate);

        timer2 = new Timer("Temporizador");
        Tarea2 tarea2 = new Tarea2(Tv2, factor2);
        timer2.scheduleAtFixedRate(tarea2, 0, rate);

        timer3 = new Timer("Temporizador");
        Tarea3 tarea3 = new Tarea3(Tv3, factor3);
        timer.scheduleAtFixedRate(tarea3, 0, rate);
    }

    @Override
    protected void onPause() {
        super.onPause();
        timer.cancel();
        timer2.cancel();
        timer3.cancel();
    }

    class Tarea extends TimerTask {
        int factor;
        TextView textTarea;

        public Tarea(TextView textView, int fact) {
            textTarea = textView;
            factor = fact;
        }

        @Override
        public void run() {
            Runnable cambiaTexto = new CambiaTexto(textTarea, factor);
            runOnUiThread(cambiaTexto);
        }

    }

    class Tarea2 extends TimerTask {
        int factor;
        TextView textTarea;

        public Tarea2(TextView textView, int fact) {
            textTarea = textView;
            factor = fact;
        }

        @Override
        public void run() {
            Runnable cambiaTexto = new CambiaTexto(textTarea, factor);
            textTarea.post(cambiaTexto);
        }
    }


    class Tarea3 extends TimerTask {
        int factor;
        TextView textTarea;

        public Tarea3(TextView textView, int fact) {
            textTarea = textView;
            factor = fact;
        }

        @Override
        public void run() {
            Runnable cambiaTexto = new CambiaTexto(textTarea, factor);
            handler.post(cambiaTexto);
        }
    }

    class CambiaTexto implements Runnable{
        int red,green,blue,factor;
        TextView textCambia;
        public CambiaTexto(TextView textView,int fact){
            textCambia=textView;
            factor=fact;

        }

        @Override
        public void run() {
            time=time+rate;
            red=(time/factor%255);
            blue=(int) ((0.60* time/factor)%255);
            String texto = "Temporizador \n rate= " + rate+ "\n t = "+time;
            textCambia.setText(texto);
            textCambia.setTypeface(null, Typeface.BOLD);
            textCambia.setTextSize(30 );
            textCambia.setTextColor(Color.rgb(red, green,blue));
        }
    }





}