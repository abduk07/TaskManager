package com.example.taskmanager.ui.profileredactor

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.taskmanager.R
import com.example.taskmanager.data.local.Pref
import com.example.taskmanager.databinding.FragmentProfileRedactorBinding
import com.example.taskmanager.model.InfoProfile
import com.example.taskmanager.utils.loadImage

class ProfileRedactorFragment : Fragment() {
    private lateinit var binding: FragmentProfileRedactorBinding
    private lateinit var pref: Pref

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProfileRedactorBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        pref = Pref(requireContext())
        binding.etName.setText(pref.getName())
        saveImage()
        saveClick()
    }

    private fun saveImage() {

        with(binding) {
            val launcher = registerForActivityResult(
                ActivityResultContracts.StartActivityForResult()
            ) { result: ActivityResult ->
                if (result.resultCode == Activity.RESULT_OK
                    && result.data != null
                ) {
                    val photoUri = result.data?.data.toString()
                    pref.savePhoto(photoUri)
                    ivSetImage.loadImage(photoUri)
                }
            }

            ivSetImage.loadImage(pref.getPhoto())
            btnChooseImage.setOnClickListener {
                val intent = Intent()
                intent.type = "image/*"
                intent.action = Intent.ACTION_GET_CONTENT
                launcher.launch(intent)
            }
        }
    }

    private fun saveClick() {
        with(binding) {
            btnSave.setOnClickListener {
                val name = etName.text.toString()
                if (name.isBlank()) {
                    Toast.makeText(requireContext(), "Fill the fields!", Toast.LENGTH_SHORT).show()
                } else {
                    pref.saveName(name)
                    findNavController().navigate(R.id.navigation_profile)
                }
            }
        }
    }

}

