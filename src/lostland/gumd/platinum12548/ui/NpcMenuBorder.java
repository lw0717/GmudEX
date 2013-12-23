/**
 * 安卓白金英雄坛制作组 <p>
 * 文件名：NpcMenuBorder.java <p>
 * 创建时间：2013-7-23 下午12:32:03 <p>
 * 所属项目：GmudTest <p>
 * @author 12548 <p>
 */
package lostland.gumd.platinum12548.ui;

import lostland.gumd.platinum12548.GmudGame;
import lostland.gumd.platinum12548.ui.core.GmudWindow;

/**
 * 类名：NpcMenuBorder <p>
 * 说明：
 * @author 12548
 */
public class NpcMenuBorder extends GmudWindow {

	public static final int TOP = 22;
	public static final int LEFT = 25;
	public static final int WIDTH = 38;
	
	public int itemcount;
	
	/**
	 * @param game
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 */
	public NpcMenuBorder(GmudGame game, boolean fourth) {
		super(game, LEFT, TOP, WIDTH, (fourth ? 4:3) * 14);
	}

	/* （非 Javadoc）
	 * @see lostland.gumd.platinum12548.ui.GmudWindow#draw()
	 */
	@Override
	public void draw() {
		this.drawBackground();
	}

}
