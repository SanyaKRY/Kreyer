package com.tinkoff.education.fintechrecyclerview.delegates.expense

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tinkoff.education.fintechrecyclerview.Categories
import com.tinkoff.education.fintechrecyclerview.R
import com.tinkoff.education.fintechrecyclerview.databinding.ExpenseItemBinding
import com.tinkoff.education.fintechrecyclerview.delegates.utils.AdapterDelegate
import com.tinkoff.education.fintechrecyclerview.delegates.utils.DelegateItem

/**
 * @author y.gladkikh
 */
class ExpenseDelegate : AdapterDelegate {

    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder =
        ViewHolder(ExpenseItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(
        holder: RecyclerView.ViewHolder,
        item: DelegateItem,
        position: Int
    ) {
        (holder as ViewHolder).bind(item.content() as ExpenseModel)
    }

    override fun isOfViewType(item: DelegateItem): Boolean = item is ExpenseDelegateItem

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
