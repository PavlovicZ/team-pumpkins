package com.bignerdranch.android.escapeovatortemp;

import android.support.v4.app.Fragment;

/**
 * Holds a MenuFragment
 * Anthony Hessler
 */
public class MenuActivity extends SingleFragmentActivity {
    private static final String EXTRA_FLOOR_NUM =
            "com.bignerdranch.android.escapeovatortemp.floor_num";

    @Override
    protected Fragment createFragment()
    {
        int floorNum = getIntent().getIntExtra(EXTRA_FLOOR_NUM, 1);
        return MenuFragment.newInstance(floorNum);
    }
}