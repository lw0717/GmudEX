/**
 * 安卓白金英雄坛制作组 <p>
 * 文件名：FullScreen.java <p>
 * 创建时间：2013-8-29 下午11:28:28 <p>
 * 所属项目：GmudTest <p>
 * @author 12548 <p>
 */
package lostland.gumd.platinum12548.ui.core;

import android.util.Log;
import lostland.gumd.platinum12548.GmudGame;
import lostland.gumd.platinum12548.battle.DummyWindow;
import lostland.gumd.platinum12548.blgframework.IGame;

/**
 * 类名：FullScreen <p>
 * 说明：去掉了onCancel的DialogScreen。。。。。。
 * @author 12548
 */
public abstract class FullScreen extends DialogScreen {

	
	
	/**
	 * @param game
	 */
	public FullScreen(IGame game) {
		super(game);
		Log.i("FS", "C0");
		this.border = new DummyWindow((GmudGame) game);
		Log.i("FS", "C1");
	}

	/* （非 Javadoc）
	 * @see lostland.gumd.platinum12548.ui.core.DialogScreen#onCancel()
	 */
	@Override
	public void onCancel() {
		// TODO 自动生成的方法存根
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
