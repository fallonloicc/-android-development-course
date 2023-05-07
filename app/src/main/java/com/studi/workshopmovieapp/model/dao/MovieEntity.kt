package com.studi.workshopmovieapp.model.dao

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.studi.workshopmovieapp.model.Movie

@Entity(tableName = "movie_table")
class MovieEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null,

    @ColumnInfo(name = "apiId")
    var apiId: String? = null,

    @ColumnInfo(name = "title")
    var title: String? = null,

    @ColumnInfo(name = "descr")
    var descr: String? = null,

    @ColumnInfo(name = "posterUrl")
    var posterUrl: String? = null
) {

    companion object {
        fun Movie.toEntity(): MovieEntity {
            return MovieEntity(
                apiId = this.id,
                title = this.title,
                descr = this.descr,
                posterUrl = this.posterUrl
            )
        }

        fun MovieEntity.toModel(): Movie {
            return Movie(
                id = this.apiId ?: "",
                title = this.title ?: "",
                descr = this.descr ?: "",
                posterUrl = this.posterUrl ?: ""
            )
        }
    }
}