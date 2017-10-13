package sjohnsoncf.picfeed;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;

import MockData.MockData;
import Model.PicItem;


public class PhotoFeedActivity extends AppCompatActivity {
    private static final String TAG = "PhotoFeedActivity";
    //select urls OR pic-item for DEV ENV
//    private static final String DEV_ENV = "URL";
    private static final String DEV_ENV = "PIC_ITEM";
    public static ArrayList<String> mFeedUrls = new ArrayList<>();
    public static ArrayList<PicItem> mPicItems = new ArrayList<>();
    private ListView mPhotoFeed;
    private Button mAddPhotoBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_feed);

        //fill mFeedUrls with mockData
        MockData.mockUrls();


        mPhotoFeed = (ListView) findViewById(R.id.listview_photo_feed);
        mAddPhotoBtn = (Button) findViewById(R.id.btn_new_photo);

        attachClickListeners();
        configureLayout();
    }

    private void configureLayout(){

        if(DEV_ENV == "URL") {
            mPhotoFeed.setAdapter(new PicListViewAdapter<String>(mFeedUrls) {
                @Override
                public View getView(int i, View view, ViewGroup viewGroup) {


                    //use recycle view?
                    View picItemView = getLayoutInflater().inflate(R.layout.photo_item, null);
                    ImageView picView = picItemView.findViewById(R.id.imageView_photo_item);
                    //pass DownloadTask 1 url (send through .execute(url))
                    //is the DlImageTask getting info from the parent PicListViewAdapter Arr.List or
                    // the Ar.List from PhotoFeedActivity?

                    new DownloadImageTask(picView).execute(mFeedUrls.get(i));

                    return picItemView;
                }
            });
        } else if(DEV_ENV == "PIC_ITEM"){
            mPhotoFeed.setAdapter(new PicListViewAdapter<PicItem>(mPicItems) {
                @Override
                public View getView(int i, View view, ViewGroup viewGroup) {
                    PicItem currentItem = mPicItems.get(i);
                    View picItemView = getLayoutInflater().inflate(R.layout.photo_item, null);
                    ImageView picView = picItemView.findViewById(R.id.imageView_photo_item);
                    EditText caption = picItemView.findViewById(R.id.photo_item_caption);
                    picView.setImageBitmap(currentItem.getPic());
                    caption.setText(currentItem.getCaption());
                    return picItemView;
                }
            });
        }
    }

    private void attachClickListeners(){
        final Context ctx = this;
        mAddPhotoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent addPhoto = new Intent(ctx, PhotoPickerActivity.class);
                startActivity(addPhoto);
            }
        });
    }
}
