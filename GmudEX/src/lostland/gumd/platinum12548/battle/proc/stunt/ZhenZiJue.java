/**
 * 安卓白金英雄坛制作组 <p>
 * 文件名：ZhenZiJue.java <p>
 * 创建时间：2013-8-16 上午11:52:09 <p>
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
 * 类名：ZhenZiJue <p>
 * 说明：
 * @author 12548
 */
public class ZhenZiJue implements Status {

	/**
	 * 
	 */
	public ZhenZiJue() {
		// TODO 自动生成的构造函数存根
	}

	/* （非 Javadoc）
	 * @see lostland.gumd.platinum12548.battle.proc.Status#execute()
	 */
	@Override
	public boolean execute() {
		
		double hit_rate = 0.5 + 0.5 * (((double)GmudWorld.bs.zdp.fp - GmudWorld.bs.bdp.fp) / (double)(GmudWorld.bs.zdp.fp + GmudWorld.bs.bdp.fp + 1));
		Log.i("震字诀","命中率=" + hit_rate);
		boolean hit = Math.random() < hit_rate;
		
		
		if(hit){
			ViewScreen.setText(GmudWorld.bs.bsp("太极之意连绵不断,一个圆圈未完,第二个圆圈已生,喀喇一响,$n一处骨头已被绞断！"));
			GmudWorld.bs.bdp.dmg(GmudWorld.bs.zdp.fp/10 +GmudWorld.bs.zdp.ads - GmudWorld.bs.bdp.fp/30,0);
		}else{
			ViewScreen.setText(GmudWorld.bs.bsp("$n内力深厚识得厉害，马上一阵急攻，$N登时手忙脚乱，再也来不及出招！"));
			GmudWorld.bs.zdp.dz +=3;
		}
		
		StuntScreen.StuntOver();
		return false;
	}

}
