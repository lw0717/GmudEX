/**
 * 安卓白金英雄坛制作组 <p>
 * 文件名：LuoYingBinFen.java <p>
 * 创建时间：2013-8-23 下午8:09:39 <p>
 * 所属项目：GmudTest <p>
 * @author 12548 <p>
 */
package lostland.gumd.platinum12548.battle.proc.stunt;

import lostland.gumd.platinum12548.GmudWorld;
import lostland.gumd.platinum12548.battle.ViewScreen;
import lostland.gumd.platinum12548.battle.proc.Status;
import lostland.gumd.platinum12548.battle.proc.StuntScreen;

/**
 * 类名：LuoYingBinFen <p>
 * 说明：
 * @author 12548
 */
public class LuoYingBinFen implements Status {

	/**
	 * 
	 */
	public LuoYingBinFen() {
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
			if(GmudWorld.wp[GmudWorld.bs.bdp.itemsckd[0]].kind == 2)
			{
				ViewScreen.setText(GmudWorld.bs.bsp("$n手中兵刃被鞭圈带动连转几圈，再也把持不定，一下子脱手飞出！"));
				GmudWorld.bs.bdp.itemsckd[0] = 0;
			}
			else
			{
				ViewScreen.setText(GmudWorld.bs.bsp("$n眼见漫天鞭影当头罩下，躲无可躲，架无可架，一下子卷入鞭圈之中！"));
				GmudWorld.bs.bdp.dmg((int) (Math.random()*200 +50), 0);
				GmudWorld.bs.bdp.dz+=3;
			}
		}
		else
		{
			if(GmudWorld.wp[GmudWorld.bs.bdp.itemsckd[0]].kind == 2)
			{
				ViewScreen.setText(GmudWorld.bs.bsp("$n身手敏捷，见机急快，连忙随著鞭势运力抽出，锵的一声逃过兵刃被夺之厄！"));
				GmudWorld.bs.zdp.dz+=3;
			}
			else
			{
				ViewScreen.setText(GmudWorld.bs.bsp("$n眼见不好，紧急中一个就地十八滚，滚出数丈之外，却也大感狼狈！"));
				GmudWorld.bs.zdp.dz+=3;
			}
		}
		
		StuntScreen.StuntOver();
		
		return false;
	}

}
