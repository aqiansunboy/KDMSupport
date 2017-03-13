package com.kodingman.support.controllers;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import com.kodingman.support.R;

/**
 * Project Name: VTECSupport
 * File Name:    BaseActivity.java
 * ClassName:    BaseActivity
 *
 * Description: 全局Activity基类.
 *
 * @author Chaoqian Wu
 * @date 2016年05月24日 下午10:36
 *
 * Copyright (c) 2016年, Roopto Network CO.ltd. All Rights Reserved.
 */
public abstract class BaseActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        // 初始化数据
        initData(getIntent());

        // 初始化视图
        setContentView(getLayoutId());
        initView(savedInstanceState);
    }

    protected abstract int getLayoutId();

    protected abstract void initView(Bundle savedInstanceState);

    protected void initData(Intent intent)
    {
        initData(intent.getExtras()==null ? new Bundle() : intent.getExtras());
    }

    protected void initData(Bundle extra)
    {
    }

    protected BaseActivity getContext()
    {
        return this;
    }

    /**
     * 关闭当前Activity
     * @param animated 是否需要动画
     */
    public void finishActivity(boolean animated)
    {
        if (!this.isFinishing())
        {
            if (!animated)
            {
                finish();
            }
            else
            {
                // 使用自定义动画退回
                finish();
                /*overridePendingTransition(R.anim.m4399_navigtor_pop_right_in,
                                          R.anim.m4399_navigtor_pop_right_out);*/
            }
        }
    }

    /**
     * 打开Fragment页面
     *
     * @param fragmentClass
     */
    public void startFragment(Class<? extends Fragment> fragmentClass)
    {
        startFragment(fragmentClass, null);
    }

    /**
     * 打开Fragment页面
     *
     * @param fragmentClass
     * @param args
     */
    public void startFragment(Class<? extends Fragment> fragmentClass, Bundle args)
    {
        this.startFragment(fragmentClass, R.id.fragment_container , null);
    }

    public void startFragment(Class<? extends Fragment> fragmentClass, int containerId, Bundle args)
    {
        Fragment fragment = null;
        if (fragment == null)
        {
            try
            {
                fragment = fragmentClass.newInstance();
            }
            catch (InstantiationException e)
            {
                e.printStackTrace();
            }
            catch (IllegalAccessException e)
            {
                e.printStackTrace();
            }
        }

        if (fragment != null)
        {
            this.startFragment(fragment, containerId, args);
        }
    }

    /**
     * 打开Fragment页面
     *
     * @param fragment
     */
    public void startFragment(Fragment fragment)
    {
        startFragment(fragment, null, null);
    }

    /**
     * 打开Fragment页面
     *
     * @param fragment
     * @param args
     */
    public void startFragment(Fragment fragment, Bundle args)
    {
        startFragment(fragment, args, null);
    }

    /**
     * 打开Fragment页面
     *
     * @param fragment
     * @param containerId
     * @param args
     */
    public void startFragment(Fragment fragment, int containerId, Bundle args)
    {
        startFragment(fragment, containerId, args, null);
    }

    /**
     * 打开Fragment页面
     *
     * @param fragment
     * @param args
     * @param tag
     */
    public void startFragment(Fragment fragment, Bundle args, String tag)
    {
        startFragment(fragment, R.id.fragment_container, args, tag);
    }

    /**
     * 打开Fragment页面
     *
     * @param fragment
     * @param containerId
     * @param args
     * @param tag
     */
    public void startFragment(Fragment fragment, int containerId, Bundle args, String tag)
    {
        if (fragment == null)
        {
            throw new IllegalArgumentException("fragment should not be null");
        }
        fragment.setArguments(args);
        getSupportFragmentManager().beginTransaction()
                                   .replace(containerId, fragment, tag)
                                   .commitAllowingStateLoss();
        getSupportFragmentManager().executePendingTransactions();
    }
}
