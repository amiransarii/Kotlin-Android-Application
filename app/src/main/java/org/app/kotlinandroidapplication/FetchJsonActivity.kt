package org.app.kotlinandroidapplication


import android.os.Bundle
import android.view.View
import android.widget.ListView
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import okhttp3.*
import org.app.kotlinandroidapplication.adapter.CustomAdapter
import org.app.kotlinandroidapplication.entity.Model
import org.json.JSONArray
import org.json.JSONObject
import java.io.IOException
import kotlin.collections.ArrayList

class FetchJsonActivity : AppCompatActivity() {
    lateinit var progress: ProgressBar
    lateinit var listView_details: ListView
    var arrayList_details:ArrayList<Model> = ArrayList();

    //OkHttpClient creates connection pool between client and server
    val client = OkHttpClient();


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fetch_json)
        progress = findViewById(R.id.progressBar)
        progress.visibility = View.VISIBLE

        listView_details = findViewById<ListView>(R.id.listView_json) as ListView

        run(getString(R.string.json_url))

    }

    fun run(url: String) {
        progress.visibility = View.VISIBLE
        val request = Request.Builder()
            .url(url)
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                progress.visibility = View.GONE
            }

            override fun onResponse(call: Call, response: Response) {
                var str_response = response.body()!!.string()
                //creating json object

                var jsonarray_info:JSONArray = JSONArray();
               // val json_contact:JSONObject = JSONObject(str_response)
                //creating json array
                //var jsonarray_info:JSONArray= json_contact.getJSONArray("info")
                var i:Int = 0
                var size:Int = jsonarray_info.length()
                arrayList_details= ArrayList();
                for (i in 0.. size-1) {
                    var json_objectdetail:JSONObject=jsonarray_info.getJSONObject(i)
                    var model:Model= Model();
                    model.id=json_objectdetail.getString("login")
                    model.name=json_objectdetail.getString("id")
                    model.email=json_objectdetail.getString("node_id")
                    arrayList_details.add(model)
                }

                runOnUiThread {
                    //stuff that updates ui
                    val obj_adapter : CustomAdapter
                    obj_adapter = CustomAdapter(applicationContext,arrayList_details)
                    listView_details.adapter=obj_adapter
                }
                progress.visibility = View.GONE
            }
        })
    }
}
