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
import java.util.List;

import eu.entropy.mediapedia.company.Company;
import eu.entropy.mediapedia.utils.OnRecyclerViewItemClickListener;

public class CompaniesFragment extends Fragment {
    public static final String ARG_PAGE = "companies";

    private List<Company> companies;

    public static CompaniesFragment newInstance(List<Company> companies) {
        Bundle args = new Bundle();
        args.putParcelableArrayList(ARG_PAGE, (ArrayList<Company>) companies);
        CompaniesFragment fragment = new CompaniesFragment();
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        companies = getArguments().getParcelableArrayList(ARG_PAGE);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_companies, container, false);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.companies);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        CompanyAdapter companyAdapter = new CompanyAdapter(companies);
        companyAdapter.setOnItemClickListener(new OnRecyclerViewItemClickListener<Company>() {
            public void onItemClick(View view, Company company) {
                if (!company.hasInformation()) {
                    return;
                }

                Intent intent = new Intent(getActivity().getApplicationContext(), CompanyActivity.class);
                intent.putExtra("company", company);
                startActivity(intent);
            }
        });
        recyclerView.setAdapter(companyAdapter);

        return view;
    }
}

