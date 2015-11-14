package barqsoft.footballscores;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPager;
import android.text.format.Time;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by yehya khaled on 2/27/2015.
 */
public class PagerFragment extends Fragment {
    public static final int NUM_PAGES = 5;
    public ViewPager mPagerHandler;
    private myPageAdapter mPagerAdapter;
    private MainScreenFragment[] viewFragments = new MainScreenFragment[5];
    public boolean rtlMode;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.pager_fragment, container, false);
        // Added to detect RTL mode and reverse the pager
        if (getResources().getBoolean(R.bool.is_right_to_left)) {
            rtlMode = true;
        } else {
            rtlMode = false;
        }
        mPagerHandler = (ViewPager) rootView.findViewById(R.id.pager);
        mPagerAdapter = new myPageAdapter(getChildFragmentManager());

        // Added so if it is RTL mode, put the dates into the fragments backwards.
        // example: "Tomorrow" on the left of "Today" instead of on the right.

        if (rtlMode) {
            Date[] tempDate = new Date[NUM_PAGES];
            SimpleDateFormat mformat = new SimpleDateFormat("yyyy-MM-dd");
            for(int i = 0; i<NUM_PAGES; i++){
                tempDate[NUM_PAGES-1-i] = new Date(System.currentTimeMillis() +((i-2)*86400000));
            }
            for(int i=0; i<NUM_PAGES; i++){
                viewFragments[i] = new MainScreenFragment();
                viewFragments[i].setFragmentDate(mformat.format(tempDate[i]));
            }
        }else {
            for (int i = 0; i < NUM_PAGES; i++) {
                Date fragmentdate = new Date(System.currentTimeMillis() + ((i - 2) * 86400000));
                SimpleDateFormat mformat = new SimpleDateFormat("yyyy-MM-dd");
                viewFragments[i] = new MainScreenFragment();
                viewFragments[i].setFragmentDate(mformat.format(fragmentdate));
            }
        }
        mPagerHandler.setAdapter(mPagerAdapter);
        mPagerHandler.setCurrentItem(MainActivity.current_fragment);

        return rootView;
    }

    private class myPageAdapter extends FragmentStatePagerAdapter {
        @Override
        public Fragment getItem(int i) {
            return viewFragments[i];
        }

        @Override
        public int getCount() {
            return NUM_PAGES;
        }

        public myPageAdapter(FragmentManager fm) {
            super(fm);
        }

        // Returns the page title for the top indicator
        @Override
        public CharSequence getPageTitle(int position) {
            return getDayName(getActivity(), System.currentTimeMillis() + ((position - 2) * 86400000),viewFragments[position].getFragmentDate());
        }

        public String getDayName(Context context, long dateInMillis, String date) {
            // If the date is today, return the localized version of "Today" instead of the actual
            // day name.

            // This RTL mode check was a way of returning a different result without reworking all of
            // the old code. In the future this logic can be reworked to the rtl mode logic so that it
            // just works all the time.

            if(rtlMode){
                SimpleDateFormat newDayFormat = new SimpleDateFormat("EEEE");
                SimpleDateFormat oldDayFormat = new SimpleDateFormat("yyyy-MM-dd");
                SimpleDateFormat dayFormat = new SimpleDateFormat("dd");

                try{
                    Date tempDate = oldDayFormat.parse(date);
                    Time t = new Time();
                    t.setToNow();
                    int currentMonthDay = t.monthDay;
                    String dayNumber = dayFormat.format(tempDate);
                    int selectedMonthDay = Integer.parseInt(dayNumber);

                    if (selectedMonthDay == currentMonthDay) {
                        return context.getString(R.string.today);
                    } else if (selectedMonthDay == currentMonthDay + 1) {
                        return context.getString(R.string.tomorrow);
                    } else if (selectedMonthDay == currentMonthDay - 1) {
                        return context.getString(R.string.yesterday);
                    } else {
                        return newDayFormat.format(tempDate);
                    }
                }catch(ParseException e){
                    Log.e("PagerFragment", "Parse exception: ", e);
                    return(getResources().getString(R.string.today));
                }
            }else {
                Time t = new Time();
                t.setToNow();
                int julianDay = Time.getJulianDay(dateInMillis, t.gmtoff);
                int currentJulianDay = Time.getJulianDay(System.currentTimeMillis(), t.gmtoff);
                if (julianDay == currentJulianDay) {
                    return context.getString(R.string.today);
                } else if (julianDay == currentJulianDay + 1) {
                    return context.getString(R.string.tomorrow);
                } else if (julianDay == currentJulianDay - 1) {
                    return context.getString(R.string.yesterday);
                } else {
                    Time time = new Time();
                    time.setToNow();
                    // Otherwise, the format is just the day of the week (e.g "Wednesday".
                    SimpleDateFormat dayFormat = new SimpleDateFormat("EEEE");
                    return dayFormat.format(dateInMillis);
                }
            }
        }
    }
}
