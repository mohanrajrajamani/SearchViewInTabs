package com.example.admin.searchviewintabs.helper;

import java.util.ArrayList;
import java.util.List;



import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.example.admin.searchviewintabs.R;

public class CustomList extends BaseAdapter implements Filterable {

	private final Activity context;

	List<String> atr = new ArrayList<String>();
	List<PackageInfo> packageList;

	PackageManager packageManager;
	ArrayList<ProductList> appsList;
	ArrayList<ProductList> mStringFilterList;
	ValueFilter valueFilter;

	public CustomList(Activity context, ArrayList<ProductList> appsList) {
		super();
		this.context = context;
		this.appsList = appsList;
		mStringFilterList = appsList;

	}

	private class ViewHolder {
		TextView txtTitle, txtSubTitle;

	}

	public int getCount() {
		return appsList.size();
	}

	public Object getItem(int position) {
		return appsList.get(position);
	}

	public long getItemId(int position) {
		return appsList.indexOf(getItem(position));
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		LayoutInflater inflater = context.getLayoutInflater();

		if (convertView == null) {
			convertView = inflater.inflate(R.layout.list_adapter, null);
			holder = new ViewHolder();

			holder.txtTitle = (TextView) convertView.findViewById(R.id.txt);
			holder.txtSubTitle = (TextView) convertView
					.findViewById(R.id.artist);

			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		final ProductList apps = appsList.get(position);
		getItem(position);

		holder.txtTitle.setText(apps.getTitle());
		holder.txtSubTitle.setText(apps.getSubTitle());

		return convertView;
	}

	@Override
	public Filter getFilter() {
		if (valueFilter == null) {
			valueFilter = new ValueFilter();
		}
		return valueFilter;
	}

	@SuppressLint("DefaultLocale")
	private class ValueFilter extends Filter {
		@SuppressLint("DefaultLocale")
		@Override
		protected FilterResults performFiltering(CharSequence constraint) {
			FilterResults results = new FilterResults();
			ProductList applist = null;
			if (constraint != null && constraint.length() > 0) {

				ArrayList<ProductList> filterList = new ArrayList<ProductList>();
				for (int i = 0; i < mStringFilterList.size(); i++) {
					if ((mStringFilterList.get(i).getTitle().toUpperCase())
							.contains(constraint.toString().toUpperCase())) {

						applist = new ProductList(mStringFilterList.get(i)
								.getTitle(), mStringFilterList.get(i)
								.getSubTitle());

						filterList.add(applist);
					}
				}
				results.count = filterList.size();
				results.values = filterList;
			} else {
				results.count = mStringFilterList.size();
				results.values = mStringFilterList;
			}
			return results;

		}

		@SuppressWarnings("unchecked")
		@Override
		protected void publishResults(CharSequence constraint,
				FilterResults results) {
			appsList = (ArrayList<ProductList>) results.values;
			notifyDataSetChanged();

		}

	}

}
