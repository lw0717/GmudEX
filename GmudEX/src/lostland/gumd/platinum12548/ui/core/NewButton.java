/**
 * 安卓白金英雄坛制作组 <p>
 * 文件名：NewButton.java <p>
 * 创建时间：2013-12-19 下午2:16:39 <p>
 * 所属项目：GmudTest <p>
 * @author 12548 <p>
 */
package lostland.gumd.platinum12548.ui.core;

import lostland.gumd.platinum12548.Assets;
import lostland.gumd.platinum12548.GmudGame;
import lostland.gumd.platinum12548.blgframework.IPixmap;
import lostland.gumd.platinum12548.blgframework.impl.BLGGame;

/**
 * 类名：NewButton <p>
 * 说明：
 * @author 12548
 */
public enum NewButton {
	NB_UP,
	NB_DOWN,
	NB_LEFT,
	NB_RIGHT,
	NB_ENTER,
	NB_BACK,
	NB_MENU,
	NB_NULL;

	public static boolean menubutton = true;

	private final int BOTTOM = 5;
	private final int LEFT = 5;
	private final int RIGHT = 5;
	private final int CENTER = 5;
	private final int RADIUS = 16;


	public int x()
	{
		if(GmudGame.classickeymap)
		{
			switch(this)
			{
			case NB_BACK:
				return BLGGame.FB2W - RIGHT - RADIUS * 2;
			case NB_DOWN:
				return LEFT + RADIUS * 2;
			case NB_ENTER:
				return BLGGame.FB2W - RIGHT - RADIUS * 3;
			case NB_LEFT:
				return LEFT + CENTER;
			case NB_RIGHT:
				return LEFT + RADIUS * 4 - CENTER;
			case NB_UP:
				return LEFT + RADIUS * 2;
			case NB_MENU:
				if(menubutton)
					return BLGGame.FB2W - RADIUS * 2 * 3 - RIGHT - CENTER * 2;
			default:
				return -1000;
			}
		}
		else
		{
			switch(this)
			{
			case NB_BACK:
				return BLGGame.FB2W - RADIUS * 2 * 7;
			case NB_DOWN:
				return BLGGame.FB2W - RADIUS * 2 * 2 - RIGHT - CENTER;
			case NB_ENTER:
				return BLGGame.FB2W - RADIUS * 2 - RIGHT;
			case NB_LEFT:
				return BLGGame.FB2W - RADIUS * 2 * 3 - RIGHT - CENTER * 2;
			case NB_RIGHT:
				return BLGGame.FB2W - RADIUS * 2 - RIGHT;
			case NB_UP:
				return BLGGame.FB2W - RADIUS * 2 * 2 - RIGHT - CENTER;
			case NB_MENU:
				if(menubutton)
					return BLGGame.FB2W - RADIUS * 2 * 3 - RIGHT - CENTER * 2;
			default:
				return -1000;
			}
		}
	}

	public int y()
	{
		if(GmudGame.classickeymap)
		{
			switch(this)
			{
			case NB_BACK:
				return BLGGame.FB2H - BOTTOM - RADIUS * 4 - BOTTOM * 2;
			case NB_DOWN:
				return BLGGame.FB2H- BOTTOM - RADIUS * 2 - CENTER;
			case NB_ENTER:
				return BLGGame.FB2H - BOTTOM - RADIUS * 2 - BOTTOM;
			case NB_LEFT:
				return BLGGame.FB2H - BOTTOM - RADIUS * 4;
			case NB_RIGHT:
				return BLGGame.FB2H - BOTTOM - RADIUS * 4;
			case NB_UP:
				return BLGGame.FB2H - BOTTOM - RADIUS * 6 + CENTER;
			case NB_MENU:
				if(menubutton)
					return BOTTOM;
			default:
				return -1000;
			}
		}
		else
		switch(this)
		{
		case NB_BACK:
			return BLGGame.FB2H  - RADIUS * 2 - BOTTOM;
		case NB_DOWN:
			return BLGGame.FB2H - RADIUS * 2 - BOTTOM;
		case NB_ENTER:
			return BLGGame.FB2H - RADIUS * 6 - BOTTOM - CENTER * 2;
		case NB_LEFT:
			return BLGGame.FB2H - RADIUS * 2 - BOTTOM;
		case NB_RIGHT:
			return BLGGame.FB2H - RADIUS * 2 - BOTTOM;
		case NB_UP:
			return BLGGame.FB2H - RADIUS * 4 - BOTTOM - CENTER;
		case NB_MENU:
			if(menubutton)
				return BOTTOM;
		default:
			return -1000;
		}
	}


	public IPixmap getPixmap()
	{
		switch(this)
		{
		case NB_BACK:
			return Assets.nbback;
		case NB_DOWN:
			return Assets.nbdown;
		case NB_ENTER:
			return Assets.nbenter;
		case NB_LEFT:
			return Assets.nbleft;
		case NB_RIGHT:
			return Assets.nbright;
		case NB_UP:
			return Assets.nbup;
		case NB_MENU:
			return Assets.nbmenu;
		default:
			return null;

		}
	}

	public boolean inBound(int x, int y)
	{
		return ButtonControlledScreen.inbound(x(), y(), x, y);
	}

	public static NewButton inbound(int x, int y)
	{
		for(NewButton i : NewButton.values())
		{
			if(ButtonControlledScreen.inbound(i.x(), i.y(), x, y))
				return i;
		}
		return NB_NULL;
	}

}
