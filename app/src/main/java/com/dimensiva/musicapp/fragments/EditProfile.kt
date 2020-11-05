package com.dimensiva.musicapp.fragments

import android.content.Intent
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.dimensiva.musicapp.R
import com.dimensiva.musicapp.databinding.FragmentEditProfileBinding
import com.dimensiva.musicapp.databinding.FragmentMusicBinding

class EditProfile : Fragment() {

    private lateinit var binding: FragmentEditProfileBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        // Inflate the layout for this fragment
        binding = FragmentEditProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.profileImageEdit.setOnClickListener {
            Toast.makeText(activity, "Seleccione una imagen de perfil", Toast.LENGTH_SHORT).show()
            val i = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            startActivityForResult(i, 1)
        }
    }

    companion object {
        fun newInstance(): EditProfile {
            return EditProfile()
        }
    }
}