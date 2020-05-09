package com.example.api_rest_call;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface AutoService {

    //Ver todos
    String API_ROUTE= "app/api/read";
    @GET(API_ROUTE)
    Call<List<Auto>> getAutos();
    //Ver seleccionado
    String API_ROUTE_GET_ITEM= "app/api/read/{id}";
    @GET(API_ROUTE_GET_ITEM)
    Call<Auto> getAuto(@Path("id") String id);
    //Editar
    String API_ROUTE_SAVE_ITEM = "app/api/update/{id}";
    @PUT(API_ROUTE_SAVE_ITEM)
    Call<Auto> saveAuto(@Path("id") String id);
    //Agregar
    String API_ROUTE_ADD_ITEM = "app/api/create";
    Call<Void> addAuto(@Body Auto auto);
    //Eliminar
}
