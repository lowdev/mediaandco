package eu.entropy.mediapedia;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import java.util.List;

public class MediaLogoAdapter extends BaseAdapter {
    private Context context;
    private final List<Media> medias;

    public MediaLogoAdapter(Context context, List<Media> medias) {
        this.context = context;
        this.medias = medias;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (null != convertView) {
            return (View) convertView;
        }

        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View gridView = inflater.inflate(R.layout.logo, null);
        ImageView imageView = (ImageView) gridView.findViewById(R.id.grid_item_image);
        imageView.setImageResource(medias.get(position).getDrawableId());

        return gridView;
    }

    @Override
    public int getCount() {
        return medias.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }
}
