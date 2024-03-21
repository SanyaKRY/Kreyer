package com.tinkoff.education.fintechrecyclerview.itemtouchhelper

import androidx.lifecycle.ViewModel
import com.tinkoff.education.fintechrecyclerview.model.ExpenseModel
import com.tinkoff.education.fintechrecyclerview.stubExpenseList

/**
 * @author by y.gladkikh
 */
class TouchHelperViewModel : ViewModel() {

    val list: MutableList<ExpenseModel> = stubExpenseList
}
