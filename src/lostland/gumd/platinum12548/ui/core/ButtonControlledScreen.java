/**
 * 安卓白金英雄坛制作组 <p>
 * 文件名：ButtonControlledScreen.java <p>
 * 创建时间：2013-12-18 下午8:44:03 <p>
 * 所属项目：GmudTest <p>
 * @author 12548 <p>
 */
package lostland.gumd.platinum12548.ui.core;

import android.util.Log;
import lostland.gumd.platinum12548.blgframework.IGame;
import lostland.gumd.platinum12548.blgframework.impl.BLGGraphics;
import lostland.gumd.platinum12548.blgframework.impl.SingleTouchHandler;

/**
 * 类名：ButtonControlledScreen <p>
 * 说明：纯虚拟按键操作了喵！
 * @author 12548
 */
public abstract class ButtonControlledScreen extends FullScreen {

	public static  int otx;

	public static  int oty;
	
	int dx,dy;
	
	public boolean isDown = false;
	
	protected GmudWindow dummyBorder;
	
	/**
	 * @param game
	 */
	public ButtonControlledScreen(IGame game) {
		super(game);
		Log.i("BCS", "C0");
	}

	public static boolean inbound(int bx, int by,int tx,int ty)
	{
		final int R = 16;
		int x = bx + R;
		int y = by + R;
		
		return (tx - x) * (tx - x) + (ty-y) * (ty-y) < R * R;
	}
	
	public boolean isInbound(NewButton b)
	{
		return b.inBound(otx, oty);
	}
	
	protected abstract void onButtonDown(NewButton b);
	public abstract void onButtonClick(NewButton b);
	
	protected abstract void show();
	
	/* （非 Javadoc）
	 * @see lostland.gumd.platinum12548.ui.core.DialogScreen#onTouchDown(int, int)
	 */
	@Override
	protected void onTouchDown(int tx, int ty) {
		
		Log.i("bts", "oTD");
		
		otx = SingleTouchHandler.t2x;
		oty = SingleTouchHandler.t2y;
		
		dx = otx;
		dy = oty;
		isDown = true;
	
		NewButton b = NewButton.inbound(otx, oty);
		
		if(b!=null)
			onButtonDown(b);
		
	}

	/* （非 Javadoc）
	 * @see lostland.gumd.platinum12548.ui.core.DialogScreen#onTouchMove(int, int)
	 */
	@Override
	protected void onTouchMove(int tx, int ty) {
//		Log.w("bts", "oTM");
		
		otx = SingleTouchHandler.t2x;
		oty = SingleTouchHandler.t2y;

	}

	/* （非 Javadoc）
	 * @see lostland.gumd.platinum12548.ui.core.DialogScreen#onTouchUp(int, int)
	 */
	@Override
	protected void onTouchUp(int tx, int ty) {
		Log.i("bts", "oTU");
		isDown = false;
		
		NewButton b = NewButton.inbound(otx, oty);
		
		if(b == NewButton.inbound(dx, dy)){
			Log.w("bts", "oBC" + b.toString());
			onButtonClick(b);
		}
	
	}

	/* （非 Javadoc）
	 * @see lostland.gumd.platinum12548.ui.core.DialogScreen#onClick(int, int)
	 */
	@Override
	protected void onClick(int tx, int ty) {
		Log.i("bts", "oTU");
		isDown = false;
		
		NewButton b = NewButton.inbound(otx, oty);
		
		if(b == NewButton.inbound(dx, dy)){
			Log.i("bts", "oBC" + b.toString());
			onButtonClick(b);
		}
	}

	/* （非 Javadoc）
	 * @see lostland.gumd.platinum12548.blgframework.CScreen#present(float)
	 */
	@Override	
	public void present(float deltaTime) {
		
		show();
		
		BLGGraphics g = (BLGGraphics) game.getGraphics();
		for(NewButton i : NewButton.values())
		{
			g.draw2ndPixmap(i.getPixmap(), i.x(), i.y());
		}

	}

}
