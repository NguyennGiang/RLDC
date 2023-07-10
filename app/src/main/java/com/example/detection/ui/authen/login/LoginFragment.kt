package com.example.detection.ui.authen.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doAfterTextChanged
import com.example.detection.R
import com.example.detection.Utils.gone
import com.example.detection.bases.ViewBindingFragment
import com.example.detection.databinding.FragmentLoginBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : ViewBindingFragment<FragmentLoginBinding>() {
    override fun getViewBinding(
        inflater: LayoutInflater,
        viewGroup: ViewGroup
    ): FragmentLoginBinding = FragmentLoginBinding.inflate(inflater)

    override fun initViewOnViewCreated(view: View, savedInstanceState: Bundle?) {
        binding?.apply {
            btnRegister.setOnClickListener { openFragment(R.id.registerFragment) }
            btnForgotPassword.setOnClickListener { openFragment(R.id.forgotPasswordFragment) }
            btnLogin.setOnClickListener { goToHome() }
            edtEmail.doAfterTextChanged {
                tvErrorEmail.gone()
                binding.tipEmail.error = ""
            }
            edtPassword.doAfterTextChanged {
                tvErrorPassword.gone()
                binding.tipPassword.error = ""
            }
        }
    }

    private fun goToHome() {
        openFragment(R.id.homeFragment)
    }
}