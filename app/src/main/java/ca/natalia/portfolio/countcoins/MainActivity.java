package ca.natalia.portfolio.countcoins;


import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {
    public static final String TAG = MainActivity.class.getSimpleName();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentUtils.showFragment(this, StartFragment.TAG);
    }


}
