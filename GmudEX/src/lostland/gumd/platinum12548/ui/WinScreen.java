/**
 * 安卓白金英雄坛制作组 <p>
 * 文件名：LookScreen.java <p>
 * 创建时间：2013-8-24 下午10:42:26 <p>
 * 所属项目：GmudTest <p>
 * @author 12548 <p>
 */
package lostland.gumd.platinum12548.ui;

import lostland.gumd.platinum12548.FontSize;
import lostland.gumd.platinum12548.GmudGame;
import lostland.gumd.platinum12548.GmudWorld;
import lostland.gumd.platinum12548.blgframework.IGame;
import lostland.gumd.platinum12548.blgframework.impl.BLGGraphics;
import lostland.gumd.platinum12548.blgframework.impl.SingleTouchHandler;
import lostland.gumd.platinum12548.ui.core.DialogScreen;
import android.os.Handler;
import android.util.Log;
import com.feiwoone.coverscreen.CoverAdComponent;

/**
 * 类名：WinScreen <p>
 * 说明：
 * @author 12548
 */
public class WinScreen extends DialogScreen {

	String s;
	BLGGraphics g = (BLGGraphics) game.getGraphics();
	
	final int stonenpc[] = {47,58,80,94,101,110};
	
	/**
	 * @param game
	 */
	public WinScreen(IGame game,int enemyid) {
		super(game);
		this.border = new StatusBorder((GmudGame) game);
		s = "大获全胜！\n你获得了：\n金钱："+GmudWorld.npc[enemyid].gold+"\n物品：";
		GmudWorld.mc.gold += GmudWorld.npc[enemyid].gold;
		for(int itm : GmudWorld.npc[enemyid].itemsckd)
		{
			if(itm>0){
				GmudWorld.mc.give(itm);
				s+= GmudWorld.wp[itm].name + " ";
			}
		}
		
		for(int i = 0; i < 6; i++)
		{
			if(((GmudGame)game).hasstone[i] && enemyid == stonenpc[i])
			{
				((GmudGame)game).hasstone[i] = false;
				GmudWorld.mc.give(77);
				s+=" 三角石板";
			}
		}
		if(enemyid == 23)
		{
			GmudWorld.mc.give(78);
			s+=" 女儿红";
		}
		else if(enemyid == 54)
		{
			GmudWorld.mc.give(10);
			s+=" 海外仙丹";
		}else if(enemyid == 27)
		{
			GmudWorld.mc.give(72);
			s+=" 毛笔";
		}else if(enemyid == 50)
		{
			GmudWorld.mc.give(69);
			s+=" 焦黄纸页";
		}else if(enemyid == 123)
		{
			GmudWorld.mc.give(67);
			s+=" 豹皮";
		}
		
		
	}

	/* （非 Javadoc）
	 * @see lostland.gumd.platinum12548.ui.DialogScreen#onTouchDown(int, int)
	 */
	@Override
	protected void onTouchDown(int tx, int ty) {
		// TODO 自动生成的方法存根

	}

	/* （非 Javadoc）
	 * @see lostland.gumd.platinum12548.ui.DialogScreen#onTouchMove(int, int)
	 */
	@Override
	protected void onTouchMove(int tx, int ty) {
		// TODO 自动生成的方法存根

	}

	/* （非 Javadoc）
	 * @see lostland.gumd.platinum12548.ui.DialogScreen#onTouchUp(int, int)
	 */
	@Override
	protected void onTouchUp(int tx, int ty) { 
		// TODO 自动生成的方法存根

	}

	/* （非 Javadoc）
	 * @see lostland.gumd.platinum12548.ui.DialogScreen#onClick(int, int)
	 */
	@Override
	protected void onClick(int tx, int ty) {
		SingleTouchHandler.flag = 11;
		game.setScreen(GmudWorld.ms);
	}

	/* （非 Javadoc）
	 * @see lostland.gumd.platinum12548.ui.DialogScreen#onCancel()
	 */
	@Override
	public void onCancel() {
		SingleTouchHandler.flag = 11;
		game.setScreen(GmudWorld.ms);
	}

	/* （非 Javadoc）
	 * @see lostland.gumd.platinum12548.blgframework.CScreen#present(float)
	 */
	@Override
	public void present(float deltaTime) {
		border.draw();
		g.drawText(s, border.x+2, border.y+2, FontSize.FT_12PX, 180);
	}

	/* （非 Javadoc）
	 * @see lostland.gumd.platinum12548.blgframework.CScreen#pause()
	 */
	@Override
	public void pause() {
		// TODO 自动生成的方法存根

	}

	/* （非 Javadoc）
	 * @see lostland.gumd.platinum12548.blgframework.CScreen#resume()
	 */
	@Override
	public void resume() {
		// TODO 自动生成的方法存根

	}

	/* （非 Javadoc）
	 * @see lostland.gumd.platinum12548.blgframework.CScreen#dispose()
	 */
	@Override
	public void dispose() {
		// TODO 自动生成的方法存根

	}

	/* （非 Javadoc）
	 * @see lostland.gumd.platinum12548.ui.core.DialogScreen#isStable()
	 */
	@Override
	public boolean isStable() {
		return false;
	}

}
