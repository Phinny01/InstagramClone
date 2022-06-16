package com.example.instagramclone;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseObject;

public class ParseApplication extends Application {

    public void onCreate() {

        super.onCreate();
        ParseObject.registerSubclass(Post.class);

        Parse.initialize(new Parse.Configuration.Builder(this)
                .
                applicationId("zV0kEDFHheN7FXpsXkwNNgkfFiDV2pInPlE9JuH3")
                .clientKey("PlBsZvnzBk4Gt1kEsSFL9eCVAbVQaWuT2mK4UkpI")
                .server("https://parseapi.back4app.com")
                .build()
        );
    }

}
