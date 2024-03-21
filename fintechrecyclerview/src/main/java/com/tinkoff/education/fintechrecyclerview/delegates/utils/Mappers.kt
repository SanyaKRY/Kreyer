package com.tinkoff.education.fintechrecyclerview.delegates.utils

import com.tinkoff.education.fintechrecyclerview.delegates.date.DateDelegateItem
import com.tinkoff.education.fintechrecyclerview.delegates.date.DateModel
import com.tinkoff.education.fintechrecyclerview.delegates.expense.ExpenseDelegateItem
import com.tinkoff.education.fintechrecyclerview.delegates.expense.ExpenseModel

/**
 * Created by y.gladkikh
 */

fun List<ExpenseModel>.concatenateWithDate(dates: List<DateModel>): List<DelegateItem> {
    val delegateItemList: MutableList<DelegateItem> = mutableListOf()

    dates.forEach { dateModel ->

        // Сразу добавляем в список дату
        delegateItemList.add(
            DateDelegateItem(id = dateModel.id, value = dateModel)
        )

        val date = dateModel.date
        // Выбираем покупки по дате
        val allDayExpenses = this.filter { expense ->
            expense.date == date
        }
        // Добавляем эти покупки
        allDayExpenses.forEach { model ->
            delegateItemList.add(
                ExpenseDelegateItem(
                    id = model.id,
                    value = model,
                )
            )
        }
    }
    return delegateItemList
}
