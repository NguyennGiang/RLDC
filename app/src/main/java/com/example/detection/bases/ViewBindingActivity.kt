package com.example.detection.bases

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.viewbinding.ViewBinding
import com.example.detection.R

abstract class ViewBindingActivity<VB : ViewBinding?> : AppCompatActivity() {
    var binding: VB? = null
    protected abstract fun getViewBinding(inflater: LayoutInflater): VB

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = getViewBinding(layoutInflater)
        setContentView(binding!!.root)
        setStatusBarColor()
        try {
            safeInitOnCreate(savedInstanceState)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    protected abstract fun safeInitOnCreate(savedInstanceState: Bundle?)
    private fun setStatusBarColor() {
        val window = window
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor = ContextCompat.getColor(this, R.color.colorPrimary)
    }

    protected fun showMessage(message: String?) {
        val toast = Toast.makeText(this, message, Toast.LENGTH_SHORT)
        val group = toast.view as ViewGroup?
        val messageTextView = group!!.getChildAt(0) as TextView
        messageTextView.textSize = 20f
        toast.show()
    }

    public override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}