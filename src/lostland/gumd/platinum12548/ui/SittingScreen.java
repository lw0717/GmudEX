/**
 * 安卓白金英雄坛制作组 <p>
 * 文件名：SittingScreen.java <p>
 * 创建时间：2013-8-30 下午2:54:19 <p>
 * 所属项目：GmudTest <p>
 * @author 12548 <p>
 */
package lostland.gumd.platinum12548.ui;

import lostland.gumd.platinum12548.GameConstants;
import lostland.gumd.platinum12548.GmudWorld;
import lostland.gumd.platinum12548.blgframework.CScreen;
import lostland.gumd.platinum12548.blgframework.IGame;
import lostland.gumd.platinum12548.data.Skill;
import lostland.gumd.platinum12548.ui.core.ProgressScreen;

/**
 * 类名：SittingScreen <p>
 * 说明：
 * @author 12548
 */
public class SittingScreen extends ProgressScreen {

	public static int s_now,s_max,s_smax;
	
	

	public SittingScreen(IGame game, CScreen bg) {
		super(game, bg, GameConstants.TICK_TIME, 
				GmudWorld.mc.getBon()/5 + GmudWorld.mc.skills[Skill.KIND_NEIGONG]/20 +GmudWorld.mc.skillsckd[3]/10 + Math.max(0, (GmudWorld.mc.getMaxmaxfp() - GmudWorld.mc.maxfp) / 20 ), 
				GmudWorld.mc.maxfp*2,GmudWorld.mc.fp,GmudWorld.mc.maxfp);
	}

	/* （非 Javadoc）
	 * @see lostland.gumd.platinum12548.ui.ProgressScreen#onComplete()
	 */
	@Override
	public void onComplete() {
		if(GmudWorld.mc.maxfp<GmudWorld.mc.getMaxmaxfp())
		{
			GmudWorld.mc.maxfp++;
			GmudWorld.mc.fp = 0;
			game.setScreen(new SittingScreen(game,bg));
		}
		else
		{
			ok = false;
			GmudWorld.mc.fp = GmudWorld.mc.maxfp;
			game.setScreen(new NotificationScreen(game,GmudWorld.ms,"你的内功等级不够！"));
		}
	}

	

	/* （非 Javadoc）
	 * @see lostland.gumd.platinum12548.ui.core.ProgressScreen#binding(int)
	 */
	@Override
	protected void binding(int now) {
		GmudWorld.mc.fp = now;
		s_now = now;
		s_max = max;
		s_smax = smax;
	}

	/* （非 Javadoc）
	 * @see lostland.gumd.platinum12548.ui.core.ProgressScreen#draw()
	 */
	@Override
	public void draw() {
		super.draw();
//		BLGGraphics g=(BLGGraphics) game.getGraphics();
//		g.drawRect(0, 50, 144, 12, GameConstants.BG_COLOR);
//		g.drawText("按手机返回键转为后台打坐", 0, 51, FontSize.FT_12PX);
		
	}



}
