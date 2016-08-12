package com.chinosoft.p2pinvest.ui;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chinosoft.p2pinvest.R;


public class TopBar extends RelativeLayout{

	private TextView leftView,rightView,centerView;
	
	LayoutParams leftParams,rightParams,centerParams;
	
	
	public TopBar(Context context, AttributeSet attrs) {
		super(context, attrs);
		TypedArray typedArray = context.obtainStyledAttributes(attrs,R.styleable.TopBar);

		String leftText = typedArray.getString(R.styleable.TopBar_leftText);
		String rightText = typedArray.getString(R.styleable.TopBar_rightText);
		String centerText = typedArray.getString(R.styleable.TopBar_centerText);
		
		int leftTextColor = typedArray.getColor(R.styleable.TopBar_leftTextColor,0xff000000);
		int centerTextColor = typedArray.getColor(R.styleable.TopBar_centerTextColor,0xff000000);
		int rightTextColor = typedArray.getColor(R.styleable.TopBar_rightTextColor,0xff000000);
		typedArray.recycle();
		
		leftParams = new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.MATCH_PARENT);
		leftParams.addRule(RelativeLayout.CENTER_VERTICAL);
		leftParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
		leftParams.leftMargin = 20;
		
		centerParams = new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.MATCH_PARENT);
		centerParams.addRule(RelativeLayout.CENTER_IN_PARENT);
		
		rightParams = new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.MATCH_PARENT);
		rightParams.addRule(RelativeLayout.CENTER_VERTICAL);
		rightParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
		rightParams.rightMargin = 20;
		

		leftView = new TextView(context);
		leftView.setId(R.id.topbar_left_view);
		leftView.setText(leftText);
		leftView.setTextColor(leftTextColor);
		leftView.getPaint().setFakeBoldText(true);
		leftView.setGravity(Gravity.CENTER);
		leftView.setLayoutParams(leftParams);
		
		rightView = new TextView(context);
		rightView.setId(R.id.topbar_right_view);
		rightView.setText(rightText);
		rightView.setTextColor(rightTextColor);
		rightView.getPaint().setFakeBoldText(true);
		rightView.setGravity(Gravity.CENTER);
		rightView.setLayoutParams(rightParams);
		
		centerView = new TextView(context);
		centerView.setId(R.id.topbar_center_view);
		centerView.setText(centerText);
		centerView.setTextColor(centerTextColor);
		centerView.setTextSize(20);
		centerView.getPaint().setFakeBoldText(true);
		centerView.setGravity(Gravity.CENTER);
		centerView.setLayoutParams(centerParams);
		
		addView(leftView);
		addView(centerView);
		addView(rightView);
				
	}
	
	public void setLeftText(String text)
	{
		leftView.setText(text);
	}
	
	public void setRightText(String text)
	{
		rightView.setText(text);
	}

	public void setCenterText(String text)
	{
		centerView.setText(text);
	}
	
	public void setLeftViewOnClickListener(OnClickListener clickListener)
	{
	    leftView.setOnClickListener(clickListener);
	}
	
	public void setRightViewOnClickListener(OnClickListener clickListener)
	{
		leftView.setOnClickListener(clickListener);
	}
	
	public void setCenterViewOnClickListener(OnClickListener clickListener)
	{
		centerView.setOnClickListener(clickListener);
	}
	
}




