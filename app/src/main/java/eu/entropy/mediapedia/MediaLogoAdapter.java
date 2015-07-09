package eu.entropy.mediapedia;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.List;

public class MediaLogoAdapter extends RecyclerView.Adapter<MediaLogoAdapter.ViewHolder> implements View.OnClickListener {

    private List<MediaItem> items;
    private OnRecyclerViewItemClickListener<MediaItem> itemClickListener;
    public MediaLogoAdapter(List<MediaItem> items) {
        this.items = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.media_logo_thumbnail, parent, false);
        v.setOnClickListener(this);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        MediaItem item = items.get(position);
        holder.image.setImageResource(item.getDrawableId());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public void onClick(View view) {
        if (itemClickListener != null) {
            MediaItem model = (MediaItem) view.getTag();
            itemClickListener.onItemClick(view, model);
        }
    }

    public void setOnItemClickListener(OnRecyclerViewItemClickListener<MediaItem> listener) {
        this.itemClickListener = listener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public ImageView image;

        public ViewHolder(View itemView) {
            super(itemView);
            image = (ImageView) itemView.findViewById(R.id.grid_item_image);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (itemClickListener != null) {
                MediaItem mediaItem = items.get(getPosition());
                itemClickListener.onItemClick(v, mediaItem);
            }
        }
    }
}
