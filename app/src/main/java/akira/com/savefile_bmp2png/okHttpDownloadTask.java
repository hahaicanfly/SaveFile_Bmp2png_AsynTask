package akira.com.savefile_bmp2png;

import android.os.AsyncTask;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Akira on 2016/5/13.
 */
public class okHttpDownloadTask {

    OkHttpClient client = new OkHttpClient();



    /**Stream
     *
     * @param Url
     * @return InputStream
     *
     * **/
    public InputStream getInputStream(String Url)throws IOException{

        Request request = new Request.Builder().url(Url).build();
        InputStream in = client.newCall(request).execute().body().byteStream();
        Log.i("getInputStream","ok");
        return in;
    }


    /**String
     *
     * @param Url
     * @return String
     *
     * **/
    public String getString(String Url)throws IOException{

        Request request = new Request.Builder().url(Url).build();
        Response response = client.newCall(request).execute();
        String CONTENT = response.body().string();

        return CONTENT;
    }



}
