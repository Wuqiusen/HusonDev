package com.example.huson.husondevandroid.ui.fragment.guide;

import java.util.ArrayList;
import java.util.List;

import com.example.huson.husondevandroid.R;
import com.example.mylibrary.animation.ZoomOutPageTransformer;
import com.example.mylibrary.adapter.ViewPagerAdapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Window;
/**
 * ViewPager 引导
 *
 */
public class ViewPagerActivity extends FragmentActivity {
	private ViewPager mVPActivity;
	private Fragment1 mFragment1;
	private Fragment2 mFragment2;
	private Fragment3 mFragment3;
	private Fragment4 mFragment4;
	private List<Fragment> mListFragment = new ArrayList<Fragment>();
	private PagerAdapter mPgAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_viewpager);
		initView();
	}

	private void initView() {
		mVPActivity = (ViewPager) findViewById(R.id.vp_activity);
		mFragment1 = new Fragment1();
		mFragment2 = new Fragment2();
		mFragment3 = new Fragment3();
		mFragment4 = new Fragment4();
		mListFragment.add(mFragment1);
		mListFragment.add(mFragment2);
		mListFragment.add(mFragment3);
		mListFragment.add(mFragment4);
		/**
		 * 传递两个参数
		 * 第一个布尔型参数 表示在两个页面切换产生动画效果时候是否要反转一下，让下一个页面在上一个页面底下，
		 * 因为 ViewPager 默认下一个页面是绘制在上一个页面的上面，这里一般传入 true 。
		 *第二次参数才是重点，这里实现了 PageTransformer接口，
		 * 然后我们所有需要的动画效果都在 transformPage 这个接口方法里面实现.
		 */
		mPgAdapter = new ViewPagerAdapter(getSupportFragmentManager(),
				mListFragment);
		mVPActivity.setAdapter(mPgAdapter);
//		mVPActivity.setPageTransformer(true, new SectorPageTransformer());
		mVPActivity.setPageTransformer(true, new ZoomOutPageTransformer());
//		mVPActivity.setPageTransformer(false, new ViewPager.PageTransformer() {
//
//			@Override
//			public void transformPage(View page, float position) {
//				final float normalizedposition = Math.abs(Math.abs(position) - 1);
//			    page.setScaleX(normalizedposition / 2 + 0.5f);
//			    page.setScaleY(normalizedposition / 2 + 0.5f);
//			}
//		});

	}
}


