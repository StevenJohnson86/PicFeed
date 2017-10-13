package sjohnsoncf.picfeed;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import java.io.InputStream;
import java.net.MalformedURLException;

/**
 * Created by steven on 10/7/17.
 */

public class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
    private static final String TAG = "DownloadImageTask";
    private ImageView mPicView;

    public DownloadImageTask(ImageView imgView){
        this.mPicView = imgView;

    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected Bitmap doInBackground(String... strings) {
        Bitmap bmp = null;
        try {
            InputStream image = new java.net.URL(strings[0]).openStream();
            bmp = BitmapFactory.decodeStream(image);
        } catch (Exception e){
            Log.d(TAG, "doInBackground: error" + e);
        }
        return bmp;
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        if(bitmap != null) {
            super.onPostExecute(bitmap);
            mPicView.setImageBitmap(bitmap);
        }

    }
}
