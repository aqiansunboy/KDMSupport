package com.kodingman.support.controllers;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.roopto.tootheye.R;

/**
 * Project Name: ToothEye4Android
 * File Name:    PullToRefreshRecyclerFragment.java
 * ClassName:    PullToRefreshRecyclerFragment
 *
 * Description: 自带下拉刷新.
 *
 * @author Chaoqian Wu
 * @date 2016年05月29日 下午10:33
 *
 * Copyright (c) 2016年, Roopto Network CO.ltd. All Rights Reserved.
 */
public abstract class PullToRefreshRecyclerFragment extends NetworkFragment
{
    protected RecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        View view = super.onCreateView(inflater, container, savedInstanceState);

        recyclerView = (RecyclerView) getContentViewLayout().findViewById(R.id.recyclerView);
        if (recyclerView == null)
        {
            throw new IllegalStateException("You should define RecyclerView id as @id/recyclerView.");
        }

        setupRecyclerView();
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener()
        {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState)
            {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy)
            {
                super.onScrolled(recyclerView, dx, dy);
                if (dy > 0 && getPullMode() == PULL_MODE_REFRESH_LOADMORE)
                {
                    onScrollUp();
                }
            }
        });
        return view;
    }

    private void onScrollUp()
    {
        if (!(recyclerView.getLayoutManager() instanceof  LinearLayoutManager))
        {
            return;
        }
        LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
        int count = layoutManager.getChildCount();
        if (layoutManager.findLastVisibleItemPosition() > count - 9 && getApi().hasMore())
        {
            onLoadData();
        }
    }

    @Override
    protected int getPullMode()
    {
        return PULL_MODE_REFRESH;
    }

    protected abstract void setupRecyclerView();
}
