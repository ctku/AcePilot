package com.demo.acepilot;

import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;

public class MainActivity extends Activity {

	private Button btnUp,btnDown,btnLeft,btnRight;
	private MyGlSurfaceView myGlSurfaceView; 	 
	private FrameLayout gl_layout;
	private Handler thread_Handler;	//宣告管理執行緒的handler
	private HandlerThread ht1;		//宣告執行緒
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);	//設定全螢幕
		
		setContentView(R.layout.activity_main);
		findView();
		
		gl_layout=(FrameLayout)findViewById(R.id.gl_layout);	//find出frameLayout
		myGlSurfaceView=new MyGlSurfaceView(MainActivity.this);	//建立MyGlSurfaceView的物件
		myGlSurfaceView.setRenderer(new MyRender());	//設定render
		gl_layout.addView(myGlSurfaceView);		//將MyGlSurfaceView的物件加入gl_layout
		
		
	}
	
	//find出元件
	private void findView(){
//		btnUp=(Button)findViewById(R.id.button1);
//		btnDown=(Button)findViewById(R.id.button2);
//		btnLeft=(Button)findViewById(R.id.button3);
//		btnRight=(Button)findViewById(R.id.button4);
//		
//		
//		btnUp.setOnTouchListener(new View.OnTouchListener() {
//		    @Override
//		    public boolean onTouch(View v, MotionEvent event) {
//		        if(event.getAction() == MotionEvent.ACTION_DOWN) {
//		        	myRender.motion="UP";
//		        } else if (event.getAction() == MotionEvent.ACTION_UP) {
//		        	myRender.motion="";
//		        }
//		        return false;   
//		    }    
//		});
//		
//		btnDown.setOnTouchListener(new View.OnTouchListener() {
//		    @Override
//		    public boolean onTouch(View v, MotionEvent event) {
//		        if(event.getAction() == MotionEvent.ACTION_DOWN) {
//		        	myRender.motion="DOWN";
//		        } else if (event.getAction() == MotionEvent.ACTION_UP) {
//		        	myRender.motion="";
//		        }
//		        return false;   
//		    }    
//		});
//		
//		btnLeft.setOnTouchListener(new View.OnTouchListener() {
//		    @Override
//		    public boolean onTouch(View v, MotionEvent event) {
//		        if(event.getAction() == MotionEvent.ACTION_DOWN) {
//		        	myRender.motion="LEFT";
//		        } else if (event.getAction() == MotionEvent.ACTION_UP) {
//		        	myRender.motion="";
//		        }
//		        return false;   
//		    }    
//		});
//		
//		btnRight.setOnTouchListener(new View.OnTouchListener() {
//		    @Override
//		    public boolean onTouch(View v, MotionEvent event) {
//		        if(event.getAction() == MotionEvent.ACTION_DOWN) {
//		        	myRender.motion="RIGHT";
//		        } else if (event.getAction() == MotionEvent.ACTION_UP) {
//		        	myRender.motion="";
//		        }
//		        return false;   
//		    }    
//		});
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}
	
	private void openDialog(){
		new AlertDialog.Builder(MainActivity.this)
		.setTitle("哈哈哈")
		.setMessage("被擊中了")
		.setPositiveButton("OK", new DialogInterface.OnClickListener(){

			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				MainActivity.this.finish();
			}
			
		})
		.setNegativeButton("HomePage", new DialogInterface.OnClickListener(){

			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
//				Intent intent=new Intent(Intent.ACTION_VIEW,Uri.parse("http://android.gasolin.idv.tw/"));
//				startActivity(intent);							
			}
			
		})
		.show();
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		Log.d("ABC","onResume()...");
		super.onResume();
		ht1=new HandlerThread("ht1");					//實體化執行緒ht1
		ht1.start();									//啟動ht1
		thread_Handler=new Handler(ht1.getLooper());	//實體化handler，把ht1交給他管
		thread_Handler.post(new Runnable() {			//handler交代工作給ht1
			
			@Override
			public void run() {							
				// TODO Auto-generated method stub			
				while(true){							//直到isDie為true則開啟對話方塊並跳出迴圈
					if(MyRender.isDie == true){
						openDialog();
						break;
					}
				}	
			}
		});
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		Log.d("ABC","onDestroy()...");
		super.onDestroy();						
		if(thread_Handler != null)				//清空handler,thread物件
			thread_Handler=null;
		if(ht1 != null)
			ht1=null;
	}
	
	

}
