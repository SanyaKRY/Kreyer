package com.tinkoff.education.fintechrecyclerview.listadapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.tinkoff.education.fintechrecyclerview.Categories
import com.tinkoff.education.fintechrecyclerview.R
import com.tinkoff.education.fintechrecyclerview.databinding.ExpenseItemBinding
import com.tinkoff.education.fintechrecyclerview.model.ExpenseModel

/**
 * @author by y.gladkikh
 */
class ExpensesListAdapter :
    ListAdapter<ExpenseModel, ExpensesListAdapter.ViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(
        ExpenseItemBinding.inflate(LayoutInflater.from(parent.context), parent, false),
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getItemCount(): Int = currentList.size

    class DiffCallback : DiffUtil.ItemCallback<ExpenseModel>() {
        override fun areItemsTheSame(oldItem: ExpenseModel, newItem: ExpenseModel): Boolean {
            return oldItem.title == newItem.title
        }

        override fun areContentsTheSame(oldItem: ExpenseModel, newItem: ExpenseModel): Boolean {
            return oldItem == newItem
        }
    }

    class ViewHolder(private val binding: ExpenseItemBinding) : RecyclerView.ViewHolder(binding.root) {

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
