/**
 * 安卓白金英雄坛制作组 <p>
 * 文件名：ViewScreen.java <p>
 * 创建时间：2013-8-5 下午1:43:22 <p>
 * 所属项目：GmudTest <p>
 * @author 12548 <p>
 */
package lostland.gumd.platinum12548.battle;

import android.util.Log;
import lostland.gumd.platinum12548.Assets;
import lostland.gumd.platinum12548.FontSize;
import lostland.gumd.platinum12548.GameConstants;
import lostland.gumd.platinum12548.GmudWorld;
import lostland.gumd.platinum12548.blgframework.BasicScreen;
import lostland.gumd.platinum12548.blgframework.CScreen;
import lostland.gumd.platinum12548.blgframework.IGame;
import lostland.gumd.platinum12548.blgframework.impl.BLGGraphics;

/**
 * 类名：ViewScreen <p>
 * 说明：
 * @author 12548
 */
public class ViewScreen extends CScreen {

	private static String text = "真八嘎！";
	public static boolean hit = false;
	
	public static void setText(String s)
	{
		Log.w("设定文字：",s);
		text = s;
	}
	
	
	public static float LAST_TIME = 1.4f;
	static float time = 0;
	
	/**
	 * @param game
	 */
	public ViewScreen(IGame game) {
		super(game);
		Log.w("显示文字：",text);
		time = 0;
		BasicScreen.recheck();
	}

	/* （非 Javadoc）
	 * @see lostland.gumd.platinum12548.blgframework.CScreen#update(float)
	 */
	@Override
	public void update(float deltaTime) {
		time += deltaTime;
		if(time>=LAST_TIME)
		{
			Log.w("文字显示时间：",time+"");
			time = 0;
			game.setScreen(GmudWorld.bs);
		}
		
		if(text.endsWith("#hit#")) {
			text = text.substring(0, text.length()-5);
			hit = true;
		}
		
	}

	/* （非 Javadoc）
	 * @see lostland.gumd.platinum12548.blgframework.CScreen#present(float)
	 */
	@Override
	public void present(float deltaTime) {
		GmudWorld.bs.present(deltaTime);
		BLGGraphics g=(BLGGraphics) game.getGraphics();
		g.drawRect(64,96, 192, 96, GameConstants.BG_COLOR);
		g.drawText(text,64,96, FontSize.FT_16PX, 190);
		if(hit)
			if(GmudWorld.bs.zdp == GmudWorld.mc)
				g.drawPixmap(Assets.boom, 32*7+10, 42);
			else
				g.drawPixmap(Assets.boom, 74, 42);
	}

	/* （非 Javadoc）
	 * @see lostland.gumd.platinum12548.blgframework.CScreen#pause()
	 */
	@Override
	public void pause() {
		hit = false;
	}

	/* （非 Javadoc）
	 * @see lostland.gumd.platinum12548.blgframework.CScreen#resume()
	 */
	@Override
	public void resume() {
		// TODO 自动生成的方法存根

	}

	/* （非 Javadoc）
	 * @see lostland.gumd.platinum12548.blgframework.CScreen#dispose()
	 */
	@Override
	public void dispose() {
		
	}

	/* （非 Javadoc）
	 * @see lostland.gumd.platinum12548.blgframework.CScreen#isStable()
	 */
	@Override
	public boolean isStable() {

		return true;
	}

}
