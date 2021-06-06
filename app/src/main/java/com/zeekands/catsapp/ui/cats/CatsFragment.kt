package com.zeekands.catsapp.ui.cats

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.zeekands.catsapp.databinding.CatsFragmentBinding
import com.zeekands.catsapp.ui.detail.DetailActivity
import com.zeekands.core.data.Resource
import com.zeekands.core.domain.model.Cat
import com.zeekands.core.ui.CatAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CatsFragment : Fragment() {

    private val viewModel: CatsViewModel by viewModels()
    private var _fragmentGamesBinding: CatsFragmentBinding? = null
    private val binding get() = _fragmentGamesBinding!!
    private lateinit var catsAdapter: CatAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _fragmentGamesBinding = CatsFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setList()
        catsAdapter = CatAdapter()

        with(binding.rvCats) {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = catsAdapter
        }

        catsAdapter.onItemClick = { selectedData ->
            val intent = Intent(activity, DetailActivity::class.java)
            intent.putExtra(DetailActivity.EXTRA_CATS, selectedData)
            startActivity(intent)
        }
    }

    private fun setList() {
        viewModel.getCats().observe(viewLifecycleOwner, catObserver)
    }

    private val catObserver = Observer<Resource<List<Cat>>> { cat ->
        if (cat != null) {
            when (cat) {
                is Resource.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                    binding.notFoundText.visibility = View.GONE
                }
                is Resource.Success -> {
                    catsAdapter.setData(cat.data)
                    cat.data?.forEach {
                        it.name?.let { it1 -> Log.d("dataku", it1) }
                    }
                    binding.progressBar.visibility = View.GONE
                    binding.notFoundText.visibility = View.GONE
                }
                is Resource.Error -> {
                    binding.progressBar.visibility = View.GONE
                    binding.notFoundText.visibility = View.VISIBLE
                    cat.message?.let { Log.d("occured_error", it) }
                }
            }
        }
    }
}