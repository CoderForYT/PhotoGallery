package android.bignerdranch.com.photogallery;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.List;

/**
 * Created by zkhk on 2016/12/1.
 */

public class PhotoGalleryFragment extends Fragment {

    protected RecyclerView mRecyclerView;

    public static PhotoGalleryFragment newInstance() {
        return new PhotoGalleryFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_photo_gallery, container, false);
        mRecyclerView = (RecyclerView)view.findViewById(R.id.fragment_photo_gallery_recyclerView);
        mRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 3));

        return view;
    }

    private class PhotoGalleryAdapter extends RecyclerView.Adapter {

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new PhotoGalleryItem(new View(getActivity()));
        }

        @Override
        public int getItemCount() {
            return 10;
        }
    }

    private class PhotoGalleryItem extends RecyclerView.ViewHolder {

        private ImageView mImageView;

        public PhotoGalleryItem(View itemView) {
            super(itemView);
        }
    }
}
