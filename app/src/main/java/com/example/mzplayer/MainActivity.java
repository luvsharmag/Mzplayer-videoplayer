package com.example.mzplayer;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    public static final int PERMISSONS_REQUEST_CODE =200;
    static ArrayList<videofiles> videofiles = new ArrayList<>();
    static ArrayList<String> folderlist = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        requestPermissions();
        bottomNavigationView = findViewById(R.id.bnavbar);
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch(item.getItemId())
                {
                    case R.id.folderlist:
                        Toast.makeText(MainActivity.this, "folder", Toast.LENGTH_SHORT).show();
                        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.mainfragment,new FolderFragment());
                        fragmentTransaction.commit();
                        item.setChecked(true);
                        break;
                    case R.id.listfiles:
                        Toast.makeText(MainActivity.this, "files", Toast.LENGTH_SHORT).show();
                        FragmentTransaction fragmentTransaction2 = getSupportFragmentManager().beginTransaction();
                        fragmentTransaction2.replace(R.id.mainfragment,new FilesFragment());
                        fragmentTransaction2.commit();
                        item.setChecked(true);
                        break;
                }
                return false;
            }
        });
    }
    private boolean checkPermission(){
        int result = ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.READ_EXTERNAL_STORAGE);
        return result == PackageManager.PERMISSION_GRANTED;
    }
    private void requestPermissions(){
        if(checkPermission()){
            Toast.makeText(this, "permission granted", Toast.LENGTH_SHORT).show();
            videofiles = getVideofiles(this);
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.mainfragment,new FolderFragment());
            fragmentTransaction.commit();
        }else{
            requestPermission();
        }
    }
    private void requestPermission(){
        ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},PERMISSONS_REQUEST_CODE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull  int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == PERMISSONS_REQUEST_CODE){
            if(grantResults[0] == PackageManager.PERMISSION_GRANTED){
                Toast.makeText(this, "permission Granted", Toast.LENGTH_SHORT).show();
                videofiles = getVideofiles(this);
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.mainfragment,new FolderFragment());
                fragmentTransaction.commit();
            }else{
                ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},PERMISSONS_REQUEST_CODE);
            }
        }

    }
    public ArrayList<videofiles> getVideofiles(Context context){
        ArrayList<videofiles> tempvideofiles = new ArrayList<>();
        Uri uri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
        String[] projection = {
                MediaStore.Video.Media._ID,
                MediaStore.Video.Media.DATA,
                MediaStore.Video.Media.TITLE,
                MediaStore.Video.Media.SIZE,
                MediaStore.Video.Media.DATE_ADDED,
                MediaStore.Video.Media.DURATION,
                MediaStore.Video.Media.DISPLAY_NAME
        };
        Cursor cursor = context.getContentResolver().query(uri,projection,null,null,null);
        if(cursor!=null){
            while (cursor.moveToNext()){
                String id = cursor.getString(0);
                String path = cursor.getString(1);
                String title = cursor.getString(2);
                String size = cursor.getString(3);
                String dateAdded = cursor.getString(4);
                String duration = cursor.getString(5);
                String filename = cursor.getString(6);
                videofiles videofiles = new videofiles(id,path,title,filename,size,dateAdded,duration);
                Log.d("path","path");

                int firstindex  = path.lastIndexOf("/");
                String substring = path.substring(0,firstindex);
                if(!folderlist.contains(substring)){
                    folderlist.add(substring);
                }
                tempvideofiles.add(videofiles);
            }
            cursor.close();
        }
        return tempvideofiles;
    }
    }