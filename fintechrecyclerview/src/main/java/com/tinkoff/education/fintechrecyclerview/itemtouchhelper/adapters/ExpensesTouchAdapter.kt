package com.tinkoff.education.fintechrecyclerview.itemtouchhelper.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tinkoff.education.fintechrecyclerview.Categories
import com.tinkoff.education.fintechrecyclerview.R
import com.tinkoff.education.fintechrecyclerview.databinding.ExpenseItemBinding
import com.tinkoff.education.fintechrecyclerview.model.ExpenseModel
import java.util.*

/**
 * @author y.gladkikh
 */
class ExpensesTouchAdapter(private val expenses: MutableList<ExpenseModel>) :
    RecyclerView.Adapter<ExpensesTouchAdapter.ViewHolder>(), ItemTouchHelperAdapter {

    override fun onItemMove(fromPosition: Int, toPosition: Int) {
        if (fromPosition < toPosition) {
            for (i in fromPosition until toPosition) {
                Collections.swap(expenses, i, i + 1)
            }
        } else {
            for (i in fromPosition downTo toPosition + 1) {
                Collections.swap(expenses, i, i - 1)
            }
        }
        notifyItemMoved(fromPosition, toPosition)
    }

    override fun onItemDismiss(position: Int) {
        expenses.remove(expenses[position])
        notifyItemRemoved(position)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(ExpenseItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(expenses[position])

    override fun getItemCount(): Int = expenses.size

    class ViewHolder(_binding: ExpenseItemBinding) : RecyclerView.ViewHolder(_binding.root) {

        var binding: ExpenseItemBinding = ExpenseItemBinding.bind(itemView)

        fun bind(model: ExpenseModel) {
            with(binding) {
                setupUi(model)
            }
        }

        private fun ExpenseItemBinding.setupUi(model: ExpenseModel) {
            title.text = model.title
            subtitle.text = model.subtitle
            price.text = model.price.priceText()

            val imageRes = when (model.subtitle) {
                Categories.AVIA.title -> R.drawable.ic_avia
                Categories.CLOTHING.title -> R.drawable.ic_clothing
                Categories.EDUCATION.title -> R.drawable.ic_education
                Categories.HOUSE.title -> R.drawable.ic_house
                Categories.PETS.title -> R.drawable.ic_pets
                Categories.ENTERTAINMENT.title -> R.drawable.ic_entertainment
                Categories.RESTAURANTS.title -> R.drawable.ic_restaurants
                Categories.SUPERMARKETS.title -> R.drawable.ic_supermarket
                else -> R.drawable.ic_else
            }
            iconImage.setImageResource(imageRes)
        }

        private fun Int.priceText(): String = "$this $"
    }
}
