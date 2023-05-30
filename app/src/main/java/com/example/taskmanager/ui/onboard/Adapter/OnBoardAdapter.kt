package com.example.taskmanager.ui.onboard.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.taskmanager.databinding.ItemOnboardBinding
import com.example.taskmanager.model.OnBoard
import com.example.taskmanager.utils.loadImage

class OnBoardAdapter(private val onFinishClick: () -> Unit) :
    Adapter<OnBoardAdapter.OnBoardViewHolder>() {
    private val boards = arrayListOf<OnBoard>(
        OnBoard(
            "https://www.chanty.com/blog/wp-content/uploads/2020/10/Task-manager-apps-scaled.jpg",
            "Title",
            "Desc"
        ), OnBoard(
            "https://www.chanty.com/blog/wp-content/uploads/2020/10/Task-manager-apps-scaled.jpg",
            "Title",
            "Desc"
        ), OnBoard(
            "https://www.chanty.com/blog/wp-content/uploads/2020/10/Task-manager-apps-scaled.jpg",
            "Title",
            "Desc"
        )
    )

    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int
    ): OnBoardViewHolder {
        return OnBoardViewHolder(
            ItemOnboardBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: OnBoardViewHolder, position: Int) {
        holder.onBind()
    }

    override fun getItemCount(): Int {
        return boards.size
    }

    inner class OnBoardViewHolder(private var binding: ItemOnboardBinding) :
        ViewHolder(binding.root) {
        fun onBind() {
            with(binding) {
                tvTitle.text = boards[adapterPosition].title
                tvDesc.text = boards[adapterPosition].desc
                ivOnboard.loadImage(boards[adapterPosition].image)

                btnStart.isVisible = adapterPosition == boards.size - 1
                btnStart.setOnClickListener {
                    onFinishClick()
                }

                btnSkip.isVisible = adapterPosition != boards.size - 1
                btnSkip.setOnClickListener {
                    onFinishClick()
                }
            }
        }
    }
}
