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

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class videofolderadapter extends RecyclerView.Adapter<videofolderadapter.viewHolder> {
    private Context mcontext;
    static ArrayList<videofiles> foldervideoFiles;

    public videofolderadapter(Context context, ArrayList<videofiles> foldervideoFiles) {
        this.mcontext = context;
        this.foldervideoFiles = foldervideoFiles;
    }
    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View  view = LayoutInflater.from(mcontext).inflate(R.layout.videoitem,parent,false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull videofolderadapter.viewHolder holder, int position) {
        videofiles current = foldervideoFiles.get(position);
        String image = current.getPath();
        holder.videoFilename.setText(current.getTitle());
        Glide.with(mcontext).load(image).into(holder.thumbImage);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mcontext,PlayerActivity.class);
                intent.putExtra("position",position);
                intent.putExtra("sender","FolderIsSending");
                mcontext.startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return foldervideoFiles.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder{
        ImageView thumbImage,more;
        TextView video_duration,videoFilename;
        public viewHolder(@NonNull View itemView) {
            super(itemView);
            thumbImage = itemView.findViewById(R.id.thumbimage);
            more = itemView.findViewById(R.id.more);
            video_duration = itemView.findViewById(R.id.video_duration);
            videoFilename = itemView.findViewById(R.id.videofilename);
        }
    }
}
