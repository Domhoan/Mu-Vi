package com.example.muvi.ui.adpater

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.muvi.base.BaseAdapter
import com.example.muvi.base.BaseViewHolder
import com.example.muvi.data.model.Company
import com.example.muvi.databinding.ItemCompanyBinding

class CompanyAdapter(
    private val listener: (Company) -> Unit
) : BaseAdapter<Company, ItemCompanyBinding>(listener, {}) {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseViewHolder<Company, ItemCompanyBinding> =
        CompanyHolder(
            ItemCompanyBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),
            listener
        )

    class CompanyHolder(
        private val itemCompanyBinding: ItemCompanyBinding,
        listener: (Company) -> Unit
    ) : BaseViewHolder<Company, ItemCompanyBinding>(itemCompanyBinding, listener) {
        override fun onBind(itemData: Company) {
            super.onBind(itemData)
            itemCompanyBinding.company = itemData
        }
    }
}
