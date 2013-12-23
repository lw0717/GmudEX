/**
 * 安卓白金英雄坛制作组 <p>
 * 文件名：StatusBorder.java <p>
 * 创建时间：2013-8-13 下午3:24:43 <p>
 * 所属项目：GmudTest <p>
 * @author 12548 <p>
 */
package lostland.gumd.platinum12548.ui;

import lostland.gumd.platinum12548.GameConstants;
import lostland.gumd.platinum12548.GmudGame;
import lostland.gumd.platinum12548.ui.core.GmudWindow;

/**
 * 类名：StatusBorder <p>
 * 说明：
 * @author 12548
 */
public class StatusBorder extends GmudWindow {

	/**
	 * @param game
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 */
	public StatusBorder(GmudGame game) {
		super(game, GameConstants.FBWIDTH/2 - 75, GameConstants.FBHEIGHT/2 - 38, 150, 76);
		// TODO 自动生成的构造函数存根
	}

	/* （非 Javadoc）
	 * @see lostland.gumd.platinum12548.ui.GmudWindow#draw()
	 */
	@Override
	public void draw() {
		this.drawBackground();
	}

}
