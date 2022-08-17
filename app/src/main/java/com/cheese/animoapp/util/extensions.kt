package com.cheese.animoapp.util
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.cheese.animoapp.R
import com.cheese.animoapp.util.enum.NavigationState


fun String.toLengthOfTime(): String {
    val length = String.format("%.2f", (this.toDouble() / 60)).split(".")
    return "${length[0]}h ${length[1]}m"
}




fun FragmentActivity.navigateTo(to: Fragment) {
    changeNavigation(this, NavigationState.ADD, to)
}

fun FragmentActivity.navigateAndReplaceTo(to: Fragment){
    changeNavigation(this, NavigationState.REPLACE, to)
}

fun FragmentActivity.back(to: Fragment) {
    changeNavigation(this, NavigationState.REMOVE, to)
}

private fun changeNavigation(activity: FragmentActivity, state: NavigationState, to: Fragment) {
    val transaction = activity.supportFragmentManager.beginTransaction()
    when (state) {
        NavigationState.ADD -> transaction.add(R.id.home_fragment, to)
        NavigationState.REMOVE -> transaction.remove(to)
        NavigationState.REPLACE -> transaction.replace(R.id.home_fragment, to)
    }
    transaction.addToBackStack(null).commit()
}




