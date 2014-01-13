/**
 * 安卓白金英雄坛制作组 <p>
 * 文件名：CScreen.java <p>
 * 创建时间：2013-5-19 下午7:29:22 <p>
 * 所属项目：GmudTest <p>
 * @author 12548 <p>
 */
package lostland.gumd.platinum12548.blgframework;

import android.util.Log;
import lostland.gumd.platinum12548.blgframework.impl.SingleTouchHandler;

/**
 * 类名：CScreen <p>
 * 说明：
 * @author 12548
 */
public abstract class CScreen extends BasicScreen {
	public CScreen(IGame game)
	{
		super(game);
	}
	
	public abstract void present(float deltaTime);
	
	public abstract void pause();
	
	public abstract void resume();
	
	public abstract void dispose();

	/* （非 Javadoc）
	 * @see lostland.gumd.platinum12548.blgframework.BasicScreen#isStable()
	 */
	@Override
	public boolean isStable() {
		return false;
	}
	
	public void npause()
	{
		if(isStable() && stime > 0.1f && !sss)
		{
			if(!BasicScreen.check())
			{
				Log.e("cheat",""+this.toString());
				SingleTouchHandler.flag = 999;
			}
		}
		else if(BasicScreen.b)
		{
			BasicScreen.recheck();
		}
		pause();
	}
	
	public void nresume()
	{
		if(isStable() && BasicScreen.b)
		{
			BasicScreen.recheck();
		}
		sss = true;
		stime = 0f;
		resume();
	}
}
