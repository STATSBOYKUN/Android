package com.umaru.asynctask;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class MainActivity extends AppCompatActivity {
    URL imageUrl = null;
    InputStream is = null;
    Bitmap bmImg = null;
    ImageView imageView = null;
    Button button = null;
    ProgressDialog p;

    ExecutorService executorService = Executors.newSingleThreadExecutor();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = findViewById(R.id.asyncTask);
        imageView = findViewById(R.id.image);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                downloadImage("https://stis.ac.id/media/source/up.png");
            }
        });
    }

    private void downloadImage(String imageUrl) {
        p = new ProgressDialog(MainActivity.this);
        p.setMessage("Downloading...");
        p.setIndeterminate(false);
        p.setCancelable(false);
        p.show();

        Future<Bitmap> future = executorService.submit(new DownloadImageTask(imageUrl));

        executorService.submit(() -> {
            try {
                Bitmap bitmap = future.get();
                runOnUiThread(() -> {
                    p.hide();
                    if (imageView != null) {
                        imageView.setImageBitmap(bitmap);
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    private class DownloadImageTask implements Callable<Bitmap> {
        private final String imageUrl;

        public DownloadImageTask(String imageUrl) {
            this.imageUrl = imageUrl;
        }

        @Override
        public Bitmap call() throws Exception {
            try {
                URL url = new URL(imageUrl);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setDoInput(true);
                conn.connect();
                is = conn.getInputStream();
                BitmapFactory.Options options = new BitmapFactory.Options();
                options.inPreferredConfig = Bitmap.Config.RGB_565;
                return BitmapFactory.decodeStream(is, null, options);
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }
    }
}
