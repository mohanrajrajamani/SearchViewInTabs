package com.example.admin.searchviewintabs.fragments;

import java.util.ArrayList;
import java.util.List;

import android.content.pm.PackageInfo;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.admin.searchviewintabs.R;
import com.example.admin.searchviewintabs.helper.CustomList;
import com.example.admin.searchviewintabs.helper.LoadListOfProducts;
import com.example.admin.searchviewintabs.helper.ProductList;


public class UserAppFragment extends Fragment {

	View rootView;
	ArrayList<ProductList> appList;
	CustomList adapter;
	ListView list;
	List<PackageInfo> packageList1;
	SearchView searchView = null;

	public UserAppFragment() {
		// Required empty public constructor
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// Inflate the layout for this fragment

		rootView = inflater.inflate(R.layout.user_app_list, container, false);
		list = (ListView) rootView.findViewById(R.id.list);
		setHasOptionsMenu(true);
		// loadListOfInstalledApps();
		adapter = LoadListOfProducts.loadListOfInstalledApps(
				getActivity(), list, true);

		return rootView;
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);

		setHasOptionsMenu(true);

	}

	@Override
	public void onResume() {
		super.onResume();

		adapter = LoadListOfProducts.loadListOfInstalledApps(
				getActivity(), list, true);
		// adapter.notifyDataSetChanged();
	}
	
	public void beginSearch(String query) {
	    adapter.getFilter().filter(query);
	}

//	@Override
//	public void onPrepareOptionsMenu(Menu menu) {
//		MenuInflater menuInflater = getActivity().getMenuInflater();
//		menuInflater.inflate(R.menu.menu_icon_text_tabs, menu);
//
//		MenuItem mSearchMenuItem = menu.findItem(R.id.action_search);
//		SearchManager searchManager = (SearchManager) getActivity()
//				.getSystemService(Context.SEARCH_SERVICE);
//
//		if (mSearchMenuItem != null) {
//			searchView = (SearchView) mSearchMenuItem.getActionView();
//		}
//		if (searchView != null) {
//			searchView.setSearchableInfo(searchManager
//					.getSearchableInfo(getActivity().getComponentName()));
//		}
//
//		searchView.setOnQueryTextListener(new OnQueryTextListener() {
//
//			@Override
//			public boolean onQueryTextSubmit(String query) {
//				adapter.getFilter().filter(query);
//				return true;
//			}
//
//			@Override
//			public boolean onQueryTextChange(String newText) {
//				if (adapter != null) {
//					if (!TextUtils.isEmpty(newText)) {
//						adapter.getFilter().filter(newText);
//					}
//					// adapter.notifyDataSetChanged();
//				}
//				return true;
//			}
//		});
//
//		searchView.setOnCloseListener(new OnCloseListener() {
//
//			@Override
//			public boolean onClose() {
//				// TODO Auto-generated method stub
//				adapter.getFilter().filter("");
//				return false;
//			}
//		});
//
//	}

}
