package com.savik.accelerometer

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.core.content.getSystemService

class MainActivity : AppCompatActivity() {
    lateinit var sManager: SensorManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val tvSensor = findViewById<TextView>(R.id.tvSensor)
        sManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        val sensor = sManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
        val sListener = object : SensorEventListener{
            override fun onSensorChanged(sEvent: SensorEvent?) {
                val value = sEvent?.values
                var x: Double = value?.get(0)!! * 9.17
                var y: Double = value?.get(1)!! * 9.17
                var z: Double = value?.get(2)!! * 9.17
                val sData = "X: ${x.toInt()}\nY: ${y.toInt()}\nZ: ${z.toInt()}"
                tvSensor.setText(sData)
            }

            override fun onAccuracyChanged(p0: Sensor?, p1: Int) {

            }

        }
        sManager.registerListener(sListener, sensor, SensorManager.SENSOR_DELAY_UI)
    }
}