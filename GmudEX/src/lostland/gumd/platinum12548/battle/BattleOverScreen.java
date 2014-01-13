/**
 * 安卓白金英雄坛制作组 <p>
 * 文件名：BattleOverScreen.java <p>
 * 创建时间：2013-8-6 下午5:41:02 <p>
 * 所属项目：GmudTest <p>
 * @author 12548 <p>
 */
package lostland.gumd.platinum12548.battle;

import java.util.ArrayList;
import java.util.Arrays;

import lostland.gumd.platinum12548.GameOverScreen;
import lostland.gumd.platinum12548.GmudMap;
import lostland.gumd.platinum12548.GmudWorld;
import lostland.gumd.platinum12548.battle.proc.BattleStart;
import lostland.gumd.platinum12548.blgframework.CScreen;
import lostland.gumd.platinum12548.blgframework.IGame;
import lostland.gumd.platinum12548.ui.EventScreen;
import lostland.gumd.platinum12548.ui.FinishScreen;
import lostland.gumd.platinum12548.ui.TalkingScreen;
import lostland.gumd.platinum12548.ui.WinScreen;
import lostland.gumd.platinum12548.ui.core.YesNoScreen;

/**
 * 类名：BattleOverScreen <p>
 * 说明：
 * @author 12548
 */
public class BattleOverScreen extends CScreen {

	public static boolean bossflag = false;
	public static boolean tpflag = false;
	public static boolean zdflag = true;
	
	/**
	 * @param game
	 */
	public BattleOverScreen(IGame game) {
		super(game);
		// TODO 自动生成的构造函数存根
	}

	/* （非 Javadoc）
	 * @see lostland.gumd.platinum12548.blgframework.CScreen#update(float)
	 */
	@Override
	public void update(float deltaTime) {
		
		if(zdflag)
		{
			zdflag = false;
			return;
		}
		
		GmudWorld.npc[GmudWorld.bs.enemyid].itemsckd[0]=BattleStart.wp;
		GmudWorld.npc[GmudWorld.bs.enemyid].refresh();
		
		GmudWorld.mc.dz = 0;
		Arrays.fill(GmudWorld.mc.sz,0);
		Arrays.fill(GmudWorld.mc.buff,0);
		GmudWorld.mc.name = BattleStart.name;
		
		if(GmudWorld.game.newint[0] > 0)
		{
			GmudWorld.mc.ref0();
		}
		
		
		
		if(tpflag)
		{
			bossflag = false;
//			GmudWorld.npc[GmudWorld.bs.enemyid].itemsckd[0]=BattleStart.wp;
//			GmudWorld.npc[GmudWorld.bs.enemyid].refresh();
//			
//			GmudWorld.mc.dz = 0;
//			Arrays.fill(GmudWorld.mc.sz,0);
//			Arrays.fill(GmudWorld.mc.buff,0);
//			GmudWorld.mc.name = BattleStart.name;
			
			zdflag = true;
			game.setScreen(new TalkingScreen(game, "哪里逃！", false));
			return;
		}
		else if(GmudWorld.mc.sp <= 0)
		{
			bossflag = false;
			if((GmudWorld.bs.zdp.fame>0 && GmudWorld.mc.fame<128) || (GmudWorld.bs.zdp.fame<=0 && GmudWorld.mc.fame>=128))
			{
				TalkingScreen.ts = new GameOverScreen(game);
				game.setScreen(new TalkingScreen(game, GmudWorld.mc.name+"，去死吧！", false));
			}
			else
			{
				game.setScreen(new TalkingScreen(game,"承让了。", false));
			}
		}
		else
		{
			if(bossflag)
			{
				game.setScreen(new FinishScreen(game));
				return;
			}
			else
			game.setScreen(new YesNoScreen(game,GmudWorld.mc.name+"这一刀要砍下去吗？"){

				@Override
				protected void onYes() {
					String ts[]={"我会在地狱里等着你的。","有的人活着，他已经死了。有。。哎，我还没说完。。。","二十年后又是一条好汉！","啊！"};
					ArrayList<String> a = new ArrayList<String>();
					a.add(ts[(int) (Math.random()*ts.length)]);
					
					
//					game.setScreen(new TalkingScreen(game,ts[(int) (Math.random()*ts.length)], false));
					
					if(GmudWorld.mc.fame >= 128)
					{
						if(GmudWorld.bs.bdp.fame > 0)
							GmudWorld.mc.fame -= GmudWorld.bs.bdp.fame*2;
						else
							GmudWorld.mc.fame -= GmudWorld.bs.bdp.fame;
					}
					else
					{
						if(GmudWorld.bs.bdp.fame > 0)
							GmudWorld.mc.fame -= GmudWorld.bs.bdp.fame;
					}
					
					if(GmudWorld.bs.enemyid == GmudWorld.npc.length-1)
					{
						GmudWorld.map[TalkingScreen.map].setEvent(TalkingScreen.ex, TalkingScreen.ey,-1);
						GmudWorld.map[TalkingScreen.map].setWalkable(TalkingScreen.ex, TalkingScreen.ey,GmudMap.MP_WALKABLE);
						GmudWorld.ms.number++;
						if(GmudWorld.ms.number>9)
						{
							GmudWorld.ms.round++;
							GmudWorld.ms.number = 0;
						}
						GmudWorld.game.nextBadman = 300;
						GmudWorld.game.hunting = false;
						
						int t = (int) ((50 + Math.random()*10) * (GmudWorld.ms.round+GmudWorld.ms.number));
						

						a.add("你被奖励了："+t*4+"点实战经验，"+t+"点潜能");
						GmudWorld.mc.setPotential(GmudWorld.mc.potential + t);
						GmudWorld.mc.setExp(GmudWorld.mc.exp + t*4);
					}
					else
						GmudWorld.bs.bdp.dead = true;
					EventScreen.ts = new WinScreen(game,GmudWorld.bs.enemyid);
					game.setScreen(new EventScreen(game,a,false));
				}

				@Override
				protected void onNo() {
					game.setScreen(new WinScreen(game,GmudWorld.bs.enemyid));
				}
				
			});
			
			
		}

		

		
		zdflag = true;
	}

	/* （非 Javadoc）
	 * @see lostland.gumd.platinum12548.blgframework.CScreen#present(float)
	 */
	@Override
	public void present(float deltaTime) {
		// TODO 自动生成的方法存根

	}

	/* （非 Javadoc）
	 * @see lostland.gumd.platinum12548.blgframework.CScreen#pause()
	 */
	@Override
	public void pause() {
		// TODO 自动生成的方法存根

	}

	/* （非 Javadoc）
	 * @see lostland.gumd.platinum12548.blgframework.CScreen#resume()
	 */
	@Override
	public void resume() {
		// TODO 自动生成的方法存根

	}

	/* （非 Javadoc）
	 * @see lostland.gumd.platinum12548.blgframework.CScreen#dispose()
	 */
	@Override
	public void dispose() {
		// TODO 自动生成的方法存根

	}

}
