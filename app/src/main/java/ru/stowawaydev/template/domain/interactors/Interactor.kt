package ru.stowawaydev.template.domain.interactors

import io.reactivex.Observable
import ru.stowawaydev.template.data.presentation.ItemVO

/**
 * template header (replace it)
 */

class ItemsInteractor {

    fun loadList(size: Int): Observable<List<ItemVO>> {
        val items = mutableListOf<ItemVO>()
        for (i in 1..size) {
            items.add(ItemVO(i.toString(), i))
        }
        return Observable.just(items)
    }
}
