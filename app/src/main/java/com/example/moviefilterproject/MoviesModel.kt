package com.example.moviefilterproject

import java.io.Serializable

data class MoviesDataModel(
    val metadata: Metadata,
    val record: Record
)

data class Metadata(
    val createdAt: String,
    val id: String,
    val `private`: Boolean
):Serializable

data class Record(
    val result: Result
):Serializable

data class Result(
    val collections: Collections,
    val index: List<Index>
):Serializable

data class Collections(
    val curated: List<Any>,
    val smart: List<Smart>,
    val user: List<User>
):Serializable

data class Index(
    val authorid: Int,
    val cd_downloads: Int,
    val curriculum_tags: List<String>,
    val downloadid: Int,
    val educator: String,
    val id: Int,
    val owned: Int,
    val progress_tracking: Double,
    val release_date: String,
    val sale: Int,
    val series_tags: List<String>,
    val skill_tags: List<String>,
    val status: Int,
    val style_tags: List<String>,
    val title: String,
    val video_count: Int,
    val watched: Int
):Serializable {
}

data class Smart(
    val courses: List<Int>,
    val description: String,
    val id: String,
    val is_archive: Int,
    val is_default: Int,
    val label: String,
    val smart: String
):Serializable

data class User(
    val courses: List<Int>,
    val description: String,
    val id: Int,
    val is_archive: Int,
    val is_default: Int,
    val label: String
):Serializable