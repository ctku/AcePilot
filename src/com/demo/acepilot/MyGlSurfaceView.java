package com.demo.acepilot;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.util.Log;
import android.view.MotionEvent;

public class MyGlSurfaceView extends GLSurfaceView{
	 
	private float mPreviousX,mPreviousY;
	
	public MyGlSurfaceView(Context context) {
		super(context);		
		Log.d("Wang","glSurfaceView constructor...");
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
//		Log.d("Wang","TouchEvent x="+event.getX()+" y="+event.getY());
		
		float x = event.getX();
        float y = event.getY();
        switch (event.getAction()) {
        case MotionEvent.ACTION_MOVE:        	
        	float dx = x - mPreviousX;
            float dy = (-1)*(y - mPreviousY);
            
            //���if�P�_�ApositionX,positionY�[�Wdx,dy�᪺����ȭn���O�p��ù��e�B�����@�b�A�]�N�O�b�ù��d��position�~���W��
            if(Math.abs(MyRender.player_positionY+dy / (MyRender.ratio_pixToDist*0.01*0.25)) <
        			(0.5*MyRender.y_screen/MyRender.ratio_pixToDist)/(0.25*0.01)){
            	MyRender.player_positionY += dy / (MyRender.ratio_pixToDist*0.01*0.25);
            }	
            if(Math.abs(MyRender.player_positionX+dx / (MyRender.ratio_pixToDist*0.01*0.25)) <
            		(0.5*MyRender.x_screen/MyRender.ratio_pixToDist)/(0.25*0.01)){
            	MyRender.player_positionX += dx / (MyRender.ratio_pixToDist*0.01*0.25);
            }

            requestRender();
            Log.d("Wang","moveX="+MyRender.player_positionX+" moveY="+MyRender.player_positionY);
        }
        mPreviousX = x;
        mPreviousY = y;
//		return super.onTouchEvent(event);
        return true;
	}

	@Override
	public boolean onTrackballEvent(MotionEvent event) {
		// TODO Auto-generated method stub
		Log.d("Wang","TrackballEvent x="+event.getX()+" y="+event.getY());
		return super.onTrackballEvent(event);		
	}

	
}
