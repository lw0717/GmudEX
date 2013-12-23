/**
 * 安卓白金英雄坛制作组 <p>
 * 文件名：TouchHandler.java <p>
 * 创建时间：2013-5-19 下午8:22:29 <p>
 * 所属项目：GmudTest <p>
 * @author 12548 <p>
 */
package lostland.gumd.platinum12548.blgframework.impl;

import java.util.List;

import lostland.gumd.platinum12548.blgframework.IInput.TouchEvent;

import android.view.View.OnTouchListener;


/**
 * 类名：TouchHandler <p>
 * 说明：没什么用……
 * @author 12548
 */
public interface TouchHandler extends OnTouchListener{
	public boolean isTouchDown(int pointer);
	
	public int getTouchX(int pointer);
	
	public int getTouchY(int pointer);
	
	public List<TouchEvent> getTouchEvents();
	
}
