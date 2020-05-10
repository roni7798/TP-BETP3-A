package com.example.api_rest_call;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import java.io.IOException;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;

public class AgregarActivity extends AppCompatActivity {


    Button buttonGuardar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar);
        setTitle("Alta de Auto");
        final TextInputEditText marca;
        final TextInputEditText modelo;

        marca= (TextInputEditText) findViewById(R.id.marca_auto);
        modelo = (TextInputEditText) findViewById(R.id.modelo_auto);

        final Button buttonGuardar = findViewById(R.id.buttonGuardar);
        buttonGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {

                    Auto auto = new Auto(marca.getText().toString(), modelo.getText().toString());
               //Log.i("MARCA", marca.getText().toString());
                    Retrofit retrofit = new Retrofit.Builder()
                            .baseUrl("https://us-central1-be-tp3-a.cloudfunctions.net/")
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();
                    AutoService autoService = retrofit.create(AutoService.class);
                    Call<Void> http_call = autoService.addAuto(auto);
                    Log.i("AUTO", auto.getMarca()+"-"+auto.getModelo());

                    http_call.enqueue(new Callback<Void>() {
                        @Override
                        public void onResponse(Call<Void> call, Response<Void> response) {
                            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                            startActivity(intent);
                        }

                        @Override
                        public void onFailure(Call<Void> call, Throwable t) {
                            Log.i("HTTP ERROR", t.getMessage());
                        }
                    });

                }catch (Exception e){
                    e.printStackTrace();
                }
            }

        });

        final Button buttonVolver = findViewById(R.id.buttonVolver);
        buttonVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }

        });

    }

    }
