package sjohnsoncf.picfeed;


import android.widget.BaseAdapter;
import java.util.ArrayList;

/**
 * Created by steven on 10/7/17.
 */

public abstract class PicListViewAdapter<T> extends BaseAdapter {
    private ArrayList<T> mPhotoUrlList;

    public PicListViewAdapter(ArrayList<T> objects){
        this.mPhotoUrlList = objects;
    }
    @Override
    public int getCount() {
        return mPhotoUrlList.size();
    }

    @Override
    public Object getItem(int i) {
        return mPhotoUrlList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

}
