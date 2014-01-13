/**
 * 安卓白金英雄坛制作组 <p>
 * 文件名：PreAttackStatus.java <p>
 * 创建时间：2014-1-6 上午11:41:22 <p>
 * 所属项目：GmudEX <p>
 * @author 12548 <p>
 */
package lostland.gumd.platinum12548.battle.proc;

import android.util.Log;
import lostland.gumd.platinum12548.GmudWorld;
import lostland.gumd.platinum12548.battle.ViewScreen;
import lostland.gumd.platinum12548.data.Gesture;

/**
 * 类名：PreAttackStatus <p>
 * 说明：预攻击状态
 * @author 12548
 */
public class PreAttackStatus implements Status {

	String prefix = "";
	
	Gesture ges;
	Status controller;
	boolean inDouble = false;
	
	
	int round = 0;
	int db = 2;
	
	
	
	public PreAttackStatus(Gesture ges,Status controller) {
		this.ges = ges;
		this.controller = controller;
		this.prefix = "";
		inDouble = false;
		round = 0;
	}
	
	public PreAttackStatus(Gesture ges,Status controller, String prefix) {
		this.ges = ges;
		this.controller = controller;
		this.prefix = prefix;
		inDouble = false;
		round = 0;
	}
	
	public PreAttackStatus(Gesture ges,Status controller, String prefix,boolean inDouble) {
		this.ges = ges;
		this.controller = controller;
		this.prefix = prefix;
		this.inDouble = inDouble;
		round = 0;
	}
	
	
	
	/* （非 Javadoc）
	 * @see lostland.gumd.platinum12548.battle.proc.Status#execute()
	 */
	@Override
	public boolean execute() {
		if(inDouble)
		{
			process();
		}
		else
		{
			
			if(doubleAttack() || round > 0)
			{
				if(round < (db-1))
				{
					GmudWorld.bs.setStatus(new PreAttackStatus(ges,this,prefix + "【连击" + (round + 1) + "/" + db + "】",true));
					round++;
				}
				else
				{
					GmudWorld.bs.setStatus(new PreAttackStatus(ges,controller,prefix + "【连击" + (round + 1) + "/" + db + "】",true));
				}
			}
			else
			{
				process();
			}
		}
		return false;
	}
	
	void process()
	{
		if(ges == null)
		{
			AttackStatus.ag = GmudWorld.bs.zdp.cg();
		}
		else
		{
			AttackStatus.ag = ges;
		}
		
		if(controller == null)
			controller = new RoundOverStatus();
		
		GmudWorld.bs.setStatus(new AnotherDummyStatus(new AttackStatus(controller)));
		ViewScreen.setText(prefix + GmudWorld.bs.bsp(AttackStatus.ag.c));
		GmudWorld.game.setScreen(new ViewScreen(GmudWorld.game));
	}
	
	boolean doubleAttack()
	{
		double r1 = 0.5;
		if(GmudWorld.bs.zdp.getAdsLimit() <= 0)
			r1 = 0.5;
		else
			r1 = 1 - (((double)GmudWorld.bs.zdp.ads) / ((double)GmudWorld.bs.zdp.getAdsLimit()));
		double r2 = GmudWorld.game.newint[0] * 0.10f;
		double r3 = ((double)GmudWorld.bs.zdp.getAgi()) / (((double)GmudWorld.bs.zdp.getAgi() + ((double)GmudWorld.bs.bdp.getBon())));
		double r = r1*r2*r3;
		Log.i("PreAttackStatus", "连击率：" + r);
		boolean da = Math.random() < r;
		Log.i("PreAttackStatus", "连击：" + da);
		return da;
	}

}
