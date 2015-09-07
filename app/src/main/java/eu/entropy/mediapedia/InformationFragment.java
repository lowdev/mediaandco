package eu.entropy.mediapedia;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.common.base.Strings;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.RequestCreator;

import eu.entropy.mediapedia.company.Company;

public class InformationFragment extends Fragment {
    public static final String ARG_PAGE = "company";

    public static InformationFragment newInstance(Company company) {
        Bundle args = new Bundle();
        args.putParcelable(ARG_PAGE, company);

        InformationFragment fragment = new InformationFragment();
        fragment.setArguments(args);

        return fragment;
    }

    private Company company;

    private ImageView imageView;
    private TextView revenueView;
    private TextView unitView;
    private TextView ownersView;
    private TextView assetsView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        company = getArguments().getParcelable(ARG_PAGE);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View companiesListView = inflater.inflate(R.layout.fragment_information_view, container, false);
        imageView = (ImageView) companiesListView.findViewById(R.id.image);

        String logo = company.getLogo();

        RequestCreator creator;
        if (Strings.isNullOrEmpty(logo)) {
            creator = Picasso.with(getActivity())
                    .load(R.drawable.no_image);
        } else {
            creator = Picasso.with(getActivity())
                    .load(logo);
        }
        creator.error(R.drawable.no_image)
                .centerInside()
                .fit()
                .into(imageView);

        String revenue = company.getRevenue();
        if (!Strings.isNullOrEmpty(revenue)) {
            companiesListView.findViewById(R.id.revenueLabel).setVisibility(View.VISIBLE);
            revenueView = (TextView) companiesListView.findViewById(R.id.revenue);
            revenueView.setText(revenue);

            unitView = (TextView) companiesListView.findViewById(R.id.unit);
            unitView.setText(company.getUnit());
        }

        ownersView = (TextView) companiesListView.findViewById(R.id.revenue);
        ownersView.setText(company.getRevenue());

        ownersView = (TextView) companiesListView.findViewById(R.id.owners);
        ownersView.setText(Integer.toString(company.getOwners().size()));

        assetsView = (TextView) companiesListView.findViewById(R.id.assets);
        assetsView.setText(Integer.toString(company.getAssets().size()));

        return companiesListView;
    }
}
