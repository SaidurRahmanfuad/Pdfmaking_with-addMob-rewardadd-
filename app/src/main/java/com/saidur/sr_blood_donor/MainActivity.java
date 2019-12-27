package com.saidur.sr_blood_donor;

import androidx.annotation.ColorRes;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.pdf.PdfDocument;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.reward.RewardItem;
import com.google.android.gms.ads.reward.RewardedVideoAd;
import com.google.android.gms.ads.reward.RewardedVideoAdListener;
import com.google.android.gms.ads.rewarded.RewardedAd;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity implements RewardedVideoAdListener {
    Button btnCreate,video_button;
    EditText editText;
    TextView showpoints;
    int points=0;
    private RewardedVideoAd mRewardedVideoAd;
    //private RewardedAd rewardedAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnCreate = findViewById(R.id.create);
       video_button = findViewById(R.id.point);
        showpoints = findViewById(R.id.show_points);
        editText = findViewById(R.id.edittext);
        showpoints.setText("Total Points :"+points);
        MobileAds.initialize(this, "ca-app-pub-3940256099942544~3347511713");

        /*rewardedAd = new RewardedAd(this,
                "ca-app-pub-3940256099942544/5224354917");*/
        // Use an activity context to get the rewarded video instance.
        mRewardedVideoAd = MobileAds.getRewardedVideoAdInstance(this);
        mRewardedVideoAd.setRewardedVideoAdListener(this);
        loadRewardedVideoAd();

        btnCreate.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View view) {
                createPdf(editText.getText().toString());
            }
        });

video_button.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        video_button.setVisibility(View.INVISIBLE);
        if (mRewardedVideoAd.isLoaded()) {
            mRewardedVideoAd.show();
            Toast.makeText(MainActivity.this, "Video load", Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(MainActivity.this, "Video not load", Toast.LENGTH_SHORT).show();
        }
    }
});




    }
    private void loadRewardedVideoAd() {
        mRewardedVideoAd.loadAd("ca-app-pub-3940256099942544/5224354917",
                new AdRequest.Builder().build());
      /*  mRewardedVideoAd.loadAd("ca-app-pub-3940256099942544~3347511713",
                new AdRequest.Builder().build());*/
    }


    @RequiresApi(api = Build.VERSION_CODES.M)
    private void createPdf(String sometext) {

        // create a new document
        PdfDocument document = null;

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
            // Do the file write
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                document = new PdfDocument();
            }
        } else {
            // Request permission from the user
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 0);

        }

        // crate a page description
        PdfDocument.PageInfo pageInfo = null;


        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
            // Do the file write
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                pageInfo = new PdfDocument.PageInfo.Builder(595, 842, 1).create();
            }
        } else {
            // Request permission from the user
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 0);

        }


        // start a page
        PdfDocument.Page page = null;

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
            // Do the file write
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                page = document.startPage(pageInfo);
            }
        } else {
            // Request permission from the user
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 0);

        }


        Canvas canvas = null;

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
            // Do the file write
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                canvas = page.getCanvas();
            }
        } else {
            // Request permission from the user
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 0);

        }


        Paint paint = new Paint();

        paint.setColor(Color.RED);
        canvas.drawCircle(50, 50, 30, paint);


        paint.setColor(Color.BLACK);
        canvas.drawText(sometext, 280, 50, paint);



        //canvas.drawRGB(255, 255, 255);
        //border's properties
        //canvas.d
      //  paint.setColor(Color.BLACK);
        /*paint.setStrokeWidth(1);
        paint.setStyle(Paint.Style.STROKE);*/
       // canvas.drawRect(100, 100, 200, 200, paint);


        //canvas.drawPicture();
        //paint.setStrokeWidth(1);

        //Breeder info
        paint.setColor(getColor(R.color.company));
        canvas.drawText("Saidur Rahman", 440, 20, paint);
        canvas.drawText("01793208341", 440, 30, paint);
        canvas.drawText("Madaripur,Dhaka", 440, 40, paint);
//step1
        paint.setColor(Color.BLUE);
        canvas.drawText("khair-123403-s9e", 40, 421, paint);
        //for line
        paint.setColor(Color.BLUE);
                   //suru pashe    lomba     sesh        sesh lomba
        canvas.drawLine(150,300,150,420,paint);
        paint.setColor(Color.MAGENTA);
        canvas.drawLine(150,420,150,540,paint);
        //bam pashe  majhe line
       canvas.drawLine(140,421,150,421,paint);

        paint.setColor(Color.BLUE);
        //1.1
        //pashe  dane line upore
        canvas.drawLine(150,300,160,300,paint);
        paint.setColor(Color.BLUE);
        canvas.drawText("sayed-123403-s9e", 165, 300, paint);
        canvas.drawText("sayedur Rahman", 165, 310, paint);
        paint.setColor(Color.BLUE);
        canvas.drawText("1,1", 165, 320, paint);



        //1.2
        //pashe dane line niche
        paint.setColor(Color.MAGENTA);
        canvas.drawLine(150,540,160,540,paint);
        paint.setColor(Color.MAGENTA);
        canvas.drawText("khair-123403-s9e", 165, 540, paint);
        canvas.drawText("khair rahman", 165, 550, paint);
        paint.setColor(Color.MAGENTA);
        canvas.drawText("1,2", 165, 560, paint);

        //1.1.1
        paint.setColor(Color.BLUE);
       //line
        canvas.drawLine(280,250,280,300,paint);
        paint.setColor(Color.MAGENTA);
        canvas.drawLine(280,300,280,350,paint);
        //pashe  dane line upore
        paint.setColor(Color.BLUE);
        canvas.drawLine(280,250,290,250,paint);
        paint.setColor(Color.BLUE);
        canvas.drawText("khair-123403-s9e", 290, 250, paint);
        canvas.drawText("khair rahman", 290, 260, paint);
        paint.setColor(Color.BLUE);
        canvas.drawText("1,1,1", 290, 270, paint);

        //1.1.2
        //pashe dane niche
        paint.setColor(Color.MAGENTA);
        canvas.drawLine(280,350,290,350,paint);
        canvas.drawText("khair-123403-s9e", 290, 355, paint);
        canvas.drawText("khair rahman", 290, 365, paint);
        paint.setColor(Color.MAGENTA);
        canvas.drawText("1,1,2", 290, 375, paint);

        //1.2
        //1.2.1
        //line
        paint.setColor(Color.BLUE);
        canvas.drawLine(280,500,280,550,paint);
        paint.setColor(Color.MAGENTA);
        canvas.drawLine(280,550,280,600,paint);
        //upore
        paint.setColor(Color.BLUE);
        canvas.drawLine(280,500,290,500,paint);
        paint.setColor(Color.BLUE);
        canvas.drawText("khair-123403-s9e", 290, 500, paint);
        canvas.drawText("khair rahman", 290, 510, paint);
        canvas.drawText("1,2,1", 290, 520, paint);
      //1.2.2
        //niche
        paint.setColor(Color.MAGENTA);
        canvas.drawLine(280,600,290,600,paint);
        paint.setColor(Color.MAGENTA);
        canvas.drawText("khair-123403-s9e", 290, 600, paint);
        canvas.drawText("khair rahman", 290, 610, paint);
        canvas.drawText("1,2,2", 290, 620, paint);


        //1.1.1.1
        //line
        paint.setColor(Color.BLUE);
        canvas.drawLine(410,190,410,240,paint);
        paint.setColor(Color.MAGENTA);
        canvas.drawLine(410,240,410,290,paint);
        //pashe  dane line upore
        paint.setColor(Color.BLUE);
        canvas.drawLine(410,190,420,190,paint);
        paint.setColor(Color.BLUE);
        canvas.drawText("sayed-123403-s9e", 425, 190, paint);
        canvas.drawText("khair rahman", 425, 200, paint);
        paint.setColor(Color.BLUE);
        canvas.drawText("1,1,1,1", 425, 210, paint);

        //1.1.1.2
        //pashe  dane line niche
        paint.setColor(Color.MAGENTA);
        canvas.drawLine(410,290,420,290,paint);
        paint.setColor(Color.MAGENTA);
        canvas.drawText("sayed-123403-s9e", 425, 280, paint);
        canvas.drawText("khair rahman", 425, 290, paint);
        paint.setColor(Color.MAGENTA);
        canvas.drawText("1,1,1,2", 425, 300, paint);

        //1.1.2.1
        //line
        paint.setColor(Color.BLUE);
        canvas.drawLine(410,315,410,365,paint);
        paint.setColor(Color.MAGENTA);
        canvas.drawLine(410,365,410,415,paint);
        //pashe  dane line upore
        paint.setColor(Color.BLUE);
        canvas.drawLine(410,315,420,315,paint);
        paint.setColor(Color.BLUE);
        canvas.drawText("sayed-123403-s9e", 425, 315, paint);
        canvas.drawText("asghgd rahman", 425, 325, paint);
        paint.setColor(Color.BLUE);
        canvas.drawText("1,1,2,1", 425, 335, paint);
        //1.1.2.2
        //niche
        paint.setColor(Color.MAGENTA);
        canvas.drawLine(410,415,420,415,paint);
        paint.setColor(Color.MAGENTA);
        canvas.drawText("sayed-123403-s9e", 425, 410, paint);
        canvas.drawText("asghgd rahman", 425, 420, paint);
        paint.setColor(Color.MAGENTA);
        canvas.drawText("1,1,2,2", 425, 430, paint);
//1.2.1.1
        //line
        paint.setColor(Color.BLUE);
        canvas.drawLine(410,440,410,490,paint);
        paint.setColor(Color.MAGENTA);
        canvas.drawLine(410,490,410,540,paint);
        //pashe  dane line upore
        paint.setColor(Color.BLUE);
        canvas.drawLine(410,440,420,440,paint);
        canvas.drawText("sayed-123403-s9e", 425, 450, paint);
        canvas.drawText("asghgd rahman", 425, 460, paint);
        paint.setColor(Color.BLUE);
        canvas.drawText("1,2,1,1", 425, 470, paint);

        //1.2.1.2
        //pashe  dane line niche
        paint.setColor(Color.MAGENTA);
        canvas.drawLine(410,540,420,540,paint);
        canvas.drawText("sayed-123403-s9e", 425, 520, paint);
        canvas.drawText("asgh rahman", 425, 530, paint);
        canvas.drawText("1,2,1,1", 425, 540, paint);


        //1.2.2.1
        //line
        paint.setColor(Color.BLUE);
        canvas.drawLine(410,570,410,620,paint);
        paint.setColor(Color.MAGENTA);
        canvas.drawLine(410,620,410,670,paint);
        //upore
        paint.setColor(Color.BLUE);
        canvas.drawLine(410,570,420,570,paint);
        canvas.drawText("sayed-123403-s9e", 425, 570, paint);
        canvas.drawText("asgh rahman", 425, 580, paint);
        canvas.drawText("1,2,2,1", 425, 590, paint);
        //niche
        paint.setColor(Color.MAGENTA);
        canvas.drawLine(410,670,420,670,paint);
        canvas.drawText("sayed-123403-s9e", 425, 670, paint);
        canvas.drawText("asgh rahman", 425, 680, paint);
        canvas.drawText("1,2,2,2", 425, 690, paint);

        /*   paint.setColor(Color.YELLOW);
        canvas.drawRect(33, 33, 77, 60, paint );*/


        // finish the page

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
            // Do the file write
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                document.finishPage(page);
            }
        } else {
            // Request permission from the user
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 0);

        }


        // draw text on the graphics object of the page

        // Create Page 2
    /*    pageInfo = new PdfDocument.PageInfo.Builder(300, 600, 2).create();
        page = document.startPage(pageInfo);
        canvas = page.getCanvas();

        paint = new Paint();
        paint.setColor(Color.BLUE);

        canvas.drawCircle(100, 100, 100, paint);
        document.finishPage(page);
*/
        // write the document content
        String directory_path = Environment.getExternalStorageDirectory().getPath() + "/mypdf/";
        File file = new File(directory_path);
        if (!file.exists()) {
            file.mkdirs();
        }
        String targetPdf = directory_path + "test-2.pdf";
        File filePath = new File(targetPdf);
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                document.writeTo(new FileOutputStream(filePath));
            }
            Toast.makeText(this, "Done", Toast.LENGTH_LONG).show();
        } catch (IOException e) {
            Log.e("main", "error " + e.toString());
            Toast.makeText(this, "Something wrong: " + e.toString(), Toast.LENGTH_LONG).show();
        }
        // close the document
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            document.close();
        }
    }

    @Override
    public void onRewardedVideoAdLoaded() {
        Toast.makeText(this, "onRewardedVideoAdLoaded", Toast.LENGTH_SHORT).show();
        video_button.setVisibility(View.VISIBLE);
    }

    @Override
    public void onRewardedVideoAdOpened() {
        Toast.makeText(this, "onRewardedVideoAdOpened", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRewardedVideoStarted() {
        Toast.makeText(this, "onRewardedVideoStarted", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRewardedVideoAdClosed() {
        loadRewardedVideoAd();
       // Toast.makeText(this, "onRewardedVideoAdClosed", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRewarded(RewardItem rewardItem) {
      points = points + 10;
      showpoints.setText("Total points :"+points);


    }

    @Override
    public void onRewardedVideoAdLeftApplication() {
        Toast.makeText(this, "onRewardedVideoAdLeftApplication",
                Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRewardedVideoAdFailedToLoad(int i) {
        Toast.makeText(this, "onRewardedVideoAdFailedToLoad", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRewardedVideoCompleted() {
        Toast.makeText(this, "onRewardedVideoCompleted", Toast.LENGTH_SHORT).show();
    }
}