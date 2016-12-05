package com.niuyi.mvp_news.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.niuyi.mvp_news.R;
import com.niuyi.mvp_news.utils.GlideImageLoader;

import butterknife.BindView;
import butterknife.ButterKnife;
import uk.co.senab.photoview.PhotoView;

public class PhotoViewActivity extends AppCompatActivity {

    @BindView(R.id.image)
    PhotoView mImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_view);
        ButterKnife.bind(this);

        String imageUrl = getIntent().getStringExtra("imageUrl");

        GlideImageLoader.displayImage(this,imageUrl,mImage);
    }

}
