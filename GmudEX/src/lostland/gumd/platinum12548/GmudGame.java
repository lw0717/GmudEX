/**
 * 安卓白金英雄坛制作组 <p>
 * 文件名：GmudGame.java <p>
 * 创建时间：2013-5-20 下午4:21:44 <p>
 * 所属项目：GmudTest <p>
 * @author 12548 <p>
 */
package lostland.gumd.platinum12548;

import java.util.Timer;
import java.util.TimerTask;

import lostland.gmud.platinum12548.R;
import lostland.gumd.platinum12548.blgframework.CScreen;
import lostland.gumd.platinum12548.blgframework.impl.BLGGame;
import lostland.gumd.platinum12548.data.Skill;
import lostland.gumd.platinum12548.ui.core.ButtonControlledScreen;
import lostland.gumd.platinum12548.ui.core.DialogScreen;
import lostland.gumd.platinum12548.ui.core.NewButton;
import android.app.AlertDialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import android.widget.Toast;

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

	SittingThread ss;

	public int newint[];
	public boolean newbool[];

	static volatile boolean b = false;
	
	private static Handler sittingHandler = new Handler(){
		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);
			switch (msg.what) {
			case 0:
				GmudWorld.game.notif.contentView.setTextViewText(R.id.content_view_text1, "打坐中："+GmudWorld.game.len+"/"+(GmudWorld.game.max/2));
				GmudWorld.game.notif.contentView.setProgressBar(R.id.content_view_progress, GmudWorld.game.max, GmudWorld.game.len, false);
				GmudWorld.game.manager.notify(0, GmudWorld.game.notif);

				break;
			case 1:
				Toast.makeText(GmudWorld.game, "打坐完成", Toast.LENGTH_SHORT).show();
				
				break;
			default:
				
				break;
			}
		}

	};




	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		GmudWorld.game = this;
		//		GmudWorld.tmd=new TopMenuDialog(this);
		//		GmudWorld.sd=new StatusDialog(this);
		//		GmudWorld.bd=new BottomDialog(this);
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
		//		GmudWorld.tmd.dismiss();
		//		GmudWorld.bd.dismiss();
		//		GmudWorld.sd.dismiss();

		super.onDestroy();
		//		android.os.Process.killProcess(android.os.Process.myPid());
	}



	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event)
	{
		CScreen cs = getCurrentScreen();

		if(keyCode==KeyEvent.KEYCODE_BACK)
			if(cs instanceof MapScreen)
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
//			else if(cs instanceof SittingScreen)
//			{
//				
//				moveTaskToBack(false);
//				setScreen(new BackgroundingScreen(this,GmudWorld.ms));
//				max = SittingScreen.s_max;
//				len = SittingScreen.s_now;
//
//				b = false;
//				// TODO Auto-generated method stub
//				//点击通知栏后打开的activity
//				Intent intent = new Intent(GmudGame.this,GmudGame.class);
//
//				PendingIntent pIntent = PendingIntent.getActivity(GmudGame.this, 0, intent, 0);
//				manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
//				notif = new Notification();
//				notif.icon = R.drawable.ic_launcher;
//				notif.tickerText = "打坐中";
//				//通知栏显示所用到的布局文件
//				notif.contentView = new RemoteViews(getPackageName(), R.layout.notif);
//				notif.contentIntent = pIntent;
//				manager.notify(0, notif);
//				ss = new SittingThread();
//				ss.start();
//				
//				return true;
//			}
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


	private class SittingThread extends Thread{
		public Timer timer = new Timer();
		@Override
		public void run() {
			// TODO Auto-generated method stub
			super.run();
			timer.schedule(new TimerTask() {
				@Override
				public void run() {
					// TODO Auto-generated method stub

					Message msg = new Message();
					msg.what = 0;
					msg.obj = len;
					sittingHandler.sendMessage(msg);

					if(len < max){
						len+=GmudWorld.mc.getBon()/5+GmudWorld.mc.skills[Skill.KIND_NEIGONG]/20 +GmudWorld.mc.skillsckd[3]/10;
						GmudWorld.mc.fp = len;
					}
					else
					{
						len = 0;
						max+=2;
						GmudWorld.mc.maxfp = max/2;
					}
					
					if(max/2 >= GmudWorld.mc.getMaxmaxfp()){
						timer.cancel();
						sittingHandler.sendEmptyMessage(1);
					}
					
					if(b)
					{
						timer.cancel();
						sittingHandler.sendEmptyMessage(2);
					}

				}
			}, 0, 1400);
			
		
		}

	}


	/* （非 Javadoc）
	 * @see lostland.gumd.platinum12548.blgframework.impl.BLGGame#onResume()
	 */
	@Override
	public void onResume() {
		super.onResume();
		b = true;
	}


}
