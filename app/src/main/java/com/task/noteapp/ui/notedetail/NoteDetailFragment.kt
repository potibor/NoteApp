package com.task.noteapp.ui.notedetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.task.noteapp.R
import com.task.noteapp.databinding.FragmentNoteDetailBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NoteDetailFragment : Fragment() {

    companion object {
        fun newInstance() = NoteDetailFragment()
    }

    private val viewModel by viewModels<NoteDetailViewModel>()
    private lateinit var binding: FragmentNoteDetailBinding
    private val args by navArgs<NoteDetailFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_note_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentNoteDetailBinding.bind(view)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        viewModel.getNoteDetail(args.argModelId)
        observeNavigation()
    }

    private fun observeNavigation() {
        viewModel.navigateBackLiveData.observe(viewLifecycleOwner) { event ->
            event.getContentIfNotHandled()?.let {
                if (it) findNavController().popBackStack()
            }
        }
    }

}