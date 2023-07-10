package com.example.detection.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.detection.bases.ViewBindingFragment
import com.example.detection.databinding.HomeFragmentBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment @Inject constructor(): ViewBindingFragment<HomeFragmentBinding>(){
    override fun getViewBinding(
        inflater: LayoutInflater,
        viewGroup: ViewGroup
    ): HomeFragmentBinding {
        return HomeFragmentBinding.inflate(inflater)
    }

    override fun initViewOnViewCreated(view: View, savedInstanceState: Bundle?) {

    }
}