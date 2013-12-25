/**
 * 安卓白金英雄坛制作组 <p>
 * 文件名：SavingScreen.java <p>
 * 创建时间：2013-9-6 下午6:14:21 <p>
 * 所属项目：GmudTest <p>
 * @author 12548 <p>
 */
package lostland.gumd.platinum12548;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.util.Log;
import lostland.gumd.platinum12548.blgframework.BasicScreen;
import lostland.gumd.platinum12548.blgframework.IGame;
import lostland.gumd.platinum12548.blgframework.impl.BLGFileIO;
import lostland.gumd.platinum12548.blgframework.impl.BLGGraphics;
import lostland.gumd.platinum12548.ui.core.FullScreen;
import lostland.gumd.platinum12548.ui.core.GmudWindow;

/**
 * 类名：SavingScreen <p>
 * 说明：
 * @author 12548
 */
public class SavingScreen extends FullScreen {

	boolean b = true;
	private Editor ed;
	
	/**
	 * @param game
	 */
	public SavingScreen(IGame game) {
		super(game);
		// TODO 自动生成的构造函数存根
	}

	/* （非 Javadoc）
	 * @see lostland.gumd.platinum12548.ui.core.DialogScreen#onTouchDown(int, int)
	 */
	@Override
	protected void onTouchDown(int tx, int ty) {
		// TODO 自动生成的方法存根
		
	}

	/* （非 Javadoc）
	 * @see lostland.gumd.platinum12548.ui.core.DialogScreen#onTouchMove(int, int)
	 */
	@Override
	protected void onTouchMove(int tx, int ty) {
		// TODO 自动生成的方法存根
		
	}

	/* （非 Javadoc）
	 * @see lostland.gumd.platinum12548.ui.core.DialogScreen#onTouchUp(int, int)
	 */
	@Override
	protected void onTouchUp(int tx, int ty) {
		// TODO 自动生成的方法存根
		
	}

	/* （非 Javadoc）
	 * @see lostland.gumd.platinum12548.ui.core.DialogScreen#onClick(int, int)
	 */
	@Override
	protected void onClick(int tx, int ty) {
		game.setScreen(GmudWorld.ms);
	}

	/* （非 Javadoc）
	 * @see lostland.gumd.platinum12548.blgframework.CScreen#present(float)
	 */
	@Override
	public void present(float deltaTime) {
		new GmudWindow((GmudGame) game, 50, 50, 50, 13){

			@Override
			public void draw() {
				this.drawBackground();
			}
			
		}.draw();
		BLGGraphics g = (BLGGraphics) game.getGraphics();
		g.drawText("存档成功！", 51, 51, FontSize.FT_12PX);
	}


	public void save()
	{
		BLGFileIO f = (BLGFileIO) game.getFileIO();
		SharedPreferences sp = f.getPreferences();
		BasicScreen.recheck();
		ed = sp.edit();
		
		ed.putInt("SaveFileVersion", 2);
		ed.putBoolean("newgame", false);
		ed.putBoolean("zlgyxz", MapScreen.zlEnabled);
		ed.putBoolean("dtajcz", MapScreen.btnsEnabled);
		
		ed.commit();


		
		
		BufferedWriter out = null;
		
		try {
			out = new BufferedWriter(new OutputStreamWriter(f.writeFile("gmudp12548")));
			
			
			out.write(GmudWorld.mc.name + '\n');

			
			out.write(Integer.toString(GmudWorld.mc.ads)+'\n');
			out.write(Integer.toString(GmudWorld.mc.age)+'\n');
			out.write(Integer.toString(GmudWorld.mc.month)+'\n');
			out.write(Integer.toString(GmudWorld.mc.agi)+'\n');
			out.write(Integer.toString(GmudWorld.mc.atk)+'\n');
			out.write(Integer.toString(GmudWorld.mc.bon)+'\n');
			out.write(Integer.toString(GmudWorld.mc.def)+'\n');
			out.write(Integer.toString(GmudWorld.mc.drink)+'\n');
			out.write(Integer.toString(GmudWorld.mc.evd)+'\n');
			out.write(Integer.toString(GmudWorld.mc.exp)+'\n');
			out.write(Integer.toString(GmudWorld.mc.faction)+'\n');
			out.write(Integer.toString(GmudWorld.mc.fame)+'\n');
			out.write(Integer.toString(GmudWorld.mc.food)+'\n');
			out.write(Integer.toString(GmudWorld.mc.fp)+'\n');
			out.write(Integer.toString(GmudWorld.mc.gold)+'\n');
			out.write(Integer.toString(GmudWorld.mc.hit)+'\n');
			out.write(Integer.toString(GmudWorld.mc.hp)+'\n');
			out.write(Integer.toString(GmudWorld.mc.looking)+'\n');
			out.write(Integer.toString(GmudWorld.mc.maxfp)+'\n');
			out.write(Integer.toString(GmudWorld.mc.maxhp)+'\n');
			out.write(Integer.toString(GmudWorld.mc.potential)+'\n');
			out.write(Integer.toString(GmudWorld.mc.sex)+'\n');
			out.write(Integer.toString(GmudWorld.mc.sp)+'\n');
			out.write(Integer.toString(GmudWorld.mc.str)+'\n');
			out.write(Integer.toString(GmudWorld.mc.wxg)+'\n');
			
			int i;
			
			for(i=0;i<6;i++)
			{
				out.write(Boolean.toString(((GmudGame)game).hasstone[i]) + '\n');
			}
			
			for(i=0;i<GmudWorld.npc.length;i++)
			{
				out.write(Boolean.toString(GmudWorld.npc[i].qingjiaoable) + '\n');
				out.write(Boolean.toString(GmudWorld.npc[i].dead) + '\n');
			}
			
			for(i=0;i<GmudWorld.mc.skills.length;i++)
			{
				out.write(Integer.toString(GmudWorld.mc.skills[i]) + '\n');
			}
			
			out.write(Integer.toString(GmudWorld.mc.skillsckd.length)+'\n');
			Log.w("Saving",GmudWorld.mc.skillsckd.length+" skills checked");
			
			for(i=0;i<GmudWorld.mc.skillsckd.length;i++)
			{
				out.write(Integer.toString(GmudWorld.mc.skillsckd[i]) + '\n');
			}
			
			
			for(i=0;i<GmudWorld.mc.learning.length;i++)
			{
				out.write(Integer.toString(GmudWorld.mc.learning[i]) + '\n');
			}
			
			for(i=0;i<GmudWorld.mc.inventory.length;i++)
			{
				out.write(Integer.toString(GmudWorld.mc.inventory[i]) + '\n');
			}
			
			out.write(Integer.toString(GmudWorld.mc.itemsckd.length)+'\n');
			
			Log.w("Saving",GmudWorld.mc.itemsckd.length+" items checked");
			
			for(i=0;i<GmudWorld.mc.itemsckd.length;i++)
			{
				out.write(Integer.toString(GmudWorld.mc.itemsckd[i]) + '\n');
			}
			
			for(i = 0;i < GmudWorld.game.newint.length;i++)
			{
				out.write(Integer.toString(GmudWorld.game.newint[i]) + '\n');
			}
			
			for(i = 0;i < GmudWorld.game.newbool.length;i++)
			{
				out.write(Boolean.toString(GmudWorld.game.newbool[i]) + '\n');
			}
			
			out.write(Long.toString(BasicScreen.chksum));
			
//			out.flush();
			
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			try{
				if(out != null)
					out.close();
			}catch (IOException e) {
				e.printStackTrace();
			}
			Log.w("Saving","保存成功：" + f.externalStoragePath + "gmudp12548");
		}
	}

	/* （非 Javadoc）
	 * @see lostland.gumd.platinum12548.ui.core.DialogScreen#update(float)
	 */
	@Override
	public void update(float deltaTime) {
		if(b)
		{
			b=false;
			save();
			GmudWorld.mc.maxhp = GmudWorld.mc.getMaxhp();
		}
		super.update(deltaTime);
	}
	
	
}
