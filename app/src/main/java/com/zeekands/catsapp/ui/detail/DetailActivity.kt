package com.zeekands.catsapp.ui.detail

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.zeekands.catsapp.R
import com.zeekands.catsapp.databinding.ActivityDetailBinding
import com.zeekands.core.domain.model.Cat
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_CATS = "EXTRA_CATS"
    }

    private lateinit var binding: ActivityDetailBinding
    private val viewModel: DetailViewModel by viewModels()

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val detailCat = intent.getParcelableExtra<Cat>(EXTRA_CATS)

        binding.backButton.setOnClickListener {
            onBackPressed()
        }

        supportActionBar?.hide()

        if (detailCat != null) {
            binding.titleDetail.text = detailCat.name
            binding.originCat.text = detailCat.countryCode + " " + detailCat.origin
            binding.temperament.text = detailCat.temperament
            binding.overview.text = detailCat.description
            setImage(this, detailCat.image, binding.posterTopBar)
            var favoriteState: Boolean
            viewModel.getCat(detailCat.idDb!!)
            viewModel.cat.observe(this, { cat ->
                setFavoriteState(cat.favourite)
                binding.favoriteButton.setOnClickListener {
                    favoriteState = !cat.favourite
                    viewModel.setFavoriteCat(detailCat, favoriteState)
                    if (favoriteState) Toast.makeText(
                        this,
                        "Added to favourite",
                        Toast.LENGTH_SHORT
                    ).show()
                    else Toast.makeText(this, "Removed from favourite", Toast.LENGTH_SHORT).show()
                    setFavoriteState(favoriteState)
                }
            })
        }
    }


    private fun setFavoriteState(state: Boolean?) {
        if (state == true) {
            binding.favoriteButton.setImageDrawable(
                ContextCompat.getDrawable(
                    this,
                    R.drawable.ic_fave
                )
            )
        } else {
            binding.favoriteButton.setImageDrawable(
                ContextCompat.getDrawable(
                    this,
                    R.drawable.ic_fave_border
                )
            )
        }
    }

    private fun setImage(context: Context, url: String?, image: ImageView) {
        Glide.with(context)
            .load(url)
            .listener(object : RequestListener<Drawable> {
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Drawable>?,
                    isFirstResource: Boolean
                ): Boolean {
                    Toast.makeText(
                        context,
                        "Image Is Empty or Cannot Be Loaded",
                        Toast.LENGTH_SHORT
                    ).show()
                    return false
                }

                override fun onResourceReady(
                    resource: Drawable?,
                    model: Any?,
                    target: Target<Drawable>?,
                    dataSource: DataSource?,
                    isFirstResource: Boolean
                ): Boolean {
                    return false
                }
            })
            .into(image)
    }
}