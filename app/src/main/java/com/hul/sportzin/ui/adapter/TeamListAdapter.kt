package com.hul.sportzin.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hul.sportzin.databinding.ItemPlayerBinding
import com.hul.sportzin.model.Players

class TeamListAdapter(val mPlayerList: List<Players>) :
    RecyclerView.Adapter<TeamListAdapter.ViewHolderTeam>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderTeam {

        val itemPlayerBinding =
            ItemPlayerBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolderTeam(itemPlayerBinding)
    }

    override fun onBindViewHolder(holder: ViewHolderTeam, position: Int) {

        val player = mPlayerList[position]

        holder.itemPlayerBinding.player = player

    }

    override fun getItemCount() = mPlayerList.size

    class ViewHolderTeam(val itemPlayerBinding: ItemPlayerBinding) :
        RecyclerView.ViewHolder(itemPlayerBinding.root) {

    }
}