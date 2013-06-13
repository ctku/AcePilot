package com.demo.acepilot;

public class Bullet extends Circle{
	private double bullet_moveX;	//�l�ux��V�첾
	private double bullet_moveY;	//�l�uy��V�첾
	private double bullet_flyX;		//�l�ux��V������첾
	private double bullet_flyY;		//�l�uy��V������첾
	private double fly_moveX;		//�l�ux��V�����`�첾
	private double fly_moveY;		//�l�uy��V�����`�첾
	
	public  Bullet(){
		super();
		setBullet_moveX(0);
		setBullet_moveY(0);
		setBullet_flyX(0);
		setBullet_flyY(0);
		setFly_moveX(0);
		setFly_moveY(0);
	}

	public double getBullet_moveX() {
		return bullet_moveX;
	}

	public void setBullet_moveX(double bullet_moveX) {
		this.bullet_moveX = bullet_moveX;
	}

	public double getBullet_moveY() {
		return bullet_moveY;
	}

	public void setBullet_moveY(double bullet_moveY) {
		this.bullet_moveY = bullet_moveY;
	}

	public double getBullet_flyX() {
		return bullet_flyX;
	}

	public void setBullet_flyX(double bullet_flyX) {
		this.bullet_flyX = bullet_flyX;
	}

	public double getBullet_flyY() {
		return bullet_flyY;
	}

	public void setBullet_flyY(double bullet_flyY) {
		this.bullet_flyY = bullet_flyY;
	}

	public double getFly_moveX() {
		return fly_moveX;
	}

	public void setFly_moveX(double fly_moveX) {
		this.fly_moveX = fly_moveX;
	}

	public double getFly_moveY() {
		return fly_moveY;
	}

	public void setFly_moveY(double fly_moveY) {
		this.fly_moveY = fly_moveY;
	}
	
	
}
