package akira.com.savefile_bmp2png;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Switch;

import java.io.IOException;
import java.io.InputStream;

public class MainActivity extends Activity {

    private Button btnSave,btnString;
    private ImageView img1;
    private String Url = "http://www.unood.me/2014/04/android-sd.html";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initActivity();
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
                            InputStream in =  new okHttp().getInputStream(Url);
                            exeInputStream(in);
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
                           String myString = new okHttp().getString(Url);
                            exeInString(myString);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }.start();

                break;
        }

    }

    public void exeInputStream(InputStream in){

        String s = in.toString();
        Log.i("exeInputStream",s);
    }


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