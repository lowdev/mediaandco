package eu.entropy.mediapedia;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.common.base.Strings;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.RequestCreator;

import java.util.List;

import eu.entropy.mediapedia.company.Stakeholder;
import eu.entropy.mediapedia.utils.OnRecyclerViewItemClickListener;

public class StakeholderAdapter extends RecyclerView.Adapter<StakeholderAdapter.ViewHolder> implements View.OnClickListener {

    private List<Stakeholder> stakeholders;
    private OnRecyclerViewItemClickListener<Stakeholder> itemClickListener;

    public StakeholderAdapter(List<Stakeholder> stakeholders) {
        this.stakeholders = stakeholders;
        this.itemClickListener = new OnRecyclerViewItemClickListener<Stakeholder>() {
            @Override public void onItemClick(View view, Stakeholder company) {}
        };
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        View v = LayoutInflater.from(context).inflate(R.layout.media_attribute, parent, false);
        return new ViewHolder(context, v);
    }

    @Override
    public void onBindViewHolder(StakeholderAdapter.ViewHolder holder, int position) {
        Stakeholder stakeholder = stakeholders.get(position);
        holder.companyName.setText(stakeholder.getName());
        String logo = stakeholder.getLogo();

        RequestCreator creator;
        if (Strings.isNullOrEmpty(logo)) {
            creator = Picasso.with(holder.context)
                    .load(R.drawable.no_image);
        } else {
            creator = Picasso.with(holder.context)
                    .load(logo);
        }
        creator.error(R.drawable.no_image)
            .centerInside()
            .fit()
            .into(holder.companyLogo);

        holder.stake.setText(stakeholder.getStake() + " stake");

        if (!stakeholder.hasInformation()) {
            holder.arrowImage.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public int getItemCount() {
        return stakeholders.size();
    }

    @Override
    public void onClick(View v) {
        Stakeholder company = (Stakeholder) v.getTag();
        itemClickListener.onItemClick(v, company);
    }

    public void setOnItemClickListener(OnRecyclerViewItemClickListener<Stakeholder> listener) {
        this.itemClickListener = listener;
    }

    public void update(List<Stakeholder> stakeholders) {
        this.stakeholders = stakeholders;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private Context context;
        private ImageView companyLogo;
        private TextView companyName;
        private TextView stake;
        private ImageView arrowImage;

        public ViewHolder(Context context, View itemView) {
            super(itemView);
            this.context = context;
            companyName = (TextView) itemView.findViewById(R.id.company_name);
            companyLogo = (ImageView) itemView.findViewById(R.id.company_logo);
            stake = (TextView) itemView.findViewById(R.id.stake);
            arrowImage = (ImageView) itemView.findViewById(R.id.arrow_icon);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (itemClickListener != null) {
                Stakeholder company = stakeholders.get(getPosition());
                itemClickListener.onItemClick(v, company);
            }
        }
    }
}
