package com.example.uniclima

import android.content.pm.PackageManager
import android.graphics.Color
import android.os.AsyncTask
import android.os.Bundle
import android.provider.Settings.Global.getString
import android.provider.Settings.Secure.getString
import android.text.method.LinkMovementMethod
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.app.ActivityCompat
import com.example.uniclima.databinding.ActivityMainBinding
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import org.json.JSONObject
import java.net.URL

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding
    val CITY: String = "moscow, russia"
    val API: String = "24eb564e2ce2331b49ffc05ca40fe78c"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        val navController = findNavController(R.id.nav_host_fragment_content_main)
        appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConfiguration)

        weatherTask().execute()
    }
    fun showAlertDialog(view:View){
        MaterialAlertDialogBuilder(this)
            .setTitle("ALERTA!!!!")
            .setMessage("LANÇANDO MISSEL NA CASA HENRY LUCCA(olinda)!!!")
            .setNeutralButton("melhor depois"){ dialog, which -> }
            .setNegativeButton("Não lançar"){ dialog, which->}
            .setPositiveButton("LANÇAAAAA!!!"){dialog,which->}
            .show()


    }

    inner class weatherTask():AsyncTask<String, Void, String>(){
        override fun doInBackground(vararg p0: String?): String? {
            var response:String?
            try{
                response = URL("https://api.openweathermap.org/data/2.5/weather?q=$CITY,db&units=metrics&appid=$API").readText(Charsets.UTF_8)
            }
            catch(e: Exception){
                response = null
            }
            return response
        }
        override fun onPostExecute(result: String?){
            super.onPostExecute(result)
            try{
                val jsonObj = JSONObject(result)
                val main = jsonObj.getJSONObject("main")
                val sys = jsonObj.getJSONObject("sys")
                val temp = main.getString("temp")+"F"
                val address = jsonObj.getString("name")+", "+sys.getString("country")
                findViewById<TextView>(R.id.country).text = address
                findViewById<TextView>(R.id.temp).text = temp

            }
            catch(e: Exception){

            }
        }
    }




    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }
}