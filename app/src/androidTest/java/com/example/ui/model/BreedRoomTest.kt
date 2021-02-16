package com.example.ui.model

import android.content.Context
import androidx.annotation.VisibleForTesting
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.example.model.local.DoggiesDatabase
import com.example.model.local.dao.BreedDao
import com.example.model.local.entities.Breed

import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit
import java.util.concurrent.TimeoutException

class BreedRoomTest {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var breedDao: BreedDao
    private lateinit var db: DoggiesDatabase

    @Before
    fun setUp() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(context, DoggiesDatabase::class.java).build()
        breedDao = db.breedDao()
    }

    @After
    fun tearDown() {
        db.close()
    }


    @Test
    fun insertBreed_happy_case() = runBlocking {
        //Given
        val breedList = listOf<Breed>(Breed("breed1"))
        //When
        breedDao.insertAllBreedList(breedList)
        //then A
        val it = breedDao.getAllBreedList().getOrAwaitValue()
        assertThat(it).isNotNull()
        assertThat(it).isNotEmpty()
        assertThat(it).hasSize(1)

        // Then b
        breedDao.getAllBreedList().observeForever {
            assertThat(it).isNotEmpty()
        //    assertThat(it).isEmpty()
        }
    }


    @Test
    fun insert_empty_list() = runBlocking {
        //given
        val breedList = listOf<Breed>()
        //when
        breedDao.insertAllBreedList(breedList)
        //Then
        val it = breedDao.getAllBreedList().getOrAwaitValue()
        assertThat(it).isNotNull()
        assertThat(it).isEmpty()

    }



    @VisibleForTesting(otherwise = VisibleForTesting.NONE)
    fun <T> LiveData<T>.getOrAwaitValue(time: Long = 2, timeUnit: TimeUnit = TimeUnit.SECONDS,
                                        afterObserver: () -> Unit = {}): T {

        var data: T? = null
        val latch = CountDownLatch(1)
        val observer = object : Observer<T> {
            override fun onChanged(t: T) {
                data = t
                latch.countDown()
                this@getOrAwaitValue.removeObserver(this)
            }
        }
        this.observeForever(observer)

        try {
            afterObserver.invoke()
            if (!latch.await(time, timeUnit)) {
                throw TimeoutException("This livedata value was never set")
            }
        } finally {
            this.removeObserver(observer)
        }
        @Suppress("UNCHECKED_CAST")
        return data as T
    }




}