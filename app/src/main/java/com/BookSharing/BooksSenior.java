package com.BookSharing;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.Map;


public class BooksSenior extends AppCompatActivity {

    private static final int PICK_IMAGE_REQUEST=1;
    private Button mButtonChooseImage;
    private Button mButtonUpload;
    //private TextView mTextViewShowUploads;
    private EditText mEditTextBookname,mEditTextAuthorname,mEditTextDetails,mEditTextNO;
    private RadioGroup mRadioButton;

    private ImageView mImageView;
    private ProgressBar mProgressBar;
    private StorageReference mStorageRef;
    private DatabaseReference mDatabadeRef;
    private StorageTask mUploadTask;

    private Uri mImageUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_books_senior);

        mButtonUpload=findViewById(R.id.button);
        //mTextViewShowUploads=findViewById(R.id.showsubmit);
        mEditTextBookname=findViewById(R.id.editText5);
        mEditTextAuthorname=findViewById(R.id.editText7);
        mEditTextDetails=findViewById(R.id.editText11);
        mEditTextNO=findViewById(R.id.editText3);
        mRadioButton=findViewById(R.id.radioGroup);
        mImageView=findViewById(R.id.imageView3);
        mProgressBar=findViewById(R.id.progressbar1);
        mStorageRef= FirebaseStorage.getInstance().getReference("Bookss");
        mDatabadeRef= FirebaseDatabase.getInstance().getReference("Books");

        mImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenFileChooser();
            }
        });
        mButtonUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mUploadTask!=null && mUploadTask.isInProgress())
                {
                    Toast.makeText(BooksSenior.this,"Upload in Progress",Toast.LENGTH_SHORT).show();
                }else {

                    uploadFile();
                }
            }
        });

        /*mTextViewShowUploads.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openImageActivity();
            }
        });*/
    }

    private void OpenFileChooser() {
        Intent intent=new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent,PICK_IMAGE_REQUEST);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==PICK_IMAGE_REQUEST && resultCode==RESULT_OK && data!=null && data.getData()!=null){
            mImageUri=data.getData();
            Picasso.get().load(mImageUri).into(mImageView);
        }
        {

        }

    }

    @Override
    public ContentResolver getContentResolver() {
        return super.getContentResolver();
    }

    private  String getFileExtension(Uri uri){
        ContentResolver cr=getContentResolver();
        MimeTypeMap mime=MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cr.getType(uri));

    }
    private void uploadFile(){
        if(mImageUri!=null){
            StorageReference fileReference=mStorageRef
                    .child("Books/"+System.currentTimeMillis()+"."+getFileExtension(mImageUri));
            mUploadTask=fileReference.putFile(mImageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    Handler handler=new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            mProgressBar.setProgress(0);
                        }
                    },5000);
                    Toast.makeText(BooksSenior.this,"Upload successfull",Toast.LENGTH_SHORT).show();
                    Map<String,String> ob1=new HashMap<>();
                    ob1.put("url",mStorageRef.getDownloadUrl().toString());
                    ob1.put("Book name",mEditTextBookname.getText().toString().trim());
                    ob1.put("Auther's Name",mEditTextAuthorname.getText().toString().trim());
                    ob1.put("Datails",mEditTextDetails.getText().toString().trim());
                    ob1.put("Mobile NO",mEditTextNO.getText().toString().trim());
                    String uploadId=mDatabadeRef.push().getKey();
                    mDatabadeRef.child(uploadId).setValue(ob1);
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(BooksSenior.this,e.getMessage(),Toast.LENGTH_SHORT).show();
                }
            }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                    double progress=(100.0*taskSnapshot.getBytesTransferred()/taskSnapshot.getTotalByteCount());
                    mProgressBar.setProgress((int) progress);
                }
            });
        }else
        {
            Toast.makeText(this," NO file Selected",Toast.LENGTH_SHORT).show();
        }
    }
   /* private void openImageActivity(){
        Intent intent=new Intent(this,donee_books.class);
        startActivity(intent);
    }*/
}