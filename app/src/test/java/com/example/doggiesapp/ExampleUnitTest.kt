package com.example.doggiesapp

import com.example.model.Calculator
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {

    private lateinit var calculator: Calculator

    @Before
    fun setUp() {
         calculator = Calculator()
    }

    @Test
    fun addition_isCorrect() {
        //given
        val num  = 10
        val num2 = 20
        //when
        val result = calculator.sum(num, num2)
        //then
         assertEquals(30, result )
    }

    @Test
    fun rest_usCorrect() {
        val num = 30
        val num2 = 20
        val result = calculator.rest(num, num2)
        assertEquals(10, result)
    }





}