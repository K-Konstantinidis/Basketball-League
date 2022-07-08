package com.example.esake.TabbedView.TabbedViewUser;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

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