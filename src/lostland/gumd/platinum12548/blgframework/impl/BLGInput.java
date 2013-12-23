/**
 * 安卓白金英雄坛制作组 <p>
 * 文件名：BLGInput.java <p>
 * 创建时间：2013-5-19 下午9:36:38 <p>
 * 所属项目：GmudTest <p>
 * @author 12548 <p>
 */
package lostland.gumd.platinum12548.blgframework.impl;

import java.util.List;

import lostland.gumd.platinum12548.blgframework.IInput;
import android.content.Context;
import android.view.View;

/**
 * 类名：BLGInput <p>
 * 说明：
 * @author 12548
 */
public class BLGInput implements IInput {
	AccelerometerHandler accelHandler;
	KeyboardHandler keyHandler;
	TouchHandler touchHandler;
	
	public BLGInput(Context context,View view,float scaleX,float scaleY,float sc2x,float sc2y)
	{
		accelHandler = new AccelerometerHandler(context);
		keyHandler=new KeyboardHandler(view);
		touchHandler=new SingleTouchHandler(view,scaleX,scaleY,sc2x,sc2y);
	}

	/* （非 Javadoc）
	 * @see lostland.gumd.platinum12548.blgframework.IInput#isKeyPressed(int)
	 */
	@Override
	public boolean isKeyPressed(int keyCode) {
		return keyHandler.isKeyPressed(keyCode);
	}

	/* （非 Javadoc）
	 * @see lostland.gumd.platinum12548.blgframework.IInput#isTouchDown(int)
	 */
	@Override
	public boolean isTouchDown(int pointer) {
		return touchHandler.isTouchDown(pointer);
	}

	/* （非 Javadoc）
	 * @see lostland.gumd.platinum12548.blgframework.IInput#getTouchX(int)
	 */
	@Override
	public int getTouchX(int pointer) {
		return touchHandler.getTouchX(pointer);
	}

	/* （非 Javadoc）
	 * @see lostland.gumd.platinum12548.blgframework.IInput#getTouchY(int)
	 */
	@Override
	public int getTouchY(int pointer) {
		return touchHandler.getTouchX(pointer);
	}

	/* （非 Javadoc）
	 * @see lostland.gumd.platinum12548.blgframework.IInput#getKeyEvents()
	 */
	@Override
	public List<KeyEvent> getKeyEvents() {
		return keyHandler.getKeyEvents();
	}

	/* （非 Javadoc）
	 * @see lostland.gumd.platinum12548.blgframework.IInput#getTouchEvents()
	 */
	@Override
	public List<TouchEvent> getTouchEvents() {
		return touchHandler.getTouchEvents();
	}

	/* （非 Javadoc）
	 * @see lostland.gumd.platinum12548.blgframework.IInput#getAccelX()
	 */
	@Override
	public float getAccelX() {
		return accelHandler.getAccelX();
	}

	/* （非 Javadoc）
	 * @see lostland.gumd.platinum12548.blgframework.IInput#getAccelY()
	 */
	@Override
	public float getAccelY() {
		return accelHandler.getAccelY();
	}

	/* （非 Javadoc）
	 * @see lostland.gumd.platinum12548.blgframework.IInput#getAccelZ()
	 */
	@Override
	public float getAccelZ() {
		return accelHandler.getAccelZ();
	}


	
}
