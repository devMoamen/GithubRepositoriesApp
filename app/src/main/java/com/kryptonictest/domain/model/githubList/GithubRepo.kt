package com.kryptonictest.domain.model.githubList

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.kryptonictest.data.localDB.RoomConverter
import com.kryptonictest.utils.general.GeneralMethods
import kotlinx.parcelize.Parcelize

@Entity
@Parcelize
@TypeConverters(RoomConverter::class)
data class GithubRepo(
    @PrimaryKey()
    val id: Int = 0,
    val name: String = "",
    val stargazers_count: Int = 0,
    val description: String? = "",
    val forks_count: Int,
    val full_name: String = "",
    val html_url: String = "",
    val language: String = "",
    val owner: Owner? = null,
    val updated_at: String = "",
    val created_at: String,
    val watchers_count: Int,
) : Parcelable {

    fun getDescriptionStr(): String {
        return if (description.isNullOrEmpty()) {
            "This project using $language and has fork $forks_count"
        } else {
            description
        }
    }

    fun getCreateAtStr(): String {
        return "${GeneralMethods.changeDateFormat(created_at)}"
    }

    fun getLastUpdateStr(): String {
        return "Last update on: ${GeneralMethods.changeDateFormat(updated_at)}"
    }
}

@Parcelize
data class Owner(
    val id: Int = 0,
    val login: String? = "",
    val avatar_url: String? = "",
    val url: String? = ""
) : Parcelable