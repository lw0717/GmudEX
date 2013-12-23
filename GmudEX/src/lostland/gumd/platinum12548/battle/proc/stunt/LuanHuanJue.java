/**
 * 安卓白金英雄坛制作组 <p>
 * 文件名：LuanHuanJue.java <p>
 * 创建时间：2013-8-23 上午11:32:41 <p>
 * 所属项目：GmudTest <p>
 * @author 12548 <p>
 */
package lostland.gumd.platinum12548.battle.proc.stunt;

import lostland.gumd.platinum12548.GmudWorld;
import lostland.gumd.platinum12548.battle.ViewScreen;
import lostland.gumd.platinum12548.battle.proc.Status;
import lostland.gumd.platinum12548.battle.proc.StuntScreen;

/**
 * 类名：LuanHuanJue <p>
 * 说明：乱环诀
 * @author 12548
 */
public class LuanHuanJue implements Status {

	/**
	 * 
	 */
	public LuanHuanJue() {
		
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
			ViewScreen.setText(GmudWorld.bs.bsp("结果$n身不由己，被推进了$N的乱环阵内！"));
			GmudWorld.bs.bdp.dz+=5;
		}
		else
		{
			ViewScreen.setText(GmudWorld.bs.bsp("可是$n急中生智奋力一挣，竟然脱出了 [乱环诀] 的包围！"));
			GmudWorld.bs.zdp.dz+=3;
		}
		
		StuntScreen.StuntOver();
		
		return false;
	}

}
