package com.example.piximongame.actividades;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.piximongame.R;
import com.example.piximongame.api.IAPIService;
import com.example.piximongame.api.RestClient;
import com.example.piximongame.entidades.Jugador;
import com.example.piximongame.entidades.Usuario;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    private String nombreUsuario;
    private String password;
    private IAPIService apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button btAceptar = findViewById(R.id.btAceptar);
        EditText etNombre = findViewById(R.id.etIngresarNombre);
        EditText etPassword = findViewById(R.id.etIngresarPassword);

        apiService = RestClient.getApiServiceInstance();

        btAceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Guardamos el nombre del jugador
                nombreUsuario = etNombre.getText().toString();
                password = etPassword.getText().toString();
                //Creamos un usuario y lo guardamos en la base de datos a través de la api service
                Usuario usuario = new Usuario(nombreUsuario, password);
                apiService.guardarUsuario(usuario).enqueue(new Callback<Boolean>() {
                    @Override
                    public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                        if (response.isSuccessful() && response.body() != null && response.body()) {
                            Log.d("USUARIO GUARDADO", "Usuario guardado: " + response.body());
                        } else {
                            Log.e("USUARIO NO GUARDADO", "Usuario no guardado");
                        }
                    }

                    @Override
                    public void onFailure(Call<Boolean> call, Throwable t) {
                        Log.e("RETROFIT", "Error al guardar usuario: " + t.getMessage());
                    }
                });
                //List<Jugador> jugadoresObtenidos = apiService.getJugadores();

               // Log.d("JUGADORES OBTENIDOS, ", "Jugadores obtenidos:, " + jugadoresObtenidos.size());
                Log.d("NOMBRE REGISTRADO", "Nombre jugador: " + nombreUsuario);
                Intent intent = new Intent(LoginActivity.this, AvatarActivity.class);
                intent.putExtra("nombreJugador", nombreUsuario);
                intent.putExtra("usuarioLogeado", usuario);
                startActivity(intent);
            }
        });
    }

    /*

    private void obtenerJugadores(Usuario usuario) {
        apiService.guardarUsuario(usuario).enqueue(new Callback<List<Usuario>>() {
            @Override
            public void onResponse(Call<List<Usuario>> call, Response<List<Usuario>> response) {
                if (response.isSuccessful()) {
                    listaFrasesPorAutor = response.body();
                    Log.d("FRASES OBTENIDAS DE AUTOR", "Cantidad: " + listaFrasesPorAutor.size());
                    adaptadorFrase.setData(listaFrasesPorAutor);
                }
            }

            @Override
            public void onFailure(Call<List<Frase>> call, Throwable t) {
                Log.d("RETROFIT", "Error al obtener frases por autor: " + t.getMessage());
                String url = call.request().url().toString();
                Log.d("RETROFIT", "URL de la llamada al listado por autor: " + url);
            }
        });
    }

     */

}
