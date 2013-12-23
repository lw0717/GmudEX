/**
 * 安卓白金英雄坛制作组 <p>
 * 文件名：TradeScreen.java <p>
 * 创建时间：2013-12-22 下午9:54:07 <p>
 * 所属项目：GmudTest <p>
 * @author 12548 <p>
 */
package lostland.gumd.platinum12548.ui;

import android.util.Log;
import lostland.gumd.platinum12548.GameConstants;
import lostland.gumd.platinum12548.GmudGame;
import lostland.gumd.platinum12548.GmudWorld;
import lostland.gumd.platinum12548.MainCharTile;
import lostland.gumd.platinum12548.blgframework.CScreen;
import lostland.gumd.platinum12548.blgframework.IGame;
import lostland.gumd.platinum12548.blgframework.impl.BLGGraphics;
import lostland.gumd.platinum12548.ui.core.NewButton;
import lostland.gumd.platinum12548.ui.core.ScrollableMenuScreen;

/**
 * 类名：TradeScreen <p>
 * 说明：
 * @author 12548
 */
public class TradeScreen extends ScrollableMenuScreen {


	TalkingWindow topWindow;
	AutoWindow bottomWindow;
	CScreen ts;

	int npcid;
	

	public TradeScreen(IGame game, int npcid,CScreen ts) {
		super(game, gets(npcid), 20, 30, 10, 6);
		this.ts = ts;
		Log.w("TS", "C1");
		this.npcid = npcid;
		String ss;
		switch(GmudWorld.npc[npcid].trading)
		{
		case 1:
			ss = "要买什么你自己看看吧！";
			break;
		case 2:
			ss  = "有什么不用的东西就拿来吧！";
			break;
		case 101:
			ss = "你想学什么就说吧！";
			break;
		default:
			ss = "baga";
			break;
		}
		Log.w("TS", "C2");
		this.topWindow = new TalkingWindow((GmudGame) game, ss, false);
		this.bottomWindow = new AutoWindow((GmudGame) game, nborder.x + 100, nborder.y + nborder.height +2, 160, 26, sticking[this.cursor] + "");
		
		
		refresh();
//		switch(GmudWorld.npc[npcid].trading)
//		{
//		case 1:
//			bottomWindow.text = "售价：" + GmudWorld.wp[sticking[this.cursor]].cost + "\n金钱：" + GmudWorld.mc.gold + " 现有数量：" + GmudWorld.mc.inventory[sticking[this.cursor]];
//			break;
//		case 2:
//			bottomWindow.text = "售价：" + (int)(GmudWorld.wp[sticking[this.cursor]].cost * 0.7f) + "\n金钱：" + GmudWorld.mc.gold;
//			break;
//		case 101:
//			bottomWindow.text = "当前等级：" + GmudWorld.mc.skills[sticking[this.cursor]] + "\n潜能：" + GmudWorld.mc.potential;
//			break;
//		default:
//		}
		Log.w("TS", "C3");
	}

	
	
	/* （非 Javadoc）
	 * @see lostland.gumd.platinum12548.ui.core.ScrollableMenuScreen#drawbg()
	 */
	@Override
	public void drawbg() {
		
		if(buttons.length < 1)
			onCancel();
		
		BLGGraphics g=(BLGGraphics) game.getGraphics();
		g.clear(GameConstants.BG_COLOR);
		
		GmudWorld.mapTile.drawMap(GmudWorld.ms.map, GmudWorld.ms.X, GmudWorld.ms.Y);
		GmudWorld.cnm.draw(MainCharTile.currentDirection, GmudWorld.cnm.currentStep, MainCharTile.X, MainCharTile.Y);
		
		topWindow.draw();
		bottomWindow.draw();
	}

	/* （非 Javadoc）
	 * @see lostland.gumd.platinum12548.ui.core.ScrollableMenuScreen#onCancel()
	 */
	@Override
	public void onCancel() {
		game.setScreen(ts);
	}

	/* （非 Javadoc）
	 * @see lostland.gumd.platinum12548.ui.core.ScrollableMenuScreen#onClick(int)
	 */
	@Override
	public void onClick(int i) {
		switch(GmudWorld.npc[npcid].trading)
		{
		case 1:
			if(GmudWorld.mc.gold > GmudWorld.wp[sticking[i]].cost)
			{
				GmudWorld.mc.gold -= GmudWorld.wp[sticking[i]].cost;
				GmudWorld.mc.give(sticking[i]);
				refresh();
			}
			break;
		case 2:
			if(!GmudWorld.mc.only(sticking[i]))
			{
				GmudWorld.mc.gold += (int)(GmudWorld.wp[sticking[this.cursor]].cost * 0.7f);
				GmudWorld.mc.drop(sticking[i],1);
				re();
			}
			break;
		case 101:
			if(!GmudWorld.mc.expcanlearn(GmudWorld.mc.skills[sticking[i]]+1))
				GmudWorld.game.setScreen(new NotificationScreen(GmudWorld.game,GmudWorld.ms,"你的武学经验不足，无法领会更深的功夫"));
			else if(GmudWorld.mc.skills[sticking[i]] > GmudWorld.npc[npcid].skills[sticking[i]])
				GmudWorld.game.setScreen(new NotificationScreen(GmudWorld.game,GmudWorld.ms,"你的功夫已经不输为师了，真是可喜可贺呀！"));
			else if(GmudWorld.mc.potential == 0)
			{
				GmudWorld.game.setScreen(new NotificationScreen(GmudWorld.game,GmudWorld.ms,"你的潜能已经发挥到极限了"));
				return;
			}
			else
			{
				GmudWorld.game.setScreen(new LearningScreen(GmudWorld.game,GmudWorld.ms,sticking[i], GmudWorld.npc[npcid].skills[sticking[i]]));
				return;
			}
			break;
		default:
		}
	}

	/* （非 Javadoc）
	 * @see lostland.gumd.platinum12548.ui.core.ButtonControlledScreen#onButtonDown(lostland.gumd.platinum12548.ui.core.NewButton)
	 */
	@Override
	protected void onButtonDown(NewButton b) {
		// TODO 自动生成的方法存根

	}
	static String t[];
	static int sticking[] = new int[1000];
	static String[] gets(int npcid)
	{
		Log.w("TS", "G0");
		
		int i;
		switch(GmudWorld.npc[npcid].trading)
		{
		case 1:
			Log.w("TS", "G1");
			t = new String[GmudWorld.npc[npcid].sells.length];
			for(i=0;i<GmudWorld.npc[npcid].sells.length;i++)
			{
				sticking[i]  = GmudWorld.npc[npcid].sells[i];
				t[i] = GmudWorld.wp[sticking[i]].name;
			}
			break;
		case 2:
			Log.w("TS", "G2");
			int k;
			k = 0;
			for(i = 0; i < GmudWorld.mc.items.length; i++)
			{
				if(GmudWorld.mc.items[i]!=0) {
					k++;
				}
			}
			t = new String[k];
			k = 0;
			for(i = 0; i < GmudWorld.mc.items.length; i++)
			{
				if(GmudWorld.mc.items[i]!=0) {
					sticking[k] = GmudWorld.mc.items[i];
					t[k] = GmudWorld.wp[GmudWorld.mc.items[i]].name + " x " + GmudWorld.mc.inventory[GmudWorld.mc.items[i]];
					k++;
				}
			}
			break;
		case 101:
			Log.w("TS", "G101");
			int tm;
			tm = 0;
			for(int j : GmudWorld.npc[npcid].skills)
				if(j>0)tm++;
			t = new String[tm];
			tm = 0;
			for(int j=0;j<GmudWorld.npc[npcid].skills.length;j++)
				if(GmudWorld.npc[npcid].skills[j]>0)
				{
					sticking[tm] = j;
					t[tm] = GmudWorld.skill[j].name + " x" + GmudWorld.npc[npcid].skills[j];
					tm++;
				}
			
			break;
		default:
			Log.e("TS", "GG");
			return null;
			
			
		}
		Log.w("TS", "GO");

		return t;
	}
	
	
	/* （非 Javadoc）
	 * @see lostland.gumd.platinum12548.ui.core.DialogScreen#isStable()
	 */
	@Override
	public boolean isStable() {
		return false;
	}



	/* （非 Javadoc）
	 * @see lostland.gumd.platinum12548.ui.core.ScrollableMenuScreen#refresh()
	 */
	@Override
	public void refresh() {
		super.refresh();
		switch(GmudWorld.npc[npcid].trading)
		{
		case 1:
			bottomWindow.text = "售价：" + GmudWorld.wp[sticking[this.cursor]].cost + "\n金钱：" + GmudWorld.mc.gold + " 现有数量：" + GmudWorld.mc.inventory[sticking[this.cursor]];
			break;
		case 2:
			bottomWindow.text = "售价：" + (int)(GmudWorld.wp[sticking[this.cursor]].cost * 0.7f) + "\n金钱：" + GmudWorld.mc.gold;
			break;
		case 101:
			bottomWindow.text = "当前等级：" + GmudWorld.mc.skills[sticking[this.cursor]] + "\n潜能：" + GmudWorld.mc.potential;
			break;
		default:
		}
	}



	protected void re() {
		super.re(gets(npcid), 20, 30, 10, 10);
		if(buttons.length < 1)
			onCancel();
		bottomWindow.y = nborder.y + nborder.height +2;
		refresh();
	}

}
