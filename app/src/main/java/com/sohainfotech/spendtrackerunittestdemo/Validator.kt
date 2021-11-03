package com.sohainfotech.spendtrackerunittestdemo

//step1:
object Validator {

    fun validateInput(amount: Int, desc: String): Boolean {
        //step7: implement the function
        return !(amount <= 0 || desc.isEmpty())
    }
}