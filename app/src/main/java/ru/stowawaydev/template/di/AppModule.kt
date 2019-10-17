package ru.stowawaydev.template.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.singleton

/**
 * template header (replace it)
 */

fun appModule() = Kodein.Module("app_module") {
    bind<Gson>() with singleton { gson() }
}

fun gson(): Gson = GsonBuilder()
    .create()
