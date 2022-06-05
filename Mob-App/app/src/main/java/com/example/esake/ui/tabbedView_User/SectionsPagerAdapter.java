package com.example.esake.ui.tabbedView_User;

import android.content.Context;

import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.esake.FragmentLineupsUser;
import com.example.esake.FragmentMatchOverviewUser;
import com.example.esake.FragmentPlayerStatsLiveUser;
import com.example.esake.FragmentTeamStatsLiveUser;
import com.example.esake.R;
import com.example.esake.ui.tabbedView_statsManager.PlaceholderFragment;

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
public class SectionsPagerAdapter extends FragmentPagerAdapter {

    @StringRes
    private static final int[] TAB_TITLES = new int[]{R.string.tab_1_user, R.string.tab_2_user, R.string.tab_3_user, R.string.tab_4_user};
    private final Context mContext;

    public SectionsPagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new FragmentMatchOverviewUser();
            case 1:
                return new FragmentTeamStatsLiveUser();
            case 2:
                return new FragmentPlayerStatsLiveUser();
            case 3:
                return new FragmentLineupsUser();
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
        // Show 4 pages.
        return 4;
    }
}