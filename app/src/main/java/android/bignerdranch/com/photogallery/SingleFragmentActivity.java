package android.bignerdranch.com.photogallery;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by zkhk on 2016/12/1.
 */

public abstract class SingleFragmentActivity extends AppCompatActivity {

    @LayoutRes
    protected int getLayoutResId(){
        return R.layout.activity_fragment;
    }

    protected abstract Fragment createFrame();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResId());
        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.fragment_content);
        if (fragment == null) {
            fragment = createFrame();
            fm.beginTransaction().add(R.id.fragment_content, fragment).commit();
        }
    }
}