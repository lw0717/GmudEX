/**
 * 安卓白金英雄坛制作组 <p>
 * 文件名：ShenDaoGuiDie.java <p>
 * 创建时间：2013-8-23 下午8:03:18 <p>
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
 * 类名：ShenDaoGuiDie <p>
 * 说明：
 * @author 12548
 */
public class ShenDaoGuiDie implements Status {

	/**
	 * 
	 */
	public ShenDaoGuiDie() {
		// TODO 自动生成的构造函数存根
	}

	/* （非 Javadoc）
	 * @see lostland.gumd.platinum12548.battle.proc.Status#execute()
	 */
	@Override
	public boolean execute() {

		double hit_rate = 0.7 + 0.3 * (((double)GmudWorld.bs.zdp.fp - GmudWorld.bs.bdp.fp) / (double)(GmudWorld.bs.zdp.fp + GmudWorld.bs.bdp.fp + 1));

		Log.i("神倒鬼跌","命中率=" + hit_rate);

		boolean hit = Math.random() < hit_rate;

		if(hit)
		{
			ViewScreen.setText(GmudWorld.bs.bsp("结果$n眼花缭乱，扑通一声被摔个昏天黑地，想要翻身站起，可又怎缓得出手来！"));
			GmudWorld.bs.bdp.dmg((int) ((GmudWorld.bs.zdp.skills[1]+GmudWorld.bs.zdp.skills[39]*1.5)/5), 0);
			GmudWorld.bs.bdp.dz+=8;
		}
		else
		{
			ViewScreen.setText(GmudWorld.bs.bsp("可是$n内力深厚，急运内力格挡，这神鬼莫测的连环三招竟全部落空！"));
			GmudWorld.bs.zdp.dz+=3;
		}

		StuntScreen.StuntOver();

		return false;
	}

}
