/**
 * 安卓白金英雄坛制作组 <p>
 * 文件名：DummyWindow.java <p>
 * 创建时间：2013-7-30 下午1:44:18 <p>
 * 所属项目：GmudTest <p>
 * @author 12548 <p>
 */
package lostland.gumd.platinum12548.battle;

import lostland.gumd.platinum12548.GameConstants;
import lostland.gumd.platinum12548.GmudGame;
import lostland.gumd.platinum12548.ui.core.GmudWindow;

/**
 * 类名：DummyWindow <p>
 * 说明：
 * @author 12548
 */
public class DummyWindow extends GmudWindow {


	public DummyWindow(GmudGame game) {
		super(game, 0, 0, GameConstants.FBWIDTH, GameConstants.FBHEIGHT);
	}

	/* （非 Javadoc）
	 * @see lostland.gumd.platinum12548.ui.GmudWindow#draw()
	 */
	@Override
	public void draw() {
		// do nothing
	}

}
