package sjohnsoncf.picfeed;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class PreparePostActivity extends AppCompatActivity {
    private static final String TAG = "PreparePostActivity";

    private Button mBtnCancel;
    private Button mBtnPost;
    private EditText mPhotoCaption;
    private ImageView mImgViewPostPreview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prepare_post);

        mBtnCancel = (Button) findViewById(R.id.btn_cancel_post);
        mBtnPost = (Button) findViewById(R.id.btn_post);
        mPhotoCaption = (EditText) findViewById(R.id.photo_caption);
        mImgViewPostPreview = (ImageView) findViewById(R.id.imgView_post_preview);

        attachClickListeners();
    }

    private void attachClickListeners(){
        mBtnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //goto PhotoPicker
            }
        });

        mBtnPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //post pic and caption to firebase/photofeed
                //goto feed
            }
        });
    }
}
