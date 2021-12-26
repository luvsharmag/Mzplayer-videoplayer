package com.example.mzplayer;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class folderadapter extends RecyclerView.Adapter<folderadapter.viewholder> {
    private Context mcontext;
    private ArrayList<String> mfoldername;
    private ArrayList<videofiles> mvideofiles;

    public folderadapter(Context context, ArrayList<String> foldername, ArrayList<videofiles> videofiles) {
        this.mcontext = context;
        this.mfoldername = foldername;
        this.mvideofiles = videofiles;
    }

    @NonNull
    @Override
    public folderadapter.viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mcontext).inflate(R.layout.folder_item,parent,false);
        return new viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull folderadapter.viewholder holder, int position) {
        try{
            int index = mfoldername.get(position).lastIndexOf("/");
            String foldername = mfoldername.get(position).substring(index+1);
            holder.foldername.setText(foldername);
            holder.countfiles.setText(String.valueOf(mfoldername.get(position)));
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mcontext,folderactivity.class);
                    intent.putExtra("foldername",foldername);
                    mcontext.startActivity(intent);
                }
            });
       }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return mvideofiles.size();
    }

    public static class viewholder extends RecyclerView.ViewHolder{
        ImageView folderimage;
        TextView foldername,countfiles;
        public viewholder(@NonNull View itemView) {
            super(itemView);
            folderimage = itemView.findViewById(R.id.folderimage);
            foldername = itemView.findViewById(R.id.foldername);
            countfiles = itemView.findViewById(R.id.countfiles);

        }
    }
    int nof(String foldername){
        int countfiles=0;
        for(videofiles videofiles : mvideofiles){
            if(videofiles.getPath().substring(0,videofiles.getPath().lastIndexOf("/"))
                .endsWith(foldername)){
                countfiles++;
            }
        }
        return 0;
    }
}
