package com.viktoriiaroi.spacex.ui.views

import android.content.Context
import android.transition.AutoTransition
import android.transition.TransitionManager
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import androidx.cardview.widget.CardView
import com.viktoriiaroi.spacex.R
import com.viktoriiaroi.spacex.databinding.ExpandableCardBinding

class ExpandableCard(context: Context, private val attrs: AttributeSet) :
    CardView(context, attrs) {

    private var _binding: ExpandableCardBinding? = null
    private val binding
        get() = _binding!!

    init {
        _binding = ExpandableCardBinding.inflate(LayoutInflater.from(context), this)
        setCustomAttributes()

        binding.iconArrow.setOnClickListener {
            changeExpandableState()
        }
    }

    private fun setCustomAttributes() {
        val customAttributes =
            context.obtainStyledAttributes(attrs, R.styleable.ExpandableCard, 0, 0)

        try {
            binding.titleTv.text = customAttributes.getString(R.styleable.ExpandableCard_titleText)
            binding.hiddenTv.text =
                customAttributes.getString(R.styleable.ExpandableCard_hiddenText)
        } finally {
            customAttributes.recycle()
        }
    }

    private fun changeExpandableState() {
        val cardView = binding.root as CardView
        if (binding.hiddenTv.visibility == View.VISIBLE) {
            TransitionManager.beginDelayedTransition(cardView, AutoTransition())
            binding.hiddenTv.visibility = View.GONE
            binding.iconArrow.setImageResource(R.drawable.arrow_closed)
        } else {
            TransitionManager.beginDelayedTransition(cardView, AutoTransition())
            binding.hiddenTv.visibility = View.VISIBLE
            binding.iconArrow.setImageResource(R.drawable.arrow_open)
        }
    }
}
