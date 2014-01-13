/**
 * 安卓白金英雄坛制作组 <p>
 * 文件名：BattleStart.java <p>
 * 创建时间：2013-8-5 下午5:53:03 <p>
 * 所属项目：GmudTest <p>
 * @author 12548 <p>
 */
package lostland.gumd.platinum12548.battle.proc;

import lostland.gumd.platinum12548.GmudWorld;
import lostland.gumd.platinum12548.battle.BattleOverScreen;

/**
 * 类名：BattleStart <p>
 * 说明：
 * @author 12548
 */
public class BattleStart implements Status {

	public static int wp;
	public static String name;
	
	/**
	 * 
	 */
	public BattleStart() {

	}

	/* （非 Javadoc）
	 * @see lostland.gumd.platinum12548.battle.proc.Status#execute()
	 */
	@Override
	public boolean execute() {
		
		GmudWorld.bs.zdp = GmudWorld.mc;
		GmudWorld.bs.bdp = GmudWorld.npc[GmudWorld.bs.enemyid];
//		GmudWorld.bs.bdp.setDifficulty(GmudWorld.game.getDifficulty());
		GmudWorld.bs.bdp.refresh();
		GmudWorld.bs.setStatus(new FreeStatus());
		GmudWorld.bs.eob = false;
		
		
		
		
		BattleOverScreen.tpflag = false;
		
		if(GmudWorld.bs.bdp.id >=124)
		{
			BattleOverScreen.bossflag = true;
		}
		
		wp =  GmudWorld.npc[GmudWorld.bs.enemyid].itemsckd[0];
		name = GmudWorld.mc.name;
		GmudWorld.mc.name = "你";
		
		boolean xg = GmudWorld.rand.nextBoolean(); // 先攻判定，以后再改
		
		if(xg)
			GmudWorld.bs.swapp();
		return true;
	}

}
