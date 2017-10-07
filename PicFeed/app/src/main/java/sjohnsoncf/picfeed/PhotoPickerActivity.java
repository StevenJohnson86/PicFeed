package sjohnsoncf.picfeed;

import android.content.Intent;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

public class PhotoPickerActivity extends AppCompatActivity {
    private static final int REQUEST_IMAGE_CAPTURE = 1;
    private static final int REQUEST_CHOOSE_IMAGE = 2;

    private ImageView mImgPreview;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_picker);

        Intent takePicIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(takePicIntent,REQUEST_IMAGE_CAPTURE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK){
            //create tempfile
            data.getExtras().get("data");

            //populate imgview

            previewImage();

        } else if (requestCode == REQUEST_CHOOSE_IMAGE && resultCode == RESULT_OK){
            //populate imgview

            previewImage();
        }
    }

    public void attachClickListeners(){
        //take pic
            //start takePicIntent

        //choose pic
            //start choosePicIntent

        //post (leave for last)
            //if img capture, save to gal, then send to 'db'
            //else, send to 'db'
    }

    private void previewImage(){
        mImgPreview = (ImageView) findViewById(R.id.imageView_preview_pic);


    }
}
