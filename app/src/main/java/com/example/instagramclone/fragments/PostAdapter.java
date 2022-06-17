package com.example.instagramclone.fragments;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.instagramclone.Post;
import com.example.instagramclone.PostDetail;
import com.example.instagramclone.R;
import com.parse.ParseFile;

import org.parceler.Parcels;

import java.util.List;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.ViewHolder> {

    private Context context;
    private List<Post> posts;
    public static final String TAG = "PostAdapter";

    public void clear() {

        posts.clear();
        notifyDataSetChanged();
    }

    public void addAll(List<Post> list) {

        posts.addAll(list);
        notifyDataSetChanged();
    }


    public PostAdapter(Context context, List<Post> posts) {

        this.context = context;
        this.posts = posts;
    }

    @NonNull
    @Override
    public PostAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.item_post, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PostAdapter.ViewHolder holder, int position) {

        Post post = posts.get(position);
        holder.bind(post);
    }


    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView tvUser;
        private ImageView ivPost;
        private TextView tvDescription;


        public ViewHolder(@NonNull View itemView) {


            super(itemView);
            tvDescription = itemView.findViewById(R.id.tvDescription);
            ivPost = itemView.findViewById(R.id.ivPost);
            tvUser = itemView.findViewById(R.id.tvUser);
            itemView.setOnClickListener(this);
        }

        public void bind(Post post) {

            tvDescription.setText(post.getDescription());
            tvUser.setText(post.getUser().getUsername());
            ParseFile image = post.getImage();
            if (image != null) {
                Glide.with(context).load(image.getUrl()).into(ivPost);
            }
            else{
                ivPost.setImageResource(0);
            }
        }

        @Override
        public void onClick(View v) {

            int position = getAdapterPosition();
            if (position != RecyclerView.NO_POSITION) {

                Log.d(TAG, "How come" + position);
                Post post = posts.get(position);
                Intent intent = new Intent(context, PostDetail.class);
                intent.putExtra(Post.class.getSimpleName(), Parcels.wrap(post));
                context.startActivity(intent);
            }
        }
    }

    @Override
    public int getItemCount() {

        return posts.size();
    }


}
