/**
 * 安卓白金英雄坛制作组 <p>
 * 文件名：NpcMenuButton.java <p>
 * 创建时间：2013-7-23 下午2:46:59 <p>
 * 所属项目：GmudTest <p>
 * @author 12548 <p>
 */
package lostland.gumd.platinum12548.ui;

import lostland.gumd.platinum12548.FontSize;
import lostland.gumd.platinum12548.GmudGame;
import lostland.gumd.platinum12548.blgframework.impl.BLGGraphics;
import lostland.gumd.platinum12548.ui.core.BlockButton;

/**
 * 类名：NpcMenuButton <p>
 * 说明：
 * @author 12548
 */
public class NpcMenuButton extends BlockButton {

	public static final int MARGIN_TOP = 1;
	public static final int MARGIN_LEFT = 1;
	
	public static final int WIDTH = 37;
	public static final int HEIGHT = 13;
	
	int npcid;
	int index;
	
	static final String S[] = {"交谈","查看","战斗","交易","拜师","请教"};
	
	/**
	 * @param game
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 */
	public NpcMenuButton(GmudGame game,int index) {
		super(game, NpcMenuBorder.LEFT + MARGIN_LEFT, NpcMenuBorder.TOP + MARGIN_TOP + HEIGHT * (index>3?3:index), WIDTH, HEIGHT);
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
