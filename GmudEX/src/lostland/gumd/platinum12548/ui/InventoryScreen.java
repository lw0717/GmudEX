/**
 * 安卓白金英雄坛制作组 <p>
 * 文件名：InventoryScreen.java <p>
 * 创建时间：2013-12-23 下午7:04:35 <p>
 * 所属项目：GmudEX <p>
 * @author 12548 <p>
 */
package lostland.gumd.platinum12548.ui;

import lostland.gumd.platinum12548.blgframework.IGame;
import lostland.gumd.platinum12548.ui.core.DoubleScrollScreen;

/**
 * 类名：InventoryScreen <p>
 * 说明：
 * @author 12548
 */
public class InventoryScreen extends DoubleScrollScreen {

	boolean b = false;
	
	/**
	 * @param game
	 * @param x
	 * @param y
	 * @param w1
	 * @param w2
	 * @param max
	 */
	public InventoryScreen(IGame game, boolean b) {
		super(game, 50, 50, 25, 100, 6);
		this.b = b;
	}

	/* （非 Javadoc）
	 * @see lostland.gumd.platinum12548.ui.core.DoubleScrollScreen#gets()
	 */
	@Override
	public void gets() {
		if(b)
			s1 = new String[] {"食物","药物"};
		else
			s1 = new String[] {"食物","药物","武器","装备","其他","丢弃"};
		
	}

	/* （非 Javadoc）
	 * @see lostland.gumd.platinum12548.ui.core.DoubleScrollScreen#onClick(int)
	 */
	@Override
	public void onClick(int cursor) {
		// TODO 自动生成的方法存根

	}

	/* （非 Javadoc）
	 * @see lostland.gumd.platinum12548.ui.core.DoubleScrollScreen#drawbg()
	 */
	@Override
	public void drawbg() {
		// TODO 自动生成的方法存根

	}

}
