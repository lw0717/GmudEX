/**
 * 安卓白金英雄坛制作组 <p>
 * 文件名：GmudGame.java <p>
 * 创建时间：2013-5-20 下午4:21:44 <p>
 * 所属项目：GmudTest <p>
 * @author 12548 <p>
 */
package lostland.gumd.platinum12548;


import com.feiwotwo.coverscreen.CoverAdComponent;

import lostland.gumd.platinum12548.blgframework.CScreen;
import lostland.gumd.platinum12548.blgframework.impl.BLGGame;
import lostland.gumd.platinum12548.ui.core.ButtonControlledScreen;
import lostland.gumd.platinum12548.ui.core.DialogScreen;
import lostland.gumd.platinum12548.ui.core.NewButton;
import android.app.AlertDialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.KeyEvent;

/**
 * 类名：GmudGame <p>
 * 说明：游戏主活动
 * @author 12548
 */
public class GmudGame extends BLGGame {

	public float nextBadman = 0;
	public boolean hunting = false;
	public boolean hasstone[] = {true,true,true,true,true,true};

	public int len;
	public NotificationManager manager;
	public Notification notif;

	public int max;

	public int newint[];
	public boolean newbool[];

	public static boolean classickeymap = false;
	public static boolean backkeyquit = true;

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		CoverAdComponent.init(getApplicationContext(), "n63D4CejIND4Mv1wj8Qo1M7k");
		GmudWorld.game = this;
	}


	/* （非 Javadoc）
	 * @see lostland.gumd.platinum12548.blgframework.IGame#getStartScreen()
	 */
	@Override
	public CScreen getStartScreen() {
		return new LoadingScreen(this);
	}

	@Override
	public void onDestroy()
	{
		super.onDestroy();
		CoverAdComponent.destory(this);
		android.os.Process.killProcess(android.os.Process.myPid());
	}



	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event)
	{
		CScreen cs = getCurrentScreen();

		if(keyCode==KeyEvent.KEYCODE_BACK)
			if(cs instanceof MapScreen)
				if(backkeyquit)
				{
					new AlertDialog.Builder(this)
					.setTitle("保存并退出")
					.setMessage("你确定要保存并退出游戏吗？如果你不想保存，请使用主菜单的退出按钮退出。")
					.setPositiveButton("是", new android.content.DialogInterface.OnClickListener(){

						@Override
						public void onClick(DialogInterface arg0, int arg1) {
							new SavingScreen(GmudWorld.game).save();
							oo();
						}

					})
					.setNegativeButton("否", new DialogInterface.OnClickListener(){

						@Override
						public void onClick(DialogInterface dialog, int which) {

						}

					}).show();
				}
				else
					setScreen(GmudWorld.mms);
			else if(cs instanceof ButtonControlledScreen)
			{
				((ButtonControlledScreen)cs).onButtonClick(NewButton.NB_BACK);
			}
			else if(cs instanceof DialogScreen)
			{
				((DialogScreen)cs).onCancel();
			}


		if(keyCode == KeyEvent.KEYCODE_MENU)
		{
			if(cs instanceof MapScreen)
			{
				setScreen(GmudWorld.mms);
			}
		}
		return false;
	}



	/* （非 Javadoc）
	 * @see lostland.gumd.platinum12548.blgframework.impl.BLGGame#onResume()
	 */
	@Override
	public void onResume() {
		super.onResume();
	}



}
