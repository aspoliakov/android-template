package ru.stowawaydev.template.ui.screens.template_list

import android.content.Context
import io.reactivex.disposables.Disposable
import ru.stowawaydev.template.R
import ru.stowawaydev.template.domain.interactors.ItemsInteractor
import ru.stowawaydev.template.extensions.applySchedulers
import ru.stowawaydev.template.ui.screens.BasePresenter
import timber.log.Timber

/**
 * template header (replace it)
 */

class ListPresenter(
    private val context: Context,
    private val view: ListView,
    private val itemsInteractor: ItemsInteractor
) : BasePresenter() {

    private var loadItemsDisposable: Disposable? = null

    fun onCreate(size: Int) {
        loadItems(size)
    }

    fun onDestroy() {

    }

    fun onItemClick(itemId: String) {
        view.showMessage(context.getString(R.string.item_clicked, itemId))
    }

    private fun loadItems(size: Int) {
        loadItemsDisposable?.dispose()
        loadItemsDisposable = itemsInteractor.loadList(size)
            .applySchedulers()
            .subscribe(view::showItems, Timber::e)
    }
}
