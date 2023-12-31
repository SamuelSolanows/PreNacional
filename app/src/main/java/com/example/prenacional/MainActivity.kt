package com.example.prenacional

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.prenacional.Model.Api.Retrofit
import com.example.prenacional.Model.Modelos.Usuario
import com.example.prenacional.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.todos.setOnClickListener {
            MostrarTodosUsuarios()
        }

        binding.uno.setOnClickListener {
            MostrarUsuario()
        }


        binding.Agregar.setOnClickListener {
            AgregarUsuario()
        }
        binding.Eliminar.setOnClickListener {
            EliminarUsuario()
        }
        binding.Actualizar.setOnClickListener {
            ActualizarUsuario()
        }
    }


    private fun ActualizarUsuario() {
        binding.etxt.setText("")
        if (binding.etxt.text.toString().isNotEmpty()) {
            var id = binding.etxt.text.toString()

            var actualizar = Usuario(id.toInt(), "", "", "", "", 2)
            Retrofit.builder.ActualizarUsuario(id, actualizar).enqueue(object : Callback<Usuario> {
                override fun onResponse(call: Call<Usuario>, response: Response<Usuario>) {
                    Toast.makeText(this@MainActivity, "Actualizao", Toast.LENGTH_SHORT).show()
                }

                override fun onFailure(call: Call<Usuario>, t: Throwable) {
                    t.message?.let { Log.e("Error", it) }
                }
            })
        }

    }

    private fun EliminarUsuario() {
        binding.txt.setText("")
        var id = binding.etxt.text.toString()
        Retrofit.builder.EliminarUsuario(id).enqueue(object : Callback<Usuario> {
            override fun onResponse(call: Call<Usuario>, response: Response<Usuario>) {
                if (response.isSuccessful) {
                    Toast.makeText(this@MainActivity, "Eliminao", Toast.LENGTH_SHORT).show()
                    MostrarTodosUsuarios()
                } else {
                    Toast.makeText(this@MainActivity, response.code().toString(), Toast.LENGTH_SHORT).show()
                    Log.e("error", response.message())
                }

            }

            override fun onFailure(call: Call<Usuario>, t: Throwable) {
                t.message?.let { Log.e("Error", it) }
            }
        })
    }

    private fun AgregarUsuario() {

        var newUser = Usuario(1, "Samuel", "Solano", "Samuelsolanoama@gmail.com", "3244123104", 1)

        Retrofit.builder.InsertarUsuario(newUser).enqueue(object : Callback<Usuario> {
            override fun onResponse(call: Call<Usuario>, response: Response<Usuario>) {
                Toast.makeText(this@MainActivity, "Se Ingreso el usuario", Toast.LENGTH_SHORT)
                    .show()
            }

            override fun onFailure(call: Call<Usuario>, t: Throwable) {
                t.message?.let { Log.e("Error", it) }
            }
        })
    }


    private fun MostrarUsuario() {

        binding.txt.setText("")
        var id = binding.etxt.text.toString()
        Retrofit.builder.GeUser(id).enqueue(object : Callback<List<Usuario>> {
            override fun onResponse(call: Call<List<Usuario>>, response: Response<List<Usuario>>) {
                var usuario = response.body()
                usuario!!.forEach {
                    binding.txt.setText(it.Nombre)
                }
            }

            override fun onFailure(call: Call<List<Usuario>>, t: Throwable) {
                t.message?.let { Log.e("error", it) }
            }
        })
    }


    private fun MostrarTodosUsuarios() {
        binding.txt.setText("")
        Retrofit.builder.GetAllUsers().enqueue(object : Callback<List<Usuario>> {
            override fun onResponse(call: Call<List<Usuario>>, response: Response<List<Usuario>>) {
                var usuario = response.body()
                usuario!!.forEach {
                    binding.txt.append("${it.Nombre} \n")
                }
                Toast.makeText(this@MainActivity, usuario.size.toString(), Toast.LENGTH_SHORT)
                    .show()
            }

            override fun onFailure(call: Call<List<Usuario>>, t: Throwable) {
                t.message?.let { Log.e("error", it) }
            }
        })
    }


}