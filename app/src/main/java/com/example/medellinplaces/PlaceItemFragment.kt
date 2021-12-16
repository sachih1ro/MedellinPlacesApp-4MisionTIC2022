package com.example.medellinplaces

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.medellinplaces.databinding.FragmentPlaceItemBinding
import com.example.medellinplaces.viewModel.PlaceViewModel

class PlaceItemFragment : Fragment() {

    var navController: NavController? = null
    private var _binding: FragmentPlaceItemBinding? = null
    private val binding get() = _binding!!
    val placeViewModel: PlaceViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPlaceItemBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        /*
        placeViewModel.placeModelInItemFrag.observe(this, Observer {
            binding.textViewPlaceNameItem.text = it.placeName
            Log.d("cambioID","el nombre actual es "+it.placeName)
            binding.textViewPlaceDescriptionItem.text = it.placeDescription
            binding.textViewScoreValueItem.text = it.placeScore

            val idImageView = resources
                .getIdentifier(it.imageName,"drawable", context!!.packageName)
            binding.imageViewPlaceImageItem.setImageResource(idImageView)
        })

         */

        binding.textViewPlaceNameItem.text = PlaceViewModel().
        getPlaceAtPosition(PlaceViewModel().obtenerPlaceid()).placeName
        binding.textViewPlaceDescriptionItem.text = PlaceViewModel().
        getPlaceAtPosition(PlaceViewModel().obtenerPlaceid()).placeDescription
        binding.textViewScoreValueItem.text = PlaceViewModel().
        getPlaceAtPosition(PlaceViewModel().obtenerPlaceid()).placeScore

        val idImageView = resources
            .getIdentifier(PlaceViewModel().getPlaceAtPosition(PlaceViewModel().
            obtenerPlaceid()).imageName,"drawable", context!!.packageName)
        binding.imageViewPlaceImageItem.setImageResource(idImageView)


        navController = Navigation.findNavController(view)
        binding.buttonMenuImageButtonItem.setOnClickListener {
            navController!!.navigate(R.id.action_placeItemFragment_to_settingsFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}