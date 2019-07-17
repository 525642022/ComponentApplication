package com.example.home.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.widget.Toast;

import com.example.adapter.FmPagerAdapter;
import com.example.base.BaseFragment;
import com.example.home.R;
import com.example.home.activity.MainActivity;
import com.example.home.api.ApiManager;
import com.example.home.bean.HomeBannerBean;
import com.example.home.bean.TabEntity;
import com.example.net.callback.CallBackObserver;
import com.example.utils.GlideImageLoader;
import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 作者：liujingchang
 * 时间：26/01/2018
 */

public class HomeFragment extends BaseFragment {
    private static HomeFragment homeFragment;
    CommonTabLayout home_tab_ctl;
    ViewPager viewPager;
    Banner fg_home_banner;
    private List<String> bannerList = new ArrayList<>();


    private FmPagerAdapter pagerAdapter;
    private ArrayList<Fragment> fragments = new ArrayList<>();
    private String[] mTitles = new String[]{"法律法规", "生活常识", "诗词名著"};
    private ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();
    private  List<HomeBannerBean.DataBean> data;
    public HomeFragment() {
        // Required empty public constructor
    }

    public static synchronized HomeFragment getInstance() {

        if (homeFragment == null) {
            homeFragment = new HomeFragment();
        }

        return homeFragment;
    }

    @Override
    protected int setLayoutResourceId() {
        return R.layout.fg_home;
    }

    @Override
    protected void initData(Bundle arguments) {
        home_tab_ctl = mRootView.findViewById(R.id.home_tab_ctl);
        viewPager = mRootView.findViewById(R.id.viewpager);
        fg_home_banner = mRootView.findViewById(R.id.fg_home_banner);
        ApiManager.getBannerList(new CallBackObserver<HomeBannerBean>() {
            @Override
            public void onNext(HomeBannerBean homeBannerBean) {
                if (homeBannerBean.getStatus().getCode().equals("0")) {
                    data = homeBannerBean.getData();
                    if(bannerList.size()==0){
                        for (int i = 0; i < data.size(); i++) {
                            bannerList.add(data.get(i).getPic());
                        }
                    }
                    setBanner();
                }else{
                    Toast.makeText(mContext, homeBannerBean.getStatus().getCninfo(), Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    @Override
    protected void setControl() {

        setTitle("首页");
        for (int i = 0; i < mTitles.length; i++) {
            if(mTabEntities.size() == mTitles.length){
                break;
            }
            mTabEntities.add(new TabEntity(mTitles[i], R.mipmap.home_dati, R.mipmap.home_dati));
            TabFragment tabFragment = new TabFragment();
            Bundle bundle = new Bundle();
            bundle.putString("cid", i+1 + "");
            tabFragment.setArguments(bundle);
            fragments.add(tabFragment);
        }


        home_tab_ctl.setTabData(mTabEntities);
        home_tab_ctl.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                viewPager.setCurrentItem(position);
                ((TabFragment)fragments.get(position)).getNetData();
            }

            @Override
            public void onTabReselect(int position) {
                viewPager.setCurrentItem(position);
            }
        });
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int position) {
                home_tab_ctl.setCurrentTab(position);
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
        pagerAdapter = new FmPagerAdapter(fragments, getChildFragmentManager());
        viewPager.setAdapter(pagerAdapter);
        fg_home_banner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {
                if(data.get(position).getType() == 1){
                    ((MainActivity)getActivity()).switchToFragment(2);
                }else{
                    ((MainActivity)getActivity()).switchToFragment(1);
                }

            }
        });
    }

    public void setBanner() {
        //设置banner样式
        fg_home_banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
        //设置图片加载器
        fg_home_banner.setImageLoader(new GlideImageLoader());
        //设置图片集合
        fg_home_banner.setImages(bannerList);
        //设置banner动画效果
        fg_home_banner.setBannerAnimation(Transformer.Default);
        fg_home_banner.setBannerTitles(bannerList);
        //设置自动轮播，默认为true
        fg_home_banner.isAutoPlay(true);
        //设置轮播时间
        fg_home_banner.setDelayTime(6000);
        //设置指示器位置（当banner模式中有指示器时）
        fg_home_banner.setIndicatorGravity(BannerConfig.CENTER);
        //banner设置方法全部调用完毕时最后调用
        fg_home_banner.start();
    }

}
