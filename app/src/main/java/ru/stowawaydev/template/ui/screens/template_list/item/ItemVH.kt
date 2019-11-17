package ru.stowawaydev.template.ui.screens.template_list.item

import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.list_item.view.*
import ru.stowawaydev.template.R
import ru.stowawaydev.template.data.presentation.ItemVO
import ru.stowawaydev.template.extensions.inflate
import ru.stowawaydev.template.ui.screens.template_list.ListNavigation

/**
 * template header (replace it)
 */

class ItemVH(
        view: View,
        private val context: Context,
        private val navigation: ListNavigation
) : RecyclerView.ViewHolder(view) {

    companion object {
        fun create(parent: ViewGroup, context: Context, navigation: ListNavigation): ItemVH =
                ItemVH(parent.inflate(R.layout.list_item), context, navigation)
    }

    private lateinit var item: ItemVO

    init {
        setupListeners()
    }

    fun onBind(item: ItemVO) {
        this.item = item
        showData()
    }

    private fun setupListeners() {
        itemView.setOnClickListener { navigation.onItemClick(item.itemId) }
    }

    private fun showData() {
        itemView.tv_item_id.text = context.getString(R.string.item_id, item.itemId)
        itemView.tv_item_payload.text = context.getString(R.string.tik_tak, item.second)
    }
}
