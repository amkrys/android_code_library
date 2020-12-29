package com.krys.codelibrary.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.krys.codelibrary.R;
import com.krys.codelibrary.customviews.CircleImageView;
import com.krys.codelibrary.models.UserResponse;
import com.krys.codelibrary.utils.CommonUtils;

public class UsersAdapter extends PagedListAdapter<UserResponse.Item, UsersAdapter.ItemViewHolder> {

    private Context context;

    public UsersAdapter(Context context) {
        super(DIFF_CALLBACK);
        this.context = context;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.row_users, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        UserResponse.Item item = getItem(position);
        if (item != null) {
            holder.textView.setText(item.owner.displayName);
            CommonUtils.showImage(context, item.owner.profileImage, holder.imageView);
        } else {
            CommonUtils.showToast(context, "Item is null");
        }
    }

    private static DiffUtil.ItemCallback<UserResponse.Item> DIFF_CALLBACK =
            new DiffUtil.ItemCallback<UserResponse.Item>() {
                @Override
                public boolean areItemsTheSame(UserResponse.Item oldItem, UserResponse.Item newItem) {
                    return oldItem.questionId.equals(newItem.questionId);
                }

                @Override
                public boolean areContentsTheSame(UserResponse.Item oldItem, UserResponse.Item newItem) {
                    return oldItem.equals(newItem);
                }
            };

    public class ItemViewHolder extends RecyclerView.ViewHolder {

        TextView textView;
        CircleImageView imageView;

        public ItemViewHolder(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.txtName);
            imageView = itemView.findViewById(R.id.imageView);
        }
    }
}