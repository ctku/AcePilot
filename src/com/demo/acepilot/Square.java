package com.demo.acepilot;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;

import javax.microedition.khronos.opengles.GL10;

public class Square {

	 // �I���}�C
	 private float vertices[] = { -1.0f, 1.0f, 0.0f, // 0, ���W��
	   -1.0f, -1.0f, 0.0f, // 1, ���U��
	   1.0f, -1.0f, 0.0f, // 2, �k�U��
	   1.0f, 1.0f, 0.0f, // 3, �k�W��
	 };

	 // �s���I������
	 private short[] indices = { 0, 1, 2, 0, 2, 3 };

	 // �I���w�İ�
	 private FloatBuffer vertexBuffer;

	 // ���ޭȽw�İ�
	 private ShortBuffer indexBuffer;

	 public Square() {
	  // �B�I�ƬO4�줸�զ]���ݭn���I�}�C���׭��H4
	  ByteBuffer vbb = ByteBuffer.allocateDirect(vertices.length * 4);
	  vbb.order(ByteOrder.nativeOrder());
	  vertexBuffer = vbb.asFloatBuffer();
	  vertexBuffer.put(vertices);
	  vertexBuffer.position(0);

	  // �u��ƬO2�줸�զ]���ݭn���I�}�C���׭��H2
	  ByteBuffer ibb = ByteBuffer.allocateDirect(indices.length * 2);
	  ibb.order(ByteOrder.nativeOrder());
	  indexBuffer = ibb.asShortBuffer();
	  indexBuffer.put(indices);
	  indexBuffer.position(0);
	 }
	 
	 /**
	  * �e�Ϩ禡
	  * 
	  * @param gl
	  */
	 public void draw(GL10 gl) {
	  // �f����
	  gl.glFrontFace(GL10.GL_CCW);
	  // �Ұ�CULL_FACE
	  gl.glEnable(GL10.GL_CULL_FACE);
	  // �R���h�ܧΪ��I��
	  gl.glCullFace(GL10.GL_BACK);

	  // �Ұ��I���w�İ�
	  gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
	  // ���w��m�M��Ʈ榡
	  gl.glVertexPointer(3, GL10.GL_FLOAT, 0, vertexBuffer);

	  // �H�T�I���X�T����
	  gl.glDrawElements(GL10.GL_TRIANGLES, indices.length,
	    GL10.GL_UNSIGNED_SHORT, indexBuffer);

	  // �����I���w�İ�
	  gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
	  // ����CULL_FACE
	  gl.glDisable(GL10.GL_CULL_FACE);
	 }
}
