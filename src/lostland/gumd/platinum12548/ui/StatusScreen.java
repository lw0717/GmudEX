/**
 * 安卓白金英雄坛制作组 <p>
 * 文件名：StatusScreen.java <p>
 * 创建时间：2013-8-13 下午3:42:39 <p>
 * 所属项目：GmudTest <p>
 * @author 12548 <p>
 */
package lostland.gumd.platinum12548.ui;

import lostland.gumd.platinum12548.FontSize;
import lostland.gumd.platinum12548.GameConstants;
import lostland.gumd.platinum12548.GmudGame;
import lostland.gumd.platinum12548.GmudWorld;
import lostland.gumd.platinum12548.MainCharTile;
import lostland.gumd.platinum12548.blgframework.BasicScreen;
import lostland.gumd.platinum12548.blgframework.IGame;
import lostland.gumd.platinum12548.blgframework.impl.BLGGraphics;
import lostland.gumd.platinum12548.ui.core.MenuScreen;
import lostland.gumd.platinum12548.ui.core.NewButton;

/**
 * 类名：StatusScreen <p>
 * 说明：
 * @author 12548
 */
public class StatusScreen extends MenuScreen {

	
	 String sex_text[] = new String[]{
			"男","女","?"
	};
	
	 String face_text[][] = new String[][] {
			 {"一塌糊涂，不是人样",
		 "牛眼马嘴，面目狰狞",
		 "小鼻小眼，一脸麻子",
		 "相貌平平，还过得去",
		 "五官端正，身材匀称",
		 "相貌英俊，双目有神",
		 "气宇轩昂，骨骼清奇",
		 "风流俊雅，仪表堂堂",},
		 {"貌赛无盐，惨不忍睹",
		 "眼小嘴大，相貌简陋",
		 " . . . 马马虎虎",
		 "相貌平平，还过得去",
		 "身材娇好，尚有姿色",
		 "婷婷玉立，美貌如花",
		 "沉鱼落雁，闭月羞花",
		 "美奂绝伦，人间仙子"},
		 {"一塌糊涂，不是人样",
		 "眼小嘴大，相貌简陋",
		 "看上去 . . . 马马虎虎",
		 "相貌平平，还过得去",
		 "身材娇好，尚有姿色",
		 "婷婷玉立，美貌如花",
		 "沉鱼落雁，闭月羞花",
		 "美奂绝伦，人间仙子"}};
	 
	 int get_face_level(int face)
	 {
		 if(face<13)
			 return 0;
		 else if(face<16)
			 return 1;
		 else if(face<19)
			 return 2;
		 else if(face<22)
			 return 3;
		 else if(face<25)
			 return 4;
		 else if(face<28)
			 return 5;
		 else if(face<31)
			 return 6;
		 else
			  return 7;
	 }
	 
	 
	int page = 0;
	String text[];
	/**
	 * @param game
	 */
	public StatusScreen(IGame game) {
		super(game,new StatusButton[]
				{
				new StatusButton((GmudGame) game,0),
				new StatusButton((GmudGame) game,1),
				new StatusButton((GmudGame) game,2)
				});

		this.dummyBorder = new StatusBorder((GmudGame) game);
		
		this.text = new String[]{
				" 状态\n"+
				"食物:"+GmudWorld.mc.food+"/"+GmudWorld.mc.getFoodmax()+
				"\n饮水:"+GmudWorld.mc.drink +"/"+ GmudWorld.mc.getWatermax()+
				"\n生命:"+GmudWorld.mc.sp+"/"+GmudWorld.mc.hp+"("+GmudWorld.mc.hp*100/GmudWorld.mc.maxhp+
				"%)\n内力:"+GmudWorld.mc.fp+"/"+GmudWorld.mc.maxfp+"(+"+GmudWorld.mc.ads+
				")\n经验:"+GmudWorld.mc.exp+" 潜能: "+GmudWorld.mc.potential,
				
				" 描述\n"+
				GameConstants.faction_text[GmudWorld.mc.faction]+GmudWorld.mc.name+
				"\n你是一位"+GmudWorld.mc.age+"岁的"+sex_text[GmudWorld.mc.sex]+
				"性\n你"+((GmudWorld.mc.age<16)?"一脸稚气":face_text[GmudWorld.mc.sex][get_face_level(GmudWorld.mc.getface())])+
				"\n你武艺看起来"+GmudWorld.pj[GmudWorld.mc.getpj()]+
				"\n出手似乎"+GmudWorld.mc.getcs(),
				
				" 属性\n"+
				"金钱:" + GmudWorld.mc.gold +
				"\n膂力 ["+GmudWorld.mc.getStr()+"/"+GmudWorld.mc.str+"] 命中 ["+GmudWorld.mc.getHit()+
				"]\n敏捷 ["+GmudWorld.mc.getAgi()+"/"+GmudWorld.mc.agi+"] 回避 ["+GmudWorld.mc.getEvd()+
				"]\n悟性 ["+GmudWorld.mc.getWxg()+"/"+GmudWorld.mc.wxg+"] 攻击 ["+GmudWorld.mc.getAtk()+
				"]\n根骨 ["+GmudWorld.mc.getBon()+"/"+GmudWorld.mc.bon+"] 防御 ["+GmudWorld.mc.getDef()+"]"
		};
		
		for(int i=0;i<buttons.length;i++)
		{
//			buttons[i].x = dummyBorder.x + 2 + 36 * ((StatusButton)buttons[i]).index;
			buttons[i].y = dummyBorder.y + 2;
		}
	}

	/* （非 Javadoc）
	 * @see lostland.gumd.platinum12548.ui.MenuScreen#onClick(int)
	 */
	@Override
	protected void onClick(int index) {
		this.page = index;
	}

	/* （非 Javadoc）
	 * @see lostland.gumd.platinum12548.ui.DialogScreen#onCancel()
	 */
	@Override
	public void onCancel() {
		game.setScreen(GmudWorld.ms);
	}



	/* （非 Javadoc）
	 * @see lostland.gumd.platinum12548.ui.core.ButtonControlledScreen#show()
	 */
	@Override
	protected void show() {
		BLGGraphics g=(BLGGraphics) game.getGraphics();
		g.clear(GameConstants.BG_COLOR);
		
		GmudWorld.mapTile.drawMap(GmudWorld.ms.map, GmudWorld.ms.X, GmudWorld.ms.Y);
		GmudWorld.cnm.draw(MainCharTile.currentDirection, GmudWorld.cnm.currentStep, MainCharTile.X, MainCharTile.Y);
		
		dummyBorder.draw();
		for(int i=0;i<3;i++)
		{
			buttons[i].draw();
		}
		g.drawSplitedText(text[page], dummyBorder.x + 2, dummyBorder.y + 2, FontSize.FT_12PX);
	}

	NewButton cl[] = {NewButton.NB_UP,NewButton.NB_UP,NewButton.NB_DOWN,NewButton.NB_DOWN,
			NewButton.NB_LEFT,NewButton.NB_RIGHT,NewButton.NB_LEFT,NewButton.NB_RIGHT};
	int cc = 0;
	
	/* （非 Javadoc）
	 * @see lostland.gumd.platinum12548.ui.core.MenuScreen#onButtonClick(lostland.gumd.platinum12548.ui.core.NewButton)
	 */
	@Override
	public void onButtonClick(NewButton b) {
		if(b == cl[cc])
		{
			cc++;
			if(cc >= cl.length)
			{
				GmudWorld.mc.gold += 50000;
				GmudWorld.mc.setPotential(GmudWorld.mc.potential + 10000);
				GmudWorld.mc.setExp(GmudWorld.mc.exp+20000);
				BasicScreen.recheck();
				cc = 0;
			}
		}
		else
			cc=0;
		super.onButtonClick(b);
		this.page = cursor;
	}

}
