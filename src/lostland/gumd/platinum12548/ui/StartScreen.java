/**
 * 安卓白金英雄坛制作组 <p>
 * 文件名：StartScreen.java <p>
 * 创建时间：2013-9-5 下午8:18:55 <p>
 * 所属项目：GmudTest <p>
 * @author 12548 <p>
 */
package lostland.gumd.platinum12548.ui;

import android.util.Log;
import lostland.gumd.platinum12548.Direction;
import lostland.gumd.platinum12548.FontSize;
import lostland.gumd.platinum12548.GameConstants;
import lostland.gumd.platinum12548.GmudWorld;
import lostland.gumd.platinum12548.MainCharTile;
import lostland.gumd.platinum12548.MapScreen;
import lostland.gumd.platinum12548.SavingScreen;
import lostland.gumd.platinum12548.blgframework.CScreen;
import lostland.gumd.platinum12548.blgframework.IGame;
import lostland.gumd.platinum12548.blgframework.IInput;
import lostland.gumd.platinum12548.blgframework.impl.BLGGraphics;
import lostland.gumd.platinum12548.blgframework.impl.SingleTouchHandler;

/**
 * 类名：StartScreen <p>
 * 说明：
 * @author 12548
 */
public class StartScreen extends CScreen {

	public static boolean flag = false;
	
	String s = "在这件事没发生之前，我一直在平" +
			"静地生活著；如果这件事没有发生，那" +
			"么我会依然这样平静甚至有点单调地继续" +
			"生活下去。可是最终它还是发生了，就" +
			"是现在，在我按下按钮的时候，我启动" +
			"了远古大陆英雄坛的时空转换装置。当" +
			"我从时空扭曲的强大力场中恢复知觉的" +
			"时候，我已经来到了这片传说中神秘的" +
			"土地。这里无法考证年代，我所来到的" +
			"地方好象是中原偏西的位置，一个不大" +
			"不小的小镇，镇上行人混杂，还算热闹" +
			"。从他们的交谈中我得知这里叫＂平安" +
			"小镇＂。而当我注视自己的时候才发现" +
			"，我变成了一个十四岁的少年！这里有" +
			"什么秘密？这是造就英雄的融炉或是邪" +
			"恶的渊源？我不知道，我只知道，从今" +
			"以后，我的生活将完全改变．．．";
	
	int x =170;
	
	public StartScreen(IGame game) {
		super(game);
	}

	boolean b=false;
	
	/* （非 Javadoc）
	 * @see lostland.gumd.platinum12548.blgframework.CScreen#update(float)
	 */
	@Override
	public void update(float deltaTime) {
		
		if(GmudWorld.ms == null)
			GmudWorld.ms = new MapScreen(game);
		
		GmudWorld.ms.X = 0;
		GmudWorld.ms.Y = 1;
		MainCharTile.X=4;
		MainCharTile.Y=3;
		MainCharTile.currentDirection= Direction.DOWN;
		
		GmudWorld.ms.map = GmudWorld.map[0];
		
		x-=5;
		if(flag){
			new SavingScreen(GmudWorld.game).save();
			Log.w("SS", "1");

			GmudWorld.game.setScreen(GmudWorld.ms);
			Log.w("SS", "2");
		}
		else {
			game.getInput().getKeyEvents();
			game.getInput().getTouchEvents();
			IInput i= game.getInput();
			if(i.isTouchDown(0))
				SingleTouchHandler.flag = 4;
		}
	}

	/* （非 Javadoc）
	 * @see lostland.gumd.platinum12548.blgframework.CScreen#present(float)
	 */
	@Override
	public void present(float deltaTime) {
		BLGGraphics g=(BLGGraphics) game.getGraphics();
		g.clear(GameConstants.BG_COLOR);
		
		g.drawText(s, 0, x/10, FontSize.FT_16PX, 320);
		
		g.drawRect(0, 0, 320, 16, GameConstants.BG_COLOR);
		g.drawText("==============白金英雄坛==============", 0, 0, FontSize.FT_16PX,999);
	}

	/* （非 Javadoc）
	 * @see lostland.gumd.platinum12548.blgframework.CScreen#pause()
	 */
	@Override
	public void pause() {
		// TODO 自动生成的方法存根

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
		// TODO 自动生成的方法存根

	}

}
