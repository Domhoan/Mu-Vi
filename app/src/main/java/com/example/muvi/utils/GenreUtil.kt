package com.example.muvi.utils

import com.example.muvi.data.model.Genre

object GenreUtil {
    val genres: List<Genre> = listOf(
        Genre(28, "Action", "/zeD4PabP6099gpE0STWJrJrCBCs.jpg"),
        Genre(12, "Adventure", "/tK1zy5BsCt1J4OzoDicXmr0UTFH.jpg"),
        Genre(16, "Animation", "/hm58Jw4Lw8OIeECIq5qyPYhAeRJ.jpg"),
        Genre(35, "Comedy", "/4n8QNNdk4BOX9Dslfbz5Dy6j1HK.jpg"),
        Genre(80, "Crime", "/2AwPvNHphpZBJDqjZKVuMAbvS0v.jpg"),
        Genre(99, "Documentary", "/nm10ajNVkKwwyf8VFPkZnr93GbC.jpg"),
        Genre(18, "Drama", "/qzA87Wf4jo1h8JMk9GilyIYvwsA.jpg"),
        Genre(10751, "Family", "/h9vTJ78h0SASYUxg4jV0AQ00oF2.jpg"),
        Genre(14, "Fantasy", "/eLT8Cu357VOwBVTitkmlDEg32Fs.jpg"),
        Genre(36, "History", "/zda0VWRKHnUSX7B7NOPqVUlu9zK.jpg"),
        Genre(27, "Horror", "/5WXeYnezavNI6hXH74aQYv6yFzj.jpg"),
        Genre(10402, "Music", "/5RbyHIVydD3Krmec1LlUV7rRjet.jpg"),
        Genre(9648, "Mystery", "/e98dJUitAoKLwmzjQ0Yxp1VQrnU.jpg"),
        Genre(10749, "Romance", "/kiX7UYfOpYrMFSAGbI6j1pFkLzQ.jpg"),
        Genre(878, "Sci-Fi", "/1f3qspv64L5FXrRy0MF8X92ieuw.jpg"),
        Genre(10770, "TV Movie", "/kPzcvxBwt7kEISB9O4jJEuBn72t.jpg"),
        Genre(53, "Thriller", "/sy6DvAu72kjoseZEjocnm2ZZ09i.jpg"),
        Genre(10752, "War", "/8GVpIEBqlRBvx28G0LfEX0U8D2k.jpg"),
        Genre(37, "Western", "/cGBmTiNomYCk6Lr4Gkhbssg0m82.jpg")
    )

    fun getNameGenre(id: Int): String = genres.first {
        it.id == id
    }.name

    fun getIdGenre(name: String) = genres.first {
        it.name == name
    }.id

    fun getGenreOfMovie(data: List<Int>?): String {
        return if (data.isNullOrEmpty()) ""
        else {
            var result = getNameGenre(data.first())
            if (data.size >1) {
                for (item in data) {
                    result += "   " + getNameGenre(item)
                }
            }
            result
        }
    }
}
