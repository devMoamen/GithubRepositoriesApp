package com.githubRepository.utils.general

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.core.content.ContextCompat
import com.githubRepository.R
import com.tapadoo.alerter.Alerter
import java.text.DateFormat
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

object GeneralMethods {

    fun changeDateFormat(mDate: String): String? {
        var mNewDate = ""
        if (mDate.contains("T")) {
            mNewDate = mDate.replace("T", " ")
        }
        var spf = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.UK)
        var newDate: Date? = null
        try {
            newDate = spf.parse(mNewDate)
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        spf = SimpleDateFormat("MMM dd,yyyy", Locale.UK)
        return newDate?.let { spf.format(it) }
    }

    fun getLastDayData(): String {
        val dateFormat: DateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.UK)
        val cal = Calendar.getInstance()
        cal.add(Calendar.DATE, -1)
       return dateFormat.format(cal.time) //your formatted date here
    }

    fun getLastWeekData(): String {
        val dateFormat: DateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.UK)
        val cal = Calendar.getInstance()
        cal.add(Calendar.DATE, -7)
      return  dateFormat.format(cal.time) //your formatted date here
    }

    fun getLastMonthData(): String {
        val dateFormat: DateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.UK)
        val cal = Calendar.getInstance()
        cal.add(Calendar.MONTH, -1)
      return  dateFormat.format(cal.time) //your formatted date here
    }

    fun showTopError(message: String, context: Activity) {
        Alerter.create(context).setTitle(context.getString(R.string.error)).setText(message)
            .setDuration(1500)
            .setBackgroundColorInt(ContextCompat.getColor(context, R.color.color_error)).show()
    }

    fun openUrlFromExternal(_url: String, context: Activity) {
        var url = _url
        if (!url.startsWith("http://") && !url.startsWith("https://")) {
            url = "http://$url"
        }

        val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        context.startActivity(browserIntent)
    }

    fun showTopSuccess(message: String, context: Activity) {
        Alerter.create(context).setTitle(context.getString(R.string.success)).setText(message)
            .setDuration(1500).setBackgroundColorInt(
                ContextCompat.getColor(context, R.color.color_success)
            ).show()
    }


}