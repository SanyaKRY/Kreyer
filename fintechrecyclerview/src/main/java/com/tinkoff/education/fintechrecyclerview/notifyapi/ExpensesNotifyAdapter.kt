package com.tinkoff.education.fintechrecyclerview.notifyapi

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tinkoff.education.fintechrecyclerview.Categories
import com.tinkoff.education.fintechrecyclerview.R
import com.tinkoff.education.fintechrecyclerview.databinding.ExpenseItemBinding
import com.tinkoff.education.fintechrecyclerview.model.ExpenseModel

/**
 * @author y.gladkikh
 */
class ExpensesNotifyAdapter(
    private val expenses: MutableList<ExpenseModel>,
) : RecyclerView.Adapter<ExpensesNotifyAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(ExpenseItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(expenses[position]) {
            expenses.remove(expenses[position])
            notifyItemRemoved(position)
            notifyItemRangeChanged(position, expenses.size)
        }

    override fun getItemCount(): Int = expenses.size

    class ViewHolder(_binding: ExpenseItemBinding) : RecyclerView.ViewHolder(_binding.root) {

        var binding: ExpenseItemBinding = ExpenseItemBinding.bind(itemView)

        fun bind(model: ExpenseModel, action: () -> Unit) {
            with(binding) {
                setupUi(model)
                item.setOnClickListener {
                    action()
                }
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
