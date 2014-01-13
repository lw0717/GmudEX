/**
 * 安卓白金英雄坛制作组 <p>
 * 文件名：SanHuanTaoYue.java <p>
 * 创建时间：2013-8-23 下午6:45:02 <p>
 * 所属项目：GmudTest <p>
 * @author 12548 <p>
 */
package lostland.gumd.platinum12548.battle.proc.stunt;

import lostland.gumd.platinum12548.GmudWorld;
import lostland.gumd.platinum12548.battle.proc.RoundOverStatus;
import lostland.gumd.platinum12548.battle.proc.Status;

/**
 * 类名：SanHuanTaoYue <p>
 * 说明：
 * @author 12548
 */
public class SanHuanTaoYue implements Status {

	/**
	 * 
	 */
	public SanHuanTaoYue(int huan) {
		this.huan = huan;
	}
	int huan;
	int round = -1;
	
	/* （非 Javadoc）
	 * @see lostland.gumd.platinum12548.battle.proc.Status#execute()
	 */
	@Override
	public boolean execute() {
		round++;
//		if(round ==0)
//		{
//			
//			AttackStatus.ag = GmudWorld.zs[huan+round];
//			GmudWorld.bs.setStatus(new AnotherDummyStatus(new AttackStatus(this)));
//			ViewScreen.setText(GmudWorld.bs.bsp(AttackStatus.ag.c));
//			GmudWorld.game.setScreen(new ViewScreen(GmudWorld.game));
//		}
//		else 
			if(round<3)
		{
			String p = "【三环套月"  + (round+1) + "/3】";
			if(huan == 87)
				p = "【旋风三连斩"  + (round+1) + "/3】";
			GmudWorld.bs.atkprocess(GmudWorld.zs[huan+round], this,p);
			
//			AttackStatus.ag = GmudWorld.zs[huan+round];
//			GmudWorld.bs.setStatus(new AttackStatus(this));
//			ViewScreen.setText(GmudWorld.bs.bsp(AttackStatus.ag.c));
//			GmudWorld.game.setScreen(new ViewScreen(GmudWorld.game));
		}
		else
		{
			if(huan == 87)
				GmudWorld.bs.zdp.dz += 1;
			else
				GmudWorld.bs.zdp.dz +=3;
//			GmudWorld.bs.zdp.str_bouns += ( GmudWorld.bs.zdp.skills[1] + GmudWorld.bs.zdp.skills[31] * 2 ) / 10;
			
			GmudWorld.bs.setStatus(new RoundOverStatus());
		}
		
		return false;
	}

}
