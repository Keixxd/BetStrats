package com.keixxdd.betstrats.domain.module

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "strats_favourite")
data class Strategy(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "picture") val picture: String,
    @ColumnInfo(name = "desc") val description: String,
    @ColumnInfo(name = "isFavorite") var isFavorite: Boolean
): Serializable
