package com.dimensiva.musicapp.views

import android.os.Bundle
import android.view.Gravity
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.dimensiva.musicapp.R
import com.dimensiva.musicapp.databinding.ActivityMainBinding
import com.dimensiva.musicapp.entity.UserAccount
import com.dimensiva.musicapp.fragments.EditProfile
import com.dimensiva.musicapp.fragments.GalleryFragment
import com.dimensiva.musicapp.fragments.MusicFragment
import com.dimensiva.musicapp.viewmodels.UserViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView


class MainActivity : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener {

    private lateinit var binding: ActivityMainBinding
    private lateinit var toggle: ActionBarDrawerToggle
    private lateinit var userViewModel: UserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        toggle = ActionBarDrawerToggle(this, binding.drawerLayout, R.string.open, R.string.close)
        binding.drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        // Get a new or existing ViewModel from the ViewModelProvider.
        userViewModel = ViewModelProvider(this).get(UserViewModel::class.java)



        userViewModel.insert(
            UserAccount(
                1,
                "valcar3d",
                "angel pat",
                "android programmer",
                R.id.musicIcon
            )
        )

        var menu: Menu = binding.navView.menu
        var navName: MenuItem = menu.findItem(R.id.navName)
        var navFullName: MenuItem = menu.findItem(R.id.navFullName)
        var navDescription: MenuItem = menu.findItem(R.id.navDescription)


        userViewModel.allUsers.observe(
            this, { users ->
                var fullSize = users.size

                if (fullSize > 0) {
                    println("Users = ${users.toString()}")
                    navName.title = users[fullSize - 1].nickName
                    navFullName.title = users[fullSize - 1].nombreYapellido
                    navDescription.title = users[fullSize - 1].descripcion
                }

            }
        )

        supportActionBar?.setDisplayHomeAsUpEnabled(true)


        binding.navView.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.editProfile -> editProfile()
            }


            true
        }

        binding.bottomNavigation.setOnNavigationItemSelectedListener(this)
        binding.bottomNavigation.selectedItemId = R.id.musicIcon

    }

    private fun editProfile() {
        Toast.makeText(this, "Editar perfil", Toast.LENGTH_SHORT).show()
        binding.drawerLayout.closeDrawer(Gravity.LEFT)
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, EditProfile.newInstance()).commit()

    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {

            R.id.musicIcon -> {

                supportFragmentManager.beginTransaction()
                    .replace(R.id.container, MusicFragment.newInstance()).commit()
                return true
            }

            R.id.galleryIcon -> {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.container, GalleryFragment.newInstance()).commit()


                return true
            }

        }
        return false
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (toggle.onOptionsItemSelected(item)) {
            return true
        }
        return super.onOptionsItemSelected(item)
    }


}

