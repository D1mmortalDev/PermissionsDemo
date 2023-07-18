package com.example.permissionsdemo

import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.permissionsdemo.databinding.ActivityMainBinding
import android.Manifest

class MainActivity : AppCompatActivity() {
    private val REQUEST_LOCATION_PERMISSION=1
    private lateinit var binding:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.M){
            if(checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED){
                requestPermissions(arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),REQUEST_LOCATION_PERMISSION)
            }else{
                //permission already granted
            }
        }
        else{
            //permission not required for lower version
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if(requestCode== REQUEST_LOCATION_PERMISSION && grantResults.isNotEmpty() &&
            grantResults[0]==PackageManager.PERMISSION_GRANTED){
            Toast.makeText(this,"Permission Granted",Toast.LENGTH_SHORT).show()
        }else{
            Toast.makeText(this,"Permission Denied",Toast.LENGTH_SHORT).show()
        }

    }
}