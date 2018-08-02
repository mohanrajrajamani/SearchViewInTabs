package com.example.admin.searchviewintabs.activity;

import android.app.SearchManager;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewConfiguration;
import android.widget.Toast;

import com.example.admin.searchviewintabs.R;
import com.example.admin.searchviewintabs.fragments.SystemDefaultAppFragment;
import com.example.admin.searchviewintabs.fragments.UserAppFragment;
import com.example.admin.searchviewintabs.helper.CustomList;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;



public class IconTextTabsActivity extends AppCompatActivity {

	private Toolbar toolbar;
	private TabLayout tabLayout;
	private ViewPager viewPager;
	private int[] tabIcons = { R.drawable.ic_tab_favourite,
			R.drawable.ic_tab_call, R.drawable.ic_tab_contacts };
	CustomList adapter;
	SearchView searchView = null;
	UserAppFragment chatFragment;
	SystemDefaultAppFragment groupsFragment;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_icon_text_tabs);

		toolbar = (Toolbar) findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);

		viewPager = (ViewPager) findViewById(R.id.viewpager);
		setupViewPager(viewPager);

		tabLayout = (TabLayout) findViewById(R.id.tabs);
		tabLayout.setupWithViewPager(viewPager);
		setupTabIcons();
		makeActionOverflowMenuShown();

		// Attach the page change listener inside the activity
		viewPager.addOnPageChangeListener(new OnPageChangeListener() {

			// This method will be invoked when a new page becomes selected.
			@Override
			public void onPageSelected(int position) {
				Toast.makeText(getApplicationContext(),
						"Selected page position: " + position,
						Toast.LENGTH_SHORT).show();
				if (searchView != null && !searchView.isIconified()) {
					//searchView.onActionViewExpanded();
					searchView.setIconified(true);
					searchView.setIconified(true);
					
				}
			}

			// This method will be invoked when the current page is scrolled
			@Override
			public void onPageScrolled(int position, float positionOffset,
					int positionOffsetPixels) {
				// Code goes here
				Toast.makeText(getApplicationContext(),
						"onPageScrolled",
						Toast.LENGTH_SHORT).show();
			}

			// Called when the scroll state changes:
			// SCROLL_STATE_IDLE, SCROLL_STATE_DRAGGING, SCROLL_STATE_SETTLING
			@Override
			public void onPageScrollStateChanged(int state) {
				// Code goes here
				Toast.makeText(getApplicationContext(),
						"onPageScrollStateChanged",
						Toast.LENGTH_SHORT).show();
			}
		});
	}
	
	private void makeActionOverflowMenuShown() {
	    //devices with hardware menu button (e.g. Samsung Note) don't show action overflow menu
	    try {
	        ViewConfiguration config = ViewConfiguration.get(this);
	        Field menuKeyField = ViewConfiguration.class.getDeclaredField("sHasPermanentMenuKey");
	        if (menuKeyField != null) {
	            menuKeyField.setAccessible(true);
	            menuKeyField.setBoolean(config, false);
	        }
	    } catch (Exception e) {
	        Log.d("", e.getLocalizedMessage());
	    }
	}
	
	private void setupTabIcons() {
		tabLayout.getTabAt(0).setIcon(tabIcons[0]);
		tabLayout.getTabAt(1).setIcon(tabIcons[2]);
		// tabLayout.getTabAt(2).setIcon(tabIcons[1]);
	}

	private void setupViewPager(ViewPager viewPager) {
		ViewPagerAdapter adapter = new ViewPagerAdapter(
				getSupportFragmentManager());
		adapter.addFrag(new UserAppFragment(), "User App");
		adapter.addFrag(new SystemDefaultAppFragment(), "System App");
		viewPager.setAdapter(adapter);
	}

	class ViewPagerAdapter extends FragmentStatePagerAdapter {
		private final List<Fragment> mFragmentList = new ArrayList<>();
		private final List<String> mFragmentTitleList = new ArrayList<>();

		public ViewPagerAdapter(FragmentManager manager) {
			super(manager);
		}

		@Override
		public Fragment getItem(int position) {
			return mFragmentList.get(position);
		}

		@Override
		public int getCount() {
			return mFragmentList.size();
		}

		public void addFrag(Fragment fragment, String title) {
			mFragmentList.add(fragment);
			mFragmentTitleList.add(title);
		}

		@Override
		public CharSequence getPageTitle(int position) {
			return mFragmentTitleList.get(position);
		}

		@Override
		public int getItemPosition(Object object) {
			// Causes adapter to reload all Fragments when
			// notifyDataSetChanged is called
			notifyDataSetChanged();
			return POSITION_NONE;
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.menu_icon_text_tabs, menu);
		SearchManager searchManager = (SearchManager) getSystemService(this.SEARCH_SERVICE);
		MenuItem mSearchMenuItem = menu.findItem(R.id.action_search);
		searchView = (SearchView) menu.findItem(R.id.action_search)
				.getActionView();

		if (mSearchMenuItem != null) {
			searchView = (SearchView) mSearchMenuItem.getActionView();
		}
		if (searchView != null) {
			searchView.setSearchableInfo(searchManager.getSearchableInfo(this
					.getComponentName()));
		}
		searchView.setIconifiedByDefault(true);
		MenuItemCompat.expandActionView(mSearchMenuItem);
		
		SearchView.OnQueryTextListener queryTextListener = new SearchView.OnQueryTextListener() {
			public boolean onQueryTextChange(String query) {
				// this is your adapter that will be filtered
				PagerAdapter pagerAdapter = (PagerAdapter) viewPager
						.getAdapter();
				for (int i = 0; i < pagerAdapter.getCount(); i++) {

					Fragment viewPagerFragment = (Fragment) viewPager
							.getAdapter().instantiateItem(viewPager, i);
					if (viewPagerFragment != null
							&& viewPagerFragment.isAdded()) {

						if (viewPagerFragment instanceof UserAppFragment) {
							chatFragment = (UserAppFragment) viewPagerFragment;
							if (chatFragment != null) {
								chatFragment.beginSearch(query);
							}
						} else if (viewPagerFragment instanceof SystemDefaultAppFragment) {
							groupsFragment = (SystemDefaultAppFragment) viewPagerFragment;
							if (groupsFragment != null) {
								groupsFragment.beginSearch(query);
							}
						}
					}
				}

				return false;

			}

			public boolean onQueryTextSubmit(String query) {
				// Here u can get the value "query" which is entered in the

				return false;

			}
		};
		searchView.setOnQueryTextListener(queryTextListener);

		return super.onCreateOptionsMenu(menu);
	}
}
