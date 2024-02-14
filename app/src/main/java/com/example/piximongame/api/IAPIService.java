package com.example.piximongame.api;


import retrofit2.Call;
//vamos a importar par usar el post de la api
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.POST;
//y el get
import retrofit2.http.GET;

import com.example.piximongame.entidades.Jugador;
import com.example.piximongame.entidades.Usuario;

import java.util.List;

//Esta interfaz define los métodos que se utilizarán para hacer las peticiones a la API
public interface IAPIService {

   // @POST("/identificarlogin")
    //Call<Jugador> identificarLogin(@Body Jugador jugador);

    //Guardamos el usuario creado en la base de datos
    @POST("/usuarios/guardar")
    Call<Response<Void>> guardarUsuario(@Body Usuario usuario);

    //Guardamos los 4 jugadores aleatorios
    @POST("/guardarjugadores")
    Call<List<Jugador>> guardarJugadores(@Body List<Jugador> jugadores);

    //Guardamos la ruta de los avatares
    @POST("/guardaravatares")
    Call<List<String>> guardarAvatares(@Body List<String> avatares);

    @GET("/jugadores/getJugadores")
    Call<List<Jugador>> getJugadores();

    @POST("/usuarios/comprobarLogin")
    Call<Response<Void>> comprobarLogin(@Body Usuario usuario);

}
