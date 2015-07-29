package eu.entropy.mediapedia;

import android.media.Image;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import eu.entropy.mediapedia.company.Stakeholder;
import eu.entropy.mediapedia.utils.OnRecyclerViewItemClickListener;

public class CompanyAdapter extends RecyclerView.Adapter<CompanyAdapter.ViewHolder> implements View.OnClickListener {

    private List<Stakeholder> companies;
    private OnRecyclerViewItemClickListener<Stakeholder> itemClickListener;

    public CompanyAdapter(List<Stakeholder> companies) {
        this.companies = companies;
        this.itemClickListener = new OnRecyclerViewItemClickListener<Stakeholder>() {
            @Override public void onItemClick(View view, Stakeholder company) {}
        };
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.media_attribute, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(CompanyAdapter.ViewHolder holder, int position) {
        Stakeholder company = companies.get(position);
        holder.companyName.setText(company.getName());
        holder.companyLogo.setImageResource(company.getLogoDrawableId());
        holder.stake.setText(company.getStake() + " stake");

        if (!company.hasInformation()) {
            holder.arrowImage.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public int getItemCount() {
        return companies.size();
    }

    @Override
    public void onClick(View v) {
        Stakeholder company = (Stakeholder) v.getTag();
        itemClickListener.onItemClick(v, company);
    }

    public void setOnItemClickListener(OnRecyclerViewItemClickListener<Stakeholder> listener) {
        this.itemClickListener = listener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private ImageView companyLogo;
        private TextView companyName;
        private TextView stake;
        private ImageView arrowImage;

        public ViewHolder(View itemView) {
            super(itemView);
            companyName = (TextView) itemView.findViewById(R.id.company_name);
            companyLogo = (ImageView) itemView.findViewById(R.id.company_logo);
            stake = (TextView) itemView.findViewById(R.id.stake);
            arrowImage = (ImageView) itemView.findViewById(R.id.arrow_icon);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (itemClickListener != null) {
                Stakeholder company = companies.get(getPosition());
                itemClickListener.onItemClick(v, company);
            }
        }
    }
}
