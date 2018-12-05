package com.example.katerynastorozh.usergenerator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.katerynastorozh.usergenerator.util.UserItem;

import java.util.ArrayList;
import java.util.List;

public class AllUsersActivity extends AppCompatActivity implements FetchDataCallbackInterface {

    private static final String LOG_TAG = AllUsersActivity.class.getSimpleName();
    private List<UserItem> userItems;
    private static final String API_URL = "https://randomuser.me/api/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_users);
        RandomFetcher fetcher = new RandomFetcher(this);
        fetcher.fetchUsers();
        }

    @Override
    public void fetchDataCallback(List<UserItem> userItems) {
        this.userItems = userItems;
        for (UserItem item: userItems) {
            Log.d(LOG_TAG, item.toString());

        }
    }
}
