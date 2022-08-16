
import com.cheese.animoapp.util.Constants
import okhttp3.HttpUrl
import okhttp3.Request

object RequestHelper {
    var httpUrl = HttpUrl.Builder()
        .scheme(Constants.ApiKey.SCHEMA)
        .host(Constants.ApiKey.HOST)

    fun makeAnimeRequest(): Request {
        val url= httpUrl
            .addPathSegment(Constants.ApiKey.PATH_FILMS)
            .build()
        return Request.Builder()
            .url(url)
            .get()
            .build()
    }
    fun makeAnimeRequestByOriginalTitle(nameAnime: String ): Request {
        val url= httpUrl
            .addPathSegment(Constants.ApiKey.PATH_FILMS)
            .addQueryParameter(Constants.ApiKey.Params.Original_Title_Romanised,
                nameAnime)
            .build()
        return Request.Builder()
            .url(url)
            .get()
            .addHeader(Constants.ApiKey.ACCEPT, Constants.ApiKey.TYPE)
            .build()
    }
    fun makeAnimeRequestByTitle(nameAnime: String): Request {
        val url= httpUrl
            .addPathSegment(Constants.ApiKey.PATH_FILMS)
            .addQueryParameter(Constants.ApiKey.Params.TITLE,
                nameAnime)
            .build()
        return Request.Builder()
            .url(url)
            .get()
            .addHeader(Constants.ApiKey.ACCEPT, Constants.ApiKey.TYPE)
            .build()
    }
    fun makeAnimeRequestById(id: String , path: String): Request {
        val url= httpUrl
            .addPathSegment(path)
            .addQueryParameter(Constants.ApiKey.Params.ID,id)
            .build()
        return Request.Builder()
            .url(url)
            .get()
            .addHeader(Constants.ApiKey.ACCEPT, Constants.ApiKey.TYPE)
            .build()
    }
}