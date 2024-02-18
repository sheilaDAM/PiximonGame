package com.example.piximongame.api;


import retrofit2.Call;
//vamos a importar par usar el post de la api
import retrofit2.http.Body;
import retrofit2.http.POST;
//y el get
import retrofit2.http.GET;

import com.example.piximongame.entidades.Jugador;
import com.example.piximongame.entidades.Usuario;
import com.example.piximongame.entidades.ResponseStatus;

import java.util.List;

//Esta interfaz define los métodos que se utilizarán para hacer las peticiones a la API
public interface IAPIService {

     @POST("/digimons/recuperar")
     Call<ResponseStatus> recuperarDigimons();

     @POST("/digimons/comprobarDigimonsEnBBDD")
     Call<ResponseStatus> insertarDigimonsEnBBDD();

    //Guardamos el usuario creado en la base de datos
    @POST("/usuarios/registrar")
    Call<ResponseStatus> registrarUsuario(@Body Usuario usuario);

    //Guardamos los 4 jugadores aleatorios
    @POST("/guardarjugadores")
    Call<List<Jugador>> guardarJugadores(@Body List<Jugador> jugadores);

    //Guardamos la ruta de los avatares
    @POST("/guardaravatares")
    Call<List<String>> guardarAvatares(@Body List<String> avatares);

    @GET("/jugadores/getJugadores")
    Call<List<Jugador>> getJugadores();

    @POST("/usuarios/comprobarLogin")
    Call<ResponseStatus> comprobarLogin(@Body Usuario usuario);

}
