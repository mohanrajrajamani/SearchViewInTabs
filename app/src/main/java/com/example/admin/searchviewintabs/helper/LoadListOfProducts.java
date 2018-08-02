package com.example.admin.searchviewintabs.helper;

import java.util.ArrayList;
import android.app.Activity;
import android.widget.ListView;

public class LoadListOfProducts extends Activity {
	static ArrayList<ProductList> appList;
	static CustomList adapter;

	public static CustomList loadListOfInstalledApps(final Activity context,
			ListView list, boolean userApp) {

		appList = new ArrayList<ProductList>();

		if (userApp) {

			for (int i = 0; i < 15; i++) {
				ProductList apps = new ProductList("Title" + i, "Subtitle" + i);
				appList.add(apps);
			}

		} else {

			for (int i = 20; i < 35; i++) {
				ProductList apps = new ProductList("Title" + i, "Subtitle" + i);
				appList.add(apps);
			}
		}

		adapter = new CustomList(context, appList);
		list.setAdapter(adapter);
		adapter.notifyDataSetChanged();

		return adapter;
	}

}
