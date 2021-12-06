package com.example.truecreditslist.ui


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.truecreditslist.R
import com.example.truecreditslist.data.TruecreditsPost


class TruecreditListViewHolder(view: View)
    : RecyclerView.ViewHolder(view) {
    private val isSelected: CheckBox = view.findViewById(R.id.isSelected)
    private val userImage: TextView = view.findViewById(R.id.userImage)
    private val userName: TextView = view.findViewById(R.id.userName)
    private var post : TruecreditsPost? = null
    init {
        view.setOnClickListener {
            post?.id?.let { id ->
                val bundle = Bundle()
                bundle.putInt("id",id)
//                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
//                view.context.startActivity(intent)
            }
        }
    }

    fun bind(post: TruecreditsPost?) {
        this.post = post
        userName.text = post?.name ?: "loading"
        isSelected.isChecked = post?.isChecked ?: false
    }

    companion object {
        fun create(parent: ViewGroup): TruecreditListViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.truecredit_post_item, parent, false)
            return TruecreditListViewHolder(view)
        }
    }

}