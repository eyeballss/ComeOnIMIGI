package me.blog.eyeballs.comeonimigi.image_detail_pager;


import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;

import me.blog.eyeballs.comeonimigi.R;
import me.blog.eyeballs.comeonimigi.ShowWebImage;

/**
 * Created by eye on 16. 11. 11.
 */
public class ImageViewpageAdapter extends PagerAdapter {
    ArrayList<String> imageUrlArray;

    LayoutInflater inflater;
    public ImageViewpageAdapter(LayoutInflater inflater, ArrayList<String> imageUrlArray) {
        // TODO Auto-generated constructor stub

        this.inflater=inflater;
        this.imageUrlArray = imageUrlArray;
    }

    public ImageViewpageAdapter(LayoutInflater inflater, String photo_url) {
        // TODO Auto-generated constructor stub

        this.inflater=inflater;
        this.imageUrlArray = new ArrayList<String>();
        this.imageUrlArray.add(photo_url);
    }

    @Override
    public int getCount() {
        return imageUrlArray.size();
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        // TODO Auto-generated method stub

        View view=null;

        view= inflater.inflate(R.layout.image_detail_pager_item, null);

        ImageView img= (ImageView)view.findViewById(R.id.image_detail_page_item_imageview);

        new ShowWebImage().setScaledImage(false).setImageView(img).execute(imageUrlArray.get(position));

        container.addView(view);

        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        // TODO Auto-generated method stub
        container.removeView((View)object);

    }


    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }
}
