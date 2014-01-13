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

	
	public static int nround = -1;
	
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
		
		double basicSpecialMovementRate = 0.2 * GmudWorld.game.newint[0];
		int aiXiqiCooldown = 4 - GmudWorld.game.newint[0];
		int aiStuntCoolDown = 5 - GmudWorld.game.newint[0];
		
		nround++;
		
		int avStunts[] = new int[0];
		
		for(int i = 0; i <  StuntScreen.name.length; i++)
		{
			if(StuntScreen.canuse(i)) {
				avStunts = GmudWorld.push_back(avStunts, i);
				Log.i("FreeStatus", StuntScreen.name[i] + " is avalible");
			}
		}
		
		Log.i("FreeStatus", avStunts.length + " Stunts avalible in total");
		
		if(avStunts.length > 0 && nround >= aiStuntCoolDown && Math.random() < basicSpecialMovementRate)
		{
			StuntScreen.process(avStunts[(int) (Math.random() * avStunts.length)]);
			nround=-1;
			GmudWorld.game.setScreen(new ViewScreen(GmudWorld.game));
		}
		else if(GmudWorld.bs.zdp.sp < GmudWorld.bs.zdp.hp && GmudWorld.bs.zdp.fp > 0 && nround >= aiXiqiCooldown && Math.random() < basicSpecialMovementRate)
		{
			GmudWorld.bs.xiqiprocess();
			nround=-1;
		}
		else
		{
			GmudWorld.bs.atkprocess(null, null);
		}
		
		
		
		
		
//		AttackStatus.ag = GmudWorld.bs.zdp.cg();
//		GmudWorld.bs.setStatus(new AttackStatus(new RoundOverStatus()));
//		ViewScreen.setText(GmudWorld.bs.bsp(AttackStatus.ag.c));
//		GmudWorld.game.setScreen(new ViewScreen(GmudWorld.game));
		return false;
	}

}
