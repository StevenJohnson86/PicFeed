package sjohnsoncf.picfeed;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PhotoPickerActivity extends AppCompatActivity {
    private static final String TAG = "PhotoPickerActivity";
    private static final int REQUEST_IMAGE_CAPTURE = 1;
    private static final int REQUEST_CHOOSE_IMAGE = 2;

    private ImageView mImgPreview;
    private Button mTakePicBtn;
    private Button mChoosePicBtn;
    private Button mPostPicBtn;

    private String mCurrentImagePath;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_picker);

        mTakePicBtn = (Button) findViewById(R.id.btn_take_pic);
        mChoosePicBtn = (Button) findViewById(R.id.btn_choose_pic);
        mPostPicBtn = (Button) findViewById(R.id.btn_post_pic);
        mImgPreview = (ImageView) findViewById(R.id.imageView_preview_pic);

        attachClickListeners();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK){

            mPostPicBtn.setVisibility(View.VISIBLE);
            mImgPreview.setVisibility(View.VISIBLE);
            previewImage(mCurrentImagePath);


        } else if (requestCode == REQUEST_CHOOSE_IMAGE && resultCode == RESULT_OK){

            try {
                Log.d(TAG, "onActivityResult: DATA*** : " + data);
                mPostPicBtn.setVisibility(View.VISIBLE);
                mImgPreview.setVisibility(View.VISIBLE);
                getGalleryFile(data);
//            previewImage(mCurrentImagePath);
            } catch (FileNotFoundException e){
                Log.d(TAG, "onActivityResult: error: " + e);
            }
        }
    }

    private void takePic(){
        try {
            File tempFile = getTempFile();
            Uri imgUri = FileProvider.getUriForFile(this, "sjohnsoncf.picfeed", tempFile);

            Intent takePicIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            takePicIntent.putExtra(MediaStore.EXTRA_OUTPUT, imgUri);
            startActivityForResult(takePicIntent, REQUEST_IMAGE_CAPTURE);
        } catch (IOException e){
            Log.d(TAG, "takePic: error: " + e);
        }
    }

    private void choosePic(){
        try {
            File tempFile = getTempFile();

            Uri imgUri = FileProvider.getUriForFile(this, "sjohnsoncf.picfeed", tempFile);
//android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI = location of external storage(where to pick from)?
            Intent choosePicIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            //comes back as stream? how do i know? Doesn't it return a Uri?
//            choosePicIntent.putExtra(MediaStore.EXTRA_OUTPUT, imgUri);
            //EXTRA OUTPUT , allows a uri for storage of requested img...
            startActivityForResult(choosePicIntent, REQUEST_CHOOSE_IMAGE);
        } catch (IOException e){
            Log.d(TAG, "choosePic: error: " + e);
        }
    }

    private void attachClickListeners(){
        //take pic
            //start takePicIntent
        mTakePicBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                takePic();
            }
        });

        //choose pic
            //start choosePicIntent
        mChoosePicBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                choosePic();
            }
        });

        //post (leave for last)
            //if img capture, save to gal, then send to 'db'
            //else, send to 'db'
        mPostPicBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                galleryAddPic(mCurrentImagePath);
                //eventually, send POST request to firebase,
                // goto picFeed or reset photoPicker
            }
        });
    }

    private File getTempFile() throws IOException {
//        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmm").format(new Date());
        File dir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File tempFile = File.createTempFile("JPEG_temp_img", ".jpg", dir);
        mCurrentImagePath = tempFile.getAbsolutePath();
        return tempFile;
    }

    private void getGalleryFile(Intent data) throws FileNotFoundException{
        //possible Inputs: galIntent data(uri), tempFilePath?
        //set String path from selected image uri?

//        File dir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
//        File temp = File.createTempFile("temp",".jpg",dir);

//        Uri galImgUri = data.getData();
//        mCurrentImagePath = galImgUri.;
        InputStream galleryFile = this.getContentResolver().openInputStream(data.getData());
        Bitmap bitmap = BitmapFactory.decodeStream(galleryFile);
        mImgPreview.setImageBitmap(bitmap);

//        return temp;
    }

    //is this needed?
    private void previewImage(String path){
        //build bmp?
        Bitmap imgPreview = BitmapFactory.decodeFile(path);
        mImgPreview.setImageBitmap(imgPreview);

    }

    private void galleryAddPic(String imgPath){
        Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        File currFile = new File(imgPath);
            //TODO:rename file?
        Uri fileUri = Uri.fromFile(currFile);
        mediaScanIntent.setData(fileUri);
        this.sendBroadcast(mediaScanIntent);
    }
}
