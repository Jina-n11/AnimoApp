package com.cheese.animoapp.ui

import android.os.Bundle
import com.bumptech.glide.Glide
import com.cheese.animoapp.data.models.AnimeFilm
import com.cheese.animoapp.databinding.FragmentDetailsBinding
import com.cheese.animoapp.ui.base.BaseFragment
import com.cheese.animoapp.util.Constants
import com.cheese.animoapp.util.toLengthOfTime

class DetailsFragment : BaseFragment<FragmentDetailsBinding>() {
    override val LOG_TAG: String = Constants.DETAILS_FRAGMENT
    var anime: AnimeFilm? = null
    override fun bindingInflater() = FragmentDetailsBinding.inflate(layoutInflater)

    override fun setUp() {
        anime = requireNotNull(arguments?.getSerializable(Constants.KEY_ID)) as AnimeFilm
        bindView(anime = requireNotNull(anime))
    }

    private fun bindView(anime: AnimeFilm) {
        binding.apply {
            title.text = anime.title
            animeName.text = anime.originalTitleRomanised
            description.text = anime.description
            rateValue.text = anime.rateScore
            releasedValue.text = anime.releaseDate
            lengthValue.text = anime.runningTime.toLengthOfTime()
            directorValue.text = anime.director
            producerValue.text = anime.producer

            Glide.with(root).load(anime.image).into(animeImage)
            Glide.with(root.context).load(anime.movieBanner).into(mainAnimeImage)
        }

    }

    override fun addCallbacks() {
        arrowBack()
    }

    private fun arrowBack() {
        binding.arrowIcon.setOnClickListener {
            this.parentFragmentManager.popBackStack()
        }
    }


    companion object {
        fun newInstance(anime: AnimeFilm) = DetailsFragment().apply {
            arguments = Bundle().apply {
                putSerializable(Constants.KEY_ID, anime)
            }
        }
    }

}