package com.gc.bordermenu.views;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.BounceInterpolator;
import android.view.animation.ScaleAnimation;
import android.widget.RelativeLayout;

import com.gc.bordermenu.activities.BorderMenuActivity;
import com.gc.bordermenu.utils.Utils;

public abstract class MenuItem extends RelativeLayout{
	
	public int id;
	BorderMenuActivity borderMenuActivity;
	int rippleColor = Color.parseColor("#88FFFFFF");
	int radius = 0;
	int x = -1,y = -1;
	int rippleSpeed;
	
	MenuItem nexItem;

	public MenuItem(BorderMenuActivity borderMenuActivity,int id) {
		super(borderMenuActivity);
		this.borderMenuActivity = borderMenuActivity;
		this.id = id;
		rippleSpeed = Utils.dpToPx(5, getResources());
		setVisibility(View.INVISIBLE);
		setWillNotDraw(false);
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		if (event.getAction() == MotionEvent.ACTION_UP) {
			if ((event.getX() <= getWidth() && event.getX() >= 0)
					&& (event.getY() <= getHeight() && event.getY() >= 0)) {
				x = getWidth() / 2;
				y = getHeight() / 2;
				radius = getWidth() / 4;
			}else{
				x = y = -1;
			}
		}
			return true;
	}
	
	
	@Override
	protected void onDraw(Canvas canvas) {
		if(x != -1){
			radius += rippleSpeed;
			Paint paint = new Paint();
			paint.setAntiAlias(true);
			paint.setColor(rippleColor);
			canvas.drawCircle(x, y, radius, paint);
		}
		invalidate();
	}
	
	/**
	 * Change color of push effect
	 * @param rippleColor
	 */
	public void setRippleColor(int rippleColor){
		this.rippleColor = rippleColor;
	}
	
	public void setNextItem(MenuItem nexItem){
		this.nexItem = nexItem;
	}
	
	public void show(){
		setVisibility(View.VISIBLE);
		ScaleAnimation scaleAnimation = new ScaleAnimation(0, 1, 0, 1,getWidth() / 2, getHeight() / 2);
		scaleAnimation.setDuration(300);
		scaleAnimation.setInterpolator(new BounceInterpolator());
		scaleAnimation.setAnimationListener(new AnimationListener() {
			
			@Override
			public void onAnimationStart(Animation arg0) {
			}
			
			@Override
			public void onAnimationRepeat(Animation arg0) {
			}
			
			@Override
			public void onAnimationEnd(Animation arg0) {
				if(nexItem != null)
					nexItem.show();
			}
		});
		startAnimation(scaleAnimation);
	}
	
	public void hide(){
		ScaleAnimation scaleAnimation = new ScaleAnimation(1, 0, 1, 0,getWidth() / 2, getHeight() / 2);
		scaleAnimation.setDuration(300);
		scaleAnimation.setInterpolator(new BounceInterpolator());
		scaleAnimation.setAnimationListener(new AnimationListener() {
			
			@Override
			public void onAnimationStart(Animation arg0) {
			}
			
			@Override
			public void onAnimationRepeat(Animation arg0) {
			}
			
			@Override
			public void onAnimationEnd(Animation arg0) {
				setVisibility(View.INVISIBLE);
			}
		});
		startAnimation(scaleAnimation);
	}
	
	

}
