package com.gunna.bigburger.androidapp.ui.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
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
        this.mSnacksList = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public SnackListAdapter.SnackViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull SnackListAdapter.SnackViewHolder snackViewHolder, int i) {

    }

    @Override
    public int getItemCount() {
        return mSnacksList.size();
    }

    public class SnackViewHolder extends RecyclerView.ViewHolder {
        public SnackViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
