/**
 * 安卓白金英雄坛制作组 <p>
 * 文件名：RoundOverStatus.java <p>
 * 创建时间：2013-8-7 下午9:22:55 <p>
 * 所属项目：GmudTest <p>
 * @author 12548 <p>
 */
package lostland.gumd.platinum12548.battle.proc;

import android.util.Log;
import lostland.gumd.platinum12548.GmudWorld;
import lostland.gumd.platinum12548.battle.ViewScreen;

/**
 * 类名：RoundOverStatus <p>
 * 说明：
 * @author 12548
 */
public class RoundOverStatus implements Status {

	/**
	 * 
	 */
	public RoundOverStatus() {
		// TODO 自动生成的构造函数存根
	}

	/* （非 Javadoc）
	 * @see lostland.gumd.platinum12548.battle.proc.Status#execute()
	 */
	@Override
	public boolean execute() {

		if(GmudWorld.bs.bdp.sp <= 0){
			GmudWorld.bs.eob = true;
			return false;
		}
		for(int i = 0; i < GmudWorld.bs.zdp.sz.length; i++)
			if(GmudWorld.bs.zdp.sz[i] > 0)
				GmudWorld.bs.zdp.sz[i]--;

		for(int i  = 0; i < GmudWorld.bs.zdp.buff.length; i++)
			if(GmudWorld.bs.zdp.buff[i] > 0)
			{
				GmudWorld.bs.zdp.buff[i]--;
				if(GmudWorld.bs.zdp.buff[i] == 0)
				{
					switch(i)
					{
					case 0:
						ViewScreen.setText(GmudWorld.bs.bsp("$N剑式一缓，剑上的大小光圈随即不见。"));
						GmudWorld.bs.zdp.evd_bouns -= GmudWorld.bs.zdp.skills[30] / 10;
						GmudWorld.bs.zdp.hit_bouns -= 10;
						break;
					case 1:
						ViewScreen.setText(GmudWorld.bs.bsp("$N缓缓收回内力，掌法也恢复平常。"));
						GmudWorld.bs.zdp.hit_bouns -= (GmudWorld.bs.zdp.skills[1]+GmudWorld.bs.zdp.skills[12])/20;
						break;
					case 2:
						ViewScreen.setText(GmudWorld.bs.bsp("$N三花心法用毕，身法也恢复正常。"));
						GmudWorld.bs.zdp.agi_bouns -= GmudWorld.bs.zdp.skills[20] / 10;
						GmudWorld.bs.zdp.def_bouns -= (GmudWorld.bs.zdp.skills[0]+GmudWorld.bs.zdp.skills[20]*2)/10 -5;
						break;
					case 3:
						ViewScreen.setText(GmudWorld.bs.bsp("$n识破了$N的烟幕诡计，攻守自如多了。"));
						GmudWorld.bs.zdp.hit_bouns -= GmudWorld.bs.bdp.hit;
						break;
					case 4:
						ViewScreen.setText(GmudWorld.bs.bsp("$N忍法影分身运行完毕，制造出来的幻影也跟著消失。"));
						GmudWorld.bs.zdp.wxg_bouns -= 90;
						break;
					case 5:
						ViewScreen.setText(GmudWorld.bs.bsp("$N冰心决运行完毕，笼罩在身周的护体真气逐渐散去。"));
						GmudWorld.bs.zdp.def_bouns -= (GmudWorld.bs.zdp.skills[0]+GmudWorld.bs.zdp.skills[36]*2)/8;
						break;
					case 6:
						ViewScreen.setText(GmudWorld.bs.bsp("$N缓缓收回内力，掌法也恢复平常。"));
						GmudWorld.bs.zdp.str_bouns -= GmudWorld.bs.zdp.skills[13]/5;
						GmudWorld.bs.zdp.hit_bouns -= GmudWorld.bs.zdp.skills[13]/10;
						break;
					}

					GmudWorld.bs.setStatus(new AnotherDummyStatus(new FreeStatus()));
					GmudWorld.bs.swapp();
					return true;
				}
			}

		
		GmudWorld.bs.bdp.temp_dmg_multiplier = 1.0;

		Log.i("回合结束", "doing");
		GmudWorld.bs.setStatus(new FreeStatus());
		GmudWorld.bs.swapp();
		return true;
	}

}
