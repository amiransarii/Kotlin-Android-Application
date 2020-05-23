package org.app.kotlinandroidapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_Login.setOnClickListener(){

            val userEmail:String = edit_userEmail.text.toString();
            val userPass: String = edit_userPass.text.toString();

             //check user email
            if(userEmail.trim() =="" || userEmail == null){
                Toast.makeText(this,getString(R.string.hint_email),Toast.LENGTH_LONG).show()
            }
           else if(userPass.trim() =="" || userPass == null){
                Toast.makeText(this,getString(R.string.hint_pass),Toast.LENGTH_LONG).show()
            }
            else{
                Toast.makeText(this,getString(R.string.login_sucess),Toast.LENGTH_LONG).show()

                intent = Intent(this,UserProfileActivity::class.java)
                intent.putExtra(getString(R.string.pref_userName),edit_userEmail.text.toString())
                startActivity(intent)

            }

        }
    }
}
