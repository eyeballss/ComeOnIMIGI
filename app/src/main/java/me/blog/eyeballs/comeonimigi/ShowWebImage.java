package me.blog.eyeballs.comeonimigi;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

import java.io.InputStream;

public class ShowWebImage extends AsyncTask<String, Void, Bitmap> {
    ImageView bmImage;

    public ShowWebImage setImageView(ImageView imageView) {
        this.bmImage = imageView;
        return this;
    }

    protected Bitmap doInBackground(String... urls) {

        String urldisplay = urls[0];
        Bitmap mIcon11 = null;
        try {
            InputStream in = new java.net.URL(urldisplay).openStream();
            mIcon11 = BitmapFactory.decodeStream(in);
        } catch (Exception e) {
            Log.e("Error", e.getMessage());
            e.printStackTrace();
        }
        return mIcon11;
    }

    protected void onPostExecute(Bitmap result) {
        bmImage.setImageBitmap(result);
    }
}