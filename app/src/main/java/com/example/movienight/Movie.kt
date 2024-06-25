
data class Movie(
    val poster_path : String?,
    val title : String,
    val vote_average : Double?,
    val overview : String?,
    var release_date: String?,
    var favourite : Boolean = false
)
