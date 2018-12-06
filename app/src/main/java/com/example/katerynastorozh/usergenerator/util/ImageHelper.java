package com.example.katerynastorozh.usergenerator.util;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.widget.ImageView;

import com.example.katerynastorozh.usergenerator.R;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;
import com.makeramen.roundedimageview.RoundedTransformationBuilder;

public class ImageHelper {
    Context context;

    public ImageHelper(Context context) {
        this.context = context;
    }


    public void loadImageToView(String URL, ImageView view)
    {
        Transformation transformation = new RoundedTransformationBuilder()
                .cornerRadiusDp(20)
                .borderColor(ContextCompat.getColor(context, R.color.imageBorder))
                .borderWidthDp(1)
                .oval(false)
                .build();
        Picasso.with(context).load(URL).placeholder(R.color.background)
                .transform(transformation).into(view);
    }
}
