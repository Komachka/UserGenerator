package com.example.katerynastorozh.usergenerator;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_users);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        UserItem item = new UserItem();
        item.setUrlThumbnail("https://randomuser.me/api/portraits/thumb/women/4.jpg");
        item.setFirstName("Vasya");
        item.setLastName("Pupkin");
        userItems.add(item);

        adapter = new MyAdapter(userItems, this, new MyAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(UserItem userItem) {
                Toast.makeText(AllUsersActivity.this, userItem.getFirstName(), Toast.LENGTH_LONG).show();
                Bundle bundle = new Bundle();
                bundle.putSerializable(getResources().getString(R.string.USER_ITEM), userItem);
                Intent intent = new Intent(AllUsersActivity.this, ProfileActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
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
                Toast.makeText(AllUsersActivity.this, "userItems.size()" + " In tun method = " + userItems.size(), Toast.LENGTH_LONG).show();
                UserItem item = new UserItem();
                item.setUrlThumbnail("https://randomuser.me/api/portraits/thumb/women/4.jpg");
                item.setFirstName("Vasya");
                item.setLastName("Pupkin2");
                userItems.add(item);


                AllUsersActivity.this.adapter.notifyDataSetChanged();
                setEmptyView();
            }
        });


    }


    private void setEmptyView() {
        /*if (userItems.isEmpty()) {
            recyclerView.setVisibility(View.GONE);
            emptyView.setVisibility(View.VISIBLE);
        }
        else {
            recyclerView.setVisibility(View.VISIBLE);
            emptyView.setVisibility(View.GONE);
        }*/
    }


}
