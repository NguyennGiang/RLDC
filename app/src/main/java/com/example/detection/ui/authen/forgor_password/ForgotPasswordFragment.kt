package com.example.detection.ui.authen.forgor_password

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.detection.bases.ViewBindingFragment
import com.example.detection.databinding.FragmentForgotPasswordBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ForgotPasswordFragment : ViewBindingFragment<FragmentForgotPasswordBinding>() {
    override fun getViewBinding(
        inflater: LayoutInflater,
        viewGroup: ViewGroup
    ): FragmentForgotPasswordBinding {
        return FragmentForgotPasswordBinding.inflate(inflater)
    }

    override fun initViewOnViewCreated(view: View, savedInstanceState: Bundle?) {
        binding?.apply {
            btnBack.setOnClickListener { backToPreviousScreen() }
        }
    }
}