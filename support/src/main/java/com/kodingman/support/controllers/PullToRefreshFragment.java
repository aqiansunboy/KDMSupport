package com.kodingman.support.controllers;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kodingman.support.R;

/**
 * Project Name: ToothEye4Android
 * File Name:    PullToRefreshFragment.java
 * ClassName:    PullToRefreshFragment
 *
 * Description: 具备下拉刷新统一基类.
 *
 * @author Chaoqian Wu
 * @date 2016年05月29日 下午6:37
 *
 * Copyright (c) 2016年, Roopto Network CO.ltd. All Rights Reserved.
 */
public abstract class PullToRefreshFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener
{
    /**
     * 具备下拉刷新
     */
    public static final int PULL_MODE_REFRESH = 0;
    /**
     * 具备下拉刷新和加载更多
     */
    public static final int PULL_MODE_REFRESH_LOADMORE = 1;
    /**
     * 禁用
     */
    public static final int PULL_MODE_DISABLED = 2;

    private boolean mRefreshLayoutCreated = false;

    protected SwipeRefreshLayout refreshLayout;

    protected int getPullMode()
    {
        return PULL_MODE_REFRESH;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = super.onCreateView(inflater, container, savedInstanceState);

        if (getPullMode() != PULL_MODE_DISABLED)
        {
            refreshLayout = (SwipeRefreshLayout) getContentViewLayout().findViewById(R.id.refresh);
            if (refreshLayout == null)
            {
                throw new IllegalStateException("You should define RefreshLayout id as @id/refresh");
            }

            if (!mRefreshLayoutCreated)
            {
                diyRfreshLayout(refreshLayout);
                refreshLayout.setOnRefreshListener(this);
                mRefreshLayoutCreated = true;
            }
        }

        return view;
    }

    /**
     * 自定义下拉刷新样式
     * @param refreshLayout
     */
    protected abstract void diyRfreshLayout(SwipeRefreshLayout refreshLayout);

    @Override
    public void onRefresh()
    {

    }
}
