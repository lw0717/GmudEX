/**
 * 安卓白金英雄坛制作组 <p>
 * 文件名：迎风一刀斩.java <p>
 * 创建时间：2013-8-23 下午11:35:21 <p>
 * 所属项目：GmudTest <p>
 * @author 12548 <p>
 */
package lostland.gumd.platinum12548.battle.proc.stunt;

import lostland.gumd.platinum12548.GmudWorld;
import lostland.gumd.platinum12548.battle.ViewScreen;
import lostland.gumd.platinum12548.battle.proc.AnotherDummyStatus;
import lostland.gumd.platinum12548.battle.proc.AttackStatus;
import lostland.gumd.platinum12548.battle.proc.RoundOverStatus;
import lostland.gumd.platinum12548.battle.proc.Status;

/**
 * 类名：迎风一刀斩 <p>
 * 说明：
 * @author 12548
 */
public class 迎风一刀斩 implements Status {

	/**
	 * 
	 */
	public 迎风一刀斩() {
		// TODO 自动生成的构造函数存根
	}

	/* （非 Javadoc）
	 * @see lostland.gumd.platinum12548.battle.proc.Status#execute()
	 */
	@Override
	public boolean execute() {
		AttackStatus.ag = GmudWorld.bs.zdp.cg();
		GmudWorld.bs.bdp.temp_dmg_multiplier = 3.0;
		GmudWorld.bs.setStatus(new AnotherDummyStatus(new AttackStatus(new RoundOverStatus())));
		ViewScreen.setText(GmudWorld.bs.bsp(AttackStatus.ag.c));
		GmudWorld.game.setScreen(new ViewScreen(GmudWorld.game));
		return false;
	}

}
