import com.cheese.animoapp.data.State
import com.cheese.animoapp.data.models.Anime
import com.google.gson.Gson
import okhttp3.OkHttpClient

interface BaseAnimeService {
    fun getAnimeById(id: String, path: String): State<Anime>
    fun getAllAnime(): State<Anime>
    fun getAnimeByOriginalTitle(nameAnime: String): State<Anime>

}

class AnimeService : BaseAnimeService {
    private val client = OkHttpClient()

    override fun getAnimeById(id: String, path: String): State<Anime> {
        val response =
            client.newCall(RequestHelper.makeAnimeRequestById(id = id, path = path)).execute()
        return if (response.isSuccessful) {
            Gson().fromJson(response.body?.string(), Anime::class.java).run {
                State.Success(this)
            }
        } else {
            State.Fail(response.message)
        }
    }

    override fun getAllAnime(): State<Anime> {
        val response = client.newCall(RequestHelper.makeAnimeRequest()).execute()
        return if (response.isSuccessful) {
            Gson().fromJson(response.body?.string(), Anime::class.java).run {
                State.Success(this)
            }
        } else {
            State.Fail(response.message)
        }
    }

    override fun getAnimeByOriginalTitle(nameAnime: String): State<Anime> {
        val response = client.newCall(
            RequestHelper.makeAnimeRequestByOriginalTitle(nameAnime = nameAnime)
        ).execute()
        return if (response.isSuccessful) {
            Gson().fromJson(response.body?.string(), Anime::class.java).run {
                State.Success(this)
            }
        } else {
            State.Fail(response.message)
        }
    }
}
