package ru.stowawaydev.template.ui.screens.template_list

import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.singleton

/**
 * template header (replace it)
 */

fun listModule(activity: ListActivity) = Kodein.Module("list_activity") {
    bind<ListPresenter>() with singleton {
        ListPresenter(activity, activity, instance())
    }
}
