package ru.stowawaydev.template.ui.screens.template_list

import android.content.Context
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.stowawaydev.template.data.presentation.ItemVO
import ru.stowawaydev.template.ui.screens.template_list.item.ItemVH

/**
 * template header (replace it)
 */

class ListRvAdapter(
        private val context: Context,
        private val navigation: ListNavigation
) : RecyclerView.Adapter<ItemVH>() {

    private var items = mutableListOf<ItemVO>()

    fun setItems(items: List<ItemVO>) {
        this.items = items.toMutableList()
        notifyDataSetChanged()
    }

    fun updateItem(item: ItemVO) {
        val index = items.indexOfFirst { it.itemId == item.itemId }
        if (index != -1) {
            notifyItemChanged(index)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemVH {
        return ItemVH.create(parent, context, navigation)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ItemVH, position: Int) {
        holder.onBind(items[position])
    }
}
