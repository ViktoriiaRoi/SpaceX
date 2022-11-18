package com.viktoriiaroi.spacex.ui.launch.adapter

import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.viktoriiaroi.core.model.DatePrecision
import com.viktoriiaroi.core.model.Launch
import com.viktoriiaroi.spacex.databinding.ItemLaunchBinding
import com.viktoriiaroi.spacex.utils.DateUtils
import java.util.*

class LaunchViewHolder(private val binding: ItemLaunchBinding) :
    RecyclerView.ViewHolder(binding.root) {

    private var timer: CountDownTimer? = null

    fun bind(launch: Launch) {
        binding.launch = launch
        binding.detailsTv.isVisible = launch.details.isNotEmpty()
        binding.successTv.isVisible = !launch.upcoming && launch.success
        binding.pendingTv.isVisible = launch.upcoming && launch.datePrecision != DatePrecision.HOUR
        binding.timerGroup.isVisible = launch.upcoming && launch.datePrecision == DatePrecision.HOUR
        if (launch.upcoming && launch.datePrecision == DatePrecision.HOUR) {
            launch.date?.let {
                startTimer(binding, it)
            }
        }
        binding.executePendingBindings()
    }

    private fun startTimer(binding: ItemLaunchBinding, launchDate: Int) {
        val launch = DateUtils.getDate(launchDate)
        val today = Date()
        val millis = java.lang.Long.max(launch.time - today.time, 0)
        timer?.cancel()
        timer = object : CountDownTimer(millis, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                binding.timeLeft = millisUntilFinished
            }

            override fun onFinish() {
                binding.timeLeft = 0
            }
        }.start()
    }

    companion object {
        fun from(parent: ViewGroup): LaunchViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = ItemLaunchBinding.inflate(layoutInflater, parent, false)
            return LaunchViewHolder(binding)
        }
    }
}
