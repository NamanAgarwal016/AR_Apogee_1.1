package com.bitspilani.apogeear.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bitspilani.apogeear.Models.MoreModel;
import com.bitspilani.apogeear.R;

import java.util.List;

public class MoreAdapter extends RecyclerView.Adapter<MoreAdapter.MoreViewHolder> {

    private Context context;
    private List<MoreModel> moreList;

    public MoreAdapter(Context context, List<MoreModel> moreList) {
        this.context = context;
        this.moreList = moreList;
    }

    @NonNull
    @Override
    public MoreViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.more_card_view,null);
        return new MoreViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MoreViewHolder holder, int position) {
        MoreModel moreModel = moreList.get(position);

        holder.textView.setText(moreModel.getName());
        holder.imageView.setImageDrawable(context.getResources().getDrawable(moreModel.getImage()));

    }

    @Override
    public int getItemCount() {
        return moreList.size();
    }

    class MoreViewHolder extends RecyclerView.ViewHolder{

        TextView textView;
        ImageView imageView;

        public MoreViewHolder(@NonNull View itemView) {
            super(itemView);

            textView = itemView.findViewById(R.id.more);
            imageView = itemView.findViewById(R.id.mark);
        }
    }
}


