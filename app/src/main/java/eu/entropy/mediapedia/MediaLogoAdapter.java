package eu.entropy.mediapedia;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.List;

import eu.entropy.mediapedia.company.Company;
import eu.entropy.mediapedia.utils.OnRecyclerViewItemClickListener;

public class MediaLogoAdapter extends RecyclerView.Adapter<MediaLogoAdapter.ViewHolder> implements View.OnClickListener {

    private List<Company> companies;

    private OnRecyclerViewItemClickListener<Company> itemClickListener;
    
    public MediaLogoAdapter(List<Company> companies) {
        this.companies = companies;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.media_logo_thumbnail, parent, false);
        v.setOnClickListener(this);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Company company = companies.get(position);
        holder.image.setImageResource(company.getDrawableIdLogo());
    }

    @Override
    public int getItemCount() {
        return companies.size();
    }

    @Override
    public void onClick(View view) {
        if (itemClickListener != null) {
            Company company = (Company) view.getTag();
            itemClickListener.onItemClick(view, company);
        }
    }

    public void setOnItemClickListener(OnRecyclerViewItemClickListener<Company> listener) {
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
                Company company = companies.get(getPosition());
                itemClickListener.onItemClick(v, company);
            }
        }
    }
}
