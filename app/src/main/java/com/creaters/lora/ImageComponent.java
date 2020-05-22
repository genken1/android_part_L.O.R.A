package com.creaters.lora;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;

import static com.facebook.FacebookSdk.getApplicationContext;

public class ImageComponent {
    private Context context;
    private String path;
    private final String TAG = "ImageDownloader";

    public ImageComponent(Context context, String path) {
        this.context = context;
        this.path = path;
    }

    public void saveImage(String image_name, String url){
        new DownloadImage(context, image_name).execute(url);
    }


    private class DownloadImage extends AsyncTask<String, Void, Bitmap> {
        Context context;
        String image_name;
        private String TAG = "DownloadImage";

        public DownloadImage(Context context, String image_name){
            this.context = context;
            this.image_name = image_name;
        }

        @Override
        protected Bitmap doInBackground(String... params) {
            return downloadImageBitmap(params[0]);
        }

        protected void onPostExecute(Bitmap result) {
            String short_path = image_name+".png";
            saveImage(context.getApplicationContext(), result, short_path);
        }

        private Bitmap downloadImageBitmap(String sUrl) {
            Bitmap bitmap = null;
            try {
                InputStream inputStream = new URL(sUrl).openStream();   // Download Image from URL
                bitmap = BitmapFactory.decodeStream(inputStream);       // Decode Bitmap
                inputStream.close();
            } catch (Exception e) {
                Log.d(TAG, "Exception 1, Something went wrong!");
                e.printStackTrace();
            }
            return bitmap;
        }
    }

    private void saveImage(Context context, Bitmap b, String imageName) {
        FileOutputStream foStream;
        try {
            foStream = context.openFileOutput(imageName, Context.MODE_PRIVATE);
            b.compress(Bitmap.CompressFormat.PNG, 100, foStream);
            foStream.close();
        } catch (Exception e) {
            Log.d("saveImage", "Exception 2, Something went wrong!");
            e.printStackTrace();
        }
    }

    public Bitmap loadImageBitmap(Context context, String imageName) {
        Bitmap bitmap = null;
        FileInputStream fiStream;
        try {
            fiStream    = context.openFileInput(imageName);
            bitmap      = BitmapFactory.decodeStream(fiStream);
            fiStream.close();
        } catch (Exception e) {
            Log.d("saveImage", "Exception 3, Something went wrong!");
            e.printStackTrace();
        }
        return bitmap;
    }
}

/**
 * D/skia: --- Failed to create image decoder with message 'unimplemented'
 * E/ImageDownloader: bitmap failed: HTTP 504
 * */
/*public void saveImage(String image_name, String imageUrl){
        Picasso.get()
                .load(imageUrl)
                .into(new Target() {
                    @Override
                    public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                        try {
                            String root =  context.getExternalFilesDir(null).getAbsolutePath() + image_name;
                            File myDir = new File(root + path);

                            if (!myDir.exists()) {
                                myDir.mkdirs();
                            }

                            String name = new Date().toString() + ".jpg";
                            //String name = image_name + ".jpg";
                            myDir = new File(myDir, name);
                            FileOutputStream out = new FileOutputStream(myDir);
                            bitmap.compress(Bitmap.CompressFormat.JPEG, 90, out);
                            out.flush();
                            out.close();
                            Log.i(TAG, "image download");
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onBitmapFailed(Exception e, Drawable errorDrawable) {
                        Log.e(TAG, "bitmap failed: "+e.getMessage());
                    }

                    @Override
                    public void onPrepareLoad(Drawable placeHolderDrawable) {
                        //something
                    }
                });
    }*/