/**
 * 安卓白金英雄坛制作组 <p>
 * 文件名：LiuXingFeiZhi.java <p>
 * 创建时间：2013-8-23 下午7:42:53 <p>
 * 所属项目：GmudTest <p>
 * @author 12548 <p>
 */
package lostland.gumd.platinum12548.battle.proc.stunt;

import lostland.gumd.platinum12548.GmudWorld;
import lostland.gumd.platinum12548.battle.ViewScreen;
import lostland.gumd.platinum12548.battle.proc.Status;
import lostland.gumd.platinum12548.battle.proc.StuntScreen;

/**
 * 类名：LiuXingFeiZhi <p>
 * 说明：
 * @author 12548
 */
public class LiuXingFeiZhi implements Status {

	/**
	 * 
	 */
	public LiuXingFeiZhi() {
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
			GmudWorld.bs.bdp.dmg(1000, 0);
			ViewScreen.setText(GmudWorld.bs.bsp("$n吓得目瞪口呆，竟忘了闪避，这一杖在$n$l对穿而过，鲜血溅得满地！"));
		}
		else
		{
			GmudWorld.bs.zdp.dz += 4;
			ViewScreen.setText(GmudWorld.bs.bsp("没想到$n身法奇快，一个燕子三抄水凌空跃出数丈，$w竟未碰到分毫！"));
		}
		GmudWorld.bs.zdp.itemsckd[0] = 0;
		StuntScreen.StuntOver();
		
		return false;
	}

}
