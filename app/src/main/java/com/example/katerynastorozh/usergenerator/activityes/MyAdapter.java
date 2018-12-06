package com.example.katerynastorozh.usergenerator.activityes;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.katerynastorozh.usergenerator.R;
import com.example.katerynastorozh.usergenerator.util.ImageHelper;
import com.example.katerynastorozh.usergenerator.util.UserItem;

import java.util.List;

class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    private List<UserItem> userItems;
    private ImageHelper helper;
    private OnItemClickListener clickListener;

    public MyAdapter(@NonNull List<UserItem> userItems, AllUsersActivity activity, OnItemClickListener clickListener) {
        this.userItems = userItems;
        this.clickListener = clickListener;
        this.helper = new ImageHelper(activity.getApplicationContext());
    }

    public interface OnItemClickListener {
        void onItemClick(UserItem userItem);
    }


    public class MyViewHolder extends RecyclerView.ViewHolder{

        public View view;
        public ImageView icon;
        public TextView firstLastName;
        public ImageView gender;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            view = itemView;
            icon = itemView.findViewById(R.id.icon);
            firstLastName = itemView.findViewById(R.id.text1);
            gender = itemView.findViewById(R.id.gender_icon);
        }


    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item, viewGroup, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);

        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder myViewHolder, final int position) {
        helper.loadImageToView(userItems.get(position).getUrlThumbnail(), myViewHolder.icon);
        myViewHolder.firstLastName.setText(userItems.get(position).getFirstName() + " " + userItems.get(position).getLastName());
        if (userItems.get(position).getGender().equals("male"))
        {
            myViewHolder.gender.setBackground(myViewHolder.gender.getContext().getResources().getDrawable(R.drawable.round_borders));
        }
        else
        {
            myViewHolder.gender.setBackground(myViewHolder.gender.getContext().getResources().getDrawable(R.drawable.round_borders_f));
        }
        myViewHolder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickListener.onItemClick(userItems.get(position));
            }
        });

    }

    @Override
    public int getItemCount() {
        return userItems.size();
    }


}
