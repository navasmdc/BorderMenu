package com.gc.bordermenu.activities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.gc.bordermenu.R;
import com.gc.bordermenu.utils.Utils;
import com.gc.bordermenu.views.IconItem;
import com.gc.bordermenu.views.MenuItem;
import com.nineoldandroids.animation.ObjectAnimator;
import com.nineoldandroids.view.ViewHelper;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Bitmap.Config;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.view.Window;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;

public abstract class BorderMenuActivity extends FragmentActivity {
	
	View contentView;
	ViewStub viewStub;
	LinearLayout containerTop;
	LinearLayout containerLeft;
	View viewFade;
	
	Drawable icon_menu_top;
	Drawable icon_menu_top_short;
	Drawable icon_menu_bottom;
	Drawable icon_menu_bottom_short;
	Drawable icon_menu_center;
	
	ImageView icn_menu_top;
	ImageView icn_menu_center;
	ImageView icn_menu_bottom;
	
	final static long ANIMATIONDURATION  = 300;
	
	private boolean menuShowed = false;
	
	private ArrayList<MenuItem> topItems = new ArrayList<MenuItem>();
	private ArrayList<IconItem> leftItems = new ArrayList<IconItem>();
	private HashMap<Integer, MenuItem> items = new HashMap<Integer, MenuItem>();
	
	
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		super.setContentView(R.layout.activity_border_menu);
		viewFade = findViewById(R.id.view_fade);
		ViewHelper.setAlpha(viewFade, 0);
		
		icon_menu_top = getResources().getDrawable(R.drawable.icn_menu_top);
		icon_menu_top_short = getResources().getDrawable(R.drawable.icn_menu_top_short);
		icon_menu_bottom = getResources().getDrawable(R.drawable.icn_menu_bottom);
		icon_menu_bottom_short = getResources().getDrawable(R.drawable.icn_menu_bottom_short);
		icon_menu_center = getResources().getDrawable(R.drawable.icn_menu_center);
		
		configureMenus();
		configureMenuButton();
	}
	
	/**
	 * Create and configure the border menus to original positions
	 */
	private void configureMenus() {
		containerTop = (LinearLayout) findViewById(R.id.ly_container_top);
		containerTop.post(new Runnable() {
			
			@Override
			public void run() {
				float origin = ViewHelper.getX(containerTop);
				ViewHelper.setX(containerTop, origin-containerTop.getWidth());
			}
		});
		containerLeft = (LinearLayout) findViewById(R.id.ly_container_left);
		containerLeft.post(new Runnable() {
			
			@Override
			public void run() {
				float origin = ViewHelper.getY(containerLeft);
				ViewHelper.setY(containerLeft, origin-containerLeft.getHeight());
			}
		});
	}
	
	/**
	 * Create and configure menu button animation and his actions
	 */
	private void configureMenuButton(){
		findViewById(R.id.btn_menu).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				if(menuShowed)
					hideMenu();
				else
					showMenu();
					
			}
		});
		icn_menu_top = (ImageView) findViewById(R.id.icn_menu_top);
		icn_menu_center = (ImageView) findViewById(R.id.icn_menu_center);
		icn_menu_bottom = (ImageView) findViewById(R.id.icn_menu_bottom);
	}
	
	public void showMenu(){
		
		// Animate icon button
		final float centerRotateAnimation = Utils.dpToPx(56, getResources()) / 2;
		RotateAnimation rotateAnimationCenter = new RotateAnimation(0,225,centerRotateAnimation,centerRotateAnimation);
		rotateAnimationCenter.setInterpolator(new AccelerateInterpolator());
		rotateAnimationCenter.setDuration(ANIMATIONDURATION);
		rotateAnimationCenter.setFillAfter(true);
		rotateAnimationCenter.setAnimationListener(new AnimationListener() {
			
			@Override
			public void onAnimationStart(Animation arg0) {}
			
			@Override
			public void onAnimationRepeat(Animation arg0) {}
			
			@Override
			public void onAnimationEnd(Animation arg0) {
				if(!topItems.isEmpty())
					topItems.get(0).show();
				if(!leftItems.isEmpty())
					leftItems.get(0).show();
			}
		});
		icn_menu_center.startAnimation(rotateAnimationCenter);
		
		RotateAnimation rotateAnimationTop = new RotateAnimation(0,120,centerRotateAnimation,centerRotateAnimation);
		rotateAnimationTop.setInterpolator(new AccelerateInterpolator());
		rotateAnimationTop.setDuration(ANIMATIONDURATION/2);
		rotateAnimationTop.setFillAfter(true);
		rotateAnimationTop.setAnimationListener(new AnimationListener() {
			
			@Override
			public void onAnimationStart(Animation arg0) {}
			
			@Override
			public void onAnimationRepeat(Animation arg0) {}
			
			@Override
			public void onAnimationEnd(Animation arg0) {
				RotateAnimation rotateAnimationTop = new RotateAnimation(120,245,centerRotateAnimation,centerRotateAnimation);
				rotateAnimationTop.setInterpolator(new AccelerateInterpolator());
				rotateAnimationTop.setDuration(ANIMATIONDURATION/2);
				rotateAnimationTop.setFillAfter(true);
				icn_menu_top.setImageDrawable(icon_menu_top_short);
				icn_menu_top.startAnimation(rotateAnimationTop);
			}
		});
		icn_menu_top.startAnimation(rotateAnimationTop);
		
		RotateAnimation rotateAnimationBottom = new RotateAnimation(0,100,centerRotateAnimation,centerRotateAnimation);
		rotateAnimationBottom.setInterpolator(new AccelerateInterpolator());
		rotateAnimationBottom.setDuration(ANIMATIONDURATION/2);
		rotateAnimationBottom.setFillAfter(true);
		rotateAnimationBottom.setAnimationListener(new AnimationListener() {
			
			@Override
			public void onAnimationStart(Animation arg0) {}
			
			@Override
			public void onAnimationRepeat(Animation arg0) {}
			
			@Override
			public void onAnimationEnd(Animation arg0) {
				RotateAnimation rotateAnimationBottom = new RotateAnimation(100,195,centerRotateAnimation,centerRotateAnimation);
				rotateAnimationBottom.setInterpolator(new AccelerateInterpolator());
				rotateAnimationBottom.setDuration(ANIMATIONDURATION/2);
				rotateAnimationBottom.setFillAfter(true);
				icn_menu_bottom.setImageDrawable(icon_menu_bottom_short);
				icn_menu_bottom.startAnimation(rotateAnimationBottom);
			}
		});
		icn_menu_bottom.startAnimation(rotateAnimationBottom);
		
		// show top menu
		ViewHelper.setX(containerTop, -containerTop.getWidth()+Utils.dpToPx(56, getResources()));
		float origin = ViewHelper.getX(containerTop);
		ObjectAnimator.ofFloat(containerTop, "x", 0)
		.setDuration(ANIMATIONDURATION).start();
		// show left menu
		ViewHelper.setY(containerLeft, -containerLeft.getHeight()+Utils.dpToPx(56, getResources()));
		origin = ViewHelper.getY(containerLeft);
		ObjectAnimator.ofFloat(containerLeft, "y", 0)
		.setDuration(ANIMATIONDURATION).start();
		//Show fade View
		viewFade.setClickable(true);
		ObjectAnimator.ofFloat(viewFade, "alpha", 0.6f)
		.setDuration(ANIMATIONDURATION).start();
		menuShowed = true;
		float originalWidth = contentView.getWidth();
		float width = contentView.getWidth() - Utils.dpToPx(56, getResources());
		float scaleX = width / originalWidth;
		float originalHeight = contentView.getHeight();
		float height = contentView.getHeight() - Utils.dpToPx(56, getResources());
		float scaleY = height / originalHeight;
		ScaleAnimation scaleAnimation = new ScaleAnimation(1, scaleX, 1, scaleY,contentView.getWidth(),contentView.getHeight());
		scaleAnimation.setInterpolator(new AccelerateInterpolator());
		scaleAnimation.setDuration(ANIMATIONDURATION/2);
		scaleAnimation.setFillAfter(true);
		contentView.startAnimation(scaleAnimation);
		
	}
	
	public void hideMenu(){
		// Animate icon button
		final float centerRotateAnimation = Utils.dpToPx(56, getResources()) / 2;
		RotateAnimation rotateAnimationCenter = new RotateAnimation(225,0,centerRotateAnimation,centerRotateAnimation);
		rotateAnimationCenter.setInterpolator(new AccelerateInterpolator());
		rotateAnimationCenter.setDuration(ANIMATIONDURATION);
		rotateAnimationCenter.setFillAfter(true);
		icn_menu_center.startAnimation(rotateAnimationCenter);
		
		RotateAnimation rotateAnimationTop = new RotateAnimation(245,120,centerRotateAnimation,centerRotateAnimation);
		rotateAnimationTop.setInterpolator(new AccelerateInterpolator());
		rotateAnimationTop.setDuration(ANIMATIONDURATION/2);
		rotateAnimationTop.setFillAfter(true);
		rotateAnimationTop.setAnimationListener(new AnimationListener() {
			
			@Override
			public void onAnimationStart(Animation arg0) {}
			
			@Override
			public void onAnimationRepeat(Animation arg0) {}
			
			@Override
			public void onAnimationEnd(Animation arg0) {
				RotateAnimation rotateAnimationTop = new RotateAnimation(120,0,centerRotateAnimation,centerRotateAnimation);
				rotateAnimationTop.setInterpolator(new AccelerateInterpolator());
				rotateAnimationTop.setDuration(ANIMATIONDURATION/2);
				rotateAnimationTop.setFillAfter(true);
				icn_menu_top.setImageDrawable(icon_menu_top);
				icn_menu_top.startAnimation(rotateAnimationTop);
			}
		});
		icn_menu_top.startAnimation(rotateAnimationTop);
		
		RotateAnimation rotateAnimationBottom = new RotateAnimation(195,100,centerRotateAnimation,centerRotateAnimation);
		rotateAnimationBottom.setInterpolator(new AccelerateInterpolator());
		rotateAnimationBottom.setDuration(ANIMATIONDURATION/2);
		rotateAnimationBottom.setFillAfter(true);
		rotateAnimationBottom.setAnimationListener(new AnimationListener() {
			
			@Override
			public void onAnimationStart(Animation arg0) {}
			
			@Override
			public void onAnimationRepeat(Animation arg0) {}
			
			@Override
			public void onAnimationEnd(Animation arg0) {
				RotateAnimation rotateAnimationBottom = new RotateAnimation(100,0,centerRotateAnimation,centerRotateAnimation);
				rotateAnimationBottom.setInterpolator(new AccelerateInterpolator());
				rotateAnimationBottom.setDuration(ANIMATIONDURATION/2);
				rotateAnimationBottom.setFillAfter(true);
				icn_menu_bottom.setImageDrawable(icon_menu_bottom);
				icn_menu_bottom.startAnimation(rotateAnimationBottom);
			}
		});
		icn_menu_bottom.startAnimation(rotateAnimationBottom);
		
		// hide top menu
		float origin = ViewHelper.getX(containerTop);
		ObjectAnimator.ofFloat(containerTop, "x", -containerTop.getWidth())
		.setDuration(ANIMATIONDURATION).start();
		// hide left menu
		origin = ViewHelper.getY(containerLeft);
		ObjectAnimator.ofFloat(containerLeft, "y", -containerLeft.getHeight())
		.setDuration(ANIMATIONDURATION).start();
		//hide fade View
		viewFade.setClickable(false);
		ObjectAnimator.ofFloat(viewFade, "alpha", 0f)
		.setDuration(ANIMATIONDURATION).start();
		
		float originalWidth = contentView.getWidth();
		float width = contentView.getWidth() - Utils.dpToPx(56, getResources());
		float scaleX = width / originalWidth;
		float originalHeight = contentView.getHeight();
		float height = contentView.getHeight() - Utils.dpToPx(56, getResources());
		float scaleY = height / originalHeight;
		ScaleAnimation scaleAnimation = new ScaleAnimation(scaleX,1,scaleY,1,contentView.getWidth(),contentView.getHeight());
		scaleAnimation.setInterpolator(new AccelerateInterpolator());
		scaleAnimation.setDuration(ANIMATIONDURATION/2);
		scaleAnimation.setFillAfter(true);
		contentView.startAnimation(scaleAnimation);
		
		for(MenuItem menuItem : topItems)
			menuItem.hide();
		for(MenuItem menuItem : leftItems)
			menuItem.hide();
		
		
		menuShowed = false;
		
		
		
	}
	
	@Override
	public void setContentView(int layoutResID) {
		contentView = findViewById(R.id.contentView); 
		viewStub = (ViewStub) findViewById(R.id.viewStub); 
		viewStub.setLayoutResource(layoutResID);
		viewStub.inflate();
	}
	
	public boolean isMenuShowed(){
		return this.menuShowed;
	}
	
	public abstract void onItemClick(int id);
	
	
	/**
	 * Search a MenuItem by id
	 * @param id
	 * @return
	 */
	public MenuItem getItemById(int id){
		return items.get(id);
	}
	
	/**
	 * Add a MenuItem to Top menu container
	 * @param item
	 */
	public void addTopItem(MenuItem item){
		topItems.add(item);
		items.put(item.id, item);
		containerTop.addView(item);
		configureTopAnimations();
		if(menuShowed)
			item.show();
	}
	
	/**
	 * Remove a top MenuItem by id
	 * @param id
	 */
	public void removeTopItem(int id){
		MenuItem item = getItemById(id);
		items.remove(id);
		topItems.remove(item);
		containerTop.removeAllViews();
		for(MenuItem menuItem : topItems)
			containerTop.addView(menuItem);
		configureTopAnimations();
		
	}

	/**
	 * Add a MenuItem to left menu container
	 * @param item
	 */
	public void addLeftItem(IconItem item){
		leftItems.add(item);
		items.put(item.id, item);
		containerLeft.addView(item);
		configureLeftAnimations();
		if(menuShowed)
			item.show();
	}
	
	/**
	 * Remove a top MenuItem by id
	 * @param id
	 */
	public void removeLeftItem(int id){
		MenuItem item = getItemById(id);
		items.remove(id);
		leftItems.remove(item);
		containerLeft.removeAllViews();
		for(MenuItem menuItem : leftItems)
			containerLeft.addView(menuItem);
		configureLeftAnimations();
	}
	
	
	/**
	 * Clear top menu
	 */
	public void removeAllTopItems(){
		for(MenuItem menuItem : topItems)
			items.remove(menuItem.id);
		topItems.clear();
		topItems = new ArrayList<MenuItem>();
		containerTop.removeAllViews();
	}
	
	/**
	 * Clear top menu
	 */
	public void removeAllLeftItems(){
		for(MenuItem menuItem : leftItems)
			items.remove(menuItem.id);
		leftItems.clear();
		leftItems = new ArrayList<IconItem>();
		containerLeft.removeAllViews();
	}
	
	/**
	 * Configure show animations of items in top container
	 */
	private void configureTopAnimations(){
		for(int i = 0; i < topItems.size(); i ++){
			MenuItem menuItem = topItems.get(i);
			if(i+1 != topItems.size())
				menuItem.setNextItem(topItems.get(i+1));
		}
	}
	
	/**
	 * Configure show animations of items in top container
	 */
	private void configureLeftAnimations(){
		for(int i = 0; i < leftItems.size(); i ++){
			MenuItem menuItem = leftItems.get(i);
			menuItem.setNextItem(null);
			if(i+1 != leftItems.size())
				menuItem.setNextItem(leftItems.get(i+1));
		}
	}
	
	/**
	 * Change color for the menu
	 * @param color
	 */
	public void setMenuColor(int color){
		containerLeft.setBackgroundColor(color);
		containerTop.setBackgroundColor(color);
		viewFade.setBackgroundColor(color);
		
	}
	
	/**
	 * If you set true, hide the menu when you touch outside of the menu
	 * @param bool
	 */
	public void setHidedOnTouchOutside(boolean bool){
		viewFade.setOnClickListener((bool)?onTouchOutside : null);
	}
	
	OnClickListener onTouchOutside = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			if(menuShowed)
				hideMenu();
		}
	};
	
	/**
	 * Change color of the menu icon 
	 * @param color
	 */
	public void setMenuIconColor(int color){
		Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.icn_menu_top);
    	Bitmap bitmapMutable = bitmap.copy(Config.ARGB_8888, true);
    	bitmapMutable = Utils.changeColorIcon(bitmapMutable, color);
    	icon_menu_top = new BitmapDrawable(getResources(),bitmapMutable);
    	bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.icn_menu_top_short);
    	bitmapMutable = bitmap.copy(Config.ARGB_8888, true);
    	bitmapMutable = Utils.changeColorIcon(bitmapMutable, color);
    	icon_menu_top_short = new BitmapDrawable(getResources(),bitmapMutable);
    	bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.icn_menu_center);
    	bitmapMutable = bitmap.copy(Config.ARGB_8888, true);
    	bitmapMutable = Utils.changeColorIcon(bitmapMutable, color);
    	icon_menu_center = new BitmapDrawable(getResources(),bitmapMutable);
    	bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.icn_menu_bottom);
    	bitmapMutable = bitmap.copy(Config.ARGB_8888, true);
    	bitmapMutable = Utils.changeColorIcon(bitmapMutable, color);
    	icon_menu_bottom = new BitmapDrawable(getResources(),bitmapMutable);
    	bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.icn_menu_bottom_short);
    	bitmapMutable = bitmap.copy(Config.ARGB_8888, true);
    	bitmapMutable = Utils.changeColorIcon(bitmapMutable, color);
    	icon_menu_bottom_short = new BitmapDrawable(getResources(),bitmapMutable);
    	
    	icn_menu_top.setImageDrawable((menuShowed)?icon_menu_top_short : icon_menu_top);
    	icn_menu_center.setImageDrawable(icon_menu_center);
    	icn_menu_bottom.setImageDrawable((menuShowed)?icon_menu_bottom_short : icon_menu_bottom);
	}
	
	// Show icon menu
	public void showIconMenu(){
		findViewById(R.id.btn_menu).setVisibility(View.VISIBLE);
	}
	
	// Hide icon menu
	public void hideIconMenu(){
		findViewById(R.id.btn_menu).setVisibility(View.INVISIBLE);
	}
}
