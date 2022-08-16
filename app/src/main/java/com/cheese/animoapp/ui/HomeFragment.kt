package com.cheese.animoapp.ui
import AnimeService
import android.util.Log
import android.view.View
import androidx.lifecycle.lifecycleScope
import com.cheese.animoapp.data.State
import com.cheese.animoapp.data.models.NewModel
import com.cheese.animoapp.data.models.NewModelItem
import com.cheese.animoapp.data.repository.AnimeRepositoryImp
import com.cheese.animoapp.databinding.FragmentHomeBinding
import com.cheese.animoapp.ui.adapters.AnimeAdapter
import com.cheese.animoapp.ui.base.BaseFragment
import com.cheese.animoapp.util.Constants
import com.cheese.animoapp.util.interfaces.ItemListener
import com.cheese.animoapp.util.navigateTo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeFragment : BaseFragment<FragmentHomeBinding>() ,ItemListener{
    override val LOG_TAG: String = Constants.HOME_FRAGMENT

    private val AnimeRepository = AnimeRepositoryImp(AnimeService())

    override fun bindingInflater() = FragmentHomeBinding.inflate(layoutInflater)

    override fun setUp() {
        getAllAnime()
    }

    override fun addCallbacks() {}



//    private fun getAllAnime() {
//        val animeService = AnimeService()
//        val animeRepositoryImp = AnimeRepositoryImp(animeService)
//        val observer = animeRepositoryImp.getAllAnime()
//            .subscribeOn(Schedulers.io())
//            .observeOn(AndroidSchedulers.mainThread())
//        observer.subscribe(::onAnime, ::onError)
//    }

    private fun getAllAnime() {
        lifecycleScope.launch(Dispatchers.Main) {
            AnimeRepository.getAllAnime().collect(::onAnime)
        }
    }


    private fun onError(throwable: Throwable) {
        Log.v(LOG_TAG, throwable.message.toString())
    }

    private fun onAnime(state: State<NewModel>) {
        when (state) {
            is State.Fail -> onFail()
            State.Loading -> onLoading()
            is State.Success -> onSuccess(state.data)
        }
    }

    private fun onFail() {
        changeProgressLoadingVisibility(View.INVISIBLE)
        changeDataContainerVisibility(View.VISIBLE)
        changeErrorDataVisibility(View.VISIBLE)

    }


    private fun onSuccess(NewModel: NewModel) {
        setupNewModelAdapter(NewModel)
        hideProgressBarAndShowData()
    }
    private fun setupNewModelAdapter(NewModel: NewModel) {
        val animeAdapter = AnimeAdapter(NewModel ,this)
        binding.mainRecyclerView.adapter = animeAdapter
    }


    private fun onLoading() {
        showProgressBarAndHideData()
    }

    private fun changeDataContainerVisibility(visibility: Int) {
//        binding.  .visibility = visibility
    }

    private fun changeProgressLoadingVisibility(visibility: Int) {
        binding.progressLoading.visibility = visibility
    }

    private fun changeErrorDataVisibility(visibility: Int) {
        binding.apply {
            errorLottie.visibility = visibility
        }
    }


    private fun showProgressBarAndHideData() {
        changeProgressLoadingVisibility(View.VISIBLE)
        changeDataContainerVisibility(View.INVISIBLE)
    }

    private fun hideProgressBarAndShowData() {
        changeProgressLoadingVisibility(View.INVISIBLE)
        changeDataContainerVisibility(View.VISIBLE)
        changeErrorDataVisibility(View.INVISIBLE)
    }

    override fun onClickItem(anime: NewModelItem) {

        requireActivity().navigateTo(DetailsFragment.newInstance(anime))
    }


}