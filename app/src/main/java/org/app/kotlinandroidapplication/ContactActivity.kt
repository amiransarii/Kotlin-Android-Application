package org.app.kotlinandroidapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_contact.*

class ContactActivity : AppCompatActivity() {
    private val contact = arrayOf<String>("Akash","Vikash","John","Rahul","Ajay")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contact)

        val arrayAdapter :ArrayAdapter<String> = ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,contact)
        listView_Contacts.adapter = arrayAdapter
        registerForContextMenu(listView_Contacts)
    }

    override fun onCreateContextMenu(menu: ContextMenu?, v: View?, menuInfo: ContextMenu.ContextMenuInfo?) {
        super.onCreateContextMenu(menu, v, menuInfo)
        val inflater = menuInflater;
        inflater.inflate(R.menu.menu_contact,menu)
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        return when (item!!.itemId) {
            R.id.call ->{
                Toast.makeText(applicationContext, "calling", Toast.LENGTH_LONG).show()
                return true
            }
            R.id.sms ->{
                Toast.makeText(applicationContext, "sms code", Toast.LENGTH_LONG).show()
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
