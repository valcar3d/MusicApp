package com.dimensiva.musicapp.database

import android.content.Context
import android.widget.ImageView
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.dimensiva.musicapp.R
import com.dimensiva.musicapp.database.dao.UserAccountDao
import com.dimensiva.musicapp.entity.UserAccount

@Database(entities = [UserAccount::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun userAccountDao(): UserAccountDao?

    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(
            context: Context
        ): AppDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "users_database"
                )
                    // Wipes and rebuilds instead of migrating if no Migration object.
                    // Migration is not part of this codelab.
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                // return instance
                instance
            }
        }

        fun populateDatabase(userDao: UserAccountDao) {
            // Start the app with a clean database every time.
            // Not needed if you only populate on creation.
            var user = UserAccount(
                0, "valcar", "angel pat oribe",
                "programador android", R.drawable.image
            )
            userDao.insert(user)

        }
    }

}