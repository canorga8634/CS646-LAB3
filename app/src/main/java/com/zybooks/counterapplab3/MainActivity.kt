package com.zybooks.counterapplab3

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import com.zybooks.counterapplab3.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    //Counter App Variables
    private lateinit var counter_text: TextView
    private lateinit var button_click: Button
    private lateinit var reset_click: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        val navController = findNavController(R.id.nav_host_fragment_content_main)
        appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConfiguration)

//        binding.fab.setOnClickListener { view ->
//            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                .setAction("Action", null).show()
//        }

        //Counter App Code
        counter_text = findViewById(R.id.counter_txt)
        button_click = findViewById(R.id.btn_click)
        reset_click = findViewById(R.id.reset_click)

        var timesClicked = 0

        button_click.setOnClickListener {
            timesClicked += 1
            counter_text.text = timesClicked.toString()

        }

        reset_click.setOnClickListener {
            timesClicked = 0
            counter_text.text = "00"
//            counter_text.text = timesClicked.toString()
        }

        //Warning Dialog Code
        val dialog = WarningDialogFragment()
        dialog.show(supportFragmentManager, "warningDialog")
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here
//        return when (item.itemId) {
//            R.id.action_settings -> true
//            else -> super.onOptionsItemSelected(item)
//        }
//    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }
}