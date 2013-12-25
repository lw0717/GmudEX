/**
 * 安卓白金英雄坛制作组 <p>
 * 文件名：BasicScreen.java <p>
 * 创建时间：2013-9-7 下午1:06:15 <p>
 * 所属项目：GmudTest <p>
 * @author 12548 <p>
 */
package lostland.gumd.platinum12548.blgframework;

import java.util.zip.Adler32;

import android.util.Log;

import lostland.gumd.platinum12548.GmudWorld;

/**
 * 类名：BasicScreen <p>
 * 说明：
 * @author 12548
 */
public abstract class BasicScreen {

	protected IGame game;

	
	public static boolean b = false;
	public static boolean acr = true;
	private static Adler32 chk = new Adler32();

//	private static Adler32 smallchk = new Adler32();

	protected static float gameTime=0,yearTime=0,hungerTime=0;

	/**
	 * @return gameTime
	 */
	public static float getGameTime() {
		return gameTime;
	}

	/**
	 * @param gameTime 要设置的 gameTime
	 */
	public static void timeFlow(float time) {
		
		float time_multiplier = 0.4f;
		
		gameTime += time * time_multiplier;
		yearTime += time * time_multiplier;
		hungerTime += time * time_multiplier;
		
	}

	volatile public static long chksum = 0;

	public static long smallchksum = 0;

	public BasicScreen(IGame game) {
		this.game = game;
		sss = true;
		stime = 0f;
	}

	public abstract void update(float deltaTime);
	
	protected float stime = 0f;
	protected boolean sss = true;
	public void render(float deltaTime)
	{
		if(b){
			timeFlow(deltaTime);
//			if(!mac.isRunning())
//				mac.start();
//			if(smallchksum != getsmallchksum())
//			{
//				GmudWorld.mc.exp = 0;
//				GmudWorld.mc.potential = 0;
//				smallrecheck();
//			}
		}
//		mac.render();
		
		
		stime+=deltaTime;
		
		if(stime > 0.00001f && sss && isStable())
		{
			recheck();
			Log.w("Rechecked", this.toString());
			sss = false;
		}
		
		
		
		
		update(deltaTime);
		
		
		
		
	}


	

	public static void recheck()
	{
		chksum = getchksum();
		Log.w("Rechecked", chksum+"");
		Log.w("Rechecked", GmudWorld.game.getCurrentScreen().toString());
	}


	public static long getchksum()
	{
		chk.reset();

		chk.update(12548);

		chk.update(GmudWorld.mc.maxhp);
		chk.update(GmudWorld.mc.maxfp);

		chk.update(GmudWorld.mc.hp);
		chk.update(GmudWorld.mc.sp);
		chk.update(GmudWorld.mc.fp);

		chk.update(GmudWorld.mc.str);
		chk.update(GmudWorld.mc.agi);
		chk.update(GmudWorld.mc.wxg);
		chk.update(GmudWorld.mc.bon);

		chk.update(GmudWorld.mc.gold);

		chk.update(12548);
		
		for(int i : GmudWorld.mc.skills)
			chk.update(i);

		for(int i : GmudWorld.mc.inventory)
			chk.update(i);

		for(int i = 0;i<GmudWorld.npc.length;i++)
		{
			chk.update(Boolean.toString(GmudWorld.npc[i].qingjiaoable).getBytes());
			chk.update(Boolean.toString(GmudWorld.npc[i].dead).getBytes());
		}

		chk.update(GmudWorld.mc.hit);
		chk.update(GmudWorld.mc.evd);
		chk.update(GmudWorld.mc.atk);
		chk.update(GmudWorld.mc.def);

		chk.update(GmudWorld.mc.exp);
		chk.update(GmudWorld.mc.potential);

		chk.update(32768);

		for(int i : GmudWorld.game.newint)
			chk.update(i);
		
		for(boolean i : GmudWorld.game.newbool)
			chk.update(Boolean.toString(i).getBytes());

		return chk.getValue();
	}

	public static boolean check()
	{
		if(!acr)
		{
			recheck();
			return true;
		}
		long a = getchksum();
		Log.w("checking",""+a+"vs"+chksum);
		return chksum == getchksum();
	}

	
	public abstract boolean isStable();
	
	
	
}