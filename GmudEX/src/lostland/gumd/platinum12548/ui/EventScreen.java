/**
 * 安卓白金英雄坛制作组 <p>
 * 文件名：TalkingScreen.java <p>
 * 创建时间：2013-8-24 上午11:31:15 <p>
 * 所属项目：GmudTest <p>
 * @author 12548 <p>
 */
package lostland.gumd.platinum12548.ui;

import java.util.ArrayList;

import lostland.gumd.platinum12548.GmudGame;
import lostland.gumd.platinum12548.GmudWorld;
import lostland.gumd.platinum12548.MainCharTile;
import lostland.gumd.platinum12548.battle.DummyWindow;
import lostland.gumd.platinum12548.blgframework.CScreen;
import lostland.gumd.platinum12548.blgframework.IGame;
import lostland.gumd.platinum12548.ui.core.DialogScreen;

/**
 * 类名：TalkingScreen <p>
 * 说明：
 * @author 12548
 */
public class EventScreen extends DialogScreen {

	public static CScreen ts=null;
	
	TalkingWindow window;

	public EventScreen(IGame game,ArrayList<String> s,boolean splited)
	{
		super(game);
		this.border = new DummyWindow((GmudGame) game);
		window = new TalkingWindow((GmudGame) game, s,splited);
	}
	
	/* （非 Javadoc）
	 * @see lostland.gumd.platinum12548.ui.DialogScreen#onTouchDown(int, int)
	 */
	@Override
	protected void onTouchDown(int tx, int ty) {
		// 什么也不做

	}

	/* （非 Javadoc）
	 * @see lostland.gumd.platinum12548.ui.DialogScreen#onTouchMove(int, int)
	 */
	@Override
	protected void onTouchMove(int tx, int ty) {
		// 什么也不做

	}

	/* （非 Javadoc）
	 * @see lostland.gumd.platinum12548.ui.DialogScreen#onTouchUp(int, int)
	 */
	@Override
	protected void onTouchUp(int tx, int ty) {
		//什么也不做

	}

	/* （非 Javadoc）
	 * @see lostland.gumd.platinum12548.ui.DialogScreen#onClick(int, int)
	 */
	@Override
	protected void onClick(int tx, int ty) {
		if(window.page<window.getPages()-1)
			window.page++;
		else if(ts==null)
			game.setScreen(GmudWorld.ms);
		else
		{
			game.setScreen(ts);
			ts = null;
		}
	}


	/* （非 Javadoc）
	 * @see lostland.gumd.platinum12548.ui.DialogScreen#onCancel()
	 */
	@Override
	public void onCancel() {
		if(window.page<window.getPages()-1)
			window.page++;
		if(ts==null)
			game.setScreen(GmudWorld.ms);
		else
		{
			game.setScreen(ts);
			ts = null;
		}
	}

	/* （非 Javadoc）
	 * @see lostland.gumd.platinum12548.blgframework.CScreen#present(float)
	 */
	@Override
	public void present(float deltaTime) {
		GmudWorld.mapTile.drawMap(GmudWorld.ms.map, GmudWorld.ms.X, GmudWorld.ms.Y);
		GmudWorld.cnm.draw(MainCharTile.currentDirection, GmudWorld.cnm.currentStep, MainCharTile.X, MainCharTile.Y);
		window.draw(); 
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
