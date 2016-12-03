package android.bignerdranch.com.photogallery;

import android.net.Uri;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by zkhk on 2016/12/1.
 */

// 用于网络请求连接的Viwe
public class FlickrFetchr {
    
    static final String TAG = "FlickrFetchr";

    private List<GalleryItem> mItems = new ArrayList<>();

    public List<GalleryItem> fetchItems() {
        try {
            String url = Uri.parse("http://www.tngou.net/tnfs/api/list")
                    .buildUpon()
                    .appendQueryParameter("page", "1")
                    .appendQueryParameter("rows", "20")
                    .toString();
            String result = getUrlString(url);
            JSONObject resultJson = new JSONObject(result);
            parseItems(mItems, resultJson);

            Log.i(TAG, "Fetcher Items result:" + result);


        }catch (JSONException je) {
                Log.i(TAG, "Fetcher Items Error:" + je);
        }
       catch (IOException ioe) {
            Log.i(TAG, "Fetcher Items Error:" + ioe);
        }
        return mItems;
    }

    // 解析服务器返回的数据
    private void parseItems(List<GalleryItem> items, JSONObject jsonBody) throws JSONException{
        JSONArray dataArray = jsonBody.getJSONArray("tngou");
        for (int i = 0; i < dataArray.length(); i ++) {
            JSONObject itemJson = dataArray.getJSONObject(i);
            GalleryItem item = new GalleryItem();
            item.setTitle(itemJson.getString("title"));
            item.setImg(itemJson.getString("img"));
            items.add(item);
        }
    }


    public byte[] getUrlBytes(String urlSpec) throws IOException {
        URL url = new URL(urlSpec);
        HttpURLConnection connection = (HttpURLConnection)url.openConnection();
        try {
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            InputStream in = connection.getInputStream();

            if (connection.getResponseCode() != HttpURLConnection.HTTP_OK) {
                throw new IOException(connection.getResponseMessage() + ": with" + urlSpec);
            }
            int byteRead = 0;
            byte[] buffer = new byte[1024];
            while ((byteRead = in.read(buffer)) > 0) {
                out.write(buffer, 0, byteRead);
            }
            out.close();
            return out.toByteArray();

        } finally {
            connection.disconnect();
        }
    }

    public String getUrlString(String urlSpec) throws IOException {
        return new String(getUrlBytes(urlSpec));
    }

}
