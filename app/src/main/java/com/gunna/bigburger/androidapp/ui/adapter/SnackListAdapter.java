package com.gunna.bigburger.androidapp.ui.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import com.gunna.bigburger.androidapp.databinding.ItemSnackBinding;
import com.gunna.bigburger.androidapp.domain.model.Snack;

import java.util.ArrayList;
import java.util.List;

public class SnackListAdapter extends RecyclerView.Adapter<SnackListAdapter.SnackViewHolder> {

    private List<Snack> mSnacksList;
    private OnSelectSnackListItemListener mListener;

    public SnackListAdapter(OnSelectSnackListItemListener mListener) {
        this.mListener = mListener;
        mSnacksList = new ArrayList<>();
    }


    public void addData(List<Snack> list) {
        if (list != null) {
            this.mSnacksList = list;
            notifyDataSetChanged();
        }
    }

    @NonNull
    @Override
    public SnackListAdapter.SnackViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new SnackViewHolder(
                ItemSnackBinding.inflate(LayoutInflater.from(viewGroup.getContext()), viewGroup, false)
        );
    }

    @Override
    public void onBindViewHolder(@NonNull SnackListAdapter.SnackViewHolder snackViewHolder, int position) {
        snackViewHolder.bindSnack(mSnacksList.get(position));
        snackViewHolder.mBinding.getRoot().setOnClickListener(v ->
                mListener.onSelectSnack(mSnacksList.get(position))
        );
    }

    @Override
    public int getItemCount() {
        return mSnacksList.size();
    }

    class SnackViewHolder extends RecyclerView.ViewHolder {

        final ItemSnackBinding mBinding;

        SnackViewHolder(@NonNull ItemSnackBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
        }

        void bindSnack(Snack snack) {
            mBinding.setSnack(snack);
        }
    }
}
