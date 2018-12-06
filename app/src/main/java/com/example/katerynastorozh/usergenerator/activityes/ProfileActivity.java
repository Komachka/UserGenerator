package com.example.katerynastorozh.usergenerator.activityes;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.katerynastorozh.usergenerator.R;
import com.example.katerynastorozh.usergenerator.util.ImageHelper;
import com.example.katerynastorozh.usergenerator.util.UserItem;

public class ProfileActivity extends AppCompatActivity {
    private static final String MALE = "male";
    private static final String FEMALE = "female";
    private ImageView icon;
    private TextView firstLastName;
    private TextView telephone;
    private TextView email;
    private TextView dateOfBirth;
    private TextView gender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        init();

        Bundle bundle = getIntent().getExtras();
        UserItem userItem = (UserItem) bundle.getSerializable(getResources().getString(R.string.USER_ITEM));
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
                //call to the permissions
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
}
