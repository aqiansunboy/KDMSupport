package com.kodingman.support.demo.controllers;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.kodingman.support.controllers.BaseActivity;
import com.kodingman.support.demo.R;

/**
 * Project Name: KDMSupport
 * File Name:    TestBaseActivity.java
 * ClassName:    TestBaseActivity
 *
 * Description: 测试BaseActivity.
 *
 * @author Chaoqian Wu
 * @date 2017年03月14日 下午2:36
 *
 * Copyright (c) 2017年, Roopto Network CO.ltd. All Rights Reserved.
 */
public class TestBaseActivity extends BaseActivity
{
    @Override
    protected int getLayoutId()
    {
        return R.layout.demo_activity_test_base;
    }

    @Override
    protected void initView(Bundle savedInstanceState)
    {
        TextView textView = (TextView) findViewById(R.id.testBaseActivityView);
        textView.setText("测试BaseActivity");

        Button testToolbarBtn = (Button) findViewById(R.id.testToolbarBtn);
        testToolbarBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(getContext(), TestToolbarActivity.class);
                startActivity(intent);
            }
        });
    }
}
