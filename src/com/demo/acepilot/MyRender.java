package com.demo.acepilot;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import android.opengl.GLSurfaceView.Renderer;
import android.opengl.GLU;
import android.util.Log;
import android.webkit.WebView.FindListener;

public class MyRender implements Renderer{
	
	public String motion="";
	
	private float x_screen=0,y_screen=0;	//�������e�P��
	private float x_move,y_move;	//x,y��V���������ʶq
	private float ratio_pixToDist;	//��ȡA�y�ФW�C���۷��h�ֹ���
	private boolean isStart=false;	//isStart��false�A�h�|�i�歺��ø�ϳ]�w
	private Square square;
//	private float angle = 0;

	 public MyRender() {
	  // ��l��
	  square = new Square();
	 }

	
	@Override
	public void onDrawFrame(GL10 gl) {
		// TODO Auto-generated method stub
		// �M���ù��M�`�׽w�İ�
		  gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT); 
		  
		  if(isStart==false){
			  x_move=0;
			  y_move=0;
			  gl.glLoadIdentity();	//�N���I���ܿù�����		
			  gl.glTranslatef(0, 0,-10);	//���驹�ù�������10���A�N�����P���I����
			  gl.glScalef(0.25f, 0.25f, 0.25f);	//�վ��Ϊ��j�p
			  isStart=true;	//isStart�]��true�A���U�Ӫ�ø�ϱN���L���B�J�A�קK�e�����m
		  }
		 
		  if(motion.equals("UP")){
			  if(y_move <= (y_screen/2)){
				  gl.glTranslatef(0, 1, 0);
				  y_move+=0.01*ratio_pixToDist*0.25;	//model��b�tZ�b10���B�A��near������b0.1���B�A�ѩ�model��scale�ܧ�L�A
			  }											//�]����model�B1��쪺���ʶq�A��񥭭������ʶq�n���W(0.1/10)��0.25�A�í��W����ন����
			  Log.d("Wang","y_move="+y_move);
		  }else if(motion.equals("DOWN")){
			  if(y_move >= (-y_screen/2)){
				  gl.glTranslatef(0, -1, 0);
				  y_move-=0.01*ratio_pixToDist*0.25;
			  }
			  Log.d("Wang","y_move="+y_move);
		  }else if(motion.equals("LEFT")){
			  if(x_move >= (-x_screen/2)){
				  gl.glTranslatef(-1, 0, 0);
				  x_move-=0.01*ratio_pixToDist*0.25;
			  }
			  Log.d("Wang","x_move="+x_move);
		  }else if(motion.equals("RIGHT")){
			  if(x_move <= (x_screen/2)){
				  gl.glTranslatef(1, 0, 0);
				  x_move+=0.01*ratio_pixToDist*0.25;
			  }
			  Log.d("Wang","x_move="+x_move);
		  }
		  
		  

		  // �Ĥ@�Ӥ��
		  // �s�x�ثe�}�C
		  gl.glPushMatrix();
		  // �Ϯ�������
//		  gl.glRotatef(angle, 0, 0, 1);
		  // �e�X�Ĥ@�Ӥ��
		  square.draw(gl);
		  // �_�즨�̫᪺�x�}
//		  gl.glPopMatrix();

//		  // �ĤG�Ӥ��
//		  // �s�x�ثe�}�C
//		  gl.glPushMatrix();
//		  // �b���ʫe������, ���ĤG�Ӥ�γ�¶�۲Ĥ@�Ӥ�α���
//		  gl.glRotatef(-angle, 0, 0, 1);
//		  // ���ʲĤG�Ӥ��
//		  gl.glTranslatef(2, 0, 0);
//		  // �վ��j�p���Ĥ@�Ӥ�Ϊ��@�b
//		  gl.glScalef(.5f, .5f, .5f);
//		  // �e�X�ĤG�Ӥ��
//		  square.draw(gl);
//
//		  // �ĤT�Ӥ��
//		  // �s�x�ثe�}�C
//		  gl.glPushMatrix();
//		  // ���ĤT�Ӥ�γ�¶�۲ĤG�Ӥ�α���
//		  gl.glRotatef(-angle, 0, 0, 1);
//		  // ���ʲĤT�Ӥ��
//		  gl.glTranslatef(2, 0, 0);
//		  // �վ��j�p���ĤG�Ӥ�Ϊ��@�b
//		  gl.glScalef(.5f, .5f, .5f);
//		  // �H�ۤv�����߱���
//		  gl.glRotatef(angle * 10, 0, 0, 1);
//		  // �e�X�ĤT�Ӥ��.
//		  square.draw(gl);
//
//		  // �_�즨�ĤT�Ӥ�Ϋe���x�}
//		  gl.glPopMatrix();
//		  // �_�즨�ĤG�Ӥ�Ϋe���x�}.
//		  gl.glPopMatrix();
//
//		  // �W�[����
//		  angle++;
		  
	}

	@Override
	public void onSurfaceChanged(GL10 gl, int width, int height) {
		// TODO Auto-generated method stub
		// �]�w�s����������j�p
		  gl.glViewport(0, 0, width, height);
		  // ��ܧ�g���}�C�Ҧ�
		  gl.glMatrixMode(GL10.GL_PROJECTION);
		  // ���]��g�}
		  gl.glLoadIdentity();
		  // �p��������e����v
		  GLU.gluPerspective(gl, 45.0f, (float) width / (float) height, 0.1f,
		    100.0f);
		  
		  x_screen=width;
		  y_screen=height;
		  ratio_pixToDist=(float)(height/(2*Math.tan(22.5*Math.PI/180)*0.1));	//�ù��W��1���۷��h�ֹ���
		  Log.d("Wang"," ratio_pixToDist="+ ratio_pixToDist);
		  Log.d("Wang","x_screen="+x_screen+" y_screen="+y_screen);
		  		  
		  // ���MODELVIEW�}�C
		  gl.glMatrixMode(GL10.GL_MODELVIEW);
		  // ���]MODELVIEW�}�C
		  gl.glLoadIdentity(); 
	}

	@Override
	public void onSurfaceCreated(GL10 gl, EGLConfig config) {
		// TODO Auto-generated method stub
		// �]�w�I���C�⬰�¦�, �榡�ORGBA
		  gl.glClearColor(0.0f, 0.0f, 0.0f, 0.5f);
		  // �]�w�y�Z�����v�Ҧ�
		  gl.glShadeModel(GL10.GL_SMOOTH);
		  // �`�׽w�Ϫ��]�w
		  gl.glClearDepthf(1.0f);
		  // �Ұʲ`�ת�����
		  gl.glEnable(GL10.GL_DEPTH_TEST);
		  // GL_LEQUAL�`�ר禡����
		  gl.glDepthFunc(GL10.GL_LEQUAL);
		  // �]�w�ܦn�����׭p��Ҧ�
		  gl.glHint(GL10.GL_PERSPECTIVE_CORRECTION_HINT, GL10.GL_NICEST); 
		  
	}

}
