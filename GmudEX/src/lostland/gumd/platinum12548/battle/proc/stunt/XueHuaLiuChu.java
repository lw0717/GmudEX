/**
 * 安卓白金英雄坛制作组 <p>
 * 文件名：XueHuaLiuChu.java <p>
 * 创建时间：2013-8-23 下午7:52:46 <p>
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
 * 类名：XueHuaLiuChu <p>
 * 说明：
 * @author 12548
 */
public class XueHuaLiuChu implements Status {

	/**
	 * 
	 */
	public XueHuaLiuChu() {
		// TODO 自动生成的构造函数存根
	}

	int round = -1;
	int chu;
	
	/* （非 Javadoc）
	 * @see lostland.gumd.platinum12548.battle.proc.Status#execute()
	 */
	@Override
	public boolean execute() {
		round++;
		if(round ==0)
		{
			chu = 1+(GmudWorld.bs.zdp.skills[38]-60)/20;
			AttackStatus.ag = GmudWorld.bs.zdp.cg();
			GmudWorld.bs.setStatus(new AnotherDummyStatus(new AttackStatus(this)));
			ViewScreen.setText(GmudWorld.bs.bsp(AttackStatus.ag.c));
			GmudWorld.game.setScreen(new ViewScreen(GmudWorld.game));
		}else if(round<chu)
		{
			AttackStatus.ag = GmudWorld.bs.zdp.cg();
			GmudWorld.bs.setStatus(new AttackStatus(this));
			ViewScreen.setText(GmudWorld.bs.bsp(AttackStatus.ag.c));
			GmudWorld.game.setScreen(new ViewScreen(GmudWorld.game));
		}
		else
		{
			GmudWorld.bs.zdp.dz +=3;
			GmudWorld.bs.setStatus(new RoundOverStatus());
		}
		
		return false;
	}

}
