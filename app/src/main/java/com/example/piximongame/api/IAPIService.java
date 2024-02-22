package com.example.piximongame.api;


import retrofit2.Call;
//vamos a importar para usar el post de la api
import retrofit2.http.Body;
import retrofit2.http.POST;
//y el get
import retrofit2.http.GET;
import retrofit2.http.Path;

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

    @POST("/inicializador/generarDatos")
    Call<Boolean> generarDatosIniciales(@Body Jugador jugador);

    //Guardamos nuestro usuario jugador y se crearán 4 bots jugadores aleatorios
    @POST("/jugadores/addJugadores")
    Call<ResponseStatus> guardarJugadores(@Body Jugador jugadores);

    @POST("/inicializador/generarDatos")
    Call<ResponseStatus> guardarUsuarioJugadorYGenerarDatos(@Body Jugador jugador);

    //Para obtener todos los jugadores de una partida concreta (nos devolverá los 5 jugadores)
    @GET("/jugadores/obtenerJugadoresPorPartidaId/{idPartida}")
    Call<List<Jugador>> obtenerJugadoresPorPartidaId(@Path("idPartida") int idPartida);

    //Para obtener todos los jugadores bots de una partida concreta
    @GET("jugadores/obtenerJugadoresAleatoriosEnPartida/{idPartida}")
    Call<List<Jugador>> obtenerJugadoresAleatoriosEnPartida(@Path("idPartida") int idPartida);

    //Para obtener las cartas de un jugador concreto
    @GET("/cartas/obtenerCartasJugador/{idJugador}")
    Call<List<Carta>> obtenerCartasJugador(@Path("idJugador") int idJugador);

    //Para obtener las cartas alineadas de un jugador concreto
    @GET("cartas/obtenerCartasAlineadasDeUnJugador/{idJugador}")
    Call<List<Carta>> obtenerCartasAlineadasDeJugador(@Path("idJugador") int idJugador);


    //Para obtener la partida actual donde pertenecen los 5 jugadores del juego actual
    @GET("/partida/obtenerPartidaActual")
    Call<Partida> obtenerPartidaActual();

    @GET("/jugadores/obtenerJugadorUsuarioEnPartida/{idPartida}")
    Call<Jugador> obtenerJugadorUsuarioEnPartida(@Path("idPartida") int idPartida);

    @GET("/cartas/obtenerCartasSinAsignar")
    Call<List<Carta>> obtenerCartasSinAsignar();

}
