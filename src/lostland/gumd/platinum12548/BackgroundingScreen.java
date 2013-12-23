/**
 * 安卓白金英雄坛制作组 <p>
 * 文件名：BackgroundingScreen.java <p>
 * 创建时间：2013-12-20 下午3:29:49 <p>
 * 所属项目：GmudTest <p>
 * @author 12548 <p>
 */
package lostland.gumd.platinum12548;

import lostland.gumd.platinum12548.blgframework.CScreen;
import lostland.gumd.platinum12548.blgframework.IGame;

/**
 * 类名：BackgroundingScreen <p>
 * 说明：
 * @author 12548
 */
public class BackgroundingScreen extends CScreen {

	CScreen ts;
	
	/**
	 * @param game
	 */
	public BackgroundingScreen(IGame game, CScreen s) {
		super(game);
		ts = s;
	}

	/* （非 Javadoc）
	 * @see lostland.gumd.platinum12548.blgframework.CScreen#present(float)
	 */
	@Override
	public void present(float deltaTime) {
		// TODO 自动生成的方法存根

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

	/* （非 Javadoc）
	 * @see lostland.gumd.platinum12548.blgframework.BasicScreen#update(float)
	 */
	@Override
	public void update(float deltaTime) {
		if(deltaTime>0)
			game.setScreen(ts);

	}

}
