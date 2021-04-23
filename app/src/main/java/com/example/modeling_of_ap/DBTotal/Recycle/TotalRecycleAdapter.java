package com.example.modeling_of_ap.DBTotal.Recycle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.modeling_of_ap.DB.Recycle.RecycleAdapter;
import com.example.modeling_of_ap.R;

import java.util.List;

public class TotalRecycleAdapter extends RecyclerView.Adapter<TotalRecycleAdapter.ViewHolder>{
    private final LayoutInflater inflater;
    private final List<TotalRecycleViewShow> list;
    private final OnRecyclerViewItemListener onRecyclerViewItemListener;

    public TotalRecycleAdapter(LayoutInflater inflater, List<TotalRecycleViewShow> list, OnRecyclerViewItemListener onRecyclerViewItemListener) {
        this.inflater = inflater;
        this.list = list;
        this.onRecyclerViewItemListener = onRecyclerViewItemListener;
    }

    @NonNull
    @Override
    public TotalRecycleAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.total_list_item, parent, false);
        return new TotalRecycleAdapter.ViewHolder(view, onRecyclerViewItemListener);
    }

    @Override
    public void onBindViewHolder(TotalRecycleAdapter.ViewHolder holder, int position) {
        TotalRecycleViewShow item = list.get(position);
        holder.nameView.setText(item.getName());
        holder.dateView.setText(item.getDate());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        final TextView nameView;
        final TextView dateView;
        OnRecyclerViewItemListener onRecyclerViewItemListener;
        ViewHolder(View view, OnRecyclerViewItemListener onRecyclerViewItemListener){
            super(view);
            nameView = view.findViewById(R.id.name);
            dateView = view.findViewById(R.id.date);

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

