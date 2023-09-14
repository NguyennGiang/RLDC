package com.example.detection.ui.authen.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.detection.R
import com.example.detection.utils.gone
import com.example.detection.bases.ViewBindingFragment
import com.example.detection.databinding.FragmentLoginBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : ViewBindingFragment<FragmentLoginBinding>() {
    private val viewModel: LoginVM by viewModels()
    override fun getViewBinding(
        inflater: LayoutInflater,
        viewGroup: ViewGroup
    ): FragmentLoginBinding = FragmentLoginBinding.inflate(inflater)

    override fun initViewOnViewCreated(view: View, savedInstanceState: Bundle?) {
        binding?.apply {
            btnRegister.setOnClickListener { openFragment(R.id.registerFragment) }
            btnForgotPassword.setOnClickListener { openFragment(R.id.forgotPasswordFragment) }
            btnLogin.setOnClickListener {
                val email = binding.edtEmail.text.toString().trim()
                val password = binding.edtPassword.text.toString().trim()
                viewModel.setUserEvent(LoginVM.Event.Login(email, password))
            }
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

    override fun observeViewModelEvent() {
        viewLifecycleOwner.lifecycleScope.launchWhenResumed {
            viewModel.state.collect {
                if (it.loading){
                    showLoading()
                }
                else {
                    hideLoading()
                }
            }
        }

        viewLifecycleOwner.lifecycleScope.launchWhenResumed {
            viewModel.effect.collect {
                if (it is LoginVM.Effect.GoToHomePage){
                    openFragment(R.id.homeFragment)
                }
                else if (it is LoginVM.Effect.ShowMessage) {
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_LONG).show()
                }
            }
        }
    }
}