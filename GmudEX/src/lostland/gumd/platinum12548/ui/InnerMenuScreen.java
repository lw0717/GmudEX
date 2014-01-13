/**
 * 安卓白金英雄坛制作组 <p>
 * 文件名：SysMenuScreen.java <p>
 * 创建时间：2013-7-22 上午11:33:22 <p>
 * 所属项目：GmudTest <p>
 * @author 12548 <p>
 */
package lostland.gumd.platinum12548.ui;

import lostland.gumd.platinum12548.GmudGame;
import lostland.gumd.platinum12548.GmudWorld;
import lostland.gumd.platinum12548.blgframework.IGame;
import lostland.gumd.platinum12548.blgframework.impl.SingleTouchHandler;
import lostland.gumd.platinum12548.data.Skill;
import lostland.gumd.platinum12548.ui.core.MenuScreen;
import lostland.gumd.platinum12548.ui.core.NewButton;

/**
 * 类名：SysMenuScreen <p>
 * 说明：系统菜单
 * @author 12548
 */
public class InnerMenuScreen extends MenuScreen {


	/**
	 * @param game
	 */
	public InnerMenuScreen(IGame game) {
		super(game, new InnerMenuButton[]{
				new InnerMenuButton((GmudGame) game,0),
				new InnerMenuButton((GmudGame) game,1),
				new InnerMenuButton((GmudGame) game,2),
				new InnerMenuButton((GmudGame) game,3)
		});
		dummyBorder = new InnerMenuBorder((GmudGame) game);

		GmudWorld.ims = this;
	}




	/* （非 Javadoc）
	 * @see lostland.gumd.platinum12548.ui.MenuScreen#onClick(int)
	 */
	@Override
	protected void onClick(int index) {
		if(GmudWorld.mc.skillsckd[3] == -1)
			game.setScreen(new NotificationScreen(game,this,"请先选择你要用的内功心法！"));
		else switch(index){
		case 0:
			game.setScreen(new SittingScreen(game,GmudWorld.ms));
			break;
		case 1:

			break;
		case 2:
			if(GmudWorld.mc.sp >= GmudWorld.mc.hp)
			{
				game.setScreen(new NotificationScreen(game,this,"你现在体力充沛。"));
			}
			else if(GmudWorld.mc.fp == 0)
			{
				game.setScreen(new NotificationScreen(game,this,"你的内力不够。"));
			}
			else
			{
				GmudWorld.mc.xiqi();
				game.setScreen(new NotificationScreen(game,this,"你深深吸了口气，脸色看起来好多了。"));
			}
			break;
		case 3:
			if(GmudWorld.mc.fp<60)
			{
				game.setScreen(new NotificationScreen(game,this,"你的内力不够，不能疗伤。"));
			}
			else if(GmudWorld.mc.hp==GmudWorld.mc.maxhp)
			{
				game.setScreen(new NotificationScreen(game,this,"你现在并没有受伤!"));
			}
			else if(GmudWorld.mc.hp < GmudWorld.mc.maxhp * 0.3)
			{
				game.setScreen(new NotificationScreen(game,this,"你已受伤过重，只怕一运真气便有生命危险。"));
			}
			else if(GmudWorld.mc.skills[Skill.KIND_NEIGONG] < 30 || GmudWorld.mc.skills[GmudWorld.mc.skillsckd[3]] < 30)
			{
				game.setScreen(new NotificationScreen(game,this,"你运功良久，一抖衣袖，长叹一声站起身来。"));
			}
			else
			{
				GmudWorld.mc.fp-=60;
				GmudWorld.mc.hp += GmudWorld.mc.maxhp * 0.05;
				if(GmudWorld.mc.hp>GmudWorld.mc.maxhp)GmudWorld.mc.hp=GmudWorld.mc.maxhp;
				game.setScreen(new NotificationScreen(game,new NotificationScreen(game,this,"你催动真气，脸上一阵白一阵红，哇地一声，吐出一口淤血，脸色看起来好多了。"),"你全身放松，坐下来开始运功疗伤。"));
			}
			break;
		}
		
		
	}


	/* （非 Javadoc）
	 * @see lostland.gumd.platinum12548.ui.MenuScreen#onCancel()
	 */
	@Override
	public void onCancel() {
		game.setScreen(GmudWorld.mms);
	}



//	/* （非 Javadoc）
//	 * @see lostland.gumd.platinum12548.ui.core.MenuScreen#onTouchDown(int, int)
//	 */
//	@Override
//	protected void onTouchDown(int tx, int ty) {
//		if(buttons[1].inBound(tx, ty) && GmudWorld.mc.skillsckd[3] != -1)
//			SingleTouchHandler.flag = 1;
//		super.onTouchDown(tx, ty);
//	}


	/* （非 Javadoc）
	 * @see lostland.gumd.platinum12548.ui.core.ButtonControlledScreen#show()
	 */
	@Override
	protected void show() {
		GmudWorld.ms.present(-1);
		dummyBorder.draw();
		for(int i=0;i<4;i++)
		{
			buttons[i].draw();
		}
	}




	/* （非 Javadoc）
	 * @see lostland.gumd.platinum12548.ui.core.MenuScreen#onButtonDown(lostland.gumd.platinum12548.ui.core.NewButton)
	 */
	@Override
	protected void onButtonDown(NewButton b) {
		if(cursor == 1 && b == NewButton.NB_ENTER)
		{
			SingleTouchHandler.flag = 1;
		}
		super.onButtonDown(b);
	}




	/* （非 Javadoc）
	 * @see lostland.gumd.platinum12548.ui.core.DialogScreen#isStable()
	 */
	@Override
	public boolean isStable() {
		return false;
	}
}
