package com.dimensiva.musicapp.fragments

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.dimensiva.musicapp.R
import com.dimensiva.musicapp.databinding.FragmentEditProfileBinding
import com.dimensiva.musicapp.entity.UserAccount
import com.dimensiva.musicapp.utils.value
import com.dimensiva.musicapp.viewmodels.UserViewModel

class EditProfile : Fragment() {

    private lateinit var binding: FragmentEditProfileBinding
    private lateinit var userViewModel: UserViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentEditProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        // Get a new or existing ViewModel from the ViewModelProvider.
        userViewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        userViewModel.allUsers.observe(viewLifecycleOwner, { users ->
            var fullSize = users.size

            if (fullSize > 0) {

                binding.txtName.value = users[fullSize - 1].nickName
                binding.txtFullName.value = users[fullSize - 1].nombreYapellido
                binding.txtDescription.value = users[fullSize - 1].descripcion

            }

        })

        binding.profileImageEdit.setOnClickListener {
            Toast.makeText(activity, "Seleccione una imagen de perfil", Toast.LENGTH_SHORT).show()
            val galleryIntent = Intent(Intent.ACTION_PICK)
            galleryIntent.type = "image/*"
            startActivityForResult(galleryIntent, 1)
        }

        binding.btnSaveChanges.setOnClickListener {
            var saveNewProfile = UserAccount(
                0,
                binding.txtName.value,
                binding.txtFullName.value,
                binding.txtDescription.value,
                R.id.galleryIcon
            )

            userViewModel.insert(saveNewProfile)

            Toast.makeText(
                activity,
                "Datos guardados",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    companion object {
        fun newInstance(): EditProfile {
            return EditProfile()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (data != null) {
            var imageUri: Uri? = data?.data
            binding.profileImageEdit.setImageURI(imageUri)
        }
    }
}