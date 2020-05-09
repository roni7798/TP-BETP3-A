package com.example.api_rest_call;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface AutoService {

    /**
     * Definicion de ruta para GET
     */
    String API_ROUTE= "app/api/read";

    /**
     * Metodo abstracto para utilizar HTTP.GET
     * @return
     */
    @GET(API_ROUTE)
    Call<List<Auto>> getAutos();

    String API_ROUTE_GET_ITEM= "app/api/read/{id}";
    @GET(API_ROUTE_GET_ITEM)
    Call<Auto> getAuto(@Path("id") String id);

}
