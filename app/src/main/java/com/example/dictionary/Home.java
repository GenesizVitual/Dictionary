package com.example.dictionary;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.example.dictionary.adapter.Vadapter;
import com.google.android.material.tabs.TabLayout;

public class Home extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        tabLayout = findViewById(R.id.tabview_layout);
        viewPager = findViewById(R.id.viewPager);

        tabLayout.setupWithViewPager(viewPager);

        Vadapter vadapter = new Vadapter(getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        vadapter.addFragment(new BIFragment(),"Banggai - Indonesia");
        vadapter.addFragment(new IBFragment(),"Indonesia - Banggai");
        viewPager.setAdapter(vadapter);
    }
}