package com.example.katerynastorozh.usergenerator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.example.katerynastorozh.usergenerator.util.ImageHelper;
import com.example.katerynastorozh.usergenerator.util.UserItem;

public class ProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        ImageView icon = findViewById(R.id.profile_icon);
        ImageHelper helper = new ImageHelper(this);

        Bundle bundle = getIntent().getExtras();
        UserItem userItem = (UserItem) bundle.getSerializable(getResources().getString(R.string.USER_ITEM));
        helper.loadImageToView(userItem.getUrlLarge(), icon);
    }
}
