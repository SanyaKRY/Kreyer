package com.tinkoff.education.fintechrecyclerview.itemtouchhelper.adapters

/**
 * @author y.gladkikh
 */
interface ItemTouchHelperAdapter {

    fun onItemMove(fromPosition: Int, toPosition: Int)

    fun onItemDismiss(position: Int)
}
