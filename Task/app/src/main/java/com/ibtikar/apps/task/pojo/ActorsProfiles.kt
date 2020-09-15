package com.ibtikar.apps.task.pojo

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ActorsProfiles(

    @SerializedName("profiles")
    @Expose
    val profiles: List<Profile>? = null,
    @SerializedName("id")
    @Expose
    val id: Int

) {
    class Profile(
        @SerializedName("iso_639_1")
        @Expose
        val iso6391: Object,
        @SerializedName("width")
        @Expose
        val width: Int,
        @SerializedName("height")
        @Expose
        val height: Int,
        @SerializedName("vote_count")
        @Expose
        val voteCount: Int,
        @SerializedName("vote_average")
        @Expose
        val voteAverage: Double,
        @SerializedName("file_path")
        @Expose
        val filePath: String,
        @SerializedName("aspect_ratio")
        @Expose
        val aspectRatio: Double

    ) {
    }

}