package id.tech.verificareolx;

/**
 * Created by RebelCreative-A1 on 02/03/2016.
 */
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import id.tech.util.Olx_RecyclerAdapter_Gallery;
import id.tech.util.Olx_RecyclerAdapter_History;
import id.tech.util.RowDataGallery;

public class Olx_GalleryView extends AppCompatActivity {
    RecyclerView rv ;
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager layoutManager;
    RecyclerView.ItemDecoration decoration;
    List<RowDataGallery> data;
    private int count;
    private Bitmap[] thumbnails;
    private String[] arrPath;
    Activity activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_rv);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        getSupportActionBar().setTitle(Html.fromHtml("<font color='#ffffff'>Select Photo </font>"));

        activity = this;
        rv = (RecyclerView)findViewById(R.id.rv);
        layoutManager = new GridLayoutManager(getApplicationContext(), 3);
        rv.setLayoutManager(layoutManager);

        new Async_LoadGallery().execute();

    }

    private class Async_LoadGallery extends AsyncTask<Void,Void,Void>{
        Olx_DialogFragmentProgress pDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new Olx_DialogFragmentProgress();
            pDialog.show(getSupportFragmentManager(),"");

            data = new ArrayList<RowDataGallery>();
        }

        @Override
        protected Void doInBackground(Void... params) {
            final String[] columns = {MediaStore.Images.Media.DATA, MediaStore.Images.Media._ID};
            final String orderBy = MediaStore.Images.Media.DATE_TAKEN;
            Cursor imageCursor = managedQuery(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, columns, null,null, orderBy + " DESC");
            int image_column_index = imageCursor.getColumnIndex(MediaStore.Images.Media._ID);

            count = imageCursor.getCount();
            thumbnails = new Bitmap[count];
            arrPath = new String[count];

            for (int i = 0; i < count; i++) {
                imageCursor.moveToPosition(i);
                int id = imageCursor.getInt(image_column_index);
                int dataColumnIndex = imageCursor.getColumnIndex(MediaStore.Images.Media.DATA);
                thumbnails[i] = MediaStore.Images.Thumbnails.getThumbnail(getApplicationContext().getContentResolver(), id,
                        MediaStore.Images.Thumbnails.MICRO_KIND, null);
                arrPath[i] = imageCursor.getString(dataColumnIndex);

                String titlenya = getFileNameByUri(getApplicationContext(), Uri.fromFile(new File(arrPath[i])));

                data.add(new RowDataGallery(titlenya,  arrPath[i], thumbnails[i]));
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            pDialog.dismiss();

            adapter = new Olx_RecyclerAdapter_Gallery(activity, getApplicationContext(), data);
            rv.setAdapter(adapter);
        }
    }

    public static String getFileNameByUri(Context context, Uri uri)
    {
        String fileName="unknown";//default fileName
        Uri filePathUri = uri;
        if (uri.getScheme().toString().compareTo("content")==0)
        {
            Cursor cursor = context.getContentResolver().query(uri, null, null, null, null);
            if (cursor.moveToFirst())
            {
                int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);//Instead of "MediaStore.Images.Media.DATA" can be used "_data"
                filePathUri = Uri.parse(cursor.getString(column_index));
                fileName = filePathUri.getLastPathSegment().toString();
            }
        }
        else if (uri.getScheme().compareTo("file")==0)
        {
            fileName = filePathUri.getLastPathSegment().toString();
        }
        else
        {
            fileName = fileName+"_"+filePathUri.getLastPathSegment();
        }
        return fileName;
    }

}
