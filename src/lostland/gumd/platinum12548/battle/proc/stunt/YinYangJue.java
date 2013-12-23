/**
 * 安卓白金英雄坛制作组 <p>
 * 文件名：YinYangJue.java <p>
 * 创建时间：2013-8-23 下午12:16:25 <p>
 * 所属项目：GmudTest <p>
 * @author 12548 <p>
 */
package lostland.gumd.platinum12548.battle.proc.stunt;

import lostland.gumd.platinum12548.GmudWorld;
import lostland.gumd.platinum12548.battle.ViewScreen;
import lostland.gumd.platinum12548.battle.proc.AnotherDummyStatus;
import lostland.gumd.platinum12548.battle.proc.AttackStatus;
import lostland.gumd.platinum12548.battle.proc.DummyStatus;
import lostland.gumd.platinum12548.battle.proc.RoundOverStatus;
import lostland.gumd.platinum12548.battle.proc.Status;
import lostland.gumd.platinum12548.battle.proc.StuntScreen;

/**
 * 类名：YinYangJue <p>
 * 说明：
 * @author 12548
 */
public class YinYangJue implements Status {

	/**
	 * 
	 */
	public YinYangJue() {
		
	}

	boolean yin;
	
	int round = -1;
	
	/* （非 Javadoc）
	 * @see lostland.gumd.platinum12548.battle.proc.Status#execute()
	 */
	@Override
	public boolean execute() {
		
		if(round==-1)yin = GmudWorld.rand.nextBoolean();
		
		round++;
		
		if(yin)
		{
			if(round==0){
				ViewScreen.setText(GmudWorld.bs.bsp("$N招式一变翻为阴手，一股绵绵劲力直向$n全身罩去 ！"));
				AttackStatus.ts = this;
				GmudWorld.bs.setStatus(new DummyStatus());
			}else{
				boolean hit = GmudWorld.rand.nextBoolean();
				if(hit)
				{
					ViewScreen.setText(GmudWorld.bs.bsp("$n於$N阴阳变化莫测之际，哪里还能招架，身子被太极柔劲推得跌跌撞撞！"));
					GmudWorld.bs.bdp.dz = 8;
				}
				else
				{
					ViewScreen.setText(GmudWorld.bs.bsp("$n大吃一惊之际，急运内力全力相抗，狼狈万状地从太极柔劲中脱得身来！"));
					GmudWorld.bs.zdp.dz = 3;
				}
				StuntScreen.StuntOver();
			}
			
			
		}else if(round ==0)
		{
			AttackStatus.ag = GmudWorld.zs[121+round];
			GmudWorld.bs.setStatus(new AnotherDummyStatus(new AttackStatus(this)));
			ViewScreen.setText(GmudWorld.bs.bsp(AttackStatus.ag.c));
			GmudWorld.game.setScreen(new ViewScreen(GmudWorld.game));
		}else if(round<3)
		{
			AttackStatus.ag = GmudWorld.zs[121+round];
			GmudWorld.bs.setStatus(new AttackStatus(this));
			ViewScreen.setText(GmudWorld.bs.bsp(AttackStatus.ag.c));
			GmudWorld.game.setScreen(new ViewScreen(GmudWorld.game));
		}
		else
		{
			GmudWorld.bs.zdp.dz +=3;
			GmudWorld.bs.zdp.str_bouns += ( GmudWorld.bs.zdp.skills[1] +GmudWorld.bs.zdp.skills[31] * 2 ) / 10;
			
			GmudWorld.bs.setStatus(new RoundOverStatus());
		}
		
		return false;
	}

}
