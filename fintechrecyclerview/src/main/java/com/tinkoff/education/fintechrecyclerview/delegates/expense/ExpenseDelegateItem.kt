package com.tinkoff.education.fintechrecyclerview.delegates.expense

import com.tinkoff.education.fintechrecyclerview.delegates.utils.DelegateItem

/**
 * @author y.gladkikh
 */
class ExpenseDelegateItem(
    val id: Int,
    private val value: ExpenseModel,
) : DelegateItem {
    override fun content(): Any = value

    override fun id(): Int = id

    override fun compareToOther(other: DelegateItem): Boolean {
        return (other as ExpenseDelegateItem).value == content()
    }
}
