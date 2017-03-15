package com.kodingman.support.demo.controllers;

import android.os.Bundle;

import com.kodingman.support.controllers.ToolbarActivity;
import com.kodingman.support.demo.R;

/**
 * Project Name: KDMSupport
 * File Name:    DemoToolbarActivity.java
 * ClassName:    DemoToolbarActivity
 *
 * Description: 自定义ToolbarActivity.
 *
 * @author Chaoqian Wu
 * @date 2017年03月15日 下午3:37
 *
 * Copyright (c) 2017年, Roopto Network CO.ltd. All Rights Reserved.
 */
public abstract class DemoToolbarActivity extends ToolbarActivity
{
    @Override
    protected int getToolbarLayoutId()
    {
        return R.layout.demo_toolbar;
    }
}
