package Models

data class Result (
	val id: Long,
	val name: String,
	val status: Status,
	val species: Species,
	val type: String,
	val gender: Gender,
	val origin: Location,
	val location: Location,
	val image: String,
	val episode: List<String>,
	val url: String,
	val created: String
)