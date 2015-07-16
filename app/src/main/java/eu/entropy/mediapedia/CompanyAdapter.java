package eu.entropy.mediapedia;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import eu.entropy.mediapedia.company.Company;
import eu.entropy.mediapedia.utils.OnRecyclerViewItemClickListener;

public class CompanyAdapter extends RecyclerView.Adapter<CompanyAdapter.ViewHolder> implements View.OnClickListener {

    private List<Company> companies;
    private OnRecyclerViewItemClickListener<Company> itemClickListener;

    public CompanyAdapter(List<Company> companies) {
        this.companies = companies;
        this.itemClickListener = new OnRecyclerViewItemClickListener<Company>() {
            @Override public void onItemClick(View view, Company company) {}
        };
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.media_attribute, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(CompanyAdapter.ViewHolder holder, int position) {
        Company company = companies.get(position);
        holder.companyName.setText(company.getName());
        holder.companyLogo.setImageResource(company.getLogoDrawableId());
    }

    @Override
    public int getItemCount() {
        return companies.size();
    }

    @Override
    public void onClick(View v) {
        Company company = (Company) v.getTag();
        itemClickListener.onItemClick(v, company);
    }

    public void setOnItemClickListener(OnRecyclerViewItemClickListener<Company> listener) {
        this.itemClickListener = listener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public ImageView companyLogo;
        public TextView companyName;

        public ViewHolder(View itemView) {
            super(itemView);
            companyName = (TextView) itemView.findViewById(R.id.company_name);
            companyLogo = (ImageView) itemView.findViewById(R.id.company_logo);
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
