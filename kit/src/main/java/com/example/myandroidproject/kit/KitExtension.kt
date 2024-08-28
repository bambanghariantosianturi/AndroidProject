package com.example.myandroidproject.kit

import android.app.Activity
import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.AppCompatTextView
import com.example.myandroidproject.kit.navigation.CrossModuleNavigator
import com.example.myandroidproject.kit.navigation.CrossModuleNavigatorProvider
import java.util.Calendar
import java.util.concurrent.TimeUnit
import kotlin.reflect.KClass

fun Activity.crossModuleNavigateTo(
    classTarget: KClass<*>?,
    bundle: Bundle? = null,
    requestCode: Int? = null
) {
    getCrossModuleNavigator().crossModuleActivityNavigateTo(this, classTarget, bundle, requestCode)
}

fun Activity.getCrossModuleNavigator(): CrossModuleNavigator {
    return (applicationContext as CrossModuleNavigatorProvider).provideCrossModuleNavigator()
}

fun View.visible() {
    this.visibility = View.VISIBLE
}

fun View.gone() {
    this.visibility = View.GONE
}

fun getTimeAgo(timeCreated: Long): String {
    // Mendapatkan waktu sekarang dalam milidetik
    val currentTime = System.currentTimeMillis()

    // Mengonversi waktu yang dibuat (Unix timestamp) ke milidetik
    val timeCreatedMillis = timeCreated * 1000

    // Menghitung selisih waktu antara sekarang dan waktu yang dibuat
    val timeDifference = currentTime - timeCreatedMillis

    // Mendapatkan Calendar instance untuk tanggal saat ini dan tanggal yang dibuat
    val calendarCreated = Calendar.getInstance().apply { timeInMillis = timeCreatedMillis }
    val calendarNow = Calendar.getInstance().apply { timeInMillis = currentTime }

    // Menghitung selisih dalam tahun, bulan, hari, jam, dan menit
    val yearsAgo = calendarNow.get(Calendar.YEAR) - calendarCreated.get(Calendar.YEAR)
    val monthsAgo = (calendarNow.get(Calendar.MONTH) - calendarCreated.get(Calendar.MONTH)) + (yearsAgo * 12)
    val daysAgo = TimeUnit.MILLISECONDS.toDays(timeDifference)
    val hoursAgo = TimeUnit.MILLISECONDS.toHours(timeDifference)
    val minutesAgo = TimeUnit.MILLISECONDS.toMinutes(timeDifference)

    // Mengembalikan hasil berdasarkan berapa lama waktu telah berlalu
    return when {
        yearsAgo > 0 -> "$yearsAgo years ago"
        monthsAgo > 0 -> "$monthsAgo months ago"
        daysAgo > 0 -> "$daysAgo days ago"
        hoursAgo > 0 -> "$hoursAgo hours ago"
        minutesAgo > 0 -> "$minutesAgo minutes ago"
        else -> "just now"
    }
}