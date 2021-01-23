package com.krys.codelibrary.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.krys.codelibrary.R;
import com.krys.codelibrary.models.MoviesRoomModel;
import com.krys.codelibrary.utils.CommonUtils;

import java.util.ArrayList;
import java.util.List;

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.ItemViewHolder> {

    private Context context;
    private EventListener mEventListener;
    private List<MoviesRoomModel> movieList = new ArrayList<>();
    private int layout;

    public MoviesAdapter(Context context, List<MoviesRoomModel> movieList, @LayoutRes int layout) {
        this.context = context;
        this.movieList = movieList;
        this.layout = layout;
    }

    public interface EventListener {
        void onItemClicked(int position);
    }

    public void setEventListener(EventListener mEventListener) {
        this.mEventListener = mEventListener;
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemLayoutView = LayoutInflater.from(parent.getContext()).inflate(layout, parent, false);
        return new ItemViewHolder(itemLayoutView);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        MoviesRoomModel movie = movieList.get(position);
        holder.movieName.setText(movie.title);
        holder.releaseDate.setText(movie.releaseDate);
        CommonUtils.showImage(context, movie.thumbnail, holder.movieImage);
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {

        private ImageView movieImage;
        private TextView movieName;
        private TextView releaseDate;

        public ItemViewHolder(View itemView) {
            super(itemView);
            movieImage = itemView.findViewById(R.id.ivMoviePoster);
            movieName = itemView.findViewById(R.id.tvMovieName);
            releaseDate = itemView.findViewById(R.id.tvReleaseDate);
        }
    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }
}
