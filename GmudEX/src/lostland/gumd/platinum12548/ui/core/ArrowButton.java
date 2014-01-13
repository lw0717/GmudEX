/**
 * 安卓白金英雄坛制作组 <p>
 * 文件名：ArrowButton.java <p>
 * 创建时间：2013-7-22 下午2:27:52 <p>
 * 所属项目：GmudTest <p>
 * @author 12548 <p>
 */
package lostland.gumd.platinum12548.ui.core;

import lostland.gumd.platinum12548.Assets;
import lostland.gumd.platinum12548.GmudGame;
import lostland.gumd.platinum12548.blgframework.impl.BLGGraphics;

/**
 * 类名：ArrowButton <p>
 * 说明：按钮基类，把辣阁边框换成了箭头喵~
 * @author 12548
 */
public abstract class ArrowButton extends GmudWindow {

	/**
	 * @param game
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 */
	public ArrowButton(GmudGame game, int x, int y, int width, int height) {
		super(game, x, y, width, height);
		// TODO 自动生成的构造函数存根
	}

	protected int padding_top;
	
	@Override
	protected void drawBackground()
	{
		BLGGraphics g =(BLGGraphics) game.getGraphics();
//		g.drawRect(x+1, y+1, width-1, height-1, GameConstants.BG_COLOR);
		if(bordered)
			g.drawPixmap(Assets.arrow, this.x + 1, this.y + padding_top + 2);
	}
}
