/**
 * 安卓白金英雄坛制作组 <p>
 * 文件名：LiuLangWenYing.java <p>
 * 创建时间：2013-8-23 下午8:24:21 <p>
 * 所属项目：GmudTest <p>
 * @author 12548 <p>
 */
package lostland.gumd.platinum12548.battle.proc.stunt;

import lostland.gumd.platinum12548.GmudWorld;
import lostland.gumd.platinum12548.battle.proc.RoundOverStatus;
import lostland.gumd.platinum12548.battle.proc.Status;

/**
 * 类名：LiuLangWenYing <p>
 * 说明：
 * @author 12548
 */
public class LiuLangWenYing implements Status {

	/**
	 * 
	 */
	public LiuLangWenYing() {
		// TODO 自动生成的构造函数存根
	}

	boolean b = true;
	int round = -1;
	int t;

	int a=3;
	
	/* （非 Javadoc）
	 * @see lostland.gumd.platinum12548.battle.proc.Status#execute()
	 */
	@Override
	public boolean execute() {
		round++;
		if(round ==0)
		{	
			if(GmudWorld.bs.zdp.skillsckd[0] == 12)
				a=2;
			
			String p = "【柳浪闻莺"  + (round+1) + "/3】";
			if(GmudWorld.bs.zdp.skillsckd[1] == 11)
				p = "【八卦刀影掌"  + (round+1) + "/"+a+"】";
			
			GmudWorld.bs.atkprocess(null, this,p);
			
//			AttackStatus.ag = GmudWorld.bs.zdp.cg();
//			GmudWorld.bs.setStatus(new AnotherDummyStatus(new AttackStatus(this)));
//			ViewScreen.setText(GmudWorld.bs.bsp(AttackStatus.ag.c));
//			GmudWorld.game.setScreen(new ViewScreen(GmudWorld.game));
		}
		else if(round<a)
		{
			if(b){
				t = GmudWorld.bs.zdp.itemsckd[0];
				GmudWorld.bs.zdp.itemsckd[0] = 0;
				b=false;
			}

			
			String p = "【柳浪闻莺"  + (round+1) + "/3】";
			if(GmudWorld.bs.zdp.skillsckd[1] == 11)
				p = "【八卦刀影掌"  + (round+1) + "/"+a+"】";
			
			GmudWorld.bs.atkprocess(null, this,p);
			
//			AttackStatus.ag = GmudWorld.bs.zdp.cg();
//			GmudWorld.bs.setStatus(new AttackStatus(this));
//			ViewScreen.setText(GmudWorld.bs.bsp(AttackStatus.ag.c));
//			GmudWorld.game.setScreen(new ViewScreen(GmudWorld.game));
		}
		else
		{
			GmudWorld.bs.zdp.dz +=3;
			GmudWorld.bs.zdp.itemsckd[0] = t;
			GmudWorld.bs.setStatus(new RoundOverStatus());
		}

		return false;
	}

}

