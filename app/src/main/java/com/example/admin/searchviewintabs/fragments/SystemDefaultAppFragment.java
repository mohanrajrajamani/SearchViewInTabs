package com.example.admin.searchviewintabs.fragments;


import java.util.ArrayList;
import java.util.List;

import android.content.pm.PackageInfo;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.admin.searchviewintabs.R;
import com.example.admin.searchviewintabs.helper.CustomList;
import com.example.admin.searchviewintabs.helper.LoadListOfProducts;
import com.example.admin.searchviewintabs.helper.ProductList;


public class SystemDefaultAppFragment extends Fragment {

	View rootView;
	ArrayList<ProductList> appList;
	CustomList adapter;
	ListView list;
	List<PackageInfo> packageList1;

	public SystemDefaultAppFragment() {
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

		rootView = inflater.inflate(R.layout.system_app_list, container, false);
		list = (ListView) rootView.findViewById(R.id.system_app_list);
		setHasOptionsMenu(true);
		adapter = LoadListOfProducts.loadListOfInstalledApps(
				getActivity(), list, true);
		adapter.notifyDataSetChanged();
		return rootView;
	}

	@Override
	public void onResume() {
		super.onResume();

		adapter = LoadListOfProducts.loadListOfInstalledApps(
				getActivity(), list, false);
		//adapter.notifyDataSetChanged();
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);

		setHasOptionsMenu(true);

	}

	public void beginSearch(String query) {
	    Log.e("QueryFragment", query);
	    adapter.getFilter().filter(query);
	}
	
	//In order to create a separate searchview for each fragment
	//make use of the following set of codes to filter.
	
	/*@Override
	public void onPrepareOptionsMenu(Menu menu) {
		MenuInflater menuInflater = getActivity().getMenuInflater();
		menuInflater.inflate(R.menu.menu_icon_text_tabs, menu);

		MenuItem mSearchMenuItem = menu.findItem(R.id.action_search);
		SearchManager searchManager = (SearchManager) getActivity()
				.getSystemService(Context.SEARCH_SERVICE);

		SearchView searchView = null;
		if (mSearchMenuItem != null) {
			searchView = (SearchView) mSearchMenuItem.getActionView();
		}
		if (searchView != null) {
			searchView.setSearchableInfo(searchManager
					.getSearchableInfo(getActivity().getComponentName()));
		}

		searchView.setOnQueryTextListener(new OnQueryTextListener() {

			@Override
			public boolean onQueryTextSubmit(String query) {
				return false;
			}

			@Override
			public boolean onQueryTextChange(String newText) {
				if (adapter != null) {

					adapter.getFilter().filter(newText);
					adapter.notifyDataSetChanged();
				} else {

					adapter.notifyDataSetChanged();
				}

				return true;
			}
		});
	}*/

}
