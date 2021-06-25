package com.otaliastudios.cameraview.demo;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.io.File;
import java.util.List;

public class GalleryAdapter extends RecyclerView.Adapter<GalleryAdapter.CustomViewHolder> {

    private final List<String> mFileList;
    private DeleteListener longClickListener;
    private final Activity mActivity;

    public GalleryAdapter(Activity activity, List<String> fileList, DeleteListener longClickListener) {
        mActivity = activity;
        mFileList = fileList;
        this.longClickListener = longClickListener;
    }


    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.gallery_item, parent, false);
        return new CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final CustomViewHolder holder, final int position) {
        String filePath = mFileList.get(position);
        Glide
                .with(mActivity)
                .load(filePath)
                .apply(RequestOptions.centerCropTransform())
                .into(holder.imageResource);

        final int itemPosition = holder.getAdapterPosition();
        if (filePath.endsWith("mp4")) {
            holder.play.setVisibility(View.VISIBLE);
        } else {
            holder.play.setVisibility(View.GONE);
        }
        holder.imageResource.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String path = mFileList.get(itemPosition);
                Class<? extends AppCompatActivity> cls = null;
                if(path.endsWith("mp4")){
                    cls = VideoPreviewActivity.class;
                }else {
                    cls = PicturePreviewActivity.class;
                }
                Intent intent = new Intent(v.getContext(), cls);
                intent.putExtra("file",new File(path));
                v.getContext().startActivity(intent);

            }
        });
        holder.imageResource.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(final View v) {
                new AlertDialog.Builder(v.getContext()).setMessage("Do you want to delete this image?").setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        longClickListener.onFileDeleted(position);
                    }
                }).create().show();
                return false;
            }
        });
    }

    @Override
    public int getItemCount() {
        return mFileList.size();
    }

    static class CustomViewHolder extends RecyclerView.ViewHolder {
        final ImageView imageResource;
        final ImageView play;

        CustomViewHolder(View itemView) {
            super(itemView);
            this.imageResource = (ImageView) itemView.findViewById(R.id.image_resource);
            this.play = (ImageView) itemView.findViewById(R.id.play);
        }
    }
}
