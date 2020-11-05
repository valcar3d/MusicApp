package com.dimensiva.musicapp.views

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.dimensiva.musicapp.R
import com.dimensiva.musicapp.databinding.ActivityMainBinding
import com.dimensiva.musicapp.fragments.GalleryFragment
import com.dimensiva.musicapp.fragments.MusicFragment
import com.google.android.material.bottomnavigation.BottomNavigationView


class MainActivity : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        binding.bottomNavigation.setOnNavigationItemSelectedListener(this)
        binding.bottomNavigation.selectedItemId = R.id.musicIcon

    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {

            R.id.musicIcon -> {

                /*   if (!MusicFragment.newInstance().isAdded) {
                       supportFragmentManager.beginTransaction().apply {
                           setCustomAnimations(
                               R.anim.fade_in,
                               R.anim.fade_out
                           )
                           add(R.id.container, MusicFragment.newInstance())
                           hide(GalleryFragment.newInstance())
                           show(MusicFragment.newInstance())
                       }.commit()
                   } else {


                       supportFragmentManager.beginTransaction()
                           .hide(GalleryFragment.newInstance())
                           .show(MusicFragment.newInstance())
                           .commit()
                   }*/
                supportFragmentManager.beginTransaction()
                    .replace(R.id.container, MusicFragment.newInstance()).commit()
                return true
            }

            R.id.galleryIcon -> {

                /*  if (!GalleryFragment.newInstance().isAdded) {
                      supportFragmentManager.beginTransaction().apply {
                          setCustomAnimations(
                              R.anim.fade_in,
                              R.anim.fade_out
                          )
                          add(R.id.container, GalleryFragment.newInstance())
                          hide(MusicFragment.newInstance())
                          show(GalleryFragment.newInstance())
                      }.commit()
                  } else {
                      supportFragmentManager.beginTransaction()
                          .hide(MusicFragment.newInstance())
                          .show(GalleryFragment.newInstance())
                          .commit()
                  }*/
                supportFragmentManager.beginTransaction()
                    .replace(R.id.container, GalleryFragment.newInstance()).commit()


                return true
            }

        }
        return false
    }
}

