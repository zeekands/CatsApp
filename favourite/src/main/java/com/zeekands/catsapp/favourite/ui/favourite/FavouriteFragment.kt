package com.zeekands.catsapp.favourite.ui.favourite

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.zeekands.catsapp.di.DynamicFeatureDependencies
import com.zeekands.catsapp.favourite.databinding.FavouriteFragmentBinding
import com.zeekands.catsapp.ui.detail.DetailActivity
import com.zeekands.core.domain.model.Cat
import com.zeekands.core.ui.CatAdapter
import dagger.hilt.android.EntryPointAccessors
import javax.inject.Inject

class FavouriteFragment : Fragment() {

    @Inject
    lateinit var viewModel: FavoriteViewModel
    private lateinit var binding: FavouriteFragmentBinding
    private lateinit var favouriteAdapter: CatAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FavouriteFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initCoreDependentInjection()
        favouriteAdapter = CatAdapter()
        setList()

        with(binding.rvCatsFave) {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = favouriteAdapter
        }

        favouriteAdapter.onItemClick = { selectedData ->
            val intent = Intent(activity, DetailActivity::class.java)
            intent.putExtra(DetailActivity.EXTRA_CATS, selectedData)
            startActivity(intent)
        }

    }

    private fun setList() {
        viewModel.getFavouriteCats().observe(viewLifecycleOwner, catObserver)
    }

    private val catObserver = Observer<List<Cat>> { cat ->
        if (cat.isNullOrEmpty()) {
            binding.progressBar.visibility = View.GONE
            binding.notFoundText.visibility = View.VISIBLE
        } else {
            binding.progressBar.visibility = View.GONE
            binding.notFoundText.visibility = View.GONE
        }
        favouriteAdapter.setData(cat)
    }

    private fun initCoreDependentInjection() {
        DaggerDfmDaggerComponent.factory().create(
            EntryPointAccessors.fromApplication(
                requireContext(),
                DynamicFeatureDependencies::class.java
            )
        ).inject(this)
    }
}