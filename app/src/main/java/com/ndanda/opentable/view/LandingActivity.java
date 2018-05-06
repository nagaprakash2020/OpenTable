package com.ndanda.opentable.view;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.ndanda.opentable.OpenTableApplication;
import com.ndanda.opentable.R;

public class LandingActivity extends AppCompatActivity {

    ReviewsFragment reviewsFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing);
    }

    @Override
    protected void onStart() {
        super.onStart();
        showSearchFragment();
    }

    private void showSearchFragment() {

        reviewsFragment = (ReviewsFragment) getSupportFragmentManager().findFragmentByTag(ReviewsFragment.class.getName());

        if (reviewsFragment == null) {
            reviewsFragment = ReviewsFragment.newInstance();
        }

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.container, reviewsFragment, ReviewsFragment.class.getName());
        fragmentTransaction.commit();
    }
}