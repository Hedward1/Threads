package com.unicuritiba.threads;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {


    private Button botaoIniciar;
    private int numero;
    private Handler handler = new Handler();
    private Boolean pararExecucao = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        botaoIniciar = findViewById(R.id.buttonIniciar);

    }

    public void pararThread(View view) {
        pararExecucao = true;
    }

    public void iniciarThread(View view) {

        /*
        MyThread thread = new MyThread();
        thread.start();*/

        pararExecucao = false;
        MyRunnable runnable = new MyRunnable();
        new Thread(runnable).start();

    }

    class MyRunnable implements Runnable {

        @Override
        public void run() {

            for (int i = 0; i <= 15; i++) {

                if (pararExecucao)
                    return;

                numero = i;
                Log.d("Threads", "Contador" + i);


                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        botaoIniciar.setText("Contador: " + numero);
                    }
                });

                /*
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        botaoIniciar.setText("Contador: " + numero);
                    }
                });
                */

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }
    }

    class MyThread extends Thread {
        @Override
        public void run() {


            for (int i = 0; i <= 15; i++) {
                Log.d("Threads", "Contador" + i);

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }


        }
    }

}