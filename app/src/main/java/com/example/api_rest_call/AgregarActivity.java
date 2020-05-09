package com.example.api_rest_call;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;



import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;

public class AgregarActivity extends AppCompatActivity {


    Button buttonGuardar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar);
        setTitle("Alta de Auto");
        final String marca;
        final String modelo;

        //marca = ((TextInputEditText) findViewById(R.id.marca_auto)).getText().toString();
       // modelo = ((TextInputEditText) findViewById(R.id.modelo_auto)).getText().toString();

        buttonGuardar = (Button) findViewById(R.id.buttonAgregar);

        final Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://us-central1-be-tp3-a.cloudfunctions.net/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        final AutoService autoService = retrofit.create(AutoService.class);

        buttonGuardar.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view)  {
                Auto auto = new Auto(marca, modelo);


                Call<Void> http_call = autoService.addAuto(auto);
                http_call.enqueue(new Callback<Void>() {

                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        Toast.makeText(AgregarActivity.this, "Se ha dado de alta el auto correctamente", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent);
                    }
                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        Toast.makeText(AgregarActivity.this, "Hubo un error", Toast.LENGTH_LONG).show();
                    }
                });


            }
        });
    }


    private void listarAutos() {
        Intent miintent = new Intent(this, MainActivity.class);
        startActivity(miintent);
    }


    public void cancelarOnClick(View view) {
        finish();
    }
}