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
import lostland.gumd.platinum12548.SavingScreen;
import lostland.gumd.platinum12548.blgframework.IGame;
import lostland.gumd.platinum12548.blgframework.impl.SingleTouchHandler;
import lostland.gumd.platinum12548.ui.core.MenuScreen;
import lostland.gumd.platinum12548.ui.core.NewButton;

/**
 * 类名：SysMenuScreen <p>
 * 说明：系统菜单
 * @author 12548
 */
public class SysMenuScreen extends MenuScreen {

	
	/**
	 * @param game
	 */
	public SysMenuScreen(IGame game) {
		super(game,new SysMenuButton[]{
				new SysMenuButton((GmudGame) game,0),
				new SysMenuButton((GmudGame) game,1),
				new SysMenuButton((GmudGame) game,2),
				new SysMenuButton((GmudGame) game,3)
		});
		dummyBorder = new SysMenuBorder((GmudGame) game);

	}



	/* （非 Javadoc）
	 * @see lostland.gumd.platinum12548.ui.MenuScreen#onClick(int)
	 */
	@Override
	protected void onClick(int index) {
		switch(index){
		case 0:
			game.setScreen(new InnerMenuScreen(game));
			break;
		case 1:
			if(GmudWorld.mc.hp < GmudWorld.mc.maxhp)
				game.setScreen(new NotificationScreen(game,this,"你受伤了，还是先治疗要紧！"));
			else
				game.setScreen(new PracticeMenuScreen(game));
			break;
		case 2:
			game.setScreen(new SavingScreen(game));
			break;
		case 3:
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




	/* （非 Javadoc）
	 * @see lostland.gumd.platinum12548.ui.core.ButtonControlledScreen#show()
	 */
	@Override
	protected void show() {
		GmudWorld.mms.present(0);
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
		if(b == NewButton.NB_ENTER)
		{
			if(cursor == 3)
				SingleTouchHandler.flag = 10;
		}
		super.onButtonDown(b);
	}
	
	
}
