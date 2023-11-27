package com.example.android_ud3_tarea3_1;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;



public class MainActivity extends AppCompatActivity {

    private SharedPreferences sharedPreferences;

    TextView tv_dificultad;
    TextView tv_bd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        tv_dificultad = findViewById(R.id.tv_dificultad);
        tv_bd = findViewById(R.id.tv_base);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.it_salir) {
            finishAffinity();
            Toast.makeText(this, "Saliendo de la app", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.it_preferencias) {
            Intent intent = new Intent(this, PreferenciasActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
        tv_dificultad.setText(sharedPreferences.getString("dificultad", "Fácil"));
        String baseS = "";
        if (sharedPreferences.getBoolean("bbdd", false)) {
            baseS = "Externa SQL, MariaDB: ";
            baseS += " " + sharedPreferences.getString("nombre_bd", "root") + " " + sharedPreferences.getString("ip", "0.0.0.0");
        } else {
            baseS = "Interna SQLite";
        }
        tv_bd.setText(baseS);

    }
}