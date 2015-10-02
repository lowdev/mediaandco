package eu.entropy.mediapedia;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import java.util.ArrayList;
import java.util.List;

import eu.entropy.mediapedia.company.Company;
import eu.entropy.mediapedia.companyactivity.CompanyActivity;
import eu.entropy.mediapedia.utils.OnRecyclerViewItemClickListener;

public class MediaFragment extends Fragment {

    private final static String ARG_PAGE = "mediaCompanies";

    private ProgressBar progressBar;
    private RecyclerView recyclerView;
    private TextView noResultTextView;
    private List<Company> companies;

    public static MediaFragment newInstance(List<Company> companies) {
        Bundle args = new Bundle();
        args.putParcelableArrayList(ARG_PAGE, new ArrayList<Parcelable>(companies));
        MediaFragment fragment = new MediaFragment();
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        companies = getArguments().getParcelableArrayList(ARG_PAGE);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_media, container, false);
        AdView mAdView = (AdView) view.findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 3));

        MediaLogoAdapter mediaLogoAdapter = new MediaLogoAdapter(companies);
        mediaLogoAdapter.setOnItemClickListener(new OnRecyclerViewItemClickListener<Company>() {
            public void onItemClick(View view, Company company) {
                Intent intent = new Intent(getActivity(), CompanyActivity.class);
                intent.putExtra("company", company);
                startActivity(intent);
            }
        });
        recyclerView.setAdapter(mediaLogoAdapter);

        progressBar = (ProgressBar)  view.findViewById(R.id.progressBar);
        noResultTextView = (TextView) view.findViewById(R.id.noResultId);

        return view;
    }

    public void updateView(final List<Company> companies) {
        this.companies = companies;

        if(companies.isEmpty()) {
            noResultTextView.setVisibility(View.VISIBLE);
        } else {
            recyclerView.setVisibility(View.VISIBLE);
            MediaLogoAdapter mediaLogoAdapter = (MediaLogoAdapter) recyclerView.getAdapter();
            mediaLogoAdapter.update(companies);
            mediaLogoAdapter.notifyDataSetChanged();
        }

        progressBar.setVisibility(View.GONE);
    }

    public void startWaiting() {
        // Useful one time at the beginning.
        if (progressBar == null) {
            return;
        }

        progressBar.setVisibility(View.VISIBLE);
        recyclerView.setVisibility(View.GONE);
        noResultTextView.setVisibility(View.GONE);
    }

    public void updateViewWithAnim(final List<Company> companies) {
        Animation anim = AnimationUtils.loadAnimation(
                getActivity(), android.R.anim.fade_out
        );
        anim.setDuration(500);

        int i = 0;
        for (View view : recyclerView.getTouchables()) {
            if (i==0) {
                i++;
                continue;
            }

            view.startAnimation(anim);
        }

        new Handler().postDelayed(new Runnable() {
            public void run() {
                updateView(companies);
            }
        }, anim.getDuration());
    }
}
