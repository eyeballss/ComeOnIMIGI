package me.blog.eyeballs.comeonimigi;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

import java.io.InputStream;

public class ShowWebImage extends AsyncTask<String, Void, Bitmap> {
    private ImageView bmImage;
    private boolean scaledImage = false;

    public ShowWebImage setImageView(ImageView imageView) {
        this.bmImage = imageView;
        return this;
    }

    public ShowWebImage setScaledImage(boolean scaledImage) {
        this.scaledImage = scaledImage;
        return this;
    }

    protected Bitmap doInBackground(String... urls) {

        String urldisplay = urls[0];
        Bitmap mIcon11 = null;
        try {
            InputStream in = new java.net.URL(urldisplay).openStream();

            if (scaledImage) {
                BitmapFactory.Options options = new BitmapFactory.Options();
                options.inSampleSize = 2;
                mIcon11 = BitmapFactory.decodeStream(in, null, options);
            } else {
                mIcon11 = BitmapFactory.decodeStream(in);
            }
        } catch (Exception e) {
            Log.e("Error", e.getMessage());
            e.printStackTrace();
        }
        return mIcon11;
    }

    private Bitmap getResizedBitmap(Bitmap bm, int newWidth, int newHeight) {
        int width = bm.getWidth();
        int height = bm.getHeight();
        float scaleWidth = ((float) newWidth) / width;
        float scaleHeight = ((float) newHeight) / height;
        // CREATE A MATRIX FOR THE MANIPULATION
        Matrix matrix = new Matrix();
        // RESIZE THE BIT MAP
        matrix.postScale(scaleWidth, scaleHeight);

        // "RECREATE" THE NEW BITMAP
        Bitmap resizedBitmap = Bitmap.createBitmap(
                bm, 0, 0, width, height, matrix, false);
        bm.recycle();
        return resizedBitmap;
    }

    protected void onPostExecute(Bitmap result) {
        if(scaledImage){//if true, the image size would be small.
            result= getResizedBitmap(result, 500,300);
        }

        bmImage.setImageBitmap(result);
    }
}