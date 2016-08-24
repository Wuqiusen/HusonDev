package com.example.mylibrary.adapter;

import java.util.ArrayList;
import java.util.List;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class ViewPagerAdapter extends FragmentPagerAdapter {
	private List<Fragment> fragmentList=new ArrayList<Fragment>();
	public ViewPagerAdapter(FragmentManager fm) {
		super(fm);		
	}
	public ViewPagerAdapter(FragmentManager fragmentManager,List<Fragment> arrayList) {
		super(fragmentManager);
		this.fragmentList=arrayList;
	}
	/**
	 * getItem()返回的是要显示的fragent对象
	 */
	@Override
	public Fragment getItem(int arg0) {
		return fragmentList.get(arg0);
	}

	/**
	 *  getCount()返回的是ViewPager页面的数量
	 */
	@Override
	public int getCount() {
		return fragmentList.size();
	}


}
