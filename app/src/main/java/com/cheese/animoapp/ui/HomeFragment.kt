package com.cheese.animoapp.ui

import AnimeService
import android.util.Log
import android.view.View
import androidx.core.widget.doOnTextChanged
import androidx.lifecycle.lifecycleScope
import com.cheese.animoapp.data.State
import com.cheese.animoapp.data.models.Anime
import com.cheese.animoapp.data.models.AnimeFilm
import com.cheese.animoapp.data.repository.AnimeRepositoryImp
import com.cheese.animoapp.databinding.FragmentHomeBinding
import com.cheese.animoapp.ui.adapters.AnimeAdapter
import com.cheese.animoapp.ui.base.BaseFragment
import com.cheese.animoapp.util.Constants
import com.cheese.animoapp.util.interfaces.ItemListener
import com.cheese.animoapp.util.navigateTo
import io.reactivex.rxjava3.core.Observable
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit

class HomeFragment : BaseFragment<FragmentHomeBinding>(), ItemListener {
    override val LOG_TAG: String = Constants.HOME_FRAGMENT

    private val AnimeRepository = AnimeRepositoryImp(AnimeService())

    override fun bindingInflater() = FragmentHomeBinding.inflate(layoutInflater)

    override fun setUp() {
        getAllAnime()

        if (binding.searchAnime.text != null)
            getAnimeByName(binding.searchAnime.text.toString())
    }

    override fun addCallbacks() {
        onSearchChange()
    }

    private fun onSearchChange() {
        Observable.create<String> { emitter ->
            binding.searchAnime.doOnTextChanged { text, _, _, count ->
                if (count != 1)
                    emitter.onNext(text.toString())
            }
        }.debounce(1, TimeUnit.SECONDS).subscribe(
            { animeName -> getAnimeByName(animeName = animeName) },
            {
                showToast(message = Constants.ANIME_NOT_FOUND)
            }
        )
    }


    private fun getAnimeByName(animeName: String) {
        lifecycleScope.launch(Dispatchers.Main) {
            AnimeRepository.getAnimeByOriginalTitle(animeName).collect(::onAnime)
        }
    }

    private fun getAllAnime() {
        lifecycleScope.launch(Dispatchers.Main) {
            AnimeRepository.getAllAnime().collect(::onAnime)
        }
    }


    private fun onError(throwable: Throwable) {
        Log.v(LOG_TAG, throwable.message.toString())
    }

    private fun onAnime(state: State<Anime>) {
        when (state) {
            is State.Fail -> onFail()
            State.Loading -> onLoading()
            is State.Success -> onSuccess(state.data)
        }
    }


    private fun onFail() {
        if (!binding.searchAnime.equals(null))
            showToast(message = Constants.ANIME_NOT_FOUND)

    }


    private fun onSuccess(Anime: Anime) {
        setupNewModelAdapter(Anime)
        hideProgressBarAndShowData()
    }

    private fun setupNewModelAdapter(Anime: Anime) {
        val animeAdapter = AnimeAdapter(Anime, this)
        binding.mainRecyclerView.adapter = animeAdapter
    }


    private fun onLoading() {
        showProgressBarAndHideData()
    }


    private fun changeProgressLoadingVisibility(visibility: Int) {
        binding.progressLoading.visibility = visibility
    }


    private fun showProgressBarAndHideData() {
        changeProgressLoadingVisibility(View.VISIBLE)
    }

    private fun hideProgressBarAndShowData() {
        changeProgressLoadingVisibility(View.INVISIBLE)
    }

    override fun onClickItem(anime: AnimeFilm) {
        requireActivity().navigateTo(DetailsFragment.newInstance(anime))
    }


}