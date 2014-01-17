/**
 * 安卓白金英雄坛制作组 <p>
 * 文件名：CreatingScreen.java <p>
 * 创建时间：2013-12-25 下午8:14:41 <p>
 * 所属项目：GmudEX <p>
 * @author 12548 <p>
 */
package lostland.gumd.platinum12548.ui;

import java.util.Arrays;
import lostland.gumd.platinum12548.Assets;
import lostland.gumd.platinum12548.Direction;
import lostland.gumd.platinum12548.FontSize;
import lostland.gumd.platinum12548.GameConstants;
import lostland.gumd.platinum12548.GmudGame;
import lostland.gumd.platinum12548.GmudWorld;
import lostland.gumd.platinum12548.MainCharTile;
import lostland.gumd.platinum12548.MapScreen;
import lostland.gumd.platinum12548.SavingScreen;
import lostland.gumd.platinum12548.blgframework.IGame;
import lostland.gumd.platinum12548.blgframework.impl.BLGGraphics;
import lostland.gumd.platinum12548.ui.core.MenuScreen;
import lostland.gumd.platinum12548.ui.core.NewButton;

/**
 * 类名：CreatingScreen <p>
 * 说明：
 * @author 12548
 */
public class CreatingScreen extends MenuScreen {

	
	AutoWindow border;
	int attr[];
	int d = 1;
	String diff[] = new String[] {"简单","正常","困难","骨灰"};


	/**
	 * @param game
	 * @param buttons
	 */
	public CreatingScreen(IGame game) {
		super(game, getButtons());
		border = new AutoWindow((GmudGame) game, 95, 31, 36*4+2, 36*4+2, "");
		attr = new int[] {
				20,20,20,20
		};
	}

	/* （非 Javadoc）
	 * @see lostland.gumd.platinum12548.ui.core.MenuScreen#onClick(int)
	 */
	@Override
	protected void onClick(int index) {
		if(index<5)return;
		GmudWorld.mc.str = attr[0];
		GmudWorld.mc.agi = attr[1];
		GmudWorld.mc.bon = attr[2];
		GmudWorld.mc.wxg = attr[3];
		GmudWorld.game.newint[0] = d;

		Arrays.fill(GmudWorld.mc.skills,0);
		Arrays.fill(GmudWorld.mc.skillsckd,-1);
		Arrays.fill(GmudWorld.mc.inventory, 0);
		GmudWorld.mc.inventory[42] = 1;
		GmudWorld.mc.refreshItems();

		GmudWorld.mc.sex = GmudWorld.mc.str<15?1:0;

		GmudWorld.mc.looking = (int) (30 - GmudWorld.mc.str + Math.random() * (30 - GmudWorld.mc.wxg/2));

		GmudWorld.mc.food = 100;
		GmudWorld.mc.drink = 100;

		new SavingScreen(GmudWorld.game).save();
		
		if(GmudWorld.ms == null)
			GmudWorld.ms = new MapScreen(game);
		
		GmudWorld.ms.X = 0;
		GmudWorld.ms.Y = 1;
		MainCharTile.X=4;
		MainCharTile.Y=3;
		MainCharTile.currentDirection= Direction.DOWN;
		
		GmudWorld.ms.map = GmudWorld.map[0];
		game.setScreen(GmudWorld.ms);
		
	}

	/* （非 Javadoc）
	 * @see lostland.gumd.platinum12548.ui.core.MenuScreen#onCancel()
	 */
	@Override
	public void onCancel() {
		// DO NOTHING
	}

	/* （非 Javadoc）
	 * @see lostland.gumd.platinum12548.ui.core.ButtonControlledScreen#show()
	 */
	@Override
	protected void show() {
		BLGGraphics g = (BLGGraphics) game.getGraphics();
		g.clear(GameConstants.BG_COLOR);
		border.draw();

		for(int i = 0; i < 6; i++)
		{
			buttons[i].draw();
			if(i<5) {
				g.drawPixmap(Assets.left,96+32+24,32+12+24*i-6);
				g.drawPixmap(Assets.right,96+32+64,32+12+24*i-6);
			}
			if(i<4)g.drawText(attr[i] + "", 96+64+12, 32+12+24*i-6, FontSize.FT_12PX);
		}
		g.drawText(diff[d] , 96+64+6, 32+12+24*4-6,FontSize.FT_12PX);

	}

	static AutoButton[] getButtons() {
		AutoButton[] t;
		t = new AutoButton[6];
		String s[] = new String[] {"膂力","敏捷","根骨","悟性","难度","开始游戏"};
		for(int i = 0; i < 6; i++)
		{
			t[i] = new AutoButton(GmudWorld.game,96+32-12,32+12+24*i-6,s[i]);
		}

		return t;
	}

	/* （非 Javadoc）
	 * @see lostland.gumd.platinum12548.ui.core.MenuScreen#onButtonClick(lostland.gumd.platinum12548.ui.core.NewButton)
	 */
	@Override
	public void onButtonClick(NewButton b) {
		if(b == NewButton.NB_LEFT)
		{
			if(cursor < 4)
			{
				if(attr[cursor]>10)
					attr[cursor]--;
			}
			else if(cursor == 4)
			{
				d--;
				if(d<0)d=3;
			}
		}
		else if(b == NewButton.NB_RIGHT)
		{
			if(cursor < 4)
			{
				int s = 0;
				for(int i:attr)
					s+=i;
				if(attr[cursor]< 30 && s < 80)
					attr[cursor]++;
			}
			else if(cursor == 4)
			{
				d++;
				if(d>3)d=0;
			}
		}
		else
			super.onButtonClick(b);
	}

	/* （非 Javadoc）
	 * @see lostland.gumd.platinum12548.ui.core.DialogScreen#isStable()
	 */
	@Override
	public boolean isStable() {
		return false;
	}
}
