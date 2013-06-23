package com.demo.acepilot;

public class Bullet extends Circle{
	private double bullet_positionX;	//�l�uX��m
	private double bullet_positionY;	//�l�uY��m
	private double bullet_fly;			//�l�u�ª��a���檺�t��(�������/��)	
	private double bullet_totalFly;		//�l�u�����`�첾	
	private double bulletAngle;			//�l�u����ߨ�
	private double bulletFlyAngle;		//�l�u��m�缾�a���V�q�P(1,0)�V�q������(0~360)
	
	public  Bullet(){
		super();
		setBullet_positionX(0);
		setBullet_positionY(0);
		setBullet_fly(0);
		setBullet_totalFly(0);		
	}

	

	public double getBullet_positionX() {
		return bullet_positionX;
	}



	public void setBullet_positionX(double bullet_positionX) {
		this.bullet_positionX = bullet_positionX;
	}



	public double getBullet_positionY() {
		return bullet_positionY;
	}



	public void setBullet_positionY(double bullet_positionY) {
		this.bullet_positionY = bullet_positionY;
	}

			

	public double getBullet_fly() {
		return bullet_fly;
	}



	public void setBullet_fly(double bullet_fly) {
		this.bullet_fly = bullet_fly;
	}



	public double getBullet_totalFly() {
		return bullet_totalFly;
	}



	public void setBullet_totalFly(double bullet_totalFly) {
		this.bullet_totalFly = bullet_totalFly;
	}



	public double getBulletAngle() {
		return bulletAngle;
	}

	public void setBulletAngle(double bulletAngle) {
		this.bulletAngle = bulletAngle;
	}



	public double getBulletFlyAngle() {
		return bulletFlyAngle;
	}



	public void setBulletFlyAngle(double bulletFlyAngle) {
		this.bulletFlyAngle = bulletFlyAngle;
	}
	
	
}
