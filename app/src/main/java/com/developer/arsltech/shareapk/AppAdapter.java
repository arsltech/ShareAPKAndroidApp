package com.developer.arsltech.shareapk;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.content.FileProvider;
import androidx.recyclerview.widget.RecyclerView;

import java.io.File;
import java.util.List;

public class AppAdapter extends RecyclerView.Adapter<AppAdapter.AppViewHolder> {

    Context context;
    List<App> apps;


    public AppAdapter(Context context, List<App> apps) {
        this.context = context;
        this.apps = apps;
    }

    @NonNull
    @Override
    public AppViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.app_row,parent,false);
        return new AppViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AppViewHolder holder, final int position) {

        holder.appName.setText(apps.get(position).getName());

        long apkSize = apps.get(position).getApkSize();

        holder.apkSize.setText(getHumanReadableSize(apkSize));
        holder.appIcon.setImageDrawable(apps.get(position).getIcon());


        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent shareAPkIntent = new Intent();
                shareAPkIntent.setAction(Intent.ACTION_SEND);

                shareAPkIntent.putExtra(Intent.EXTRA_STREAM, FileProvider.getUriForFile(
                        context,BuildConfig.APPLICATION_ID + ".provider", new File(apps.get(position).getApkPath())
                ));

                shareAPkIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                shareAPkIntent.setType("application/vnd.android.package-archive");

                context.startActivity(Intent.createChooser(shareAPkIntent,"Share APK"));


            }
        });

    }

    private String getHumanReadableSize(long apkSize) {

        String humanReadableSize;
        if(apkSize<1024){
            humanReadableSize = String.format(
                    context.getString(R.string.app_size_b),
                    (double) apkSize
            );
        } else if(apkSize < Math.pow(1024,2)){
            humanReadableSize = String.format(
                    context.getString(R.string.app_size_kib),
                    (double) (apkSize/1024)
            );
        }else if(apkSize < Math.pow(1024,3)){
            humanReadableSize = String.format(
                    context.getString(R.string.app_size_mib),
                    (double) (apkSize/Math.pow(1024,2))
            );
        } else{
            humanReadableSize = String.format(
                    context.getString(R.string.app_size_gib),
                    (double) (apkSize/Math.pow(1024,3))
            );
        }
        return humanReadableSize;
    }

    @Override
    public int getItemCount() {
        return apps.size();
    }

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    public static class AppViewHolder extends RecyclerView.ViewHolder{

        CardView cardView;
        ImageView appIcon;
        TextView appName,apkSize;

        public AppViewHolder(@NonNull View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.app_row);
            appIcon = itemView.findViewById(R.id.app_icon);
            appName = itemView.findViewById(R.id.app_name);
            apkSize = itemView.findViewById(R.id.apk_size);
        }
    }
}
