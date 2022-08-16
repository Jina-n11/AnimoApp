package com.cheese.animoapp.data.repository

import BaseAnimeService
import com.cheese.animoapp.data.State
import com.cheese.animoapp.data.models.NewModel
import io.reactivex.rxjava3.core.Observable
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn


//interface AnimeRepository {
//    fun getAnimeById(id: String , path :String): Observable<State<NewModel>>
//    fun getAllAnime(): Observable<State<NewModel>>
//    fun getAnimeByOriginalTitle(nameAnime: String): Observable<State<NewModel>>
//}
//
//class AnimeRepositoryImp(private val animeService: BaseAnimeService) : AnimeRepository {

//    override fun getAnimeById(id: String , path :String): Observable<State<NewModel>> {
//        return Observable.create { emit ->
//            emit.onNext(State.Loading)
//            emit.onNext(animeService.getAnimeById(id = id , path =path))
//            emit.onComplete()
//        }
//    }
//
//    override fun getAllAnime(): Observable<State<NewModel>>{
//        return Observable.create { emit ->
//            emit.onNext(State.Loading)
//            emit.onNext(animeService.getAllAnime())
//            emit.onComplete()
//        }
//    }
//
//    override fun getAnimeByOriginalTitle(nameAnime: String): Observable<State<NewModel>> {
//        return Observable.create { emit ->
//            emit.onNext(State.Loading)
//            emit.onNext(animeService.getAnimeByOriginalTitle(nameAnime = nameAnime))
//            emit.onComplete()
//        }
//    }
//
//}

interface AnimeRepository {
    fun getAllAnime(): Flow<State<NewModel>>
}

class AnimeRepositoryImp(private val animeService: BaseAnimeService) : AnimeRepository {
    override fun getAllAnime(): Flow<State<NewModel>> {
        return flow {
            emit(State.Loading)
            try {
                emit(animeService.getAllAnime())

            } catch (e: Exception) {
                emit(State.Fail(e.message.toString()))
            }

        }.flowOn(Dispatchers.IO)
    }
}