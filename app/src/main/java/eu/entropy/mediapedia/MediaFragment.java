package eu.entropy.mediapedia;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import eu.entropy.mediapedia.company.Company;
import eu.entropy.mediapedia.company.CompanyRepository;
import eu.entropy.mediapedia.utils.OnRecyclerViewItemClickListener;

public class MediaFragment extends Fragment {

    private final static String ARG_PAGE = "mediaCompanies";

    private List<Company> companies;

    public static MediaFragment newInstance(List<Company> companies) {
        Bundle args = new Bundle();
        args.putParcelableArrayList(ARG_PAGE, (ArrayList<Company>) companies);
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
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
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

        return view;
    }
}
