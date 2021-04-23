package com.example.modeling_of_ap.DB.Recycle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.modeling_of_ap.R;

import java.util.List;

public class RecycleAdapter extends RecyclerView.Adapter<RecycleAdapter.ViewHolder>{
    private final LayoutInflater inflater;
    private final List<RecycleViewShow> list;
    private final OnRecyclerViewItemListener onRecyclerViewItemListener;

    public RecycleAdapter(LayoutInflater inflater, List<RecycleViewShow> list, OnRecyclerViewItemListener onRecyclerViewItemListener) {
        this.inflater = inflater;
        this.list = list;
        this.onRecyclerViewItemListener = onRecyclerViewItemListener;
    }

    @NonNull
    @Override
    public RecycleAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.list_item, parent, false);
        return new ViewHolder(view, onRecyclerViewItemListener);
    }

    @Override
    public void onBindViewHolder(RecycleAdapter.ViewHolder holder, int position) {
        RecycleViewShow item = list.get(position);
        holder.nameView.setText(item.getName());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        final TextView nameView;
        OnRecyclerViewItemListener onRecyclerViewItemListener;
        ViewHolder(View view, OnRecyclerViewItemListener onRecyclerViewItemListener){
            super(view);
            nameView = (TextView) view.findViewById(R.id.name);

            this.onRecyclerViewItemListener = onRecyclerViewItemListener;
            view.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            onRecyclerViewItemListener.onItemClick(getAdapterPosition());
        }
    }

    public interface OnRecyclerViewItemListener{
        void onItemClick(int position);
    }
}

