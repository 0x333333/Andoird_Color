package com.jesusjzp.color;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Display;
import android.view.Menu;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

public class MainActivity extends Activity {
	
	private static final int PHOTO_REQUEST_GALLERY = 1;
	private static final int PHOTO_REQUEST_TAKEPHOTO = 2;
	
	Button btn_1;
	Button btn_2;
	Button btn_3;
	Button btn_4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
		WindowManager manage=getWindowManager();
		Display display=manage.getDefaultDisplay();
        
        btn_1 = (Button)findViewById(R.id.btn_1);
        btn_2 = (Button)findViewById(R.id.btn_2);
        btn_3 = (Button)findViewById(R.id.btn_3);
        btn_4 = (Button)findViewById(R.id.btn_4);
        
        btn_1.setOnClickListener(new Button.OnClickListener() {
        	public void onClick(View v) {
        		Intent intent = new Intent();
        		Bundle bundle = new Bundle();
        		bundle.putString("flag", PHOTO_REQUEST_GALLERY+"");
        		intent.setClass(MainActivity.this, ImageActivity.class);
        		intent.putExtras(bundle);
        		startActivity(intent);
        	}
        });
        
        btn_2.setOnClickListener(new Button.OnClickListener() {
        	public void onClick(View v) {
        		Intent intent = new Intent();
        		Bundle bundle = new Bundle();
        		bundle.putString("flag", PHOTO_REQUEST_TAKEPHOTO+"");
        		intent.setClass(MainActivity.this, ImageActivity.class);
        		intent.putExtras(bundle);
        		startActivity(intent);
        	}
        });
    }
    
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
}
