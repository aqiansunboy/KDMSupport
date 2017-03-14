package com.kodingman.support.controllers;

import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kodingman.support.R;

/**
 * Project Name: ToothEye4Android
 * File Name:    BaseFragment.java
 * ClassName:    BaseFragment
 *
 * Description: 全局Fragment基类.
 *
 * @author Chaoqian Wu
 * @date 2016年05月28日 下午10:24
 *
 * Copyright (c) 2016年, Roopto Network CO.ltd. All Rights Reserved.
 */
public abstract class BaseFragment extends Fragment
{
    /**
     * 界面主视图
     */
    private View mRootView;

    /**
     * 界面内容布局
     */
    private ViewGroup mContentViewLayout;

    /**
     * 工具条对象
     */
    private Toolbar mToolbar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        if (mRootView == null)
        {
            if (isSupportToolbar())
            {
                mRootView = inflater.inflate(getSupportToolbarLayoutId(), container, false);
                initToolbar();
            }
            else
            {
                mRootView = inflater.inflate(R.layout.layout_disable_toolbar, container, false);
            }
            View contentView = View.inflate(getContext(), getLayoutId(), null);
            ((ViewGroup) mRootView).addView(contentView, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));

            mContentViewLayout = (ViewGroup) contentView;

            initView(mContentViewLayout, savedInstanceState);
        }

        return mRootView;
    }

    public View getRootView()
    {
        return mRootView;
    }

    public ViewGroup getContentViewLayout()
    {
        return mContentViewLayout;
    }

    public Toolbar getToolbar()
    {
        if (mToolbar != null)
        {
            return mToolbar;
        }
        else
        {
            if (getParentFragment() != null && getParentFragment() instanceof BaseFragment)
            {
                mToolbar = ((BaseFragment)getParentFragment()).getToolbar();
            }
        }
        return mToolbar;
    }

    protected boolean isSupportToolbar()
    {
        // 如果外围Activity已经是带Toolbar的，那所包含的Fragment都不需要支持Toolbar
        if (getContext() !=null && getContext() instanceof ToolbarActivity)
        {
            return false;
        }
        return getParentFragment()==null;
    }

    protected int getMenuId()
    {
        return -1;
    }
    protected int getSupportToolbarLayoutId()
    {
        return R.layout.layout_support_toolbar;
    }

    protected abstract int getLayoutId();
    protected abstract void initView(ViewGroup container, Bundle savedInstanceState);

    @CallSuper
    protected void initToolbar()
    {
        mToolbar = (Toolbar) mRootView.findViewById(R.id.toolbar);
        if (mToolbar != null)
        {
            if (getMenuId() > 0)
            {
                mToolbar.inflateMenu(getMenuId());
            }
        }
    }

    protected final void setNavigationToolbar()
    {
        mToolbar.setNavigationIcon(getNavigationIcon());
        mToolbar.setNavigationOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                if (getContext() == null)
                {
                    return;
                }

                getContext().finishActivity(true);
            }
        });
    }

    /**
     * 提供默认的返回箭头
     * @return
     */
    protected int getNavigationIcon()
    {
        return R.mipmap.ic_arrow_back_black;
    }

    public BaseActivity getContext()
    {
        return (BaseActivity) getActivity();
    }

    @Override
    public void onDestroyView()
    {
        super.onDestroyView();

        if (mRootView != null)
        {
            ((ViewGroup) mRootView.getParent()).removeView(mRootView);
        }
    }

    @Override
    public void onDestroy()
    {
        super.onDestroy();

        if (mToolbar != null)
        {
            mToolbar.setNavigationOnClickListener(null);
            mToolbar.setOnMenuItemClickListener(null);
        }
    }
}
