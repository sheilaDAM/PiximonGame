package com.example.piximongame.api;


import retrofit2.Call;
//vamos a importar par usar el post de la api
import retrofit2.http.Body;
import retrofit2.http.POST;
//y el get
import retrofit2.http.GET;
import retrofit2.http.Query;

import com.example.piximongame.entidades.Carta;
import com.example.piximongame.entidades.Jugador;
import com.example.piximongame.entidades.Partida;
import com.example.piximongame.entidades.Usuario;
import com.example.piximongame.entidades.ResponseStatus;

import java.util.List;

//Esta interfaz define los métodos que se utilizarán para hacer las peticiones a la API
public interface IAPIService {

     //Al logearnos con éxito, se cargarán digimons en la bbdd de la api si no existen ya en la bbdd
     @POST("/digimons/comprobarDigimonsEnBBDD")
     Call<ResponseStatus> insertarDigimonsEnBBDD();

    //Guardamos el usuario creado en la base de datos
    @POST("/usuarios/registrar")
    Call<ResponseStatus> registrarUsuario(@Body Usuario usuario);

    //Al ingresar con un usuario en pantalla login, comprobar si existe y si es correcto
    @POST("/usuarios/comprobarLogin")
    Call<ResponseStatus> comprobarLogin(@Body Usuario usuario);

    //Guardamos nuestro usuario jugador y se crearán 4 bots jugadores aleatorios
    @POST("/jugadores/addJugadores")
    Call<ResponseStatus> guardarJugadores(@Body Jugador jugadores);


    //Para obtener todos los jugadores de una partida concreta (nos devolverá los 5 jugadores)
    @GET("/jugadores/obtenerJugadoresPorPartidaId(int idPartida)")
    Call<List<Jugador>> obtenerJugadoresPorPartidaId(int idPartida);

    //Para obtener todos los jugadores bots de una partida concreta
    @GET("jugadores/obtenerJugadoresAleatoriosEnPartida")
    Call<List<Jugador>> obtenerJugadoresAleatoriosEnPartida(@Query("idPartida") int idPartida);

    //Para obtener las cartas de un jugador concreto
    @GET("/cartas/obtenerCartasJugador")
    Call<List<Carta>> obtenerCartasJugador(@Query("idJugador") int idJugador);

    //Para obtener la partida actual donde pertenecen los 5 jugadores del juego actual
    @GET("/partida/obtenerPartidaActual")
      Call<Partida> obtenerPartidaActual(@Query("nombre") String nombreUsuarioJugador);


}
