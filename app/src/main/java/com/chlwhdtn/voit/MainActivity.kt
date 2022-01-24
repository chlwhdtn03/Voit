package com.chlwhdtn.voit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    val debates = ArrayList<Debate>()
    val adapter = DebateAdapter(debates,this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var search: TextInputLayout = findViewById(R.id.main_search)
        var search_btn: Button = findViewById(R.id.main_search_btn)

        var search_title: TextView = findViewById(R.id.search_title)
        var search_agree: TextView = findViewById(R.id.search_agree)
        var search_disagree: TextView = findViewById(R.id.search_disagree)

        var recyclerView: RecyclerView = findViewById(R.id.main_recyclerview)

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        search_btn.setOnClickListener {
            var str = search.editText?.text.toString().trim().toString()
            if(str.trim().isEmpty())
                return@setOnClickListener

            VoitRetrofit.DebateService.getDebate(str).enqueue(object: Callback<VoitResponse> {
                override fun onFailure(call: Call<VoitResponse>, t: Throwable) {
                    t.printStackTrace()                }

                override fun onResponse(
                    call: Call<VoitResponse>,
                    response: Response<VoitResponse>
                ) {
                    if(!response.body()!!.success) {
                        search_title.text = response.body()!!.message
                        return
                    }
                    var debate: Debate = Gson().fromJson(response.body()!!.data.toString(), Debate::class.java)
                    search_title.text = debate.title + " - " + debate.id
                    search_agree.text = "찬성 " + debate.ticket_agree.toString()
                    search_disagree.text = "반대 " + debate.ticket_disagree.toString()

                }
            })


        }

        VoitRetrofit.DebateService.getDebates(0).enqueue(object : Callback<VoitResponse> {
            override fun onFailure(call: Call<VoitResponse>, t: Throwable) {
                t.printStackTrace()
            }

            override fun onResponse(call: Call<VoitResponse>, response: Response<VoitResponse>) {
                if(response.body()!!.success) {
                    var arr = Gson().fromJson(response.body()!!.data.toString(), Array<Debate>::class.java)
                    debates.addAll(arr)
                    adapter.notifyDataSetChanged()
                } else {
                    println(response.body()!!.message);
                }
            }

        })
    }

}