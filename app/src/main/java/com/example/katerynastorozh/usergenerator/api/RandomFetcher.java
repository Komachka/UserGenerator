package com.example.katerynastorozh.usergenerator.api;

import android.net.Uri;
import android.util.Log;

import com.example.katerynastorozh.usergenerator.activityes.FetchDataCallbackInterface;
import com.example.katerynastorozh.usergenerator.util.UserItem;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class RandomFetcher {
    private static final String LOG_TAG = RandomFetcher.class.getSimpleName();
    private static final String API_URL = "https://randomuser.me/api/";
    private List<UserItem> userItems;
    private OkHttpClient okHttpClient;
    private FetchDataCallbackInterface callbackInterface;


    public RandomFetcher(FetchDataCallbackInterface callbackInterface, List<UserItem> userItems) {
        this.okHttpClient = new OkHttpClient();
        this.callbackInterface = callbackInterface;
        this.userItems = userItems;
    }

    Call get(String url, Callback callback) {
        Request request = new Request.Builder()
                .url(url)
                .build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(callback);
        return call;
    }


    private Callback getPageCallback = new Callback() {
        @Override
        public void onFailure(Call call, IOException e) {
            Log.e(LOG_TAG, e.getMessage());
        }

        @Override
        public void onResponse(Call call, Response response) throws IOException {
            String jsonString = response.body().string();
            try {
                JSONObject jsonObjectBody = new JSONObject(jsonString);
                parseJSON(jsonObjectBody);
                callbackInterface.fetchDataCallback(userItems);
            } catch (JSONException e) {
                Log.e(LOG_TAG, e.getMessage());
            }

        }
    };


    public void fetchUsers() {

        String url = Uri.parse(API_URL).buildUpon().appendQueryParameter("results", "20").build().toString();
        Call callFirstPage = get(url, getPageCallback);



    }

    public void loadMore(int currentPage)
    {
        String url = Uri.parse(API_URL).buildUpon()
                .appendQueryParameter("page", String.valueOf(currentPage))
                .appendQueryParameter("results", "20")
                .appendQueryParameter("seed", "abc")
                .build().toString();
        Call callNextPage = get(url, getPageCallback);

    }

    private void parseJSON(JSONObject jsonObjectBody) throws JSONException
    {
        JSONArray results = jsonObjectBody.getJSONArray("results");
        for (int i = 0; i <results.length() ; i++) {
            JSONObject name = results.getJSONObject(i).getJSONObject("name");
            UserItem userItem = new UserItem();
            setName(userItem, name);

            userItem.setGender(results.getJSONObject(i).get("gender").toString());

            JSONObject registered = results.getJSONObject(i).getJSONObject("registered");
            setBirthday(userItem, registered);

            userItem.setPhoneNumber(results.getJSONObject(i).get("phone").toString());
            userItem.setEmail(results.getJSONObject(i).get("email").toString());
            JSONObject picture = results.getJSONObject(i).getJSONObject("picture");
            userItem.setUrlThumbnail(picture.get("thumbnail").toString());
            userItem.setUrlLarge(picture.get("large").toString());

            userItems.add(userItem);
            //Log.d(LOG_TAG, "userItems after added " + userItems.size());
        }
        //Log.d(LOG_TAG, "userItems after loop " + userItems.size());
    }

    private void setBirthday(UserItem userItem, JSONObject registered) throws JSONException {
        userItem.setDateOfBirth(registered.get("date").toString());

    }

    private void setName(UserItem item, JSONObject name)throws JSONException {
        item.setFirstName(name.get("first").toString());
        item.setLastName(name.get("last").toString());
    }

}
