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
    private final List<MediaItem> mediaItems;

    public MediaLogoAdapter(Context context, List<MediaItem> mediaItems) {
        this.context = context;
        this.mediaItems = mediaItems;
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
        imageView.setImageResource(mediaItems.get(position).getDrawableId());

        return gridView;
    }

    @Override
    public int getCount() {
        return mediaItems.size();
    }

    @Override
    public Object getItem(int position) {
        return mediaItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return mediaItems.get(position).getId();
    }
}
