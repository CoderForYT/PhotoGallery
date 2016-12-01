package android.bignerdranch.com.photogallery;

import android.support.v4.app.Fragment;

public class PhotoGalleryActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFrame() {
         return PhotoGalleryFragment.newInstance();
    }
}
