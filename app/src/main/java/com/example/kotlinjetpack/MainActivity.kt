package com.example.kotlinjetpack

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.kotlinjetpack.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var count: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)

        binding.btnCount.setOnClickListener {
            count += 1
            binding.txtCount.text = count.toString()
        }

        binding.btnDownload.setOnClickListener {

            // background thread
            CoroutineScope(Dispatchers.IO).launch {
                download()
            }

            /**
             * Scope
             * 1. CoroutineScope
             *    help us to track running coroutine, cancel the unused coroutine to avoid memory leak
             * 2. GlobalScope
             *    is one of the ways by which coroutines are launched. When coroutines are launched
             *    within the global scope, they live long as the application does. If the
             *    coroutines finish it is job, it will be destroyed and will not keep alive until
             *    the application dies
             * 3. LifecycleScope
             *    When we are using CoroutineScope in activity / fragment we have to make sure the
             *    running coroutine stop when we destroy the activity / fragment to avoid memory
             *    leak and resource waste
             *    Therefore, we should check and cancel coroutine scope when the lifecycle owner
             *    is about to be destroyed. In this case by using LifecycleScope we do not have
             *    to manually check and cancel the running coroutine before lifecycle owner is
             *    destroyed
             * 4. ViewModel Scope
             *    it is also the same as the lifecycle scope, only difference is that the coroutine
             *    in this scope will live as long the view modal is alive
             */

            /**
             * Dispatchers
             * 1. Dispatchers.Main
             *    a coroutine dispatchers that is confined to the main thread operating with ui
             *    objects. This dispatchers can be used either directly or via the MainScope factory
             * 2. Dispatchers.Default
             *    the default CoroutineDispatcher that is used by all coroutine builders like launch
             *    , async, etc. If no dispatcher nor any other ContinuationInterceptor is specified
             *    in their context. It is Optimized for CPU intensive work off the main thread
             * 3. Dispacthers.IO
             *    is designed for offloading blocking IO tasks to a shared pool of threads and
             *    networking operations. Additional threads in this pool are created and are shutdown
             *    on demand
             */
        }
    }

    private suspend fun download(){
        for (i in 1..100000){
            Log.e("TAG","Downloading : $i")

            // Main thread
            withContext(Dispatchers.Main){
                binding.txtDownload.text = "$i"
            }
        }
    }
}