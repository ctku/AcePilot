package com.demo.acepilot;

import java.util.ArrayList;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import android.opengl.GLSurfaceView.Renderer;
import android.opengl.GLU;
import android.util.Log;

public class MyRender implements Renderer{
	
	public String motion="";					//�Ψ��ѧO�ʧ@���r��
	
	private float x_screen=0,y_screen=0;		//�������e�P��
	private float x_movePix,y_movePix;			//����x,y��V�������첾
	private int x_move,y_move;					//����x,y��V���첾
	private float ratio_pixToDist;				//��ȡA�y�ФW�C���۷��h�ֹ���
	private boolean isStart=false;				//isStart��false�A�h�|�i�歺��ø�ϳ]�w
	private Square square;
	private ArrayList<Bullet> bulletList;		//�ŧi�w�Ƥl�u��list
	private final int num_bullet=50;			//�]�w�w�Ƥl�u�ƥ�
	private double radius; 						//�G�p�l�u���ꤧ�b�|(���:����)
	private double bullteAngle;					//�G�p�l�u����ߨ�
		
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
			  x_movePix=0;
			  y_movePix=0;
			  gl.glLoadIdentity();										//�N���I���ܿù�����		
			  gl.glTranslatef(0, 0,-10);								//���驹�ù�������10���A�N�����P���I����
			  gl.glScalef(0.25f, 0.25f, 0.25f);							//�վ��Ϊ��j�p
			  isStart=true;												//isStart�]��true�A���U�Ӫ�ø�ϱN���L���B�J�A�קK�e�����m
		  }
		 
		  gl.glPushMatrix();											//�x�s�ثegl���A		 
		  if(motion.equals("UP")){
			  if(y_movePix <= (y_screen/2)){
				  y_move++;												//y��V�첾�q+1
				  y_movePix=(float)(y_move*0.01*ratio_pixToDist*0.25);	//model��b�tZ�b10���B�A��near������b0.1���B�A�ѩ�model��scale�ܧ�L�A
			  }															//�]����model�B1��쪺���ʶq�A��񥭭������ʶq�n���W(0.1/10)��0.25�A�í��W����ন����
			  Log.d("Wang","y_move="+y_move);			 
		  }else if(motion.equals("DOWN")){
			  if(y_movePix >= (-y_screen/2)){
				  y_move--;
				  y_movePix=(float)(y_move*0.01*ratio_pixToDist*0.25);
			  }
			  Log.d("Wang","y_move="+y_move);
		  }else if(motion.equals("LEFT")){
			  if(x_movePix >= (-x_screen/2)){
				  --x_move;
				  x_movePix=(float)(x_move*0.01*ratio_pixToDist*0.25);
			  }
			  Log.d("Wang","x_move="+x_move);
		  }else if(motion.equals("RIGHT")){
			  if(x_movePix <= (x_screen/2)){
				  ++x_move;
				  x_movePix=(float)(x_move*0.01*ratio_pixToDist*0.25);
			  }
			  Log.d("Wang","x_move="+x_move);
		  }		  
		  gl.glTranslatef(x_move, y_move, 0);							//�M�wx_move,y_move��i��첾
		  square.draw(gl);		  										//�e�X���
		  gl.glPopMatrix();												//�^��W�@��gl�x�s�I�����A
		  		  		  		  
		  prepareBullet();		  
		  for(int i=0;i<bulletList.size();i++){														//�e�X�l�u�_�l��m���j��			  						  
			  Bullet b=bulletList.get(i);
			  gl.glPushMatrix();
			  gl.glTranslatef((float)(b.getBullet_moveX()), (float)(b.getBullet_moveY()), 0);		//����
			  gl.glScalef(0.25f, 0.25f, 0.25f);			  
			  b.draw(gl);			  
			  deleteBullet(b,i);			  			  											//�ˬd�O�_�ݭn�����l�u
			  gl.glPopMatrix();			  			  
			  
			  b.setBullet_moveX(b.getBullet_moveX()+b.getBullet_flyX());							//�]�w��e�l�u�U�@��frame��x��V���첾
			  b.setBullet_moveY(b.getBullet_moveY()+b.getBullet_flyY());							//�]�w��e�l�u�U�@��frame��y��V���첾
			  b.setFly_moveX(b.getFly_moveX()+b.getBullet_flyX());									//�]�w��e�l�ux��V�����`�첾
			  b.setFly_moveY(b.getFly_moveY()+b.getBullet_flyY());			  						//�]�w��e�l�uy��V�����`�첾
		  }
		  
//		  gl.glPushMatrix();	
//		  gl.glTranslatef(-4, 0, 0);
//		  gl.glScalef(0.25f, 0.25f, 0.25f);
//		  new Bullet().draw(gl);		  
//		  gl.glPopMatrix();

		  // �Ĥ@�Ӥ��
		  // �s�x�ثe�}�C
//		  gl.glPushMatrix();
		  // �Ϯ�������
//		  gl.glRotatef(angle, 0, 0, 1);
		  // �e�X�Ĥ@�Ӥ��
//		  square.draw(gl);
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
		  radius=0.5*Math.sqrt(width*width + height*height);					//�]�w�G�p�l�u���ꤧ�b�|=�e���������
		  Log.d("Wang","ratio_pixToDist="+ ratio_pixToDist);
		  Log.d("Wang","x_screen="+x_screen+" y_screen="+y_screen);
		  Log.d("Wang","radius="+ radius);		  
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
		  
		  bulletList=new ArrayList<Bullet>();
	}
	
	//�ǳƤl�u
	private void prepareBullet(){															
		bullteAngle=0;																		//�l�u��ߨ���l��0
		if(bulletList.size() == 0){															//��bulletList������=0��
			bulletList=new ArrayList<Bullet>();												//bulletList�N�A���V�@�ӷs��ArrayList
			while(bulletList.size() <= num_bullet){											//��bulletList������<=���w���l�u��خɡA�i�J�j��				
				  Bullet tmpBullet=new Bullet();			  
				  
				  double tmpRadius=(radius/ratio_pixToDist)/(0.25*0.01);					//�N�b�|�q�����ഫ���y�ФW�����
				  tmpBullet.setBullet_moveX(tmpRadius*Math.cos(bullteAngle*Math.PI/180));	//�]�w��e�l�u��lx��V���첾
				  tmpBullet.setBullet_moveY(tmpRadius*Math.sin(bullteAngle*Math.PI/180));	//�]�w��e�l�u��ly��V���첾
				  tmpBullet.setBullet_flyX(((2*Math.random()-1)));							//�]�w��e�l�ux��V������첾�A-1~1�A�i���W�Y�ƽվ㭸��t��
				  tmpBullet.setBullet_flyY(((2*Math.random()-1)));							//�]�w��e�l�uy��V������첾�A-1~1�A�i���W�Y�ƽվ㭸��t��
				  
				  bulletList.add(tmpBullet);												
				  bullteAngle=bullteAngle + 360/num_bullet;
			  }		  		  
		}		
	}
	
	//�������X��P���~���l�u�A�ǤJ���i��ܷ�ebulletList��index��
	private void deleteBullet(Bullet b,int i){
//		Log.d("Wang","tmpFly="+Math.sqrt(b.getFly_moveX()*b.getFly_moveX() + b.getFly_moveY()*b.getFly_moveY()));
		double tmpFly=Math.sqrt(b.getFly_moveX()*b.getFly_moveX() + b.getFly_moveY()*b.getFly_moveY());			//��e�l�u�������`�첾
		double tmpRadius=(radius/ratio_pixToDist)/(0.25*0.01);													//�N�b�|�q�����ഫ���y�ФW�����
		if(tmpFly > 2*tmpRadius)																				//�����`�첾>���|
			bulletList.remove(i);																				//�h�qbulletList�R����e�l�u
	}
	

	
}
