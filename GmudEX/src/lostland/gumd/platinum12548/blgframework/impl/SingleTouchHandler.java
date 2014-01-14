/**
 * 安卓白金英雄坛制作组 <p>
 * 文件名：SingleTouchHandler.java <p>
 * 创建时间：2013-5-19 下午8:26:32 <p>
 * 所属项目：GmudTest <p>
 * @author 12548 <p>
 */
package lostland.gumd.platinum12548.blgframework.impl;

import java.util.ArrayList;
import java.util.List;

import com.feiwoone.coverscreen.CoverAdComponent;

import lostland.gumd.platinum12548.GmudWorld;
import lostland.gumd.platinum12548.SavingScreen;
import lostland.gumd.platinum12548.blgframework.Pool;
import lostland.gumd.platinum12548.blgframework.IInput.TouchEvent;
import lostland.gumd.platinum12548.blgframework.Pool.PoolObjectFactory;
import lostland.gumd.platinum12548.ui.NpcMenuScreen;
import lostland.gumd.platinum12548.ui.meta.AdsDialog;
import lostland.gumd.platinum12548.ui.meta.CreatingDialog;
import lostland.gumd.platinum12548.ui.meta.ItemDialog;
import lostland.gumd.platinum12548.ui.meta.SellingDialog;
import lostland.gumd.platinum12548.ui.meta.SettingDialog;
import lostland.gumd.platinum12548.ui.meta.SkillDialog;
import lostland.gumd.platinum12548.ui.meta.TradeDialog;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Handler;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;


/**
 * 类名：SingleTouchHandler <p>
 * 说明：
 * @author 12548
 */
public class SingleTouchHandler implements TouchHandler {
	boolean isTouched;
	public static int touchX;
	public static int touchY;
	public static int t2x,t2y;
	
	
	public static int flag = 0;
	
	public static int oriX,oriY;
	
	
	
	
	Pool<TouchEvent> touchEventPool;
	List<TouchEvent> touchEvents = new ArrayList<TouchEvent>();
	List<TouchEvent> touchEventsBuffer = new ArrayList<TouchEvent>();
	float scaleX;
	float scaleY;
	float s2x,s2y;
	
	public SingleTouchHandler(View view, float scaleX,float scaleY,float sc2x,float sc2y)
	{
		GmudWorld.sth = this;
		PoolObjectFactory<TouchEvent> factory= new PoolObjectFactory<TouchEvent>()
				{
					@Override
					public TouchEvent createObject() {
						return new TouchEvent();
					}
				};
		touchEventPool=new Pool<TouchEvent>(factory, 100);
		
		view.setOnTouchListener(this);
		//view.setOnTouchListener(new GmudTouchHandler());
		
		this.scaleX = scaleX;
		this.scaleY = scaleY;
		
		this.s2x = sc2x;
		this.s2y = sc2y;
	}

	/* （非 Javadoc）
	 * @see android.view.View.OnTouchListener#onTouch(android.view.View, android.view.MotionEvent)
	 */
	@Override
	public boolean onTouch(View v, MotionEvent event) {
		synchronized(this)
		{
			TouchEvent touchEvent = touchEventPool.newObject();
			switch(event.getAction())
			{
			case MotionEvent.ACTION_DOWN:
				touchEvent.type=TouchEvent.TOUCH_DOWN;
				isTouched=true;
				break;
			case MotionEvent.ACTION_MOVE:
				touchEvent.type=TouchEvent.TOUCH_DRAGGED;
				isTouched=true;
				break;
			case MotionEvent.ACTION_CANCEL:
			case MotionEvent.ACTION_UP:
				touchEvent.type= TouchEvent.TOUCH_UP;
				isTouched=false;
				break;
			}
			
			oriX = (int) event.getX();
			oriY = (int) event.getY();
			
			touchEvent.x = (int)(event.getX()*scaleX * BLGFastRenderView.multiplier);
			touchEvent.y = (int)(event.getY()*scaleY * BLGFastRenderView.multiplier);
			touchX          = (int) (touchEvent.x);
			touchY          = (int) (touchEvent.y);
			
			
			t2x = (int)(oriX*BLGGame.s2x);
			t2y = (int)(oriY*BLGGame.s2y);
			
			touchEventsBuffer.add(touchEvent);
			if(flag > 0)
			{
				
				switch(flag)
				{
				case 1:
					new AdsDialog(GmudWorld.game).show();
					break;
				case 2:
					new TradeDialog(GmudWorld.game).show(NpcMenuScreen.npcid);
					break;
				case 3:
					new SettingDialog(GmudWorld.game).show();
					break;
				case 4:
					new CreatingDialog(GmudWorld.game).show();
					break;
				case 5:
					new SkillDialog(GmudWorld.game).show();
					break;
				case 6:
					new ItemDialog(GmudWorld.game,true).show();
					break;
				case 7:
					new ItemDialog(GmudWorld.game, false).show();
					break;
				case 8:
					new SellingDialog(GmudWorld.game,true).show();
					break;
				case 9:
					GmudWorld.game.oo();
					break;
				case 10:
		    		new AlertDialog.Builder(GmudWorld.game)
		    		.setTitle("白金英雄坛说")
		    		.setMessage("你确定要退出游戏吗？")
		    		.setPositiveButton("是", new android.content.DialogInterface.OnClickListener(){

		    			@Override
		    			public void onClick(DialogInterface arg0, int arg1) {
		    				GmudWorld.game.oo();
		    			}

		    		})
		    		.setNegativeButton("否", new DialogInterface.OnClickListener(){

		    			@Override
		    			public void onClick(DialogInterface dialog, int which) {
		    				
		    			}
		    			
		    		}).show();
					break;
				case 11:
					new Handler().postDelayed(new Runnable() {
						@Override
						public void run() {
							CoverAdComponent.showAd(GmudWorld.game.getApplicationContext());
						}
					}, 1000);
					break;
				case 999:
		    		new AlertDialog.Builder(GmudWorld.game)
		    		.setTitle("白金英雄坛说")
		    		.setMessage("检测到作弊！")
		    		.setPositiveButton("确定", new android.content.DialogInterface.OnClickListener(){

		    			@Override
		    			public void onClick(DialogInterface arg0, int arg1) {
		    				new SavingScreen(GmudWorld.game).save();
		    				GmudWorld.game.oo();
		    			}

		    			
		    		}).show();
					break;
				case 1000:
					new AlertDialog.Builder(GmudWorld.game)
		    		.setTitle("白金英雄坛说")
		    		.setMessage("检测到存档出错！")
		    		.setPositiveButton("确定", new android.content.DialogInterface.OnClickListener(){

		    			@Override
		    			public void onClick(DialogInterface arg0, int arg1) {
		    				SharedPreferences sp = GmudWorld.game.getFileIO().getPreferences();
		    				while(!sp.edit().putBoolean("newgame", true).commit())
		    					;
		    				GmudWorld.game.oo();
		    			}

		    			
		    		}).show();
				}
				if(flag<999)flag = 0;
			}
			
			
			return true;
		}
	}

	/* （非 Javadoc）
	 * @see lostland.gumd.platinum12548.blgframework.impl.TouchHandler#isTouchDown(int)
	 */
	@Override
	public boolean isTouchDown(int pointer) {
		synchronized(this)
		{
			if(pointer==0)
				return isTouched;
			else
				return false;
		}
	}

	/* （非 Javadoc）
	 * @see lostland.gumd.platinum12548.blgframework.impl.TouchHandler#getTouchX(int)
	 */
	@Override
	public int getTouchX(int pointer) {
		synchronized(this)
		{
			return touchX;
		}
	}

	/* （非 Javadoc）
	 * @see lostland.gumd.platinum12548.blgframework.impl.TouchHandler#getTouchY(int)
	 */
	@Override
	public int getTouchY(int pointer) {
		synchronized(this)
		{
			return touchY;
		}
	}

	/* （非 Javadoc）
	 * @see lostland.gumd.platinum12548.blgframework.impl.TouchHandler#getTouchEvents()
	 */
	@Override
	public List<TouchEvent> getTouchEvents() {
		synchronized(this)
		{
			int len=touchEvents.size();
			for(int i=0;i<len;i++)
				touchEventPool.free(touchEvents.get(i));
			touchEvents.clear();
			touchEvents.addAll(touchEventsBuffer);
			touchEventsBuffer.clear();
			return touchEvents;
		}
	}
}
