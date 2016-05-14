package akira.com.savefile_bmp2png;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class MainActivity extends Activity {

    private Button btnSave,btnString;
    private ImageView img1;
    private String Url = "http://goo.gl/z8oZEP";
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initActivity();
        context = this;
    }

    public void initActivity(){

        btnSave = (Button)findViewById(R.id.btnSave);
        btnString = (Button)findViewById(R.id.btnString);
        img1 = (ImageView)findViewById(R.id.imageView);

    }

    public void onClick(View view){
        int id = view.getId();

        switch (id){

            case R.id.btnSave:
                new Thread(){
                    @Override
                    public void run() {
                        try {
                            InputStream in =  new okHttpDownloadTask().getInputStream(Url);

                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }.start();
                break;

            case R.id.btnString:

                new Thread(){
                    @Override
                    public void run() {
                        try {
                           String myString = new okHttpDownloadTask().getString(Url);
                            exeInString(myString);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }.start();

                break;
        }

    }

    /** handle InputStream**/
    public void exeInputStream(InputStream in){

//        String s = in.toString();
//        Log.i("exeInputStream",s);
        Bitmap bmp = BitmapFactory.decodeStream(in);
//        SaveAsPNG(bmp);

    }

    public void SaveAsPNG(Bitmap bmp){

        try {
            String path = Environment.getExternalStorageDirectory().toString();
            File file = new File(path, "myPNGFile.png");

            FileOutputStream fout = new FileOutputStream(file);
            bmp.compress(Bitmap.CompressFormat.PNG,90,fout);

            fout.flush();
            fout.close();

        }catch (FileNotFoundException e){e.printStackTrace();

        }catch (IOException e){e.printStackTrace();

        }
    }


    /** handle String **/
    public void exeInString(String in){
        Log.i("exeInString",in);
    }



}


/**Bitmap bmp;

 // R.raw.gball is the resource id of the image in the res => raw(folder) => gball.png
 bmp = BitmapFactory.decodeResource ( getResources(), R.raw.gball);

 //If you want to set this bitmap in ImageView...
 mImageView.setImageBitmap ( bmp );

 **/