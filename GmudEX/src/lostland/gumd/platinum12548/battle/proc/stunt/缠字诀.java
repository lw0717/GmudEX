/**
 * 安卓白金英雄坛制作组 <p>
 * 文件名：缠字诀.java <p>
 * 创建时间：2013-8-23 下午5:53:12 <p>
 * 所属项目：GmudTest <p>
 * @author 12548 <p>
 */
package lostland.gumd.platinum12548.battle.proc.stunt;

import lostland.gumd.platinum12548.GmudWorld;
import lostland.gumd.platinum12548.battle.ViewScreen;
import lostland.gumd.platinum12548.battle.proc.Status;
import lostland.gumd.platinum12548.battle.proc.StuntScreen;

/**
 * 类名：缠字诀 <p>
 * 说明：
 * @author 12548
 */
public class 缠字诀 implements Status {

	/**
	 * 
	 */
	public 缠字诀() {
		// TODO 自动生成的构造函数存根
	}

	/* （非 Javadoc）
	 * @see lostland.gumd.platinum12548.battle.proc.Status#execute()
	 */
	@Override
	public boolean execute() {
		
		boolean hit = Math.random() < 0.5 + Math.random() * 0.5;
		
		if(hit)
		{
			ViewScreen.setText(GmudWorld.bs.bsp("太极剑意散发出的细丝越积越多，似是积成了一团团丝棉，将$n紧紧裹了起来！"));
			GmudWorld.bs.bdp.dz+=5;
		}
		else
		{
			ViewScreen.setText(GmudWorld.bs.bsp("$n大叫一声不好，一个细胸巧翻云远远跃出丈外。"));
			GmudWorld.bs.zdp.dz+=3;
		}
		
		StuntScreen.StuntOver();
		
		return false;
	}

}
