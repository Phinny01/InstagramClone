package com.example.instagramclone;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.parse.ParseFile;

import org.parceler.Parcels;

import java.util.Date;

public class PostDetail extends AppCompatActivity {
    TextView tvTimeStamp;
    TextView tvName;
    TextView tvCaption;
    ImageView ivPictures;
    Post post;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_detail);
        tvCaption = findViewById(R.id.tvCaption);
        tvName = findViewById(R.id.tvName);
        context=this;
        post = Parcels.unwrap(getIntent().getParcelableExtra(Post.class.getSimpleName()));
        tvCaption.setText(post.getDescription());
        tvName.setText(post.getUser().getUsername());
        Date createdAt = post.getCreatedAt();
        String timeAgo = calculateTimeAgo(createdAt);
        tvTimeStamp = findViewById(R.id.tvTimeStamp);
        tvTimeStamp.setText(timeAgo);
        ivPictures = findViewById(R.id.ivPictures);
        ParseFile image = post.getImage();
        if (image != null) {
            Glide.with(context).load(image.getUrl()).into(ivPictures);
        }
        else{
            ivPictures.setImageResource(0);
        }

    }

    public static String calculateTimeAgo(Date createdAt) {

        int SECOND_MILLIS = 1000;
        int MINUTE_MILLIS = 60 * SECOND_MILLIS;
        int HOUR_MILLIS = 60 * MINUTE_MILLIS;
        int DAY_MILLIS = 24 * HOUR_MILLIS;

        try {
            createdAt.getTime();
            long time = createdAt.getTime();
            long now = System.currentTimeMillis();

            final long diff = now - time;
            if (diff < MINUTE_MILLIS) {
                return "just now";
            } else if (diff < 2 * MINUTE_MILLIS) {
                return "a minute ago";
            } else if (diff < 50 * MINUTE_MILLIS) {
                return diff / MINUTE_MILLIS + " m";
            } else if (diff < 90 * MINUTE_MILLIS) {
                return "an hour ago";
            } else if (diff < 24 * HOUR_MILLIS) {
                return diff / HOUR_MILLIS + " h";
            } else if (diff < 48 * HOUR_MILLIS) {
                return "yesterday";
            } else {
                return diff / DAY_MILLIS + " d";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "";
    }
}
