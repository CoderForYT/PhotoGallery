package android.bignerdranch.com.photogallery;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by zkhk on 2016/12/1.
 */

public class PhotoGalleryFragment extends Fragment {

    protected RecyclerView mRecyclerView;
    protected List<GalleryItem> mItems;

    public static PhotoGalleryFragment newInstance() {
        return new PhotoGalleryFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        new FetchItemTask().execute();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_photo_gallery, container, false);
        mRecyclerView = (RecyclerView)view.findViewById(R.id.fragment_photo_gallery_recyclerView);
        mRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 3));
        return view;
    }

    private void setAdapter() {
        if (isAdded()) {
            mRecyclerView.setAdapter(new PhotoGalleryAdapter(mItems));
        }
    }

    private class PhotoGalleryAdapter extends RecyclerView.Adapter<GalleryHolder> {

        public List<GalleryItem> mItems;

        public PhotoGalleryAdapter(List<GalleryItem> items) {
            mItems = items;
        }

        @Override
        public GalleryHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new GalleryHolder(new TextView(getActivity()));
        }

        @Override
        public void onBindViewHolder(GalleryHolder holder, int position) {
            GalleryItem item = mItems.get(position);
            holder.onBindItem(item);
        }

        @Override
        public int getItemCount() {
            return mItems.size();
        }
    }

    private class GalleryHolder extends RecyclerView.ViewHolder {

        private GalleryItem mItem;
        private TextView mTextView;

        public GalleryHolder(View itemView) {
            super(itemView);
            mTextView = (TextView) itemView;
        }
        public void onBindItem(GalleryItem item){
            mItem = item;
            mTextView.setText(item.getTitle());
        }
    }

    private class FetchItemTask extends AsyncTask <Void, Void, List<GalleryItem>>{

        @Override
        protected List<GalleryItem> doInBackground(Void... voids) {

            return new FlickrFetchr().fetchItems();
        }

        @Override
        protected void onPostExecute(List<GalleryItem> galleryItems) {
            super.onPostExecute(galleryItems);
            mItems = galleryItems;
            setAdapter();
        }

    }

}
