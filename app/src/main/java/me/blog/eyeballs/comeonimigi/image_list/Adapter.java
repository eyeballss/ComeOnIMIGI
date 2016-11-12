package me.blog.eyeballs.comeonimigi.image_list;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import me.blog.eyeballs.comeonimigi.R;
import me.blog.eyeballs.comeonimigi.ShowWebImage;

/**
 * Created by eye on 16. 11. 11.
 */
public class Adapter extends BaseAdapter {

    private ArrayList<String> imageArray;
    private LayoutInflater inflater;

    public Adapter(Activity context, List imageArray) {
        super();

        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.imageArray = (ArrayList<String>)imageArray;

    }

    @Override
    public int getCount() {
        return imageArray.size();
    }

    @Override
    public Object getItem(int i) {
        return imageArray.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    public static class ViewHolder {
        ImageView image_item_imageview;
    }

    public void remove(int position){
        imageArray.remove(position);
    }

    public void removeAll(){
        imageArray.clear();
    }

    public void setImageArray(List imageArray){
        this.imageArray = (ArrayList<String>)imageArray;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {

        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.image_list_item, null);

            holder.image_item_imageview  = (ImageView) convertView.findViewById(R.id.image_item_imageview);


            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        new ShowWebImage().setScaledImage(true).setImageView(holder.image_item_imageview).execute(imageArray.get(i));

        return convertView;
    }
}