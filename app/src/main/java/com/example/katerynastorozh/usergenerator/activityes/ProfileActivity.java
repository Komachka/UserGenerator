package com.example.katerynastorozh.usergenerator.activityes;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.katerynastorozh.usergenerator.R;
import com.example.katerynastorozh.usergenerator.util.ImageHelper;
import com.example.katerynastorozh.usergenerator.util.UserItem;

public class ProfileActivity extends AppCompatActivity {
    private static final String LOG_TAG = ProfileActivity.class.getSimpleName();
    private static final String MALE = "male";
    private static final String FEMALE = "female";
    private ImageView icon;
    private TextView firstLastName;
    private TextView telephone;
    private TextView email;
    private TextView dateOfBirth;
    private TextView gender;
    private String[] PERMISSION_CALL = {Manifest.permission.CALL_PHONE};
    private static final int PERMISSION_REQUEST = 123;
    private UserItem userItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        getSupportActionBar().setTitle(R.string.profile_title);
        init();

        Bundle bundle = getIntent().getExtras();
        userItem = (UserItem) bundle.getSerializable(getResources().getString(R.string.USER_ITEM));
        ImageHelper helper = new ImageHelper(this);
        helper.loadImageToView(userItem.getUrlLarge(), icon);
        firstLastName.setText(userItem.getFirstLastName());
        telephone.setText(userItem.getPhoneNumber());
        email.setText(userItem.getEmail());
        dateOfBirth.setText(userItem.getDateOfBirth());
        gender.setText(userItem.getGender());
        if (userItem.getGender().equals(MALE))
            gender.setTextColor(getResources().getColor(R.color.male));
        else
            gender.setTextColor(getResources().getColor(R.color.female));


        telephone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!hasPermissions()) {
                    ActivityCompat.requestPermissions(ProfileActivity.this, PERMISSION_CALL, PERMISSION_REQUEST);
                    return;
                } else {
                    Log.d(LOG_TAG, getString(R.string.aplication_has_permission));
                    call();
                }

            }
        });
    }

    private void init() {
        icon = findViewById(R.id.profile_icon);
        firstLastName = findViewById(R.id.first_last_name);
        telephone = findViewById(R.id.phone);
        email = findViewById(R.id.email);
        dateOfBirth = findViewById(R.id.date_of_birth);
        gender = findViewById(R.id.gender);
    }

    private boolean hasPermissions() {

        if (ActivityCompat.checkSelfPermission(this, PERMISSION_CALL[0]) != PackageManager.PERMISSION_GRANTED) {
            Log.d(LOG_TAG, PERMISSION_CALL[0]);
            return false;
        }

        return true;
    }



    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == PERMISSION_REQUEST) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Log.d(LOG_TAG, getString(R.string.permission_grant));
                if (!hasPermissions())
                    return;
                }
                call();
            } else {
                Log.d(LOG_TAG,getString(R.string.permission_denied));

        }
    }


    @SuppressLint("MissingPermission")
    private void call()
    {
        Intent telIntent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + userItem.getPhoneNumber()));
        if (telIntent.resolveActivity(getPackageManager()) != null) {
            if (!hasPermissions())
                return;
        }
            startActivity(telIntent);
        }

}
