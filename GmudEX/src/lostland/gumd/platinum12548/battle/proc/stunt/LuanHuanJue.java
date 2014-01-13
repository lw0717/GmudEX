/**
 * 安卓白金英雄坛制作组 <p>
 * 文件名：LuanHuanJue.java <p>
 * 创建时间：2013-8-23 上午11:32:41 <p>
 * 所属项目：GmudTest <p>
 * @author 12548 <p>
 */
package lostland.gumd.platinum12548.battle.proc.stunt;

import android.util.Log;
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

		double hit_rate = 0.7 + 0.3 * (((double)GmudWorld.bs.zdp.fp - GmudWorld.bs.bdp.fp) / (double)(GmudWorld.bs.zdp.fp + GmudWorld.bs.bdp.fp + 1));

		Log.i("乱环诀","命中率=" + hit_rate);

		boolean hit = Math.random() < hit_rate;

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
