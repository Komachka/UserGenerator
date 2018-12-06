package com.example.katerynastorozh.usergenerator.activityes;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.katerynastorozh.usergenerator.FetchDataCallbackInterface;
import com.example.katerynastorozh.usergenerator.R;
import com.example.katerynastorozh.usergenerator.RandomFetcher;
import com.example.katerynastorozh.usergenerator.util.UserItem;

import java.util.ArrayList;
import java.util.List;

public class AllUsersActivity extends AppCompatActivity implements FetchDataCallbackInterface
{

    private static final String LOG_TAG = AllUsersActivity.class.getSimpleName();
    private List<UserItem> userItems = new ArrayList<>();
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private View emptyView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_users);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        emptyView = findViewById(R.id.empty_view);
        adapter = new MyAdapter(userItems, this, new MyAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(UserItem userItem) {
                Toast.makeText(AllUsersActivity.this, userItem.getFirstName(), Toast.LENGTH_LONG).show();
                Bundle bundle = new Bundle();
                bundle.putSerializable(getResources().getString(R.string.USER_ITEM), userItem);
                Intent intent = new Intent(AllUsersActivity.this, ProfileActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);

                //overridePendingTransition(R.anim.fadein, R.anim.fadeout);
            }
        });
        recyclerView.setAdapter(adapter);

        RandomFetcher fetcher = new RandomFetcher(this, userItems);
        fetcher.fetchUsers();




        }

    @Override
    public void fetchDataCallback(final List<UserItem> userItems) {
        this.userItems = userItems;
        for (UserItem item: userItems) {
            Log.d(LOG_TAG, item.toString());
            }
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                AllUsersActivity.this.adapter.notifyDataSetChanged();
                setEmptyView();
            }
        });


    }


    private void setEmptyView() {
        if (userItems.isEmpty()) {
            recyclerView.setVisibility(View.GONE);
            emptyView.setVisibility(View.VISIBLE);
        }
        else {
            recyclerView.setVisibility(View.VISIBLE);
            emptyView.setVisibility(View.GONE);
        }
    }


}
