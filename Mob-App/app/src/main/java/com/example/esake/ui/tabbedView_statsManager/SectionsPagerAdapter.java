package com.example.esake.ui.tabbedView_statsManager;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;


import com.example.esake.FragmentGameManagementStatsManager;
import com.example.esake.FragmentMatchOverviewStatsManager;
import com.example.esake.FragmentTeamManagementStatsManager;
import com.example.esake.R;

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
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
                return new FragmentMatchOverviewStatsManager();
            case 1:
                return new FragmentGameManagementStatsManager();
            case 2:
                return new FragmentTeamManagementStatsManager();
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
}