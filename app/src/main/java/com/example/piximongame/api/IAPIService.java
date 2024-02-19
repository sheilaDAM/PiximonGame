package com.example.piximongame.api;


import retrofit2.Call;
//vamos a importar par usar el post de la api
import retrofit2.http.Body;
import retrofit2.http.POST;
//y el get
import retrofit2.http.GET;
import retrofit2.http.Query;

import com.example.piximongame.entidades.Jugador;
import com.example.piximongame.entidades.Partida;
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

    @POST("/usuarios/comprobarLogin")
    Call<ResponseStatus> comprobarLogin(@Body Usuario usuario);

    //Guardamos nuestro usuario jugador y se crearán 4 bots jugadores aleatorios
    @POST("/jugadores/addJugadores")
    Call<ResponseStatus> guardarJugadores(@Body Jugador jugadores);

    @GET("/jugadores/getJugadores")
    Call<List<Jugador>> obtenerJugadoresEnPartida();

    @GET("/jugadores/obtenerJugadoresPorPartidaId(int idPartida)")
    Call<List<Jugador>> obtenerJugadoresPorPartidaId(int idPartida);

    @GET("jugadores/obtenerJugadoresAleatoriosEnPartida")
    Call<List<Jugador>> obtenerJugadoresAleatoriosEnPartida(@Query("idPartida") int idPartida);

    @GET("/partida/obtenerPartidaActual")
      Call<Partida> obtenerPartidaActual(@Query("nombre") String nombreUsuarioJugador);


}
