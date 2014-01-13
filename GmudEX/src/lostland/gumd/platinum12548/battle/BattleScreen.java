/**
 * 安卓白金英雄坛制作组 <p>
 * 文件名：BattleScreen.java <p>
 * 创建时间：2013-7-30 下午4:06:28 <p>
 * 所属项目：GmudTest <p>
 * @author 12548 <p>
 */
package lostland.gumd.platinum12548.battle;

import android.util.Log;
import lostland.gumd.platinum12548.Assets;
import lostland.gumd.platinum12548.FontSize;
import lostland.gumd.platinum12548.GameConstants;
import lostland.gumd.platinum12548.GmudWorld;
import lostland.gumd.platinum12548.battle.proc.BattleStart;
import lostland.gumd.platinum12548.battle.proc.FreeStatus;
import lostland.gumd.platinum12548.battle.proc.PreAttackStatus;
import lostland.gumd.platinum12548.battle.proc.Status;
import lostland.gumd.platinum12548.blgframework.CScreen;
import lostland.gumd.platinum12548.blgframework.IGame;
import lostland.gumd.platinum12548.blgframework.impl.BLGGraphics;
import lostland.gumd.platinum12548.blgframework.impl.BLGPixmap;
import lostland.gumd.platinum12548.data.Gesture;
import lostland.gumd.platinum12548.data.Npc;

/**
 * 类名：BattleScreen
 * <p>
 * 说明：
 * 
 * @author 12548
 */
public class BattleScreen extends CScreen {

	public int enemyid = 0;
	public boolean eob = false, fob = true;

	public Status st;

	int a = 0, b = 1;

	public FreeStatus fs;

	public Npc zdp = new Npc(), bdp = new Npc();

	/**
	 * @param game
	 */
	public BattleScreen(IGame game) {
		super(game);
		st = new BattleStart();
	}

	/*
	 * （非 Javadoc）
	 * 
	 * @see lostland.gumd.platinum12548.blgframework.CScreen#update(float)
	 */
	@Override
	public void update(float deltaTime) {
		if (eob) {
			BattleOverScreen.zdflag = false;
			game.setScreen(new BattleOverScreen(game));
			return;
		}

		st.execute();

	}

	/*
	 * （非 Javadoc）
	 * 
	 * @see lostland.gumd.platinum12548.blgframework.CScreen#present(float)
	 */
	@Override
	public void present(float deltaTime) {

		final int X1 = 25;
		final int Y1 = 66;
		final int DX = 23;

		final int X2 = 208;

		int i, j;

		
		BLGPixmap pixmap = (BLGPixmap) Assets.maincharTile;
		if(GmudWorld.mc.sex > 0) pixmap = (BLGPixmap) Assets.girl;
		
		BLGGraphics g = (BLGGraphics) game.getGraphics();
		g.clear(GameConstants.BG_COLOR);
		g.drawPixmap(pixmap, 64, 32, 0, 0, 32, 32);
		g.drawPixmap(Assets.vs, 150, 44);
		GmudWorld.npcc.drawBiased(7, 1, 0, 0, enemyid);

		g.drawPixmap(Assets.hpfp, 25, 64);

		for (i = X1 + DX; i <= X1 + DX
				+ (64 * GmudWorld.mc.hp / GmudWorld.mc.maxhp); i++)
			g.drawPixel(i, Y1 + 13, 0);
		for (i = X1 + DX; i <= X1 + DX
				+ (64 * GmudWorld.mc.sp / GmudWorld.mc.maxhp); i++) {
			for (j = Y1; j <= Y1 + 10; j++)
				g.drawPixel(i, j, 0);
		}
		g.drawText(GmudWorld.mc.sp + "/" + GmudWorld.mc.hp, 130, Y1 - 1, FontSize.FT_12PX);

		if (GmudWorld.mc.maxfp > 0) {
			for (i = X1 + DX; i <= X1 + DX
					+ (64 * (GmudWorld.mc.fp < GmudWorld.mc.maxfp ? GmudWorld.mc.fp
							: GmudWorld.mc.maxfp) / GmudWorld.mc.maxfp); i++)
				for (j = Y1 + 16; j <= Y1 + 26; j++)
					g.drawPixel(i, j, 0);
			for (i = X1 + DX; i <= X1 + DX + 64; i++)
				g.drawPixel(i, Y1 + 29, 0);

			g.drawText(GmudWorld.mc.fp + "/" + GmudWorld.mc.maxfp, 130, Y1 + 16, FontSize.FT_12PX);
		} else
			g.drawText("0", 130, Y1 + 16, FontSize.FT_12PX);

		for (i = X2; i <= X2
				+ (64 * GmudWorld.npc[enemyid].hp / GmudWorld.npc[enemyid].maxhp); i++)
			g.drawPixel(i, Y1 + 13, 0);
		for (i = X2; i <= X2
				+ (64 * GmudWorld.npc[enemyid].sp / GmudWorld.npc[enemyid].maxhp); i++) {
			for (j = Y1; j <= Y1 + 10; j++)
				g.drawPixel(i, j, 0);
		}

		if (GmudWorld.npc[enemyid].maxfp > 0) {
			for (i = X2; i <= X2
					+ (64 * (GmudWorld.npc[enemyid].fp < GmudWorld.npc[enemyid].maxfp ? GmudWorld.npc[enemyid].fp
							: GmudWorld.npc[enemyid].maxfp) / GmudWorld.npc[enemyid].maxfp); i++)
				for (j = Y1 + 16; j <= Y1 + 26; j++)
					g.drawPixel(i, j, 0);
			for (i = X2; i <= X2 + 64; i++)
				g.drawPixel(i, Y1 + 29, 0);
		}

	}

	public void setStatus(Status s) {
		Log.i("设置状态：", s.toString());
		this.st = s;
	}

	public void swapp() {
		Npc t;
		t = zdp;
		zdp = bdp;
		bdp = t;

		Log.i("交换战斗者：", "主动：" + zdp.name + "被动：" + bdp.name);
	}

	/*
	 * （非 Javadoc）
	 * 
	 * @see lostland.gumd.platinum12548.blgframework.CScreen#pause()
	 */
	@Override
	public void pause() {
		// TODO 自动生成的方法存根

	}

	/*
	 * （非 Javadoc）
	 * 
	 * @see lostland.gumd.platinum12548.blgframework.CScreen#resume()
	 */
	@Override
	public void resume() {
		// TODO 自动生成的方法存根

	}

	/*
	 * （非 Javadoc）
	 * 
	 * @see lostland.gumd.platinum12548.blgframework.CScreen#dispose()
	 */
	@Override
	public void dispose() {
		// TODO 自动生成的方法存根

	}

	public String bsp(String s) {
		String t = new String(s);
		t = t.replace("$N", zdp.name);
		t = t.replace("$n", bdp.name);
		t = t.replace("$w", GmudWorld.wp[zdp.itemsckd[0]].name);
		t = t.replace("$W", GmudWorld.wp[bdp.itemsckd[0]].name);
		String p[] = { "胸前", "后背", "左臂", "右臂", "左手", "右手", "左脚", "右脚", "头部",
				"小腹", "左肩", "右肩", "裆部", "臀部" };
		int i = (int) (Math.random() * p.length);
		t = t.replace("$1", p[i]);
		return t;
	}

	public void xiqiprocess()
	{
		zdp.xiqi();
		ViewScreen.setText(zdp.name + "深深吸了口气，脸色看起来好多了。");
		game.setScreen(new ViewScreen(game));
	}
	
	public void atkprocess(Gesture ges,Status controller)
	{
		setStatus(new PreAttackStatus(ges,controller));
	}
	
	public void atkprocess(Gesture ges,Status controller, String prefix)
	{
		setStatus(new PreAttackStatus(ges,controller,prefix));
	}
	
}
