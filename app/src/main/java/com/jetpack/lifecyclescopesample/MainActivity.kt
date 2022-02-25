package com.jetpack.lifecyclescopesample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.whenResumed
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        log("onCreate")
        lifecycleScope.launchWhenResumed {
            log("launchWhenResumed")
        }
    }

    override fun onResume() {
        super.onResume()
        log("onResume")
    }

    fun lifecycleScopeLaunch(view: View) {

        lifecycleScope.launch {
            log("lifecycleScopeLaunch ---> ${Thread.currentThread().name}")
            withContext(Dispatchers.IO){
                log("lifecycleScopeLaunch  withContext(Dispatchers.IO)---> ${Thread.currentThread().name}")
            }
        }
    }
    fun lifecycleScopeLaunchIO(view: View) {
        lifecycleScope.launch(Dispatchers.IO){
            log("lifecycleScopeLaunchIO---> ${Thread.currentThread().name}")
        }
    }
    fun lifecycleScopeLaunch_whenResumed(view: View) {
        lifecycleScope.launch {
            whenResumed {
                log("lifecycleScopeLaunch_whenResumed---> ${Thread.currentThread().name}")
            }
        }
    }
    fun lifecycleScopeLaunchWhenResumed(view: View) {
        lifecycleScope.launchWhenResumed {
            log("lifecycleScopeLaunchWhenResumed---> ${Thread.currentThread().name}")
        }
    }

    private fun log(msg:String){
        Log.d("MainActivity",msg)
    }
}