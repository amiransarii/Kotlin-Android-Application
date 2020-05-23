package org.app.kotlinandroidapplication

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_computer_courses.*
import kotlinx.android.synthetic.main.activity_user_profile.*

class ComputerCoursesActivity : AppCompatActivity() {

    private val sharedPrefFile ="coursesharedpreference"
    private var selectedCourse ="";
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_computer_courses)

        //show the course list
        val languages:Array<String> = resources.getStringArray(R.array.technology_list)
        val arrayAdapter = ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,languages);
        val sharedPreferences: SharedPreferences = this.getSharedPreferences(sharedPrefFile, Context.MODE_PRIVATE)

         //check if user already take any courses
          val courseTaken = sharedPreferences.getString(getString(R.string.pref_course),"");
         txt_course.setText(getString(R.string.current_course) +" "+courseTaken)

        lv_courses.adapter = arrayAdapter;

        //choose course
        lv_courses.onItemClickListener = AdapterView.OnItemClickListener{
                adapterView, view, position, id ->

            selectedCourse = adapterView.getItemAtPosition(position) as String;

            Toast.makeText(applicationContext,"click item $selectedCourse" , Toast.LENGTH_SHORT).show()
            showCourseDialog(selectedCourse)

             val editor:SharedPreferences.Editor = sharedPreferences.edit();
            editor.putString(getString(R.string.pref_course),selectedCourse)
            editor.apply()
            editor.commit()
        }
    }


          //course details dialog

          fun showCourseDialog(courseName:String){

              val builder = AlertDialog.Builder(this)
              //set the title  for alert dialog
              builder.setTitle("Selected Course "+courseName)
              //set message for alert dialog
              builder.setMessage(getMessage(courseName))
              builder.setIcon(android.R.drawable.ic_dialog_alert)

              //performing positive action
               builder.setPositiveButton("Yes"){
                   dialogInterface, i ->
                   Toast.makeText(applicationContext,"clicked yes",Toast.LENGTH_LONG).show()
               }

              //performing cancel action
              builder.setNeutralButton("Cancel"){dialogInterface , which ->
                  Toast.makeText(applicationContext,"clicked cancel\n operation cancel",Toast.LENGTH_LONG).show()
              }

              //performing negative action
              builder.setNegativeButton("No"){dialogInterface, which ->
                  Toast.makeText(applicationContext,"clicked No",Toast.LENGTH_LONG).show()
              }

              // Create the AlertDialog
              val alertDialog: AlertDialog = builder.create()
              // Set other dialog properties
              alertDialog.setCancelable(false)
              alertDialog.show()
          }

    fun getMessage(course:String) :String{

        val message =  "Course Name "+course  +" \n"+"Course Fees "+ 2000 +"\n"+"Joining Date "+ "30-06-2020" +"\n"+" Teacher "+" Andrew Watson"
        return  message;
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.action_settings -> {
                Toast.makeText(applicationContext, "click on setting", Toast.LENGTH_LONG).show()
                true
            }
            R.id.action_share ->{
                Toast.makeText(applicationContext, "click on share", Toast.LENGTH_LONG).show()
                return true
            }
            R.id.action_exit ->{
                Toast.makeText(applicationContext, "click on exit", Toast.LENGTH_LONG).show()
                return true
            }
            else -> super.onOptionsItemSelected(item)

        }
    }

          }




