package com.example.esake.TabbedView.TabbedViewStatsManager;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;


import java.util.ArrayList;
import java.util.List;

/*
public class SectionsPagerAdapter extends FragmentPagerAdapter {

    @StringRes
    private static final int[] TAB_TITLES = new int[]{R.string.tab_1, R.string.tab_2, R.string.tab_3};
    private final Context mContext;

    public SectionsPagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
    }

    @NonNull
	@Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new FragmentSmGameOverview();
            case 1:
                return new FragmentSmGameManage();
            case 2:
                return new FragmentSmTeamManage();
            default:
                return new PlaceholderFragment();
        }
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mContext.getResources().getString(TAB_TITLES[position]);
    }

    @Override
    public int getCount() {
        // Show 3 total pages.
        return 3;
    }
}*/

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
public class SectionsPagerAdapter extends FragmentPagerAdapter {

	private final List<Fragment> listFragment = new ArrayList<>();
	private final List<String> listTitle = new ArrayList<>();

	public SectionsPagerAdapter(FragmentManager fm) {
		super(fm);
	}

	@NonNull
	public Fragment getItem(int position) {
		return listFragment.get(position);
	}

	@Override
	public int getCount() {
		return listTitle.size();
	}

	@Override
	public CharSequence getPageTitle(int position) {
		return listTitle.get(position);
	}


	public void AddFragment (Fragment fragment, String title){
		listFragment.add(fragment);
		listTitle.add(title);
	}
}