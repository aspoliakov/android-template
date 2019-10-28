package ru.stowawaydev.template.data

import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.singleton
import ru.stowawaydev.template.data.database.AppDatabase
import ru.stowawaydev.template.data.database.dao.TemplateDao
import ru.stowawaydev.template.data.eventbus.TemplateEventBus

/**
 * template header (replace it)
 */

fun dataModule() = Kodein.Module("data_module") {
    // Shared Preferences
    bind<PreferencesHelper>() with singleton { PreferencesHelper(instance()) }
    // Database Instance
    bind<AppDatabase>() with singleton { AppDatabase.getInstance(instance()) }
    // Event Buses
    bind<TemplateEventBus>() with singleton { TemplateEventBus() }
    // DAOs
    bind<TemplateDao>() with singleton { instance<AppDatabase>().templateDao() }
}
