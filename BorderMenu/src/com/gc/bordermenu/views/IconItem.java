package com.gc.bordermenu.views;

import android.graphics.Canvas;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.ImageView.ScaleType;

import com.gc.bordermenu.activities.BorderMenuActivity;
import com.gc.bordermenu.utils.Utils;

public class IconItem extends MenuItem {
	
	ImageView icon;

	public IconItem(BorderMenuActivity borderMenuActivity, int id, int idResource) {
		super(borderMenuActivity, id);
		icon = new ImageView(borderMenuActivity);
		setMinimumHeight(Utils.dpToPx(56, getResources()));
		setMinimumWidth(Utils.dpToPx(56, getResources()));
		icon.setAdjustViewBounds(true);
		icon.setScaleType(ScaleType.CENTER_CROP);
		icon.setImageResource(idResource);
		RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(Utils.dpToPx(24, getResources()),Utils.dpToPx(24, getResources()));
		params.addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE);
		icon.setLayoutParams(params);
		rippleSpeed = Utils.dpToPx(2, getResources());
		addView(icon);
	}
	
	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		if(radius >= getWidth() / 2 - rippleSpeed){
			x = -1;
			y = -1;
			radius = getWidth() / 4;
			borderMenuActivity.onItemClick(id);
		}
	}
	
	
	/**
	 * Change icon of iconItem
	 * @param idResource
	 */
	public void setIcon(int idResource){
		icon.setImageResource(idResource);
	}

}
