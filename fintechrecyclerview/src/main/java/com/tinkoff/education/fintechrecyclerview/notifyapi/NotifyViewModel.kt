package com.tinkoff.education.fintechrecyclerview.notifyapi

import androidx.lifecycle.ViewModel
import com.tinkoff.education.fintechrecyclerview.model.ExpenseModel
import com.tinkoff.education.fintechrecyclerview.stubExpenseList

/**
 * @author by y.gladkikh
 */
class NotifyViewModel : ViewModel() {

    val list: MutableList<ExpenseModel> = stubExpenseList
}
