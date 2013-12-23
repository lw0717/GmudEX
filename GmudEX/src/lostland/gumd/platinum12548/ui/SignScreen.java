/**
 * 安卓白金英雄坛制作组 <p>
 * 文件名：SignScreen.java <p>
 * 创建时间：2013-7-26 下午10:28:49 <p>
 * 所属项目：GmudTest <p>
 * @author 12548 <p>
 */
package lostland.gumd.platinum12548.ui;

import lostland.gumd.platinum12548.GmudGame;
import lostland.gumd.platinum12548.GmudWorld;
import lostland.gumd.platinum12548.blgframework.IGame;
import lostland.gumd.platinum12548.ui.core.DialogScreen;

/**
 * 类名：SignScreen <p>
 * 说明：衙门牌子
 * @author 12548
 */
public class SignScreen extends DialogScreen {

	/**
	 * @param game
	 */
	public SignScreen(IGame game) {
		super(game);
		String text;
		
		if(GmudWorld.mc.fame < 128)
			text = "悬赏通缉嫌犯"+GmudWorld.mc.name;
		else if(!GmudWorld.game.hunting)
			text = "本镇治安良好";
		else
			text = "悬赏通缉嫌犯"+GmudWorld.npc[GmudWorld.npc.length-1].name;
		
		this.border = new BottomWindow((GmudGame) game,text);
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
		// TODO 自动生成的方法存根

	}

	/* （非 Javadoc）
	 * @see lostland.gumd.platinum12548.ui.DialogScreen#onCancel()
	 */
	@Override
	public void onCancel() {
		game.setScreen(GmudWorld.ms);
	}

	/* （非 Javadoc）
	 * @see lostland.gumd.platinum12548.blgframework.CScreen#present(float)
	 */
	@Override
	public void present(float deltaTime) {
		border.draw();
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

}
