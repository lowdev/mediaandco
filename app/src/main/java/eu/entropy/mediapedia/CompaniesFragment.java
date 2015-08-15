package eu.entropy.mediapedia;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import eu.entropy.mediapedia.company.Stakeholder;
import eu.entropy.mediapedia.utils.DividerItemDecoration;
import eu.entropy.mediapedia.utils.OnRecyclerViewItemClickListener;
import eu.entropy.mediapedia.utils.SimpleDividerItemDecoration;

public class CompaniesFragment extends Fragment {
    public static final String ARG_PAGE = "companies";

    private List<Stakeholder> companies;

    public static CompaniesFragment newInstance(List<Stakeholder> companies) {
        Bundle args = new Bundle();
        args.putParcelableArrayList(ARG_PAGE, (ArrayList<Stakeholder>) companies);
        CompaniesFragment fragment = new CompaniesFragment();
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        companies = getArguments().getParcelableArrayList(ARG_PAGE);
        Collections.sort(companies);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_companies, container, false);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.companies);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        CompanyAdapter companyAdapter = new CompanyAdapter(companies);
        companyAdapter.setOnItemClickListener(new OnRecyclerViewItemClickListener<Stakeholder>() {
            public void onItemClick(View view, Stakeholder stakeholder) {
                if (!stakeholder.hasInformation()) {
                    return;
                }

                Intent intent = new Intent(getActivity().getApplicationContext(), CompanyActivity.class);
                intent.putExtra("company", stakeholder.getCompany());
                getActivity().startActivity(intent);
            }
        });
        recyclerView.setAdapter(companyAdapter);

        recyclerView.addItemDecoration(new SimpleDividerItemDecoration(
                getActivity().getResources().getDrawable(R.drawable.line_divider)));

        return view;
    }
}

