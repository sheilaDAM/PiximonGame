package com.example.piximongame.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/*
Vamos a consumir una api de Digimons que hay en internet.
Con esta clase se pretende realizar una consulta HTTP (una petición) a la API de Digimon
para obtener la información de los Digimons y almacenarla en la base de datos.
Esta información serán los nombres y las fotos todos de los digimons, que utilizaremos
para poder crear las cartas de los jugadores.
 */
public class RestClient {

    private static IAPIService apiInstance;
    //private static final String BASE_URL = "http://192.168.50.135:8081";
    //private static final String BASE_URL = "http://192.168.50.165:8082"; //la ip del ordenador en clase con la api
    private static final String BASE_URL = "http://192.168.18.36:8082";

    private RestClient() {
    }

    public synchronized static IAPIService getApiServiceInstance() {
        if (apiInstance == null) {
            Gson gson = new GsonBuilder().setLenient().create();
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();
            apiInstance = retrofit.create(IAPIService.class);
        }
        return apiInstance;
    }




}
