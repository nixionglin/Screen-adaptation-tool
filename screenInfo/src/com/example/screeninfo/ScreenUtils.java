package com.example.screeninfo;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.WindowManager;

public class ScreenUtils {
	public static DisplayMetrics getDisplayMetrics(Context ctx) {
		DisplayMetrics outMetrics = new DisplayMetrics();
		WindowManager wm = (WindowManager) ctx.getSystemService(Context.WINDOW_SERVICE);
		wm.getDefaultDisplay().getMetrics(outMetrics);
		return outMetrics;
	}
	
	/**
	 * 获得设备的dpi
	 */
	public static float getScreenDpi(Context context) {
		return getDisplayMetrics(context).densityDpi;
	}
	
	/**
	 * 获得设备屏幕密度
	 */
	public static float getScreenDensity(Context context) {
		DisplayMetrics dm = getDisplayMetrics(context);
		return dm.density;
	}
	
	/**
	 * 获得设备屏幕宽度
	 */
	public static int getScreenWidth(Context context) {
		DisplayMetrics dm = getDisplayMetrics(context);
		return dm.widthPixels;
	}

	/**
	 * 获得设备屏幕高度
	 * According to phone resolution height
	 */
	public static int getScreenHeight(Context context) {
		DisplayMetrics dm = getDisplayMetrics(context);
		return dm.heightPixels;
	}
	
	public static int px2dip(Context context, float px) {
		float density = getScreenDensity(context);
		return (int) (px / density + 0.5f);
	}
	
	public static int dip2px(Context ctx, int dip) {
		float density = getScreenDensity(ctx);
		return (int) (dip * density + 0.5f);
	}
}
