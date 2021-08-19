package com.vincenzocassown.bottomnavviewpager2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.material.navigation.NavigationBarView;
import com.vincenzocassown.bottomnavviewpager2.adapter.MyViewPagerAdapter;
import com.vincenzocassown.bottomnavviewpager2.databinding.ActivityMainBinding;
import com.vincenzocassown.bottomnavviewpager2.transformer.DepthPageTransformer;
import com.vincenzocassown.bottomnavviewpager2.transformer.ZoomOutPageTransformer;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private MyViewPagerAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        adapter = new MyViewPagerAdapter(this);
        binding.viewPager2.setAdapter(adapter);

        binding.bottomNavigation.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.bottomHome:
                        binding.viewPager2.setCurrentItem(0);
                        break;
                    case R.id.bottomFav:
                        binding.viewPager2.setCurrentItem(1);
                        break;
                    case R.id.bottomHistory:
                        binding.viewPager2.setCurrentItem(2);
                        break;
                }
                return true;
            }
        });

        binding.viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                switch (position){
                    case 0:
                        binding.bottomNavigation.getMenu().findItem(R.id.bottomHome).setChecked(true);
                        break;
                    case 1:
                        binding.bottomNavigation.getMenu().findItem(R.id.bottomFav).setChecked(true);
                        break;
                    case 2:
                        binding.bottomNavigation.getMenu().findItem(R.id.bottomHistory).setChecked(true);
                        break;
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId()==R.id.menu_zoom){
            binding.viewPager2.setPageTransformer(new ZoomOutPageTransformer());
        }
        else if (item.getItemId()==R.id.menu_depth){
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                binding.viewPager2.setPageTransformer(new DepthPageTransformer());
            }
        }
        return true;
    }
}