/**
 * 安卓白金英雄坛制作组 <p>
 * 文件名：InventoryScreen.java <p>
 * 创建时间：2013-12-23 下午7:04:35 <p>
 * 所属项目：GmudEX <p>
 * @author 12548 <p>
 */
package lostland.gumd.platinum12548.ui;

import android.util.Log;
import lostland.gumd.platinum12548.GmudGame;
import lostland.gumd.platinum12548.GmudWorld;
import lostland.gumd.platinum12548.MainCharTile;
import lostland.gumd.platinum12548.blgframework.CScreen;
import lostland.gumd.platinum12548.blgframework.IGame;
import lostland.gumd.platinum12548.data.Skill;
import lostland.gumd.platinum12548.ui.core.DoubleScrollScreen;

/**
 * 类名：InventoryScreen <p>
 * 说明：
 * @author 12548
 */
public class SkillScreen extends DoubleScrollScreen {

	int sticking[][] = new int[10][Skill.skill_name.length];

	CScreen ts;
	AutoWindow bottomWindow;


	/**
	 * @param game
	 * @param x
	 * @param y
	 * @param w1
	 * @param w2
	 * @param max
	 */
	public SkillScreen(IGame game, CScreen ts) {
		super(game, 60, 40, 36, 180, 6);
		this.ts = ts;
		bottomWindow = new AutoWindow((GmudGame) game, x, y + max * 12, w1+w2-1, 26, "");
	}

	/* （非 Javadoc）
	 * @see lostland.gumd.platinum12548.ui.core.DoubleScrollScreen#gets()
	 */
	@Override
	public void gets() {
		s1 = new String[] {"拳脚","兵刃","轻功","内功","招架","知识"};

		if(s2 == null)
			s2 = new String[6][];

		if(sticking == null)
		{
			sticking = new int[10][Skill.skill_name.length];
		}

		int i,k;
		for(i = 0 ; i < 6;i++)
		{
			if(i==4)
				continue;
			
			k=0;
			for(int  j = 0; j < GmudWorld.mc.skills.length;j++)
				if(GmudWorld.mc.skills[j] >0 && GmudWorld.skill[j].pos == i)
					k++;

			s2[i] = new String[k];

			k=0;
			for(int  j = 0; j < GmudWorld.mc.skills.length;j++)
			{
				if(GmudWorld.mc.skills[j] > 0 && GmudWorld.skill[j].pos == i)
				{

					boolean b = false;
					if(i<5)
					if(GmudWorld.mc.skillsckd[i] == j)
						b=true;

					s2[i][k] = GmudWorld.skill[j].name + (b?"（已装备）":"");
					sticking[i][k] = j;
					k++;
				}
			}
		}
		
		i = 4;
		
		k=0;
		for(int  j = 10; j < GmudWorld.mc.skills.length;j++)
			if(GmudWorld.mc.skills[j] >0 && (GmudWorld.skill[j].pos == 0 || GmudWorld.skill[j].pos == 1))
				k++;

		s2[i] = new String[k+1];

		s2[i][0] = GmudWorld.skill[Skill.KIND_ZHAOJIA].name;
		
		k=1;
		for(int  j = 10; j < GmudWorld.mc.skills.length;j++)
		{
			if(GmudWorld.mc.skills[j] >0 && (GmudWorld.skill[j].pos == 0 || GmudWorld.skill[j].pos == 1))
			{

				boolean b = false;
				if(GmudWorld.mc.skillsckd[i] == j)
					b=true;

				s2[i][k] = GmudWorld.skill[j].name + (b?"（已装备）":"");
				sticking[i][k] = j;
				k++;
			}
		}
		

		if(bottomWindow != null)
			if(layer==1)
				bottomWindow.text = "      " + GmudWorld.pj[GmudWorld.mc.skills[sticking[v1.cursor][v2.cursor]]/5] + "   " + GmudWorld.mc.skills[sticking[v1.cursor][v2.cursor]] + "/" + GmudWorld.mc.learning[sticking[v1.cursor][v2.cursor]];
			else
				bottomWindow.text = "";
	}

	/* （非 Javadoc）
	 * @see lostland.gumd.platinum12548.ui.core.DoubleScrollScreen#onClick(int)
	 */
	@Override
	public void onClick(int cu) {
		int d = v1.cursor;
		int j = sticking[d][cu];
		Log.i("ChildClick", "group"+d+"child"+cu+"skill"+j);
		if(j >10 && GmudWorld.skill[j].kind != Skill.KIND_ZHISHI)
		{
			
			if(GmudWorld.mc.skillsckd[d] == j)
				GmudWorld.mc.skillsckd[d] = -1;
			else{
				GmudWorld.mc.skillsckd[d] = j;
				if(d<=1)
					GmudWorld.mc.skillsckd[4] = j;
			}

			
//			boolean b = false;
//			if(d<5)
//			if(GmudWorld.mc.skillsckd[d] == GmudWorld.mc.skills[j])
//				b=true;
//
//			s2[v1.cursor][v2.cursor] = GmudWorld.skill[j].name + (b?"（已装备）":"");
			
			Log.i("ChildClick", "checked skill:"+ j + "on" + d);

		}
		if(layer==1)
			bottomWindow.text = "      " + GmudWorld.pj[GmudWorld.mc.skills[sticking[v1.cursor][v2.cursor]]/5] + "   " + GmudWorld.mc.skills[sticking[v1.cursor][v2.cursor]] + "/" + GmudWorld.mc.learning[sticking[v1.cursor][v2.cursor]];
		else
			bottomWindow.text = "";
	}

	/* （非 Javadoc）
	 * @see lostland.gumd.platinum12548.ui.core.DoubleScrollScreen#drawbg()
	 */
	@Override
	public void drawbg() {
		GmudWorld.mapTile.drawMap(GmudWorld.ms.map, GmudWorld.ms.X, GmudWorld.ms.Y);
		GmudWorld.cnm.draw(MainCharTile.currentDirection, GmudWorld.cnm.currentStep, MainCharTile.X, MainCharTile.Y);
		bottomWindow.draw();
	}

	/* （非 Javadoc）
	 * @see lostland.gumd.platinum12548.ui.core.DoubleScrollScreen#cancel()
	 */
	@Override
	public void cancel() {
		game.setScreen(ts);
	}

}
