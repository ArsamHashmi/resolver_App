package com.axiomindustries.resolver002.models;

import android.graphics.Bitmap;

public class Notification {
    String Package, Ticker, Extra;
    long Timestamp;
    Bitmap icon;

    public Notification(String aPackage, String ticker, String extra, long timestamp, Bitmap icon) {
        this.Package = aPackage;
        this.Ticker = ticker;
        this.Extra = extra;
        this.Timestamp = timestamp;
        this.icon = icon;
    }

    public String getPackage() {
        return Package;
    }

    public String getTicker() {
        return Ticker;
    }

    public String getExtra() {
        return Extra;
    }

    public long getTimestamp() {
        return Timestamp;
    }

    public Bitmap getIcon() {
        return icon;
    }
}
