package org.app.kotlinandroidapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import kotlinx.android.synthetic.main.activity_user_profile.*

class UserProfileActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_profile)

        val bundle:Bundle? = intent.extras
         if(bundle != null){
             val userName:String = bundle.get(getString(R.string.pref_userName)).toString()
             txt_userName.setText(getString(R.string.welcome) +" " +userName)
         }

        // course button
        btn_courses.setOnClickListener{
            intent = Intent(this,ComputerCoursesActivity::class.java)
            startActivity(intent)
        }

        //contact button
        btn_contacts.setOnClickListener{
            intent = Intent(this,ContactActivity::class.java)
            startActivity(intent)
        }

         //webveiw button
        btn_openWeb.setOnClickListener{
            intent = Intent(this,WebViewActivity::class.java)
            startActivity(intent)
        }

        //json fetch
        btn_fetchJson.setOnClickListener{
            intent = Intent(this,FetchJsonActivity::class.java)
            startActivity(intent)
        }
    }
}
