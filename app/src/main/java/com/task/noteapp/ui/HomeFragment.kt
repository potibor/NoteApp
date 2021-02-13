package com.task.noteapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.task.noteapp.NavGraphDirections
import com.task.noteapp.R
import com.task.noteapp.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private lateinit var viewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        val binding = FragmentHomeBinding.bind(view)
        binding.viewModel = viewModel

        observeNavigation()
    }

    private fun observeNavigation() {
        viewModel.navigation.observe(viewLifecycleOwner) { event ->
            event.getContentIfNotHandled()?.let {
                if (it) findNavController().navigate(NavGraphDirections.toAddNoteFragment())
            }
        }
    }
}