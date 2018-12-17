package com.gunna.bigburger.androidapp.ui.adapter.offers;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;

import android.view.ViewGroup;
import com.gunna.bigburger.androidapp.databinding.ItemOfferBinding;
import com.gunna.bigburger.androidapp.domain.model.Offer;

import java.util.ArrayList;
import java.util.List;

public class OffersAdapter extends RecyclerView.Adapter<OffersAdapter.OfferViewHolder> {

    private List<Offer> mOffers;


    public OffersAdapter() {
        this.mOffers = new ArrayList<>();
    }

    public void addData(List<Offer> list) {
        if (list != null) {
            this.mOffers = list;
            notifyDataSetChanged();
        }
    }


    @NonNull
    @Override
    public OfferViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new OfferViewHolder(
                ItemOfferBinding.inflate(LayoutInflater.from(viewGroup.getContext()), viewGroup, false)
        );

    }

    @Override
    public void onBindViewHolder(@NonNull OfferViewHolder offerViewHolder, int i) {
        offerViewHolder.bindOffer(mOffers.get(i));
    }

    @Override
    public int getItemCount() {
        return mOffers.size();
    }

    public void clear() {
        mOffers.clear();
        notifyDataSetChanged();
    }

    class OfferViewHolder extends RecyclerView.ViewHolder {
        private final ItemOfferBinding mBinding;

        OfferViewHolder(@NonNull ItemOfferBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
        }

        void bindOffer(Offer offer) {
            mBinding.setOffer(offer);
        }
    }
}
