/**
 * 安卓白金英雄坛制作组 <p>
 * 文件名：YesNoButton.java <p>
 * 创建时间：2013-8-24 下午4:58:28 <p>
 * 所属项目：GmudTest <p>
 * @author 12548 <p>
 */
package lostland.gumd.platinum12548.ui.core;

import lostland.gumd.platinum12548.FontSize;
import lostland.gumd.platinum12548.GmudGame;
import lostland.gumd.platinum12548.blgframework.impl.BLGGraphics;

/**
 * 类名：YesNoButton <p>
 * 说明：
 * @author 12548
 */
public class YesNoButton extends ArrowButton {

	int index;
	String S[] = {"是","否"};
	
	/**
	 * @param game
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 */
	public YesNoButton(GmudGame game,int index) {
		super(game, 26, 27 + index *12, 24, 13);
		this.index = index;
	}

	/* （非 Javadoc）
	 * @see lostland.gumd.platinum12548.ui.GmudWindow#draw()
	 */
	@Override
	public void draw() {
		this.drawBackground();
		BLGGraphics g = (BLGGraphics) game.getGraphics();
		g.drawText(S[index], x  + 12, y ,FontSize.FT_12PX);
	}

}
