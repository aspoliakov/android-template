package ru.stowawaydev.template.utils

import java.text.FieldPosition
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit
import kotlin.math.abs

/**
 * template header (replace it)
 */

object TimeUtil {

    const val TIMESTAMP_PATTERN_ISO8601 = "yyyy-MM-dd'T'HH:mm:ss.SSSZ"
    const val MDYHM_SKELETON_24 = "MMddyyyyHHmm"
    const val MDYHM_SKELETON_12 = "MMddyyyyhhmma"

    const val HOUR_IN_MINUTES = 60
    const val DAY_IN_HOURS = 24
    const val MILLISECONDS_IN_DAY = 24 * 60 * 60 * 1000
    const val MILLISECONDS_IN_HOUR = 60 * 60 * 1000

    fun getEndOfMonth(time: Long): Long {
        val calendar = Calendar.getInstance()
        calendar.timeInMillis = time
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH))
        calendar.set(Calendar.HOUR_OF_DAY, 23)
        calendar.set(Calendar.MINUTE, 59)
        calendar.set(Calendar.SECOND, 59)
        calendar.set(Calendar.MILLISECOND, 999)
        return calendar.timeInMillis
    }

    fun format(time: Long, pattern: String): String {
        val sdf = SimpleDateFormat(pattern, Locale.getDefault())
        return sdf.format(Date(time))
    }

    fun format(date: Date, outPattern: String): String {
        val sdfOut = SimpleDateFormat(outPattern, Locale.getDefault())
        return sdfOut.format(date)
    }

    fun format(date: Date): String {
        val dateFormat = object : SimpleDateFormat(TIMESTAMP_PATTERN_ISO8601) {
            override fun format(
                date: Date, buffer: StringBuffer,
                position: FieldPosition
            ): StringBuffer {
                val toFix = super.format(date, buffer, position)
                return toFix.insert(toFix.length - 2, ':')
            }
        }
        dateFormat.timeZone = TimeZone.getTimeZone("UTC")
        return dateFormat.format(date)
    }

    fun getTimeDiff(from: Date, to: Date, timeUnit: TimeUnit): Long {
        val diff = abs(to.time - from.time)
        return timeUnit.convert(diff, TimeUnit.MILLISECONDS)
    }

    private fun isToday(time: Long): Boolean {
        val current = Calendar.getInstance()

        val comparable = Calendar.getInstance()
        comparable.timeInMillis = time

        return current.get(Calendar.ERA) == comparable.get(Calendar.ERA) &&
                current.get(Calendar.YEAR) == comparable.get(Calendar.YEAR) &&
                current.get(Calendar.DAY_OF_YEAR) == comparable.get(Calendar.DAY_OF_YEAR)
    }

    fun isSameDay(timeA: Long, timeB: Long): Boolean {
        if (timeA == timeB) {
            return true
        }
        val calendarA = Calendar.getInstance()
        calendarA.timeInMillis = timeA
        val calendarB = Calendar.getInstance()
        calendarB.timeInMillis = timeB
        return calendarA.get(Calendar.ERA) == calendarB.get(Calendar.ERA) &&
                calendarA.get(Calendar.YEAR) == calendarB.get(Calendar.YEAR) &&
                calendarA.get(Calendar.DAY_OF_YEAR) == calendarB.get(Calendar.DAY_OF_YEAR)
    }

    private fun isYesterday(time: Long): Boolean {
        val current = Calendar.getInstance()
        val currentMidnight = clearTime(current.timeInMillis)
        val inputMidnight = clearTime(time)
        return currentMidnight - inputMidnight == TimeUnit.DAYS.toMillis(1)
    }

    private fun isInCurrentWeek(time: Long): Boolean {
        val current = Calendar.getInstance()
        val currentMidnight = clearTime(current.timeInMillis)
        val inputMidnight = clearTime(time)
        return currentMidnight - inputMidnight <= TimeUnit.DAYS.toMillis(6)
    }

    private fun isCurrentYear(time: Long): Boolean {
        val current = Calendar.getInstance()
        val comparable = Calendar.getInstance()
        comparable.timeInMillis = time
        return current.get(Calendar.ERA) == comparable.get(Calendar.ERA) &&
                current.get(Calendar.YEAR) == comparable.get(Calendar.YEAR)
    }

    private fun clearTime(date: Long): Long {
        val calendar = Calendar.getInstance()
        calendar.timeInMillis = date
        calendar.set(Calendar.HOUR_OF_DAY, 0)
        calendar.set(Calendar.MINUTE, 0)
        calendar.set(Calendar.SECOND, 0)
        calendar.set(Calendar.MILLISECOND, 0)
        return calendar.timeInMillis
    }
}
