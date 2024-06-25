
data class Movie(
    val poster_path : String?,
    val title : String,
    val vote_average : Double?,
    var release_date: String?,
    var favourite : Boolean = false
)
