package eu.entropy.mediapedia.companyactivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import eu.entropy.mediapedia.StakeholderAdapter;
import eu.entropy.mediapedia.R;
import eu.entropy.mediapedia.company.Stakeholder;
import eu.entropy.mediapedia.utils.OnRecyclerViewItemClickListener;
import eu.entropy.mediapedia.utils.SimpleDividerItemDecoration;

public class StakeholdersFragment extends Fragment {
    public static final String ARG_PAGE = "stakeholders";

    private ProgressBar progressBar;
    private RecyclerView recyclerView;
    private Map<String, Double> stakeholders;

    public static StakeholdersFragment newInstance(Map<String, Double> stakeholders) {
        Bundle args = new Bundle();
        args.putSerializable(ARG_PAGE, (HashMap<String, Double>) stakeholders);
        StakeholdersFragment fragment = new StakeholdersFragment();
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        stakeholders = (HashMap<String, Double>) getArguments().getSerializable(ARG_PAGE);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_companies, container, false);
        progressBar = (ProgressBar)  view.findViewById(R.id.fragmentCompaniesProgressBar);

        recyclerView = (RecyclerView) view.findViewById(R.id.stakeholders);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        StakeholderAdapter stakeholderAdapter = new StakeholderAdapter(new ArrayList<Stakeholder>());
        stakeholderAdapter.setOnItemClickListener(new OnRecyclerViewItemClickListener<Stakeholder>() {
            public void onItemClick(View view, Stakeholder stakeholder) {
                if (!stakeholder.hasInformation()) {
                    return;
                }

                Intent intent = new Intent(getActivity().getApplicationContext(), CompanyActivity.class);
                intent.putExtra("company", stakeholder.getCompany());
                getActivity().startActivity(intent);
                getActivity().overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
            }
        });
        recyclerView.setAdapter(stakeholderAdapter);

        recyclerView.addItemDecoration(new SimpleDividerItemDecoration(
                getActivity().getResources().getDrawable(R.drawable.line_divider)));

        StakeholderLoader.newInstance(getActivity(), this).execute(stakeholders);

        return view;
    }

    public void updateView(final List<Stakeholder> stakeholders) {
        StakeholderAdapter stakeholderAdapter = (StakeholderAdapter) recyclerView.getAdapter();
        stakeholderAdapter.update(stakeholders);
        stakeholderAdapter.notifyDataSetChanged();

        endWaiting();
    }

    public void startWaiting() {
        progressBar.setVisibility(View.VISIBLE);
        recyclerView.setVisibility(View.GONE);
    }

    public void endWaiting() {
        recyclerView.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.GONE);
    }
}

