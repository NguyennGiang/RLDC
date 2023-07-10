package com.example.detection.ui.authen.intro

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.navigation.Navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
import com.example.detection.R
import com.example.detection.Utils.SharedPreferencesManager
import com.example.detection.Utils.gone
import com.example.detection.Utils.visible
import com.example.detection.bases.ViewBindingFragment
import com.example.detection.databinding.FragmentIntroBinding
import com.example.detection.databinding.ItemIntroBinding
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class IntroFragment: ViewBindingFragment<FragmentIntroBinding>() {

    @Inject
    lateinit var sharedPreferencesManager: SharedPreferencesManager

    override fun getViewBinding(
        inflater: LayoutInflater,
        viewGroup: ViewGroup
    ): FragmentIntroBinding {
        return FragmentIntroBinding.inflate(layoutInflater, viewGroup, false)
    }

    override fun initViewOnViewCreated(view: View, savedInstanceState: Bundle?){
        binding.viewPager.adapter = IntroAdapter()
        TabLayoutMediator(binding.tabLayout, binding.viewPager){_, _, ->}.attach()
        binding.viewPager.registerOnPageChangeCallback(object: OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                if (position == 2){
                    binding.btnContinue.text = "Start"
                    binding.btnSkip.gone()
                }
                else {
                    binding.btnContinue.text = "Continue"
                    binding.btnSkip.visible()
                }
            }
        })

        binding.btnSkip.setOnClickListener{
            goToLogin()
        }

        binding.btnContinue.setOnClickListener {
            if (binding.viewPager.currentItem != 2){
                binding.viewPager.currentItem += 1
            }
            else {
                goToLogin()
            }
        }
    }

    private fun goToLogin(){
        try {
            sharedPreferencesManager.showIntroBefore = true
            findNavController(binding.root).navigate(R.id.action_introFragment_to_navigation_authentication)
        } catch (e: Exception){
            e.printStackTrace()
        }
    }
}

class IntroAdapter : RecyclerView.Adapter<IntroAdapter.Holder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        return  Holder(ItemIntroBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(position)
    }

    override fun getItemCount(): Int = 3


    class Holder(private val binding: ItemIntroBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(position: Int){
            val context = binding.root.context
            val resource = context.resources
            when(position){
                0 -> {
                    binding.ivIntro.setImageDrawable(ResourcesCompat.getDrawable(resource, R.drawable.img_intro, null))
                    binding.tvTitleIntro.text = "Page 1"
                    binding.tvSubTitleIntro.text = "Scale star select list star device move vector create thumbnail. List scrolling bold ellipse asset pixel pixel horizontal."
                }
                1 -> {
                    binding.ivIntro.setImageDrawable(ResourcesCompat.getDrawable(resource, R.drawable.img_intro, null))
                    binding.tvTitleIntro.text = "Page 2"
                    binding.tvSubTitleIntro.text = "Scale star select list star device move vector create thumbnail. List scrolling bold ellipse asset pixel pixel horizontal."
                }
                2 -> {
                    binding.ivIntro.setImageDrawable(ResourcesCompat.getDrawable(resource, R.drawable.img_intro, null))
                    binding.tvTitleIntro.text = "Page 2"
                    binding.tvSubTitleIntro.text = "Scale star select list star device move vector create thumbnail. List scrolling bold ellipse asset pixel pixel horizontal."
                }
            }
        }
    }
}
