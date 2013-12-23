/**
 * 安卓白金英雄坛制作组 <p>
 * 文件名：KeyboardHandler.java <p>
 * 创建时间：2013-5-19 下午7:58:10 <p>
 * 所属项目：GmudTest <p>
 * @author 12548 <p>
 */
package lostland.gumd.platinum12548.blgframework.impl;

import java.util.ArrayList;
import java.util.List;

import lostland.gumd.platinum12548.blgframework.Pool;
import lostland.gumd.platinum12548.blgframework.IInput.KeyEvent;
import lostland.gumd.platinum12548.blgframework.Pool.PoolObjectFactory;

import android.view.View;
import android.view.View.OnKeyListener;


/**
 * 类名：KeyboardHandler <p>
 * 说明：
 * @author 12548
 */
public class KeyboardHandler implements OnKeyListener {
	boolean[] pressedKeys=new boolean[128];
	Pool<KeyEvent> keyEventPool;
	List<KeyEvent> keyEventsBuffer = new ArrayList<KeyEvent>();
	List<KeyEvent> keyEvents = new ArrayList<KeyEvent>();
	
	public KeyboardHandler(View view)
	{
		PoolObjectFactory<KeyEvent> factory = new PoolObjectFactory<KeyEvent>()
				{
					@Override
					public KeyEvent createObject() {
						return new KeyEvent();
					}
				};
		keyEventPool = new Pool<KeyEvent>(factory, 100);
		view.setOnKeyListener(this);
		view.setFocusableInTouchMode(true);
		view.requestFocus();
	}

	/* （非 Javadoc）
	 * @see android.view.View.OnKeyListener#onKey(android.view.View, int, android.view.KeyEvent)
	 */
	@Override
	public boolean onKey(View v, int keyCode, android.view.KeyEvent event) {
		if (event.getAction() == android.view.KeyEvent.ACTION_MULTIPLE)
			return false;
		
		
		synchronized (this)
		{
			KeyEvent keyEvent = keyEventPool.newObject();
			keyEvent.keyCode=keyCode;
			keyEvent.keyChar=(char) event.getUnicodeChar();
			if(event.getAction() == android.view.KeyEvent.ACTION_DOWN)
			{
				keyEvent.type = KeyEvent.KEY_DOWN;
				if(keyCode>0 && keyCode<127)
					pressedKeys[keyCode]=true;
			}
			if(event.getAction() == android.view.KeyEvent.ACTION_UP)
			{
				keyEvent.type = KeyEvent.KEY_UP;
				if(keyCode>0 && keyCode<127)
					pressedKeys[keyCode]=false;
			}
			keyEventsBuffer.add(keyEvent);
		}
		return false;
	}
	
	public boolean isKeyPressed(int keyCode)
	{
		if(keyCode<0 || keyCode>127)
			return false;
		return pressedKeys[keyCode];
	}
	
	public List<KeyEvent> getKeyEvents()
	{
		synchronized(this)
		{
			int len = keyEvents.size();
			for(int i=0; i<len;i++)
				keyEventPool.free(keyEvents.get(i));
			keyEvents.clear();
			keyEvents.addAll(keyEventsBuffer);
			keyEventsBuffer.clear();
			return keyEvents;
		}
	}
}
