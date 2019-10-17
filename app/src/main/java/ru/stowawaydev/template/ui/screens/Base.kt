package ru.stowawaydev.template.ui.screens

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware

/**
 * template header (replace it)
 */

abstract class BaseActivity : AppCompatActivity(), KodeinAware, MvpView {

    private val parentKodein by lazy { (applicationContext as KodeinAware).kodein }

    override val kodein by lazy {
        Kodein {
            extend(parentKodein)
            import(depsModule())
        }
    }

    abstract fun depsModule(): Kodein.Module
}

abstract class BaseFragment: Fragment(), KodeinAware {

    private val parentKodein by lazy { (activity as KodeinAware).kodein }

    override val kodein by lazy {
        Kodein {
            extend(parentKodein)
            import(depsModule())
        }
    }

    abstract fun depsModule(): Kodein.Module
}

abstract class BasePresenter

interface MvpView
