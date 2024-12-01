package com.example.kotlinapplication

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlinapplication.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

 lateinit var binding: ActivityMainBinding
 lateinit var adapter: DataAdapter

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        /*enableEdgeToEdge()
        binding.text.setOnClickListener {
            binding.text.text="text xhanged on click"
        }
        binding.permissionBtn.setOnClickListener {
            binding.text.text="button clicked"
        }*/

        val retrofitBuilder= Retrofit.Builder()
            .baseUrl("https://deezerdevs-deezer.p.rapidapi.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiInterface::class.java)

        val retrofitData = retrofitBuilder.getData("eminem")

        retrofitData.enqueue(object : Callback<MyData?> {
            override fun onResponse(p0: Call<MyData?>, response: Response<MyData?>) {
                val dataList = response.body()?.data!!
                adapter = DataAdapter(this@MainActivity,dataList)
                binding.recycleID.adapter = adapter
                binding.recycleID.layoutManager = LinearLayoutManager(this@MainActivity)
            }

            override fun onFailure(p0: Call<MyData?>, t: Throwable) {
                Log.e("TAG: On Failure", "onFailure: ${t.message}", )
            }
        })

    }
}