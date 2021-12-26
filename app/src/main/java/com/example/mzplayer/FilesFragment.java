package com.example.mzplayer;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import static com.example.mzplayer.MainActivity.videofiles;

public class FilesFragment extends Fragment {
    RecyclerView recyclerView;
    View view;
    videofolderadapter videorvadapter;
    public FilesFragment() {

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_files, container, false);
        recyclerView = view.findViewById(R.id.fileRecyclerview);
        if(videofiles != null && videofiles.size()>0){
            videorvadapter = new videofolderadapter(getContext(),videofiles);
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,false));
            recyclerView.setAdapter(videorvadapter);
        }
        return view;
    }
}