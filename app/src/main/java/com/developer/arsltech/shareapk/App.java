package com.developer.arsltech.shareapk;

import android.graphics.drawable.Drawable;

public class App {

    private String name;
    private Drawable icon;
    private String apkPath;
    private long apkSize;

    public App(String name, Drawable icon, String apkPath, long apkSize) {
        this.name = name;
        this.icon = icon;
        this.apkPath = apkPath;
        this.apkSize = apkSize;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Drawable getIcon() {
        return icon;
    }

    public void setIcon(Drawable icon) {
        this.icon = icon;
    }

    public String getApkPath() {
        return apkPath;
    }

    public void setApkPath(String apkPath) {
        this.apkPath = apkPath;
    }

    public long getApkSize() {
        return apkSize;
    }

    public void setApkSize(long apkSize) {
        this.apkSize = apkSize;
    }
}
