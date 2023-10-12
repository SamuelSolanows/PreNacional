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
    }

    private fun MostrarUsuario() {
        binding.txt.setText("")
        var id=binding.etxt.text.toString()
        Retrofit.builder.GeUser(id).enqueue(object : Callback<List<Usuario>>{
            override fun onResponse(call: Call<List<Usuario>>, response: Response<List<Usuario>>) {
                var usuario=response.body()
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