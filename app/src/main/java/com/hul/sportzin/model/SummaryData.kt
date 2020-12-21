package com.hul.sportzin.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class SummaryData(
    @SerializedName("Teams")
    val teams: MutableMap<String, Team>
):Parcelable {
}

@Parcelize
data class Team(
    @SerializedName("Name_Full")
    var teamFullName: String? = "",
    @SerializedName("Name_Short")
    var teamShortName: String? = "",
    @SerializedName("Players")
    val player: MutableMap<String, Players>
):Parcelable

@Parcelize
@Entity(tableName = "player_info")
data class Players(
    var teamName:String = "",
    @PrimaryKey(autoGenerate = true)
    val playerId: Int,
    @SerializedName("Position")
    val position: Int,
    @SerializedName("Name_Full")
    val fullName: String = "",
    @SerializedName("Iscaptain")
    val isCaptain: Boolean = false,
    @SerializedName("Iskeeper")
    val isKeeper: Boolean = false
):Parcelable {

}
