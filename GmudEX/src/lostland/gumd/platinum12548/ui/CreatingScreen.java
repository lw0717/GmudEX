/**
 * 安卓白金英雄坛制作组 <p>
 * 文件名：CreatingScreen.java <p>
 * 创建时间：2013-12-25 下午8:14:41 <p>
 * 所属项目：GmudEX <p>
 * @author 12548 <p>
 */
package lostland.gumd.platinum12548.ui;

import lostland.gumd.platinum12548.GameConstants;
import lostland.gumd.platinum12548.GmudGame;
import lostland.gumd.platinum12548.GmudWorld;
import lostland.gumd.platinum12548.blgframework.IGame;
import lostland.gumd.platinum12548.blgframework.impl.BLGGraphics;
import lostland.gumd.platinum12548.ui.core.MenuScreen;

/**
 * 类名：CreatingScreen <p>
 * 说明：
 * @author 12548
 */
public class CreatingScreen extends MenuScreen {

	
	AutoWindow border;
	
	/**
	 * @param game
	 * @param buttons
	 */
	public CreatingScreen(IGame game) {
		super(game, getButtons());
		border = new AutoWindow((GmudGame) game, 95, 31, 36*4+1, 36*4+1, "");
		// TODO 自动生成的构造函数存根
	}

	/* （非 Javadoc）
	 * @see lostland.gumd.platinum12548.ui.core.MenuScreen#onClick(int)
	 */
	@Override
	protected void onClick(int index) {
		// TODO 自动生成的方法存根

	}

	/* （非 Javadoc）
	 * @see lostland.gumd.platinum12548.ui.core.MenuScreen#onCancel()
	 */
	@Override
	public void onCancel() {
		// DO NOTHING
	}

	/* （非 Javadoc）
	 * @see lostland.gumd.platinum12548.ui.core.ButtonControlledScreen#show()
	 */
	@Override
	protected void show() {
		BLGGraphics g = (BLGGraphics) game.getGraphics();
		g.clear(GameConstants.BG_COLOR);
		

	}
	
	static AutoButton[] getButtons() {
		AutoButton[] t;
		t = new AutoButton[6];
		String s[] = new String[] {"膂力","敏捷","根骨","悟性","难度","开始游戏"};
		for(int i = 0; i < 6; i++)
		{
			t[i] = new AutoButton(GmudWorld.game,96+32,32+12+24*i,s[i]);
		}
		
		return t;
	}
}
