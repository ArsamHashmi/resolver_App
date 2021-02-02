package com.axiomindustries.resolver002;

import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.CursorLoader;
import androidx.loader.content.Loader;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.RecyclerView;

import com.axiomindustries.resolver002.adapters.NotificationsAdapter;
import com.axiomindustries.resolver002.models.Notification;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor>{

    private NotificationsAdapter mAdapter;
    private ArrayList<Notification> notificationArrayList;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        notificationArrayList = new ArrayList<>();
        setupViews();
    }

    private void setupViews(){
        mAdapter = new NotificationsAdapter(notificationArrayList);
        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setAdapter(mAdapter);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        (findViewById(R.id.btn_begin)).setOnClickListener(v->init());
    }

    public void init() {
        LoaderManager.getInstance(this).initLoader(0, null, this);
    }

    @NonNull
    @Override
    public Loader<Cursor> onCreateLoader(int arg0, Bundle arg1) {
        return new CursorLoader(this, Uri.parse("content://com.axiomindustries.provider002.Notification/cte"), null, null, null, null);
    }

    @Override
    public void onLoadFinished(@NonNull Loader<Cursor> arg0, Cursor cursor) {
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Bitmap icon = null;
            try{
                icon = getImage(cursor.getBlob(cursor.getColumnIndex("icon")));
            }
            catch (Exception exception){
                exception.printStackTrace();
            }
            Notification notification = new Notification(
                    cursor.getString(cursor.getColumnIndex("package")),
                    cursor.getString(cursor.getColumnIndex("ticker")),
                    cursor.getString(cursor.getColumnIndex("extra")),
                    cursor.getLong(cursor.getColumnIndex("timestamp")),
                    icon
            );
            notificationArrayList.add(notification);
            mAdapter.notifyDataSetChanged();

            recyclerView.scrollToPosition(notificationArrayList.size()-1);
            cursor.moveToNext();
        }
    }

    @Override
    public void onLoaderReset(@NonNull Loader<Cursor> arg0) {

    }
    @Override
    public void onDestroy() {
        super.onDestroy();
    }
    public static Bitmap getImage(byte[] image) {
        return BitmapFactory.decodeByteArray(image, 0, image.length);
    }
}
