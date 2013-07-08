package com.jesusjzp.color;

import java.io.File;
import java.sql.Date;
import java.text.SimpleDateFormat;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.Display;
import android.view.WindowManager;
import android.widget.ImageButton;

public class ImageActivity extends Activity {
	
	private static int flag;
	ImageButton img_btn;
	private static final int PHOTO_REQUEST_GALLERY = 1;
	private static final int PHOTO_REQUEST_TAKEPHOTO = 2;
	private static final int PHOTO_REQUEST_CUT = 3;
	File tempFile = new File(Environment.getExternalStorageDirectory(),getPhotoFileName());
		
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);
        
        // disable windows title
        WindowManager manage=getWindowManager();
		Display display=manage.getDefaultDisplay();
		
		Bundle bundle = new Bundle();
		bundle = this.getIntent().getExtras();
		flag = Integer.parseInt(bundle.getString("flag"));
		
		img_btn = (ImageButton) findViewById(R.id.img_btn);
		
		switch(flag) {
		case PHOTO_REQUEST_GALLERY:
    		Intent localIntent = new Intent();  
    		localIntent.setType("image/*");  
    		localIntent.setAction("android.intent.action.GET_CONTENT");  
    		startActivityForResult(localIntent, PHOTO_REQUEST_GALLERY); 
			break;
		case PHOTO_REQUEST_TAKEPHOTO:
			Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
			intent.putExtra(MediaStore.EXTRA_OUTPUT,Uri.fromFile(tempFile));
			startActivityForResult(intent, PHOTO_REQUEST_TAKEPHOTO);
			break;
		}
	}
	
	@Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

		switch (requestCode) {
		case PHOTO_REQUEST_GALLERY:
			if (data != null)
				startPhotoZoom(data.getData(), 150);
			break;
		
    	case PHOTO_REQUEST_TAKEPHOTO:
    		startPhotoZoom(Uri.fromFile(tempFile), 150);
    		break;
		
	    case PHOTO_REQUEST_CUT:
			if (data != null) 
				setPicToView(data);
			break;
		}
		super.onActivityResult(requestCode, resultCode, data);
	}
	
	private void startPhotoZoom(Uri uri, int size) {
		Intent intent = new Intent("com.android.camera.action.CROP");
		intent.setDataAndType(uri, "image/*");
		intent.putExtra("crop", "true");

		intent.putExtra("aspectX", 1);
		intent.putExtra("aspectY", 1);

		intent.putExtra("outputX", size);
		intent.putExtra("outputY", size);
		intent.putExtra("return-data", true);

		startActivityForResult(intent, PHOTO_REQUEST_CUT);
	}
	
	private void setPicToView(Intent picdata) {
		Bundle bundle = picdata.getExtras();
		if (bundle != null) {
			Bitmap photo = bundle.getParcelable("data");
			Drawable drawable = new BitmapDrawable(photo);
			img_btn.setBackgroundDrawable(drawable);
		}
	}

 	private String getPhotoFileName() {
 		Date date = new Date(System.currentTimeMillis());
 		SimpleDateFormat dateFormat = new SimpleDateFormat("'IMG'_yyyyMMdd_HHmmss");
 		return dateFormat.format(date) + ".jpg";
 	}
    
}
