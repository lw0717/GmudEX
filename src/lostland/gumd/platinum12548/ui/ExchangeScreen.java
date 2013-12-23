/**
 * 安卓白金英雄坛制作组 <p>
 * 文件名：ExchangeScreen.java <p>
 * 创建时间：2013-8-24 下午5:19:22 <p>
 * 所属项目：GmudTest <p>
 * @author 12548 <p>
 */
package lostland.gumd.platinum12548.ui;

import android.util.Log;
import lostland.gumd.platinum12548.GmudWorld;
import lostland.gumd.platinum12548.blgframework.IGame;
import lostland.gumd.platinum12548.ui.core.YesNoScreen;

/**
 * 类名：ExchangeScreen <p>
 * 说明：
 * @author 12548
 */
public class ExchangeScreen extends YesNoScreen {
	boolean fail = true;
	int change ,count ,forwp;
	String s2;
	
	/**
	 * @param game
	 * @param s
	 */
	public ExchangeScreen(IGame game, String s,String s2,int change ,int count ,int forwp) {
		super(game, s);
		fail = false;
		this.change = change;
		this.count = count;
		this.forwp = forwp;
		this.s2 = s2;
	}

	/* （非 Javadoc）
	 * @see lostland.gumd.platinum12548.ui.DialogScreen#update(float)
	 */
	@Override
	public void update(float deltaTime) {
		if(deltaTime == 0 && GmudWorld.mc.inventory[change]<count){
			Log.w("Exchanging", "fail");
			fail = true;
			GmudWorld.game.setScreen(GmudWorld.ms);
			return;
		}
		if(fail){
			GmudWorld.game.setScreen(GmudWorld.ms);
			return;
		}
		super.update(deltaTime);
	}

	/* （非 Javadoc）
	 * @see lostland.gumd.platinum12548.ui.YesNoScreen#present(float)
	 */
	@Override
	public void present(float deltaTime) {
		if(fail){
			GmudWorld.game.setScreen(GmudWorld.ms);
			return;
		}
		super.present(deltaTime);
	}

	/* （非 Javadoc）
	 * @see lostland.gumd.platinum12548.ui.YesNoScreen#onYes()
	 */
	@Override
	protected void onYes() {
		GmudWorld.mc.drop(change, count);
		GmudWorld.mc.give(forwp);
		GmudWorld.game.setScreen(new TalkingScreen(game,s2, false));
	}

	/* （非 Javadoc）
	 * @see lostland.gumd.platinum12548.ui.YesNoScreen#onNo()
	 */
	@Override
	protected void onNo() {
		GmudWorld.game.setScreen(GmudWorld.ms);
	}

}
