package id.tech.util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.pkmmte.view.CircularImageView;

import java.util.List;

import id.tech.verificareolx.R;

/**
 * Created by RebelCreative-A1 on 02/03/2016.
 */
public class Olx_RecyclerAdapter_Gallery extends RecyclerView.Adapter<Olx_RecyclerAdapter_Gallery.ViewHolder>{
    private Activity activity_adapter;
    private Context context_adapter;
    private List<RowDataGallery> data;

    public Olx_RecyclerAdapter_Gallery(Activity activity_adapter, Context context_adapter, List<RowDataGallery> data) {
        this.activity_adapter = activity_adapter;
        this.context_adapter = context_adapter;
        this.data = data;
    }


    @Override
    public int getItemCount() {
        return data.size();
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final RowDataGallery item = data.get(position);

        holder.img.setImageBitmap(item.bitmap);
//        holder.tv_nama.setText(item.title);
        holder.img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context_adapter, item.path, Toast.LENGTH_LONG).show();

                holder.img_checked.setVisibility(View.VISIBLE);

//                Intent intent = new Intent();
//                intent.putExtra("mUrl_Img", item.path);
//                activity_adapter.setResult(Activity.RESULT_OK, intent);
//                activity_adapter.finish();;
            }
        });

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context_adapter).inflate(R.layout.item_gallery, null);

        ViewHolder viewHolder = new ViewHolder(v, activity_adapter);
        return viewHolder;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tv_nama;
        public ImageView img, img_checked;
        private Activity activity;

        public ViewHolder(View v, Activity activity) {
            super(v);
            tv_nama = (TextView) v.findViewById(R.id.tv_nama);
            img = (ImageView) v.findViewById(R.id.img);
            img_checked = (ImageView) v.findViewById(R.id.img_checked);
            this.activity = activity;

        }

    }
}
