package com.example.mzplayer;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class folderactivity extends AppCompatActivity {
    RecyclerView recyclerView;
    videofolderadapter videofolderadapter;
    String myfoldername;
    ArrayList<videofiles> videofilesArrayList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_folderactivity);
        recyclerView = findViewById(R.id.folderactrv);
        myfoldername = getIntent().getStringExtra("FolderName");
        if(myfoldername!=null){
            videofilesArrayList = getVideofiles(this,myfoldername);
        }
        if(videofilesArrayList.size()>0){
            videofolderadapter = new videofolderadapter(this,videofilesArrayList);;
            recyclerView.setAdapter(videofolderadapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(this,RecyclerView.VERTICAL,false));
        }
    }
    public ArrayList<videofiles> getVideofiles(Context context,String Foldername){
        ArrayList<videofiles> tempvideofiles = new ArrayList<>();
        Uri uri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
        String[] projection = {
                MediaStore.Video.Media._ID,
                MediaStore.Video.Media.DATA,
                MediaStore.Video.Media.TITLE,
                MediaStore.Video.Media.SIZE,
                MediaStore.Video.Media.DATE_ADDED,
                MediaStore.Video.Media.DURATION,
                MediaStore.Video.Media.DISPLAY_NAME,
                MediaStore.Video.Media.BUCKET_DISPLAY_NAME
        };
        String selection = MediaStore.Video.Media.DATA + "like?";
        String[] SelectionArgs = new String[]{"%"+Foldername+"%"};
        try {
        Cursor cursor = context.getContentResolver().query(uri,projection,selection,SelectionArgs,null);
        if(cursor!=null) {
            while (cursor.moveToNext()) {
                String id = cursor.getString(0);
                String path = cursor.getString(1);
                String title = cursor.getString(2);
                String size = cursor.getString(3);
                String dateAdded = cursor.getString(4);
                String duration = cursor.getString(5);
                String filename = cursor.getString(6);
                String bucketName = cursor.getString(7);
                videofiles videofiles = new videofiles(id, path, title, filename, size, dateAdded, duration);
                Log.d("path", "path");
                if(Foldername.endsWith(bucketName)){
                   tempvideofiles.add(videofiles);
                }
            }
            cursor.close();
        }
        }catch (Exception e){
            e.printStackTrace();
        }

        return tempvideofiles;
    }
}