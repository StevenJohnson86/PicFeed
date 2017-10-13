package Model;

import android.graphics.Bitmap;

/**
 * Created by steven on 10/9/17.
 */

public class PicItem {
    //store img/uri/path
    //store date uploaded
    //store text description
    //store user?
    private Bitmap mImage;
    private String mCaption;
//    private String mDateUploaded;
//    private User mItemOwner;
    public PicItem(Bitmap bmp, String caption){
        this.mImage = bmp;
        this.mCaption = caption;
    }

    public Bitmap getPic(){
        return mImage;
    }
    public String getCaption(){
        return mCaption;
    }
}
