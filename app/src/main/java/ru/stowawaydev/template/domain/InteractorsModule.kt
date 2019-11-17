package ru.stowawaydev.template.domain

import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.singleton
import ru.stowawaydev.template.domain.interactors.ItemsInteractor

/**
 * template header (replace it)
 */

fun interactorsModule() = Kodein.Module("interactors_module") {
    bind<ItemsInteractor>() with singleton { ItemsInteractor() }
}
