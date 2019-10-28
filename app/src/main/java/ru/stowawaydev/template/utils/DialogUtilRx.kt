package ru.stowawaydev.template.utils

import android.content.Context
import android.view.LayoutInflater
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.annotation.StringRes
import androidx.appcompat.app.AlertDialog
import io.reactivex.Completable
import io.reactivex.Single
import ru.stowawaydev.template.R

/**
 * template header (replace it)
 */

object DialogUtilRx {

    fun <T : DialogTitle> openSelectDialog(
        context: Context,
        cancelable: Boolean,
        @StringRes title: Int,
        dialogItems: List<T>
    ): Single<T> {
        return Single.create { emitter ->
            val builder = AlertDialog.Builder(context)
            val adapter = ArrayAdapter(context, R.layout.view_simple_list_item, dialogItems)
            if (title != 0) builder.setTitle(title)
            builder.setAdapter(adapter) { _, which -> emitter.onSuccess(dialogItems[which]) }
            builder.setCancelable(cancelable)
            val dialog = builder.create()
            dialog.setOnCancelListener { emitter.tryOnError(Exception("Dialog view canceled")) }
            dialog.show()
        }
    }

    fun openInfoDialog(
        context: Context,
        title: String,
        message: String,
        selectableText: Boolean
    ): Completable {
        return Completable.create { subscriber ->
            val tv = LayoutInflater.from(context)
                .inflate(R.layout.view_alert_dialog_info, null, false) as TextView
            tv.text = message
            tv.setTextIsSelectable(selectableText)
            AlertDialog.Builder(context)
                .setTitle(title)
                .setView(tv)
                .setPositiveButton(R.string.ok) { _, _ -> subscriber.onComplete() }
                .create()
                .show()
        }
    }

    fun openConfirmDialog(context: Context, title: String, message: String): Single<Boolean> {
        return Single.create { subscriber ->
            val builder = AlertDialog.Builder(context)
            builder.setTitle(title)
                .setMessage(message)
                .setPositiveButton(context.getString(R.string.ok)) { _, _ ->
                    subscriber.onSuccess(true)
                }
                .setNegativeButton(context.getString(R.string.cancel)) { _, _ ->
                    subscriber.onSuccess(false)
                }
            builder.create().show()
        }
    }
}

open class DialogTitle(
    val itemId: Int,
    val title: CharSequence
)
