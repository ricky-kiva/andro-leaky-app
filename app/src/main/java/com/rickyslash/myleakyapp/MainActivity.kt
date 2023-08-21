package com.rickyslash.myleakyapp

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    private lateinit var broadcastReceiver: BroadcastReceiver
    private lateinit var tvPowerStatus: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvPowerStatus = findViewById(R.id.tv_power_status)
    }

    private fun daftarBroadcastReceiver() {
        broadcastReceiver = object : BroadcastReceiver() { // BroadcastReceiver class defines what should happen when broadcast received
            override fun onReceive(context: Context?, intent: Intent?) { // this will executed when broadcast received
                when (intent?.action) { // do this when received intent-type broadcast event
                    Intent.ACTION_POWER_CONNECTED -> {
                        tvPowerStatus.text = getString(R.string.power_connected)
                    }
                    Intent.ACTION_POWER_DISCONNECTED -> {
                        tvPowerStatus.text = getString(R.string.power_disconnected)
                    }
                }
            }
        }
        val intentFilter = IntentFilter() // specify type of broadcast the receiver should listen
        intentFilter.apply { // this intentFilter listen to 2 action
            addAction(Intent.ACTION_POWER_CONNECTED)
            addAction(Intent.ACTION_POWER_DISCONNECTED)
        }
        registerReceiver(broadcastReceiver, intentFilter) // register `broadcastReceiver` var with `intentFilter` to be listened
    }

    override fun onStart() {
        super.onStart()
        daftarBroadcastReceiver() // start the register on start
    }

    override fun onStop() {
        super.onStop()
        unregisterReceiver(broadcastReceiver)
    }
}