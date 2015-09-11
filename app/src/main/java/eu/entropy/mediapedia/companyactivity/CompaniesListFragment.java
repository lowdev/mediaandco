package eu.entropy.mediapedia.companyactivity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import eu.entropy.mediapedia.R;
import eu.entropy.mediapedia.company.Company;
import eu.entropy.mediapedia.company.StakeholderRepository;

public class CompaniesListFragment extends Fragment {

    private View parentActivity;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        parentActivity = (View) container.getParent().getParent();

        StakeholderRepository stakeholderRepository = new StakeholderRepository();
        Company company = (Company) getArguments().get("company");

        View companiesListView = inflater.inflate(R.layout.fragment_companies_list_view, container, false);
        ViewPager viewPager = (ViewPager) companiesListView.findViewById(R.id.viewpager);
        viewPager.setAdapter(new CompanyFragmentPagerAdapter(
                        getFragmentManager(),
                        company,
                        stakeholderRepository.findByIds(company.getOwners()),
                        stakeholderRepository.findByIds(company.getAssets())
                )
        );

        TabLayout tabLayout = (TabLayout) parentActivity.findViewById(R.id.tabLayout);
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        tabLayout.setupWithViewPager(viewPager);

        return companiesListView;
    }
}
