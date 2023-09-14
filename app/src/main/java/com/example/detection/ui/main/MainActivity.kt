package com.example.detection.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import androidx.activity.viewModels
import androidx.navigation.fragment.NavHostFragment
import com.example.detection.R
import com.example.detection.utils.SharedPreferencesManager
import com.example.detection.bases.ViewBindingActivity
import com.example.detection.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ViewBindingActivity<ActivityMainBinding>() {

    @Inject
    lateinit var sharedPreferencesManager: SharedPreferencesManager

    private val viewModel: MainViewModel by viewModels()

    override fun getViewBinding(inflater: LayoutInflater): ActivityMainBinding = ActivityMainBinding.inflate(inflater)

    override fun safeInitOnCreate(savedInstanceState: Bundle?) {
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val inflater = navHostFragment.navController.navInflater
        val graph = inflater.inflate(R.navigation.navigation_main)
        if (sharedPreferencesManager.showIntroBefore){
            graph.setStartDestination(R.id.navigation_authentication)
        }
        else {
            graph.setStartDestination(R.id.introFragment)
        }
        val navController = navHostFragment.navController
        navController.graph = graph
    }

}