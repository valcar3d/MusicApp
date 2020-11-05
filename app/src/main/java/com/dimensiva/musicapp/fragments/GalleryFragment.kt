package com.dimensiva.musicapp.fragments

import android.content.Intent
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.dimensiva.musicapp.R
import com.dimensiva.musicapp.databinding.FragmentGalleryBinding


class GalleryFragment : Fragment() {
    private lateinit var binding: FragmentGalleryBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val i = Intent(Intent.ACTION_VIEW, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        startActivityForResult(i, 1)
        // Inflate the layout for this fragment
        binding = FragmentGalleryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {


        super.onViewCreated(view, savedInstanceState)
    }

    companion object {
        fun newInstance(): GalleryFragment {
            return GalleryFragment()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        activity?.supportFragmentManager?.beginTransaction()
            ?.replace(R.id.container, MusicFragment.newInstance())?.commit()



        super.onActivityResult(requestCode, resultCode, data)
    }
}