package com.sohainfotech.spendtrackerunittestdemo

//step5: remove below import
//import org.junit.Assert.*
import com.google.common.truth.Truth.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

//step2:
@RunWith(JUnit4::class)
class ValidatorTest{

    //step3:
    @Test
    fun whenInputIsValid(){
        val amount = 10
        val desc = "some string for junit test"
        val result = Validator.validateInput(amount, desc)
        //step6:
        assertThat(result).isEqualTo(true)
    }

    //step8:
    @Test
    fun whenInputIsInvalid(){
        val amount = 0
        val desc = ""
        val result = Validator.validateInput(amount, desc)
        assertThat(result).isEqualTo(false)
    }

}