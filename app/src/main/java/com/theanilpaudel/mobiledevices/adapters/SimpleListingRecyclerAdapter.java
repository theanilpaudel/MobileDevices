package com.theanilpaudel.mobiledevices.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.theanilpaudel.mobiledevices.R;
import com.theanilpaudel.mobiledevices.details.DeviceDetailActivity;
import com.theanilpaudel.mobiledevices.utils.Brand;

import java.util.List;

import timber.log.Timber;


/**
 * Created by nitv on 18/03/16.
 */
public class SimpleListingRecyclerAdapter extends RecyclerView.Adapter<SimpleListingRecyclerAdapter.ViewHolder> {
    public List<Brand> brandList;
    private Context context;
    Activity activity;

    public SimpleListingRecyclerAdapter(Context context, List<Brand> brandList) {
        this.brandList = brandList;
        this.context = context;
        activity = (Activity) context;
    }

    @Override
    public SimpleListingRecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.simple_listing_single, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {


        holder.tvTitle.setText(brandList.get(position).getName());

    }

    @Override
    public int getItemCount() {
        return brandList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        protected TextView tvTitle;

        public ViewHolder(View view) {
            super(view);
            itemView.setClickable(true);
            itemView.setOnClickListener(this);
            this.tvTitle = (TextView) view.findViewById(R.id.TitleTV);
        }

        @Override
        public void onClick(View v) {

            int position = getAdapterPosition();

            Timber.d("news_url in adapter %s",brandList.get(position).getID());
            Intent intent = new Intent(context, DeviceDetailActivity.class);
            intent.putExtra("brand", brandList.get(position).getName());
            context.startActivity(intent);

        }


    }


}
