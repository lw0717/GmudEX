/**
 * 安卓白金英雄坛制作组 <p>
 * 文件名：FreeStatus.java <p>
 * 创建时间：2013-8-5 下午6:00:57 <p>
 * 所属项目：GmudTest <p>
 * @author 12548 <p>
 */
package lostland.gumd.platinum12548.battle.proc;

import android.util.Log;
import lostland.gumd.platinum12548.GmudWorld;
import lostland.gumd.platinum12548.battle.ControlScreen;
import lostland.gumd.platinum12548.battle.ViewScreen;

/**
 * 类名：FreeStatus <p>
 * 说明：
 * @author 12548
 */
public class FreeStatus implements Status {

	
	public FreeStatus() {
		// TODO 自动生成的构造函数存根
	}

	/* （非 Javadoc）
	 * @see lostland.gumd.platinum12548.battle.proc.Status#execute()
	 */
	@Override
	public boolean execute() {
		Log.i("自由状态", "doing");
		if(GmudWorld.bs.zdp == GmudWorld.mc)
		{
			Log.e("自由状态", "主角AI");
			GmudWorld.game.setScreen(new ControlScreen(GmudWorld.game));
			Log.e("自由状态", "控制界面已设置");
			return false;
		}
		else if(GmudWorld.bs.zdp.dz>0)
		{
			GmudWorld.bs.zdp.dz--;
			GmudWorld.bs.setStatus(new RoundOverStatus());
			ViewScreen.setText(GmudWorld.bs.zdp.name+"现在呆若木鸡！");
			GmudWorld.game.setScreen(new ViewScreen(GmudWorld.game));
			return false;
		}
		
		AttackStatus.ag = GmudWorld.bs.zdp.cg();
		GmudWorld.bs.setStatus(new AttackStatus(new RoundOverStatus()));
		ViewScreen.setText(GmudWorld.bs.bsp(AttackStatus.ag.c));
		GmudWorld.game.setScreen(new ViewScreen(GmudWorld.game));
		return false;
	}

}
