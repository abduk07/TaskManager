package com.example.taskmanager.ui.profile

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import com.example.taskmanager.data.local.Pref
import com.example.taskmanager.databinding.FragmentProfileBinding
import com.example.taskmanager.utils.loadImage

class ProfileFragment : Fragment() {
    private lateinit var binding: FragmentProfileBinding
    private lateinit var pref: Pref

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        pref = Pref(requireContext())
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
                    ivPerson.loadImage(photoUri)
                }
            }
            ivPerson.loadImage(pref.getPhoto())
            ivPerson.setOnClickListener {
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
                pref.saveName(name)
            }
            binding.etName.setText(pref.getName())
        }
    }
}