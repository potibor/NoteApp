package com.task.noteapp.ui.notehome

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.task.noteapp.NavGraphDirections
import com.task.noteapp.R
import com.task.noteapp.data.model.NoteModel
import com.task.noteapp.databinding.FragmentNoteHomeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment(), HomeClickListener {

    private lateinit var homeAdapter: HomeAdapter

    private val viewModel by viewModels<HomeViewModel>()
    private lateinit var binding: FragmentNoteHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_note_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentNoteHomeBinding.bind(view)
        binding.viewModel = viewModel
        homeAdapter = HomeAdapter(this)
        binding.adapter = homeAdapter
        viewModel.fetchNotes()

        observeNavigation()
        observeList()
        observeFailure()
    }

    private fun observeList() {
        viewModel.noteListLiveData.observe(viewLifecycleOwner) { event ->
            event.getContentIfNotHandled()?.let {
                homeAdapter.submitList(it)
            }
        }
    }

    private fun observeNavigation() {
        viewModel.navigation.observe(viewLifecycleOwner) { event ->
            event.getContentIfNotHandled()?.let {
                if (it) findNavController().navigate(NavGraphDirections.toAddNoteFragment())
            }
        }
    }

    private fun observeFailure() {
        viewModel.errorFailureLiveData.observe(viewLifecycleOwner) { event ->
            event.getContentIfNotHandled()?.let {
                if (it) Toast.makeText(
                    requireContext(), "Something went wrong",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }

    override fun noteItemClicked(model: NoteModel) {
        findNavController().navigate(NavGraphDirections.toNoteDetailFragment(model.id))
    }
}