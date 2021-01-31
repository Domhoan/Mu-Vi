package com.example.muvi.ui.adpater

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.muvi.base.BaseAdapter
import com.example.muvi.base.BaseViewHolder
import com.example.muvi.data.model.Actor
import com.example.muvi.data.model.Movie
import com.example.muvi.databinding.ItemActorBinding

class ActorAdapter(
    private val listener: (Actor) -> Unit
) : BaseAdapter<Actor, ItemActorBinding>(listener, {}) {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseViewHolder<Actor, ItemActorBinding> =
        ActorHolder(
            ItemActorBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),
            listener
        )

    class ActorHolder(
        private val itemActorBinding: ItemActorBinding,
        listener: (Actor) -> Unit
    ) : BaseViewHolder<Actor, ItemActorBinding>(itemActorBinding, listener) {
        override fun onBind(itemData: Actor) {
            super.onBind(itemData)
            itemActorBinding.actor = itemData
        }
    }
}
