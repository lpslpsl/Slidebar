package com.example.lps.slidebar;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by dangxiaohui on 2017/1/11.
 */
public class RecycleAdapter extends RecyclerView.Adapter<RecycleAdapter.ViewHolder> {


    private Context context;
    private List<DataBean> list;

    public RecycleAdapter(Context context, List<DataBean> list) {

        this.context = context;
        this.list = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate( R.layout.item, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.name.setText(list.get(position).getName());
        holder.letter.setText(list.get(position).getTag());
        if (position == 0) {
            holder.letter.setVisibility(View.GONE);
        } else if (list.get(position - 1).getTag().equals(list.get(position).getTag())) {
            holder.letter.setVisibility(View.GONE);
        }else {
            holder.letter.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView name, letter;

        public ViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.name);
            letter = (TextView) itemView.findViewById(R.id.letter);
        }
    }
}
