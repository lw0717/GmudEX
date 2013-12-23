/**
 * 安卓白金英雄坛制作组 <p>
 * 文件名：MainMenuBorder.java <p>
 * 创建时间：2013-6-30 下午10:23:49 <p>
 * 所属项目：GmudTest <p>
 * @author 12548 <p>
 */
package lostland.gumd.platinum12548.ui;

import lostland.gumd.platinum12548.GameConstants;
import lostland.gumd.platinum12548.GmudGame;
import lostland.gumd.platinum12548.ui.core.GmudWindow;

/**
 * 类名：MainMenuBorder <p>
 * 说明：主菜单的边界。
 * @author 12548
 */
public class MainMenuBorder extends GmudWindow {
	
	public final static int HEIGHT = 20;
	
	public MainMenuBorder(GmudGame game)
	{
		super(game, 0, 0, GameConstants.FBWIDTH+1, HEIGHT);
	}

	/* （非 Javadoc）
	 * @see lostland.gumd.platinum12548.GmudWindow#draw()
	 */
	@Override
	public void draw() {
		drawBackground();
	}

}
