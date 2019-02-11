package com.huang.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.huang.bean.Work;
import com.huang.tomatowork.R;
import com.huang.tomatowork.TimeActivity;

import java.util.List;

public class PotatoAdapter extends RecyclerView.Adapter<PotatoAdapter.ViewHolder> {
    private Context mContext;
    private List<Work> works;

    public PotatoAdapter(List<Work> works) {
        this.works = works;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        TextView title;
        ImageView itemLogo;
        ImageView start;

        public ViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.title);
            itemLogo = (ImageView) view.findViewById(R.id.item_logo);
            start = (ImageView) view.findViewById(R.id.start);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mContext == null) {
            mContext = parent.getContext();
        }
        View view = LayoutInflater.from(mContext).inflate(R.layout.work_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final Work work = works.get(position);
        holder.title.setText(work.getTitle());

        switch (position % 3) {
            case 0:
                Glide.with(mContext).load(R.drawable.month).into(holder.itemLogo);
                break;
            case 1:
                Glide.with(mContext).load(R.drawable.week).into(holder.itemLogo);
                break;
            case 2:
                Glide.with(mContext).load(R.drawable.once2).into(holder.itemLogo);
                break;
            default:
        }

        holder.start.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, TimeActivity.class);
                mContext.startActivity(intent);
            }
        });

        holder.title.setOnLongClickListener(new View.OnLongClickListener() {

            @Override
            public boolean onLongClick(View v) {
                return false;
            }
        });


    }

    @Override
    public int getItemCount() {
        return works.size();
    }

    public void removeItem(int pos) {
        works.remove(pos);
        notifyItemRemoved(pos);
    }
}
