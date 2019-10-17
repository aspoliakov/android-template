package ru.stowawaydev.template.ui.screens.main

import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.singleton

/**
 * template header (replace it)
 */

fun mainModule(activity: MainActivity) = Kodein.Module("main_activity") {
    bind<MainPresenter>() with singleton {
        MainPresenter(activity, activity)
    }
}
