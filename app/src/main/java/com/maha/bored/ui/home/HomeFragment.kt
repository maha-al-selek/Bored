package com.maha.bored.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.maha.bored.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val homeViewModel by viewModels<HomeViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        homeViewModel.getVolumesResponseLiveData().observe(viewLifecycleOwner){
            if (it != null) {
                binding.apply {
                    cardView.visibility = View.VISIBLE
                    binding.textViewActivity.text = it.activity
                    binding.textViewType.text = it.type
                }
            }
            else
                binding.cardView.visibility = View.GONE
        }

        binding.buttonBored.setOnClickListener { homeViewModel.getActivities() }

        return binding.root
    }

}