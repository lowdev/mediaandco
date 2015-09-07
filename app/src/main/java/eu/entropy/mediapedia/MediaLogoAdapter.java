package eu.entropy.mediapedia;

import android.content.Context;
import android.os.Handler;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.List;

import eu.entropy.mediapedia.company.Company;
import eu.entropy.mediapedia.utils.OnRecyclerViewItemClickListener;

public class MediaLogoAdapter extends RecyclerView.Adapter<MediaLogoAdapter.ViewHolder> implements View.OnClickListener {

    private List<Company> companies;
    private OnRecyclerViewItemClickListener<Company> itemClickListener;

    public MediaLogoAdapter(List<Company> companies) {
        this.companies = companies;
    }

    public void update(List<Company> companies) {
        this.companies = companies;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        View v = LayoutInflater.from(context).inflate(R.layout.media_logo_thumbnail, parent, false);
        return new ViewHolder(context, v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Company company = companies.get(position);
        Picasso.with(holder.context)
                .load(company.getLogo())
                .error(R.drawable.no_image)
                .into(holder.image);
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
        private Context context;
        private ImageView image;

        public ViewHolder(Context context, View itemView) {
            super(itemView);
            this.context = context;
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
