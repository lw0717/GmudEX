/**
 * 安卓白金英雄坛制作组 <p>
 * 文件名：JiZiJue.java <p>
 * 创建时间：2013-8-21 上午9:22:40 <p>
 * 所属项目：GmudTest <p>
 * @author 12548 <p>
 */
package lostland.gumd.platinum12548.battle.proc.stunt;

import lostland.gumd.platinum12548.GmudWorld;
import lostland.gumd.platinum12548.battle.ViewScreen;
import lostland.gumd.platinum12548.battle.proc.Status;
import lostland.gumd.platinum12548.battle.proc.StuntScreen;

/**
 * 类名：JiZiJue <p>
 * 说明：
 * @author 12548
 */
public class JiZiJue implements Status {

	/**
	 * 
	 */
	public JiZiJue() {
		// TODO 自动生成的构造函数存根
	}

	/* （非 Javadoc）
	 * @see lostland.gumd.platinum12548.battle.proc.Status#execute()
	 */
	@Override
	public boolean execute() {
		boolean hit = GmudWorld.rand.nextBoolean();
		
		if(hit)
		{
			ViewScreen.setText(GmudWorld.bs.bsp("跟著$N横劲发出，$n给这么一挤，招式中的劲力打了个空，心中空荡荡的十分难受！"));
			GmudWorld.bs.bdp.fp-=GmudWorld.bs.zdp.fp/10+315+GmudWorld.bs.zdp.ads;
			if(GmudWorld.bs.bdp.fp<0)GmudWorld.bs.bdp.fp=0;
		}
		else
		{
			boolean hit2 = GmudWorld.rand.nextBoolean();
			if(hit2)
			{
				ViewScreen.setText(GmudWorld.bs.bsp("$n见此情景，一声惊噫，连忙收回自己的劲力，闪身避让！"));
				GmudWorld.bs.bdp.fp-=350;
				if(GmudWorld.bs.bdp.fp<0)GmudWorld.bs.bdp.fp=0;
			}
			else
			{
				ViewScreen.setText(GmudWorld.bs.bsp("没想到$n内力浑厚无比，$N这一挤非但分毫无功，自己反而被牵得跌出几步！"));
				GmudWorld.bs.zdp.dz = 3;
			}
		}

		StuntScreen.StuntOver();
		
		return false;
	}

}
