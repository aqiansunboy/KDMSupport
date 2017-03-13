package com.kodingman.support.controllers;

import android.support.annotation.CallSuper;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.kodingman.support.R;

import me.zhanghai.android.materialprogressbar.MaterialProgressBar;

/**
 * Project Name: ToothEye4Android
 * File Name:    ToolbarActivity.java
 * ClassName:    ToolbarActivity
 *
 * Description: 带Toolbar的Activity.
 *
 * @author Chaoqian Wu
 * @date 2016年05月25日 下午9:09
 *
 * Copyright (c) 2016年, Roopto Network CO.ltd. All Rights Reserved.
 */
public abstract class ProgressToolbarActivity extends BaseActivity
{
    private Toolbar mToolbar;

    private MaterialProgressBar mProgressBar;

    @CallSuper
    protected void initToolbar()
    {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        if (mToolbar != null)
        {
            if (getMenuId() > 0)
            {
                mToolbar.inflateMenu(getMenuId());
            }
        }
    }

    public Toolbar getToolbar()
    {
        return mToolbar;
    }

    @Override
    public void setContentView(int layoutId)
    {
        setContentView(View.inflate(this, layoutId, null));
    }

    @Override
    public void setContentView(View view)
    {
        super.setContentView(R.layout.layout_support_progress_toolbar);
        LinearLayout rootLayout = (LinearLayout) findViewById(R.id.root_layout);
        if (rootLayout == null)
        {
            return;
        }
        rootLayout.addView(view, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                                                             ViewGroup.LayoutParams.MATCH_PARENT));

        mProgressBar = (MaterialProgressBar) findViewById(R.id.horizontal_progress_toolbar);

        initToolbar();
    }

    protected int getMenuId()
    {
        return -1;
    }

    protected final void setNavigationToolbar()
    {
        mToolbar.setNavigationIcon(getNavigationIcon());
        mToolbar.setNavigationOnClickListener(  new View.OnClickListener()
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

    protected MaterialProgressBar getProgressBar()
    {
        return mProgressBar;
    }

}
