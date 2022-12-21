package com.example.mvvm_livedata_room

import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.example.mvvm_livedata_room.database.MovieDAO
import com.example.mvvm_livedata_room.database.MovieDatabase
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException
@RunWith(AndroidJUnit4::class)
class MovieDBTest {

        private lateinit var MovieDao: MovieDAO
        private lateinit var db: MovieDatabase

        @Before
        fun createDb() {
            val context = InstrumentationRegistry.getInstrumentation().targetContext
            // Using an in-memory database because the information stored here disappears when the
            // process is killed.
            db = Room.inMemoryDatabaseBuilder(context, MovieDatabase::class.java)
                // Allowing main thread queries, just for testing.
                .allowMainThreadQueries()
                .build()
            MovieDao = db.MovieDao()
        }

        @After
        @Throws(IOException::class)
        fun closeDb() {
            db.close()
        }

        @Test
        @Throws(Exception::class)
        fun insertAndGetNight() {
//           val Movie = Movie( "Agavaceae", "Agave")
//           MovieDao.ins
//            val tonight = MovieDao.get
//            assertEquals(tonight?.sleepQuality, -1)
        }
}