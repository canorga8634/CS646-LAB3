package com.zybooks.counterapplab3

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.AnimationDrawable
import android.media.MediaPlayer
import android.os.Bundle
import android.provider.MediaStore
import android.view.Menu
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.zybooks.counterapplab3.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    //Counter App Variables
    private lateinit var counter_text: TextView
    private lateinit var button_click: Button
    private lateinit var reset_click: Button

    //Sound Variables
    private var mediaPlayer: MediaPlayer? = null

    //Rocket Animation Variable
    private lateinit var rocketAnimation: AnimationDrawable

    //Camera Variables
    private lateinit var resultLauncher: ActivityResultLauncher<Intent>
    private var currentImagePath: String? = null

    private lateinit var btnOpenCamera: Button
    private lateinit var ivPhoto: ImageView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        val navController = findNavController(R.id.nav_host_fragment_content_main)
        appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConfiguration)


        //Counter App Code
        counter_text = findViewById(R.id.counter_txt)
        button_click = findViewById(R.id.btn_click)
        reset_click = findViewById(R.id.reset_click)

        var timesClicked = 0

        button_click.setOnClickListener {
            timesClicked += 1
            counter_text.text = timesClicked.toString()

            //Sound Button Click
            mediaPlayer = MediaPlayer.create(this, R.raw.sound)
            mediaPlayer?.start()
        }

        reset_click.setOnClickListener {
            timesClicked = 0
            counter_text.text = "00"
            //counter_text.text = timesClicked.toString()
        }

        //Warning Dialog Code
        val dialog = WarningDialogFragment()
        dialog.show(supportFragmentManager, "warningDialog")

        //Rocket Animation Code
        val rocketImage = findViewById<ImageView>(R.id.rocket_image).apply {
            setBackgroundResource(R.drawable.rocket_color)
            rocketAnimation = background as AnimationDrawable
        }
        rocketImage.setOnClickListener({ rocketAnimation.start() })

        //Camera Code
        btnOpenCamera = findViewById(R.id.btnOpenCamera)
            //Variable to handle preview and image goes here

        resultLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                if (result.resultCode == Activity.RESULT_OK) {
                    handleCameraImage(result.data)
                }
            }

        btnOpenCamera.setOnClickListener {

            //intent to open camera app
            val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            resultLauncher.launch(cameraIntent)

        }
    }

    private fun handleCameraImage(intent: Intent?) {
        val bitmap = intent?.extras?.get("data") as Bitmap
        ivPhoto.setImageBitmap(bitmap)

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }
}