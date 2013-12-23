/**
 * 安卓白金英雄坛制作组 <p>
 * 文件名：BlockButton.java <p>
 * 创建时间：2013-7-23 下午5:52:35 <p>
 * 所属项目：GmudTest <p>
 * @author 12548 <p>
 */
package lostland.gumd.platinum12548.ui.core;

import lostland.gumd.platinum12548.Assets;
import lostland.gumd.platinum12548.GmudGame;
import lostland.gumd.platinum12548.blgframework.impl.BLGGraphics;

/**
 * 类名：BlockButton <p>
 * 说明：
 * @author 12548
 */
public abstract class BlockButton extends GmudWindow {

	/**
	 * @param game
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 */
	public BlockButton(GmudGame game, int x, int y, int width, int height) {
		super(game, x, y, width, height);
		// TODO 自动生成的构造函数存根
	}


	@Override
	protected void drawBackground()
	{
		BLGGraphics g =(BLGGraphics) game.getGraphics();
//		g.drawRect(x, y, width, height, GameConstants.BG_COLOR);
		if(bordered)
			g.drawPixmap(Assets.filled, x, y);
		else
			g.drawPixmap(Assets.empty, x, y);
	}

}
