package com.kodingman.support.controllers;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.kodingman.support.R;

/**
 * Project Name: ToothEye4Android
 * File Name:    NetworkFragment.java
 * ClassName:    NetworkFragment
 *
 * Description: 统一网络请求.
 *
 * @author Chaoqian Wu
 * @date 2016年05月30日 下午10:53
 *
 * Copyright (c) 2016年, Roopto Network CO.ltd. All Rights Reserved.
 */
public abstract class NetworkFragment extends PullToRefreshFragment
{
    /**
     * 是否第一次加载到数据
     */
    private boolean isLoadedFirst = true;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        View view = super.onCreateView(inflater, container, savedInstanceState);
        if (isSupportErrorView())
        {
            View errorView = View.inflate(getContext(), getErrorViewLayoutId(), null);
            if (isSupportToolbar())
            {
                ((ViewGroup) getRootView()).addView(errorView, 1, new ViewGroup.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            }
            else
            {
                ((ViewGroup) getRootView()).addView(errorView, 0, new ViewGroup.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            }
            getRootView().findViewById(R.id.error_layout).setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    onErrorViewClick(v);
                }
            });
        }
        return view;
    }

    private boolean isSupportErrorView()
    {
        return getErrorViewLayoutId() != 0;
    }

    protected int getErrorViewLayoutId()
    {
        return R.layout.kdm_view_loading;
    }

    protected void showErrorView()
    {
        if (getRootView() != null && isSupportErrorView())
        {
            getRootView().findViewById(R.id.error_layout).setVisibility(View.VISIBLE);
            getRootView().findViewById(R.id.error_pic).setVisibility(View.VISIBLE);
            ((TextView)getRootView().findViewById(R.id.error_tip)).setText(R.string.error_network_retry);
        }
    }

    protected void dismissErrorView()
    {
        if (getRootView() != null && isSupportErrorView())
        {
            getRootView().findViewById(R.id.error_layout).setVisibility(View.GONE);
            getRootView().findViewById(R.id.error_pic).setVisibility(View.GONE);
        }
    }

    @Override
    public void onStart()
    {
        super.onStart();
        /*if (getApi() != null && getApi().isFirstLoad())
        {
            onLoadData();
        }*/
    }

    @Override
    public void onRefresh()
    {
        onReloadData();
    }

    @Override
    protected int getPullMode()
    {
        return PULL_MODE_DISABLED;
    }

    protected void onLoadData()
    {
        /*if (null != getApi() && !getApi().isLoading())
        {
            getApi().loadData(this);
        }*/
    }

    protected void onReloadData()
    {
        /*if (null != getApi())
        {
            getApi().reloadData(this);
        }*/
    }

    // protected abstract RetrofitApi getApi();

    protected abstract void onDataSetChanged();

    /*@Override
    public void onBefore()
    {
        Timber.d("开始请求");
    }

    @Override
    public void onSuccess()
    {
        if (refreshLayout != null)
        {
            refreshLayout.setRefreshing(false);
        }
        isLoadedFirst = false;
        dismissErrorView();
        onDataSetChanged();
    }

    @Override
    public void onFailure(Throwable error)
    {
        if (refreshLayout != null)
        {
            refreshLayout.setRefreshing(false);
        }
        if (isLoadedFirst)
        {
            showErrorView();
        }
        Timber.e(error, "%s", error.toString());
    }*/

    protected void onErrorViewClick(View view)
    {
        if (getRootView() != null)
        {
            ((TextView)getRootView().findViewById(R.id.error_tip)).setText(R.string.error_network_retry);
        }
        onReloadData();
    }
}
