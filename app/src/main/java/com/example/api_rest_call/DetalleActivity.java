package com.example.api_rest_call;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import org.w3c.dom.Text;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DetalleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_activity);
        setTitle("Vehículo");
        Bundle b = getIntent().getExtras();

        String id = b.getString("id");


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://us-central1-be-tp3-a.cloudfunctions.net/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // Defnimos la interfaz para que utilice la base retrofit de mi aplicacion ()
        final AutoService autoService = retrofit.create(AutoService.class);

        Call<Auto> http_call = autoService.getAuto(id);

        http_call.enqueue(new Callback<Auto>() {
            @Override
            public void onResponse(Call<Auto> call, Response<Auto> response) {
                 Auto auto = response.body();

                TextView idAuto = (TextView) findViewById(R.id.id_auto);
                idAuto.setText(auto.getId());
                TextView marca = (TextView) findViewById(R.id.marca_auto);
                marca.setText(auto.getMarca());
                TextView modelo = (TextView) findViewById(R.id.modelo_auto);
                modelo.setText(auto.getModelo());

                Toast.makeText(
                        DetalleActivity.this,
                        auto.getMarca(),
                        Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<Auto> call, Throwable t) {
                Toast.makeText(DetalleActivity.this, "Hubo un error con la llamada a la API", Toast.LENGTH_LONG);


            }
        });


        final Button buttonEditar = findViewById(R.id.buttonEditar);
        buttonEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*
                TextView idAuto;
                TextInputEditText marca;
                TextInputEditText modelo;
                idAuto= (TextView) findViewById(R.id.id_auto);
                marca= (TextInputEditText) findViewById(R.id.marca_auto);
                modelo = (TextInputEditText) findViewById(R.id.modelo_auto);
                Log.i("DATOS", idAuto.getText().toString()+"-"+marca.getText().toString()+"-"+modelo.getText().toString());
                 */

                Call<Void> http_call = autoService.saveAuto(
                        idAuto,
                        marca,
                        modelo
                );

                http_call.enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        Log.i("Ok","Actualizado correctamente");
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        Log.i("ERROR", t.getMessage());
                    }
                });
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