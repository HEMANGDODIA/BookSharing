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
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
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

public class ReferenceSenior extends AppCompatActivity {

    private static final int PICK_IMAGE_REQUEST=1;
    private Button mButtonUpload;
    private EditText mEditTextSubjectname,mEditTextDetails,mEditTextLink;
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
        setContentView(R.layout.activity_reference_senior);
        mButtonUpload=findViewById(R.id.button);

        mEditTextSubjectname=findViewById(R.id.editText5);
        mEditTextDetails=findViewById(R.id.editText7);
        mEditTextLink=findViewById(R.id.editText);
        mRadioButton=findViewById(R.id.radioGroup);
        mImageView=findViewById(R.id.imageView4);
        mProgressBar=findViewById(R.id.progressbar1);
        mStorageRef= FirebaseStorage.getInstance().getReference("Books");
        String uploadId= FirebaseAuth.getInstance().getCurrentUser().getUid();
        mDatabadeRef= FirebaseDatabase.getInstance().getReference().child("profileinfo").child(uploadId).child("Reference");


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
                    Toast.makeText(ReferenceSenior.this,"Upload in Progress",Toast.LENGTH_SHORT).show();
                }else {

                    uploadFile();
                }
            }
        });
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
            final StorageReference fileReference=mStorageRef.child(System.currentTimeMillis()+"."+getFileExtension(mImageUri));

            mUploadTask=fileReference.putFile(mImageUri);
            Task downloadURI = mUploadTask.continueWithTask(new Continuation() {
                @Override
                public Object then(@NonNull Task task) throws Exception {
                    return fileReference.getDownloadUrl();
                }
            }).addOnCompleteListener(new OnCompleteListener() {
                @Override
                public void onComplete(@NonNull Task task) {

                    if(task.isSuccessful()){
                        Map<String,String> ob1=new HashMap<>();
                        Toast.makeText(ReferenceSenior.this,"Upload successfull",Toast.LENGTH_SHORT).show();
                        //Task<Uri> downloadUri = taskSnapshot.getStorage().getDownloadUrl();
                        ob1.put("url",task.getResult().toString());
                        ob1.put("Subjectname",mEditTextSubjectname.getText().toString().trim());
                        ob1.put("Datails",mEditTextDetails.getText().toString().trim());
                        ob1.put("Link",mEditTextLink.getText().toString().trim());
                        String uploadId=mDatabadeRef.push().getKey();
                        mDatabadeRef.child(uploadId).setValue(ob1);

                    }

                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(ReferenceSenior.this,e.getMessage(),Toast.LENGTH_SHORT).show();
                }
            });
        }else
        {
            Toast.makeText(this," NO file Selected",Toast.LENGTH_SHORT).show();
        }
    }

}
