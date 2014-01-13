/**
 * 安卓白金英雄坛制作组 <p>
 * 文件名：SysMenuScreen.java <p>
 * 创建时间：2013-7-22 上午11:33:22 <p>
 * 所属项目：GmudTest <p>
 * @author 12548 <p>
 */
package lostland.gumd.platinum12548.ui;

import android.util.Log;
import lostland.gumd.platinum12548.GmudGame;
import lostland.gumd.platinum12548.GmudWorld;
import lostland.gumd.platinum12548.blgframework.IGame;
import lostland.gumd.platinum12548.data.Skill;
import lostland.gumd.platinum12548.ui.core.GmudWindow;
import lostland.gumd.platinum12548.ui.core.MenuScreen;

/**
 * 类名：SysMenuScreen <p>
 * 说明：系统菜单
 * @author 12548
 */
public class PracticeMenuScreen extends MenuScreen {

	
	/**
	 * @param game
	 */
	public PracticeMenuScreen(IGame game) {
		super(game,getWindows());
		int n=0;
		dummyBorder = new PracticeMenuBorder((GmudGame) game);
		if(GmudWorld.mc.skillsckd[0]>10)n++;
		if(GmudWorld.mc.skillsckd[1]>10)n++;
		if(GmudWorld.mc.skillsckd[2]>10)n++;
		
		if(n==0)
		{
			game.setScreen(GmudWorld.mms);
			return;
		}
		
	}




	/* （非 Javadoc）
	 * @see lostland.gumd.platinum12548.ui.MenuScreen#onClick(int)
	 */
	@Override
	protected void onClick(int index) {
		if(GmudWorld.mc.skillsckd[3] == -1)
			game.setScreen(new NotificationScreen(game,this,"请先选择你要用的内功心法！"));
		else switch(((PracticeMenuButton)buttons[index]).item)
		{
		case 0:
			if(GmudWorld.mc.skills[GmudWorld.mc.skillsckd[0]]> GmudWorld.mc.skills[GmudWorld.skill[GmudWorld.mc.skillsckd[0]].getBasicSkill().id])
				game.setScreen(new NotificationScreen(game,this,"你的功夫很难再有所提高了，还是向师傅请教下吧"));
			else if(!GmudWorld.mc.expcanlearn(GmudWorld.mc.skills[GmudWorld.mc.skillsckd[0]]+1))
				game.setScreen(new NotificationScreen(game,this,"你的武学经验不足，无法领会更深的功夫"));
			else if(GmudWorld.mc.maxfp < GmudWorld.mc.skills[GmudWorld.mc.skillsckd[0]]+1)
				game.setScreen(new NotificationScreen(game,this,"你的内力修为不足，要勤修内功！"));
			else
				game.setScreen(new PracticingScreen(game,this,GmudWorld.mc.skillsckd[0]));
			break;
		case 1:
			if(GmudWorld.mc.skills[GmudWorld.mc.skillsckd[1]]> GmudWorld.mc.skills[GmudWorld.skill[GmudWorld.mc.skillsckd[1]].getBasicSkill().id])
				game.setScreen(new NotificationScreen(game,this,"你的功夫很难再有所提高了，还是向师傅请教下吧"));
			else if(GmudWorld.skill[Skill.eqpbias2[GmudWorld.wp[GmudWorld.mc.itemsckd[0]].subkind]].subkind != GmudWorld.skill[GmudWorld.mc.skillsckd[1]].subkind)
				game.setScreen(new NotificationScreen(game,this,"趁手的兵器都没有一把，瞎比划什么？"));
			else if(!GmudWorld.mc.expcanlearn(GmudWorld.mc.skills[GmudWorld.mc.skillsckd[1]]+1))
				game.setScreen(new NotificationScreen(game,this,"你的武学经验不足，无法领会更深的功夫"));
			else if(GmudWorld.mc.maxfp < GmudWorld.mc.skills[GmudWorld.mc.skillsckd[1]]+1)
				game.setScreen(new NotificationScreen(game,this,"你的内力修为不足，要勤修内功！"));
			else
				game.setScreen(new PracticingScreen(game,this,GmudWorld.mc.skillsckd[1]));
			break;
		case 2:
			boolean  d1,d2,d3;
			
			d1 = GmudWorld.mc.skills[GmudWorld.mc.skillsckd[2]] > GmudWorld.mc.skills[GmudWorld.skill[GmudWorld.mc.skillsckd[2]].getBasicSkill().id];
			Log.w("Practicemenu", "d1");
			d2 = !GmudWorld.mc.expcanlearn(GmudWorld.mc.skills[GmudWorld.mc.skillsckd[2]]+1);
			Log.w("Practicemenu", "d2");
			d3 = GmudWorld.mc.maxfp < GmudWorld.mc.skills[GmudWorld.mc.skillsckd[2]]+1;
			Log.w("Practicemenu", "d3");
			if(d1)
				game.setScreen(new NotificationScreen(game,this,"你的功夫很难再有所提高了，还是向师傅请教下吧"));
			else if(d2)
				game.setScreen(new NotificationScreen(game,this,"你的武学经验不足，无法领会更深的功夫"));
			else if(d3)
				game.setScreen(new NotificationScreen(game,this,"你的内力修为不足，要勤修内功！"));
			else
				game.setScreen(new PracticingScreen(game,this,GmudWorld.mc.skillsckd[2]));
			break;
		}
		
		
	}

	
	static GmudWindow[] getWindows()
	{
		int n=0,i=0;
		if(GmudWorld.mc.skillsckd[0]>10)n++;
		if(GmudWorld.mc.skillsckd[1]>10)n++;
		if(GmudWorld.mc.skillsckd[2]>10)n++;
		PracticeMenuButton[] tw = new PracticeMenuButton[n];
		
		if(n==0)
		{
			GmudWorld.game.setScreen(GmudWorld.mms);
			return tw;
		}
		
		if(GmudWorld.mc.skillsckd[0]>10)tw[i] = new PracticeMenuButton(GmudWorld.game,i++,0);
		if(GmudWorld.mc.skillsckd[1]>10)tw[i] = new PracticeMenuButton(GmudWorld.game,i++,1);
		if(GmudWorld.mc.skillsckd[2]>10)tw[i] = new PracticeMenuButton(GmudWorld.game,i++,2);

		return tw;
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
		if(buttons.length==0)
		{
			game.setScreen(GmudWorld.mms);
			return;
		}
		
		GmudWorld.ms.present(-1);
		dummyBorder.draw();
		for(GmudWindow i:buttons)
		{
			i.draw();
		}
	}
}
