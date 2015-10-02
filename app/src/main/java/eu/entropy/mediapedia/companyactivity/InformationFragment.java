package eu.entropy.mediapedia.companyactivity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.common.base.Strings;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.RequestCreator;

import eu.entropy.mediapedia.R;
import eu.entropy.mediapedia.company.Company;
import eu.entropy.mediapedia.company.Revenue;

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
    private TextView ownersView;
    private TextView assetsView;
    private TextView valueView;
    private TextView unitView;
    private TextView currencyView;
    private TextView yearView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        company = getArguments().getParcelable(ARG_PAGE);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View informationView = inflater.inflate(R.layout.fragment_information_view, container, false);
        AdView mAdView = (AdView) informationView.findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        imageView = (ImageView) informationView.findViewById(R.id.image);

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

        Revenue revenue = company.getRevenue();
        if (null != revenue) {
            informationView.findViewById(R.id.revenue).setVisibility(View.VISIBLE);
            valueView = (TextView) informationView.findViewById(R.id.value);
            valueView.setText(revenue.getValue());

            unitView = (TextView) informationView.findViewById(R.id.unit);
            unitView.setText(revenue.getUnit());

            currencyView = (TextView) informationView.findViewById(R.id.currency);
            currencyView.setText(revenue.getCurrency());

            yearView = (TextView) informationView.findViewById(R.id.year);
            yearView.setText(String.format("(%s)", revenue.getYear()));
        }

        ownersView = (TextView) informationView.findViewById(R.id.owners);
        ownersView.setText(Integer.toString(company.getOwners().size()));

        assetsView = (TextView) informationView.findViewById(R.id.assets);
        assetsView.setText(Integer.toString(company.getAssets().size()));

        return informationView;
    }
}
