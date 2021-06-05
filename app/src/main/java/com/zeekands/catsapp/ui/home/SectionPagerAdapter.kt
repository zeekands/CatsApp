package com.zeekands.catsapp.ui.home

import android.content.Context
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.zeekands.catsapp.R
import com.zeekands.catsapp.ui.cats.CatsFragment

class SectionsPagerAdapter(private val mContext: Context, fm: FragmentManager) :
    FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    companion object {
        @StringRes
        private val TAB_TITLES = intArrayOf(
            R.string.Cats,
            R.string.Favourite
        )
    }

    override fun getItem(position: Int): Fragment =
        when (position) {
            0 -> CatsFragment()
            1 -> instantiateFragment(className)
            else -> Fragment()
        }

    override fun getPageTitle(position: Int): CharSequence =
        mContext.resources.getString(TAB_TITLES[position])

    override fun getCount(): Int = TAB_TITLES.size

    private val className: String
        get() = "com.zeekands.catsapp.favourite.ui.favourite.FavouriteFragment"

    private fun instantiateFragment(className: String): Fragment {
        return Class.forName(className).newInstance() as Fragment
    }

}