package ru.stowawaydev.template.ui.screens.template_list

import ru.stowawaydev.template.data.presentation.ItemVO

/**
 * template header (replace it)
 */

interface ListView {

    fun showItems(items: List<ItemVO>)

    fun showMessage(message: String)
}
