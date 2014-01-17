/**
 * ��׿�׽�Ӣ��̳������ <p>
 * �ļ�����GmudGame.java <p>
 * ����ʱ�䣺2013-5-20 ����4:21:44 <p>
 * ������Ŀ��GmudTest <p>
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
 * ������GmudGame <p>
 * ˵������Ϸ���
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


	/* ���� Javadoc��
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
					.setTitle("���沢�˳�")
					.setMessage("��ȷ��Ҫ���沢�˳���Ϸ������㲻�뱣�棬��ʹ�����˵����˳���ť�˳���")
					.setPositiveButton("��", new android.content.DialogInterface.OnClickListener(){

						@Override
						public void onClick(DialogInterface arg0, int arg1) {
							new SavingScreen(GmudWorld.game).save();
							oo();
						}

					})
					.setNegativeButton("��", new DialogInterface.OnClickListener(){

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



	/* ���� Javadoc��
	 * @see lostland.gumd.platinum12548.blgframework.impl.BLGGame#onResume()
	 */
	@Override
	public void onResume() {
		super.onResume();
	}



}
