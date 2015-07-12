package eu.entropy.mediapedia;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class MediaAttributeAdapter extends RecyclerView.Adapter<MediaAttributeAdapter.ViewHolder> {
    private List<String> mediaAttributes;

    public MediaAttributeAdapter(List<String> mediaAttributes) {
        this.mediaAttributes = mediaAttributes;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.media_attribute, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(MediaAttributeAdapter.ViewHolder holder, int position) {
        String mediaAttribute = mediaAttributes.get(position);
        holder.mediaAttribute.setText(mediaAttribute);
    }

    @Override
    public int getItemCount() {
        return mediaAttributes.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView mediaAttribute;

        public ViewHolder(View itemView) {
            super(itemView);
            mediaAttribute = (TextView) itemView.findViewById(R.id.attribute);
        }
    }
}
