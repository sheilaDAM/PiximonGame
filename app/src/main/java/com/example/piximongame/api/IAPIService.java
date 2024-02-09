package com.example.piximongame.api;


import retrofit2.Call;
//vamos a importar par usar el post de la api
import retrofit2.http.Body;
import retrofit2.http.POST;
//y el get
import retrofit2.http.GET;

import com.example.piximongame.entidades.Jugador;

//Esta interfaz define los métodos que se utilizarán para hacer las peticiones a la API
public interface IAPIService {

    @POST("/identificarlogin")
    Call<Jugador> identificarLogin(@Body Jugador jugador);

}
