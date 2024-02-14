package com.example.piximongame.actividades;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.piximongame.R;
import com.example.piximongame.api.IAPIService;
import com.example.piximongame.api.RestClient;
import com.example.piximongame.entidades.Jugador;
import com.example.piximongame.entidades.Usuario;
import com.example.piximongame.util.HashGenerator;

import java.security.NoSuchAlgorithmException;
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
        Button btRegistrarse = findViewById(R.id.btRegistrarse);
        EditText etNombre = findViewById(R.id.etIngresarNombre);
        EditText etPassword = findViewById(R.id.etIngresarPassword);

        apiService = RestClient.getApiServiceInstance();

        btRegistrarse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Guardamos el nombre del jugador
                nombreUsuario = etNombre.getText().toString();
                password = etPassword.getText().toString();
                //Creamos un usuario y lo guardamos en la base de datos a través de la api service
                Usuario usuario = null;
                try {
                    usuario = new Usuario(nombreUsuario, HashGenerator.getSHAString(password));
                } catch (NoSuchAlgorithmException e) {
                    throw new RuntimeException(e);
                }
                apiService.guardarUsuario(usuario).enqueue(new Callback<Response<Void>>() {
                    @Override
                    public void onResponse(Call<Response<Void>> call, Response<Response<Void>> response) {
                        if (response.code() == 201) {
                            Log.d("USUARIO GUARDADO", "Usuario guardado: " + response.body());
                            Toast.makeText(LoginActivity.this, "Usuario registrado con éxito :).", Toast.LENGTH_SHORT).show();
                        } else if (response.code() == 409) {
                            // El usuario ya existe, maneja este caso
                            Toast.makeText(LoginActivity.this, "El usuario ya existe en la bbdd.", Toast.LENGTH_SHORT).show();
                        } else {
                            Log.e("USUARIO NO GUARDADO", "Usuario no guardado");
                        }
                    }

                    @Override
                    public void onFailure(Call<Response<Void>> call, Throwable t) {
                        Log.e("RETROFIT", "Error al guardar usuario: " + t.getMessage());
                    }
                });
                //List<Jugador> jugadoresObtenidos = apiService.getJugadores();

               // Log.d("JUGADORES OBTENIDOS, ", "Jugadores obtenidos:, " + jugadoresObtenidos.size());
                Log.d("NOMBRE REGISTRADO", "Nombre jugador: " + nombreUsuario);
            }
        });

        btAceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Comprobamos si nuestro usuario existe en la base de datos

                nombreUsuario = etNombre.getText().toString();
                password = etPassword.getText().toString();
                //Creamos un usuario y lo guardamos en la base de datos a través de la api service
                Usuario usuario = null;
                try {
                    usuario = new Usuario(nombreUsuario, HashGenerator.getSHAString(password));
                } catch (NoSuchAlgorithmException e) {
                    throw new RuntimeException(e);
                }

                Usuario finalUsuario = usuario;
                apiService.comprobarLogin(usuario).enqueue(new Callback<Response<Void>>() {
                    @Override
                    public void onResponse(Call<Response<Void>> call, Response<Response<Void>> response) {

                            if (response.code() == 202) {
                                // Credenciales válidas
                                Log.d("USUARIO EXISTE", "Usuario existe");
                                Toast.makeText(LoginActivity.this, "Usuario correcto.", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(LoginActivity.this, AvatarActivity.class);
                                intent.putExtra("usuarioLogeado", finalUsuario);
                                Log.d("USUARIO LOGEADO", "Usuario logeado: " + finalUsuario);
                                startActivity(intent);
                            } else if (response.code() == 401) {
                                // Credenciales inválidas
                                Log.e("USUARIO NO EXISTE", "Credenciales inválidas");
                                Toast.makeText(LoginActivity.this, "Datos incorrectos.", Toast.LENGTH_SHORT).show();

                           } else {
                            Log.e("RETROFIT", "Error al comprobar login: " + response.message());
                            Toast.makeText(LoginActivity.this, "Error al insertar datos o comprobar login", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Response<Void>> call, Throwable t) {
                        Log.e("RETROFIT", "Error al comprobar login: " + t.getMessage());
                    }
                });

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
