package com.cheese.animoapp.ui.base
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding


abstract class BaseFragment<VB :ViewBinding> : Fragment() {
    abstract val  LOG_TAG:String

    abstract fun bindingInflater(): VB
    private var _binding : ViewBinding? = null

     val binding  get() = _binding as VB

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        arguments
        _binding = bindingInflater()

        setUp()
        addCallbacks()

        return requireNotNull(_binding).root
    }

    abstract fun setUp()
    abstract fun addCallbacks()
    protected fun log(value:Any){
        Log.v(LOG_TAG,value.toString())
    }

//    protected fun showToast(message:String){
//        Toast.makeText(
//            this,
//            message,
//            Toast.LENGTH_SHORT
//        ).show()
//    }




}