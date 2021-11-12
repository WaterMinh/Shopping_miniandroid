package com.example.bkshopping;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.widget.ListView;

import java.util.List;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        change_screen();
    }



    private void change_screen()   {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
                    Intent manhinhwelcome = new Intent(MainActivity.this, MainActivity2.class);
                    startActivity(manhinhwelcome);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        Thread task = new Thread(runnable);
        task.start();
    }
}