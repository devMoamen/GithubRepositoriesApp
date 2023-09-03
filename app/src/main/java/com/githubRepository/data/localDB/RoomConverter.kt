package com.githubRepository.data.localDB

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.githubRepository.domain.model.githubList.Owner

object RoomConverter {

    @TypeConverter
    @JvmStatic
    fun fromOwner(value: Owner): String {
        return Gson().toJson(value)
    }

    @TypeConverter
    @JvmStatic
    fun toOwner(value: String): Owner {
        return Gson().fromJson(value, Owner::class.java)
    }
}