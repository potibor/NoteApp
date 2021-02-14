package com.task.noteapp.ui.addnote

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.task.noteapp.R
import com.task.noteapp.databinding.FragmentAddNoteBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddNoteFragment : Fragment() {

    private val viewModel by viewModels<AddNoteViewModel>()
    private lateinit var binding: FragmentAddNoteBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_add_note, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentAddNoteBinding.bind(view)
        binding.viewModel = viewModel

        binding.addNoteImage.setOnClickListener {
            onImageButtonClicked(it)
        }
        observeSuccess()
        observeFailure()
    }

    private fun observeSuccess() {
        viewModel.successLiveData.observe(viewLifecycleOwner) { event ->
            event.getContentIfNotHandled()?.let {
                if (it) findNavController().popBackStack()
            }
        }
    }

    private fun observeFailure() {
        viewModel.errorFailureLiveData.observe(viewLifecycleOwner) { event ->
            event.getContentIfNotHandled()?.let {
                if (it) Toast.makeText(
                    requireContext(), "Check for inputs",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }

    private fun onImageButtonClicked(view: View) {
        startActivityForResult(
            Intent(
                Intent.ACTION_PICK,
                MediaStore.Images.Media.INTERNAL_CONTENT_URI
            ), PICK_IMAGE
        )
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == PICK_IMAGE) {
            data?.let {
                setNoteImage(it.data)
            }
        }
    }

    private fun setNoteImage(uri: Uri?) {
        Glide.with(this).load(uri).into(binding.addNoteImage)
        viewModel.imageUrl.value = uri.toString()
    }

    companion object {
        private const val PICK_IMAGE = 1
    }

}