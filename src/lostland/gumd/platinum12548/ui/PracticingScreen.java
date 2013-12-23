/**
 * 安卓白金英雄坛制作组 <p>
 * 文件名：PracticingScreen.java <p>
 * 创建时间：2013-8-31 上午10:41:44 <p>
 * 所属项目：GmudTest <p>
 * @author 12548 <p>
 */
package lostland.gumd.platinum12548.ui;

import lostland.gumd.platinum12548.GameConstants;
import lostland.gumd.platinum12548.GmudWorld;
import lostland.gumd.platinum12548.blgframework.CScreen;
import lostland.gumd.platinum12548.blgframework.IGame;
import lostland.gumd.platinum12548.ui.core.ProgressScreen;

/**
 * 类名：PracticingScreen <p>
 * 说明：
 * @author 12548
 */
public class PracticingScreen extends ProgressScreen {

	int skild;
	/**
	 * @param game
	 * @param bg
	 * @param ticktime
	 * @param speed
	 * @param max
	 */
	public PracticingScreen(IGame game, CScreen bg, int sklid) {
		super(game, bg, GameConstants.TICK_TIME, 1+GmudWorld.mc.skills[GmudWorld.skill[sklid].getBasicSkill().id], (GmudWorld.mc.skills[sklid]+1)*(GmudWorld.mc.skills[sklid]+1),GmudWorld.mc.learning[sklid],GmudWorld.mc.skills[sklid]);
		this.skild = sklid;
	}



	/* （非 Javadoc）
	 * @see lostland.gumd.platinum12548.ui.core.ProgressScreen#onComplete()
	 */
	@Override
	public void onComplete() { 
		GmudWorld.mc.skills[skild]++;
		GmudWorld.mc.learning[skild] = 0;

		if(GmudWorld.mc.skills[skild] > GmudWorld.mc.skills[GmudWorld.skill[skild].getBasicSkill().id])
			game.setScreen(new NotificationScreen(game,bg,"你的功夫很难再有所提高了，还是向师傅请教下吧"));
		else if(!GmudWorld.mc.expcanlearn(GmudWorld.mc.skills[skild]+1))
			game.setScreen(new NotificationScreen(game,bg,"你的武学经验不足，无法领会更深的功夫"));
		else if(GmudWorld.mc.maxfp < GmudWorld.mc.skills[skild]+1)
			game.setScreen(new NotificationScreen(game,bg,"你的内力修为不足，要勤修内功！"));
		else
			game.setScreen(new PracticingScreen(game,bg,skild));

	}



	/* （非 Javadoc）
	 * @see lostland.gumd.platinum12548.ui.core.ProgressScreen#binding(int)
	 */
	@Override
	protected void binding(int now) {
		GmudWorld.mc.learning[skild] = now;
	}


}
