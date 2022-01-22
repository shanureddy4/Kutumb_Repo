package com.example.kutumb_repo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import org.json.JSONArray

class MainActivity : AppCompatActivity() {
    lateinit var textView : TextView
    lateinit var queue:RequestQueue
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var button = findViewById<Button>(R.id.button)
        textView = findViewById<TextView>(R.id.textView)

        queue = Volley.newRequestQueue(this)
        button.setOnClickListener {
            JParse()
        }

    }

    private fun JParse() {
        val url = "https://gh-trending-api.herokuapp.com/repositories"
        var request = JsonObjectRequest(Request.Method.GET, url, null,
            {
                var jsonarray = JSONArray(it)
                for (i in 0 until it.length()-1)
                {
                    var repos = jsonarray.getJSONObject(i)
                    var username = repos.getString("username")
                    Toast.makeText(this,username + "shanu",Toast.LENGTH_SHORT).show()
                    textView.append("$username ")
                }
            }) {}
        queue.add(request)
    }
}