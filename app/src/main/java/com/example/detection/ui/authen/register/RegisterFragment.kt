package com.example.detection.ui.authen.register

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.detection.R
import com.example.detection.utils.ValidateUtils
import com.example.detection.utils.gone
import com.example.detection.utils.visible
import com.example.detection.bases.ViewBindingFragment
import com.example.detection.databinding.FragmentRegisterBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegisterFragment : ViewBindingFragment<FragmentRegisterBinding>() {
    private val viewModel: RegisterFragmentVM by viewModels()
    override fun getViewBinding(
        inflater: LayoutInflater,
        viewGroup: ViewGroup
    ): FragmentRegisterBinding {
        return FragmentRegisterBinding.inflate(inflater)
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
            viewModel.effect.collect{
                if(it is RegisterFragmentVM.Effect.RegisterSuccess){
                    openFragment(R.id.homeFragment)
                }
                if (it is RegisterFragmentVM.Effect.ShowMessage){
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_LONG).show()
                }
            }
        }
    }
    override fun initViewOnViewCreated(view: View, savedInstanceState: Bundle?) {
        binding?.apply {
            btnBack.setOnClickListener {
                backToPreviousScreen()
            }
            btnBackToLogin.setOnClickListener { backToPreviousScreen() }
            btnPolicy.setOnClickListener { }
            btnRegister.setOnClickListener { onClickContinue() }

            edtEmail.doAfterTextChanged {
                tvErrorEmail.gone()
                binding.tipEmail.error = ""
            }
            edtPassword.doAfterTextChanged {
                tvErrorPassword.gone()
                binding.tipPassword.error = ""
            }
            edtRePassword.doAfterTextChanged {
                tvErrorRePassword.gone()
                binding.tipRePassword.error = ""
            }
        }

    }

    private fun onClickContinue() {
        val email = binding.edtEmail.text.toString()
        val password = binding.edtPassword.text.toString()
        val rePassword = binding.edtRePassword.text.toString()
        if (email.isBlank()) {
            binding.tvErrorEmail.visible()
            binding.tvErrorEmail.text = getString(R.string.enter_email_please)
            binding.tipEmail.error = getString(R.string.enter_email_please)
            return
        }
        if (!ValidateUtils.validateEmail(email = email)) {
            binding.tvErrorEmail.visible()
            binding.tvErrorEmail.text = getString(R.string.invalid_email)
            binding.tipEmail.error = getString(R.string.invalid_email)
            return
        }
        if (password.isBlank()) {
            binding.tvErrorPassword.visible()
            binding.tvErrorPassword.text = getString(R.string.enter_password_please)
            binding.tipPassword.error = getString(R.string.enter_password_please)
            return
        }

        if (rePassword.isBlank()) {
            binding.tvErrorRePassword.visible()
            binding.tvErrorRePassword.text = getString(R.string.enter_password_please)
            binding.tipRePassword.error = getString(R.string.enter_password_please)
            return
        }
        if (rePassword != password) {
            binding.tvErrorRePassword.visible()
            binding.tvErrorRePassword.text = getString(R.string.error_re_password_not_match)
            binding.tipRePassword.error = getString(R.string.error_re_password_not_match)

            binding.tvErrorPassword.visible()
            binding.tvErrorPassword.text = getString(R.string.error_re_password_not_match)
            binding.tipPassword.error = getString(R.string.error_re_password_not_match)
            return
        }
        viewModel.setUserEvent(RegisterFragmentVM.Event.RegisterEvent(email, password))
    }

    companion object {
        const val EMAIL_FIELD = "email"
    }
}