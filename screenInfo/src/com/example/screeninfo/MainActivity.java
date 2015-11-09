package com.example.screeninfo;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends Activity {
	private TextView mTextView;
	private TextView mSupportView;
	
	private int widthDp, heightDp;
	private float screenSize;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

        mTextView = (TextView) findViewById(R.id.text);
        mSupportView = (TextView) findViewById(R.id.support);

        mTextView.setText(showScreenInfo());
        mSupportView.setText(getSupportInfo());
	}
	
	private String showScreenInfo(){
		int width = ScreenUtils.getScreenWidth(this);
        int height = ScreenUtils.getScreenHeight(this);
        float densityDpi = ScreenUtils.getScreenDensity(this);
        double diagonalPixels = Math.sqrt(Math.pow(width, 2) + Math.pow(height, 2));
        screenSize = Math.round(diagonalPixels / (160 * densityDpi)); //独立像素密度值是160
		
		StringBuffer sb = new StringBuffer();
		sb.append("屏幕尺寸:").append(screenSize).append("\n")
		  .append("屏幕宽高:").append(width).append("*").append(height).append("\n")
		  .append("屏幕密度:").append(densityDpi).append("\n")
		  .append("屏幕dpi:").append(ScreenUtils.getScreenDpi(this));
		
		return sb.toString();
	}
	
	@TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
	private String getSupportInfo(){
	      //values-mcc310-en-sw320dp-w720dp-h720dp-large-long-port-car-night-ldpi-notouch-keysexposed-nokeys-navexposed-nonav-v7
        StringBuffer sb = new StringBuffer();
        sb.append("values").append("-");

        Configuration config = getResources().getConfiguration();
        
//        sb.append("mcc" + config.mcc).append("-");
//        sb.append("mnc" + config.mnc).append("-");

        if(Build.VERSION.SDK_INT > Build.VERSION_CODES.HONEYCOMB_MR2){
        	sb.append("sw" + config.smallestScreenWidthDp + "dp").append("-");
        	
            sb.append("w" + config.screenWidthDp + "dp").append("-");
            sb.append("h" + config.screenHeightDp + "dp").append("-");
        }

        int relativeWidthDp = Math.min(widthDp, heightDp);
        int relativeHeightDp = Math.max(widthDp, heightDp);
        if(relativeWidthDp <= 230 && relativeHeightDp <= 426){
        	sb.append("small").append("-");
        }
        else if(relativeWidthDp <= 320 && relativeHeightDp <= 470){
        	sb.append("normal").append("-");
        }
        else if(relativeWidthDp <= 480 && relativeHeightDp <= 640){
        	sb.append("large").append("-");
        }
        else if(relativeWidthDp <= 720 && relativeHeightDp <= 960){
        	sb.append("xlarge").append("-");
        }

        if(config.orientation == 1){
        	sb.append("port").append("-");
        }
        else if(config.orientation == 2){
        	sb.append("land").append("-");
        }

        float densityDpi = ScreenUtils.getScreenDensity(this);
        if(densityDpi <= 120){
        	sb.append("ldpi").append("-");
        }
        else if(densityDpi <= 160){
        	sb.append("mdpi").append("-");
        }
        else if(densityDpi <= 240){
        	sb.append("hdpi").append("-");
        }
        else if(densityDpi <= 320){
        	sb.append("xhdpi").append("-");
        }
        else if(densityDpi <= 480){
        	sb.append("xxhdpi").append("-");
        }
        else if(densityDpi <= 640){
        	sb.append("xxxhdpi").append("-");
        }

        sb.append("v").append(Build.VERSION.SDK_INT);
        
        return sb.toString();
	}
}
