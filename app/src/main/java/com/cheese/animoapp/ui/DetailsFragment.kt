package com.cheese.animoapp.ui
import android.os.Bundle
import android.os.Parcelable
import com.cheese.animoapp.data.models.Films
import com.cheese.animoapp.data.models.NewModelItem
import com.cheese.animoapp.databinding.FragmentDetailsBinding
import com.cheese.animoapp.ui.base.BaseFragment
import com.cheese.animoapp.util.Constants

class DetailsFragment : BaseFragment<FragmentDetailsBinding>() {
    override val LOG_TAG: String = Constants.DETAILS_FRAGMENT

    override fun bindingInflater() = FragmentDetailsBinding.inflate(layoutInflater)

    override fun setUp() {
        arguments?.let {
//            val anime = it.getParcelable<Films>(Constants.KEY_ID)
        }
    }

    override fun addCallbacks() {}

    override fun onStart() {
        super.onStart()

    }


    companion object {
        fun newInstance(anime: NewModelItem) = DetailsFragment().apply {
                  arguments = Bundle().apply {
//                      putParcelable(Constants.KEY_ID , anime )
                }
        }
    }

}