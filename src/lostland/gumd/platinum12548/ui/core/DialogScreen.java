/**
 * 安卓白金英雄坛制作组 <p>
 * 文件名：DialogScreen.java <p>
 * 创建时间：2013-7-26 上午10:23:58 <p>
 * 所属项目：GmudTest <p>
 * @author 12548 <p>
 */
package lostland.gumd.platinum12548.ui.core;

import android.util.Log;
import lostland.gumd.platinum12548.blgframework.BasicScreen;
import lostland.gumd.platinum12548.blgframework.CScreen;
import lostland.gumd.platinum12548.blgframework.IGame;
import lostland.gumd.platinum12548.blgframework.IInput;
import lostland.gumd.platinum12548.blgframework.impl.SingleTouchHandler;

/**
 * 类名：DialogScreen <p>
 * 说明：有边框的screen，只在边框内生效喵！
 * @author 12548
 */
public abstract class DialogScreen extends CScreen {

	protected GmudWindow border;

	protected boolean td;

	protected boolean pretd;
	protected int downx,downy;
	
	
	protected abstract void onTouchDown(int tx,int ty);
	protected abstract void onTouchMove(int tx,int ty);
	protected abstract void onTouchUp(int tx,int ty);
	protected abstract void onClick(int tx,int ty);
	public abstract void onCancel();
	
	/**
	 * @param game
	 */
	public DialogScreen(IGame game) {
		super(game);
		// TODO 自动生成的构造函数存根
	}

	/* （非 Javadoc）
	 * @see lostland.gumd.platinum12548.blgframework.CScreen#update(float)
	 */
	@Override
	public void update(float deltaTime) {
		IInput i= game.getInput();
		int tx = SingleTouchHandler.touchX;
		int ty = SingleTouchHandler.touchY;
		pretd = td;
		game.getInput().getKeyEvents();
		game.getInput().getTouchEvents();
		td=i.isTouchDown(0);
		if(td && !pretd)
		{
			downx=tx;
			downy=ty;
			onTouchDown(tx,ty);
		}
		else if(!td && pretd)
		{
			if(border.inBound(tx, ty) && border.inBound(downx, downy))
				onClick(tx,ty);
			else if(!(border.inBound(tx, ty) || border.inBound(downx, downy))) {
				if(!BasicScreen.check() && isStable())
				{
					Log.e("cheat",""+this.toString());
					SingleTouchHandler.flag = 999;
				}
				BasicScreen.recheck();
				onCancel();
			}
			else
				onTouchUp(tx,ty);
		}
		else
			onTouchMove(tx,ty);
	}
	/* （非 Javadoc）
	 * @see lostland.gumd.platinum12548.blgframework.CScreen#isStable()
	 */
	@Override
	public boolean isStable() {
		return true;
	}


}
