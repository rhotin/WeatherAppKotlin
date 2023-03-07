package com.example.weatherapp.ui

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.example.weatherapp.R
import com.example.weatherapp.databinding.FragmentMainBinding
import com.example.weatherapp.network.Resource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : Fragment(R.layout.fragment_main) {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var binding: FragmentMainBinding
    private val mainViewModel by viewModels<MainViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMainBinding.bind(view)

        mainViewModel.getWeatherByLocationRequest(null)
        mainViewModel.weatherResponse.observe(viewLifecycleOwner) {
            when (it) {
                is Resource.Failure -> {
                    Toast.makeText(
                        requireContext(),
                        "Something went wrong",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                is Resource.Loading -> {
                    Toast.makeText(
                        requireContext(),
                        "Loading",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                is Resource.Success -> {
                    binding.locationTV.text = it.value.title
                    Glide.with(requireContext())
                        .load(getString(R.string.baseUrl) + it.value.consolidated_weather.first().weather_state_abbr + ".png")
                        .placeholder(android.R.drawable.ic_menu_gallery)
                        .into(binding.weatherIconIV)

                    binding.weatherDegreeTV.text = buildString {
                        append(it.value.consolidated_weather.first().the_temp.toInt().toString())
                        append(resources.getString(R.string.degrees))
                    }
                    binding.weatherDescriptionTV.text =
                        it.value.consolidated_weather.first().weather_state_name
                    binding.lowWeatherTV.text = buildString {
                        append(resources.getString(R.string.l_degrees))
                        append(it.value.consolidated_weather.first().min_temp.toInt().toString())
                        append(resources.getString(R.string.degrees))
                    }
                    binding.highWeatherTV.text = buildString {
                        append(resources.getString(R.string.h_degrees))
                        append(it.value.consolidated_weather.first().max_temp.toInt().toString())
                        append(resources.getString(R.string.degrees))
                    }

                }
            }
        }
    }

}