package ca.natalia.portfolio.countcoins;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;

/**
 * Created by natalia on 11/04/17.
 */
public class FragmentUtils {
    public static final String TAG = FragmentUtils.class.getSimpleName();

    public static Fragment showFragment(FragmentActivity activity, String fragmentTag) {
        return showFragment(activity, fragmentTag, null);
    }

    public static Fragment showFragment(FragmentActivity activity, String fragmentTag, Bundle args) {
        Fragment fragment = getFragmentByTag(activity, fragmentTag, args);

        FragmentTransaction transaction = activity.getSupportFragmentManager().beginTransaction();
        //transaction.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out);

        transaction.replace(R.id.frame_container, fragment, fragmentTag);
        transaction.addToBackStack(fragmentTag);
        transaction.commitAllowingStateLoss();

        //Log.d(FragmentUtils.class.getName(), "New fragment has been displayed: " + fragmentTag);
        return fragment;
    }

    private static Fragment getFragmentByTag(FragmentActivity activity, String fragmentTag, Bundle args) {
        Fragment fragment = activity.getSupportFragmentManager().findFragmentByTag(fragmentTag);
        if (fragment == null) {
            switch (fragmentTag) {
                case StartFragment.TAG:
                    fragment = new StartFragment();
                    break;
                case GameBoardFragment.TAG:
                    fragment = new GameBoardFragment();
                    break;
            }

            if (fragment != null) {
                if (args != null) {
                    fragment.setArguments(args);
                }
                return fragment;
            }
        } else {
            Log.i(TAG, String.format("Existing fragment found for %s", fragmentTag));
            return fragment;
        }

        throw new RuntimeException(String.format("Unknown fragment tag: %s", fragmentTag));
    }
}
