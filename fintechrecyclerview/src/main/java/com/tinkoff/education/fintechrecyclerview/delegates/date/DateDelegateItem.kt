package com.tinkoff.education.fintechrecyclerview.delegates.date

import com.tinkoff.education.fintechrecyclerview.delegates.utils.DelegateItem

/**
 * @author y.gladkikh
 */
class DateDelegateItem(
    val id: Int,
    private val value: DateModel,
) : DelegateItem {
    override fun content(): Any = value

    override fun id(): Int = id

    override fun compareToOther(other: DelegateItem): Boolean {
        return (other as DateDelegateItem).value == content()
    }
}
