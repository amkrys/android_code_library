package com.krys.codelibrary.adapters;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.krys.codelibrary.R;
import com.krys.codelibrary.customviews.CircleImageView;
import com.krys.codelibrary.models.UserResponse;
import com.krys.codelibrary.utils.CommonUtils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;


public class UsersAdapter extends RecyclerView.Adapter<UsersAdapter.ViewHolder> {

    private Context context;
    private List<UserResponse.Item> dataArrayList = new ArrayList<>();
    private EventListener eventListener;

    public UsersAdapter(Context context) {
        this.context = context;
    }

    public interface EventListener {
        void onItemClicked(int position);
    }

    public void setEventListener(EventListener eventListener) {
        this.eventListener = eventListener;
    }

    public void clear() {
        this.dataArrayList.clear();
        notifyDataSetChanged();
    }

    public void addAll(List<UserResponse.Item> dataArrayList) {
        this.dataArrayList.clear();
        this.dataArrayList.addAll(dataArrayList);
        notifyDataSetChanged();
    }

    public UserResponse.Item getItemData(int position) {
        return dataArrayList.get(position);
    }

    @Override
    public UsersAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_users, viewGroup, false);
        return new UsersAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(UsersAdapter.ViewHolder holder, final int position) {
        UserResponse.Item item = dataArrayList.get(position);
        if (item != null) {
            holder.textView.setText(item.owner.displayName);
            CommonUtils.showImage(context, item.owner.profileImage, holder.imageView);
        } else {
            CommonUtils.showToast(context, "Item is null");
        }
    }

    @Override
    public int getItemCount() {
        return dataArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView textView;
        private CircleImageView imageView;

        public ViewHolder(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.txtName);
            imageView = itemView.findViewById(R.id.imageView);
        }
    }
}
