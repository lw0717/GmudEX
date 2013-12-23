/**
 * 安卓白金英雄坛制作组 <p>
 * 文件名：StatusButton.java <p>
 * 创建时间：2013-8-13 下午3:12:46 <p>
 * 所属项目：GmudTest <p>
 * @author 12548 <p>
 */
package lostland.gumd.platinum12548.ui;

import lostland.gumd.platinum12548.GameConstants;
import lostland.gumd.platinum12548.GmudGame;
import lostland.gumd.platinum12548.battle.BattleButton;

/**
 * 类名：StatusButton <p>
 * 说明：
 * @author 12548
 */
public class StatusButton extends BattleButton {

	/**
	 * @param game
	 * @param index
	 */
	public StatusButton(GmudGame game, int index) {
		super(game, index);
		this.x = GameConstants.FBWIDTH/2 - 37 + index * 12;
		this.y = 5;
		this.text = new String[]{"","",""};
		resize();
		this.bordered = index == 0?true:false;
	}

	
	
}
