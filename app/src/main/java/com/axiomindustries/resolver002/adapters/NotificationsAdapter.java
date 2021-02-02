package com.axiomindustries.resolver002.adapters;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.axiomindustries.resolver002.R;
import com.axiomindustries.resolver002.models.Notification;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.TimeZone;


public class NotificationsAdapter extends RecyclerView.Adapter<NotificationsAdapter.ViewHolder> {

    private final ArrayList<Notification> data_arr;

    public NotificationsAdapter(ArrayList<Notification> itemsData) {
        this.data_arr = itemsData;
    }

    @NonNull
    @Override
    public NotificationsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemLayoutView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.notification_item, null);
        return new ViewHolder(itemLayoutView);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, final int position) {

        @SuppressLint("SimpleDateFormat") SimpleDateFormat fmt = new SimpleDateFormat("'Date: 'yyy-MM-dd' Time: 'hh:mm:ss");
        fmt.setTimeZone(TimeZone.getTimeZone("UTC"));
        String timestamp_utc = fmt.format(data_arr.get(position).getTimestamp());

        viewHolder.tv_package.setText("Package: "+data_arr.get(position).getPackage());
        viewHolder.tv_extra.setText("Extra: "+data_arr.get(position).getExtra());
        viewHolder.tv_ticker.setText("Ticker: "+data_arr.get(position).getTicker());
        viewHolder.tv_timestamp.setText("Timestamp: "+timestamp_utc);

        Bitmap bitmap  = data_arr.get(position).getIcon();
        if(bitmap!=null)
            viewHolder.img_icon.setImageBitmap(bitmap);
    }

    @Override
    public int getItemCount() {
        if (data_arr != null)
            return data_arr.size();
        return 0;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        TextView tv_package, tv_ticker, tv_timestamp, tv_extra;
        ImageView img_icon;

        ViewHolder(final View itemLayoutView) {
            super(itemLayoutView);

            tv_package = itemLayoutView.findViewById(R.id.tv_package);
            tv_ticker = itemLayoutView.findViewById(R.id.tv_ticker);
            tv_timestamp = itemLayoutView.findViewById(R.id.tv_time);
            tv_extra = itemLayoutView.findViewById(R.id.tv_extra);
            img_icon = itemLayoutView.findViewById(R.id.img_icon);
        }
    }
}