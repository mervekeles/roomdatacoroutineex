package com.example.mvvm_livedata_room

import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.example.mvvm_livedata_room.database.Plant
import com.example.mvvm_livedata_room.database.PlantDAO
import com.example.mvvm_livedata_room.database.PlantDatabase
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException
@RunWith(AndroidJUnit4::class)
class PlantDBTest {

        private lateinit var plantDao: PlantDAO
        private lateinit var db: PlantDatabase

        @Before
        fun createDb() {
            val context = InstrumentationRegistry.getInstrumentation().targetContext
            // Using an in-memory database because the information stored here disappears when the
            // process is killed.
            db = Room.inMemoryDatabaseBuilder(context, PlantDatabase::class.java)
                // Allowing main thread queries, just for testing.
                .allowMainThreadQueries()
                .build()
            plantDao = db.plantDao()
        }

        @After
        @Throws(IOException::class)
        fun closeDb() {
            db.close()
        }

        @Test
        @Throws(Exception::class)
        fun insertAndGetNight() {
//           val plant = Plant( "Agavaceae", "Agave")
//           plantDao.ins
//            val tonight = plantDao.get
//            assertEquals(tonight?.sleepQuality, -1)
        }
}