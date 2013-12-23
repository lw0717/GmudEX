/**
 * 安卓白金英雄坛制作组 <p>
 * 文件名：FixedScreen.java <p>
 * 创建时间：2013-8-30 上午12:07:32 <p>
 * 所属项目：GmudTest <p>
 * @author 12548 <p>
 */
package lostland.gumd.platinum12548.ui.core;

import lostland.gumd.platinum12548.blgframework.CScreen;
import lostland.gumd.platinum12548.blgframework.IGame;

/**
 * 类名：FixedScreen <p>
 * 说明：名为FixedScreen，但实际是背景不能确定的Screen
 * @author 12548
 */
public abstract class FixedScreen extends FullScreen {

	public CScreen bg;
	
	/**
	 * @param game
	 */
	public FixedScreen(IGame game,CScreen bg) {
		super(game);
		this.bg = bg;
	}

	/* （非 Javadoc）
	 * @see lostland.gumd.platinum12548.blgframework.CScreen#present(float)
	 */
	@Override
	public void present(float deltaTime) {
		bg.present(deltaTime);
		draw();
	}

	public abstract void draw();

	/* （非 Javadoc）
	 * @see lostland.gumd.platinum12548.ui.core.DialogScreen#isStable()
	 */
	@Override
	public boolean isStable() {
		return false;
	}
}
