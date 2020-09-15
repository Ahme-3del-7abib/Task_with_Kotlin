package com.ibtikar.apps.task.pojo

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Actors(
    @SerializedName("page")
    @Expose
    val page: Int,
    @SerializedName("total_results")
    @Expose
    val totalResults: Int,
    @SerializedName("total_pages")
    @Expose
    val totalPages: Int,

    @SerializedName("results")
    @Expose
    val results: List<Result>
) {

    class KnownFor(

        @SerializedName("release_date")
        @Expose
        val releaseDate: String,
        @SerializedName("id")
        @Expose
        val id: Int,
        @SerializedName("vote_count")
        @Expose
        val voteCount: Int,
        @SerializedName("video")
        @Expose
        val video: Boolean,
        @SerializedName("media_type")
        @Expose
        val mediaType: String,
        @SerializedName("vote_average")
        @Expose
        val voteAverage: Double,
        @SerializedName("title")
        @Expose
        val title: String,
        @SerializedName("genre_ids")
        @Expose
        val genreIds: List<Int>? = null,
        @SerializedName("original_title")
        @Expose
        val originalTitle: String,
        @SerializedName("original_language")
        @Expose
        val originalLanguage: String,
        @SerializedName("adult")
        @Expose
        val adult: Boolean,
        @SerializedName("backdrop_path")
        @Expose
        val backdropPath: String,
        @SerializedName("overview")
        @Expose
        val overview: String,
        @SerializedName("poster_path")
        @Expose
        val posterPath: String,
        @SerializedName("original_name")
        @Expose
        val originalName: String,
        @SerializedName("name")
        @Expose
        val name: String,
        @SerializedName("first_air_date")
        @Expose
        val firstAirDate: String,
        @SerializedName("origin_country")
        @Expose
        val originCountry: List<String>? = null

    ) {
    }

    class Result(

        @SerializedName("popularity")
        @Expose
        val popularity: Double,
        @SerializedName("known_for_department")
        @Expose
        val knownForDepartment: String,
        @SerializedName("gender")
        @Expose
        val gender: Int,
        @SerializedName("id")
        @Expose
        val id: Int,
        @SerializedName("profile_path")
        @Expose
        val profilePath: String,
        @SerializedName("adult")
        @Expose
        val adult: Boolean,
        @SerializedName("known_for")
        @Expose
        val knownFor: List<KnownFor>? = null,
        @SerializedName("name")
        @Expose
        val name: String
    ) {
    }

}