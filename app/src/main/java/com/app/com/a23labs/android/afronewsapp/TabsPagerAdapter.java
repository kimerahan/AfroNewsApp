package com.app.com.a23labs.android.afronewsapp;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by KHaN on 2/13/2015.
 */
public class TabsPagerAdapter  extends FragmentPagerAdapter {
    public TabsPagerAdapter(FragmentManager fm) {
        super(fm);
    }
    @Override
    public Fragment getItem(int index) {

        switch (index) {
            case 0:
                // Top Rated fragment activity
                return new TopNews();
            case 1:
                // Games fragment activity
                return new Health();
            case 2:
                // Movies fragment activity
                return new Others();
            case 3:
                // Movies fragment activity
                return new Tech();
            case 4:
                // Movies fragment activity
                return new Gossip();
        }

        return null;
    }

    @Override
    public int getCount() {
        // get item count - equal to number of tabs
        return 5;
    }

}

