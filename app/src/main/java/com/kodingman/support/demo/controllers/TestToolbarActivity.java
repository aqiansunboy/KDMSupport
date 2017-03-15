package com.kodingman.support.demo.controllers;

import android.os.Bundle;
import android.widget.TextView;

import com.kodingman.support.controllers.ToolbarActivity;
import com.kodingman.support.demo.R;

/**
 * Project Name: KDMSupport
 * File Name:    TestToolbarActivity.java
 * ClassName:    TestToolbarActivity
 *
 * Description: 测试ToolbarActivity.
 *
 * @author Chaoqian Wu
 * @date 2017年03月14日 下午2:55
 *
 * Copyright (c) 2017年, Roopto Network CO.ltd. All Rights Reserved.
 */
public class TestToolbarActivity extends DemoToolbarActivity
{
    @Override
    protected int getLayoutId()
    {
        return R.layout.demo_activity_test_toolbar;
    }

    @Override
    protected void initView(Bundle savedInstanceState)
    {
        TextView textView = (TextView) findViewById(R.id.testToolbarActivityView);
        textView.setText("测试ToolbarActivity");
    }

    @Override
    protected void initToolbar()
    {
        super.initToolbar();
        setNavigationToolbar();
        getToolbar().setTitle("这是一个测试");
    }
}
