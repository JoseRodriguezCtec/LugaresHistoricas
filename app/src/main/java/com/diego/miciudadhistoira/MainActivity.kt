package com.diego.miciudadhistoira

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.miciudad.Ciudad.CiudadAdapter
import com.example.miciudad.Ciudad.CiudadViewModel
class MainActivity : AppCompatActivity() {

private lateinit var ciudadViewModel: CiudadViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

    ciudadViewModel = ViewModelProvider (this)[CiudadViewModel::class.java]

        val recyclerview = findViewById<RecyclerView>(R.id.recyclerCiudad)
        val adapter = CiudadAdapter()
        recyclerview.adapter = adapter
        recyclerview.layoutManager = LinearLayoutManager(this)

        ciudadViewModel.getCiudad()
        ciudadViewModel.ciudadListMutable.observe(this){
            if (it.isNotEmpty()) {
                adapter.setCiudad(it)
            }
        }
    }
}