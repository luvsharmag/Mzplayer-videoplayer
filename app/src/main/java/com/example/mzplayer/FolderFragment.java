package com.example.mzplayer;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import static com.example.mzplayer.MainActivity.folderlist;
import static com.example.mzplayer.MainActivity.videofiles;

public class FolderFragment extends Fragment {
    folderadapter folderadapter;
    RecyclerView recyclerView;
    View view;
    public FolderFragment() {

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_folder, container, false);
        recyclerView = view.findViewById(R.id.folderRecyclerview);
        if(folderlist!=null && folderlist.size()>0 && videofiles!=null){
            folderadapter = new folderadapter(getContext(), folderlist, videofiles);
            recyclerView.setAdapter(folderadapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,false));
        }
        return view;
    }
}