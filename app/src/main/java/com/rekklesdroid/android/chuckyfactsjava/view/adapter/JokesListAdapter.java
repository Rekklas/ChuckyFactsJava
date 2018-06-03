package com.rekklesdroid.android.chuckyfactsjava.view.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.rekklesdroid.android.chuckyfactsjava.R;
import com.rekklesdroid.android.chuckyfactsjava.entity.Value;

import java.util.List;
import java.util.Locale;

public class JokesListAdapter extends RecyclerView.Adapter<JokesListAdapter.ViewHolder> {

    private List<Value> dataList;

    public JokesListAdapter(List<Value> dataList) {
        this.dataList = dataList;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView tvId;
        public TextView tvJoke;

        public ViewHolder(View itemView) {
            super(itemView);

            tvId = itemView.findViewById(R.id.tv_id_card_view_custom_layout);
            tvJoke = itemView.findViewById(R.id.tv_joke_card_view_custom_layout);
        }
    }

    @NonNull
    @Override
    public JokesListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View viewRow = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_view_custom_layout, parent, false);
        return new ViewHolder(viewRow);
    }

    @Override
    public void onBindViewHolder(@NonNull JokesListAdapter.ViewHolder holder, int position) {
        Value joke = dataList.get(position);

        holder.tvId.setText(String.format(Locale.US,"%d",joke.getId()));
        holder.tvJoke.setText(String.format(Locale.US,"%s",joke.getJoke()));
    }

    @Override
    public int getItemCount() {
        return (dataList != null) ? dataList.size() : 0;
    }

    public void updateData(List<Value> list) {
        dataList = list;
        this.notifyDataSetChanged();
    }
}
