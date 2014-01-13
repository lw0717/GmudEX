/**
 * 安卓白金英雄坛制作组 <p>
 * 文件名：GmudWorld.java <p>
 * 创建时间：2013-5-20 下午4:50:38 <p>
 * 所属项目：GmudTest <p>
 * @author 12548 <p>
 */
package lostland.gumd.platinum12548;

import java.util.Random;

import lostland.gumd.platinum12548.battle.BattleScreen;
import lostland.gumd.platinum12548.blgframework.impl.SingleTouchHandler;
import lostland.gumd.platinum12548.data.Gesture;
import lostland.gumd.platinum12548.data.Item;
import lostland.gumd.platinum12548.data.MainChar;
import lostland.gumd.platinum12548.data.Npc;
import lostland.gumd.platinum12548.data.Skill;
import lostland.gumd.platinum12548.ui.InnerMenuScreen;
import lostland.gumd.platinum12548.ui.MainMenuScreen;

/**
 * 类名：GmudWorld <p>
 * 说明：
 * @author 12548
 */
public class GmudWorld {
	
	
	public static Random rand = new Random();
	
	
	public static GmudGame game;
	
//	public static TradeDialog td;
//	public static TopMenuDialog tmd;
//	public static StatusDialog sd;
//	public static BottomDialog bd;
	public static MainCharTile cnm;
	public static MapTile mapTile;
	
	
	
	public static MainMenuScreen mms;
	public static MapScreen ms;
	public static BattleScreen bs;
	
	public static InnerMenuScreen ims;
	public static SingleTouchHandler sth;
	
	public static GmudNPC npcc;
	public static GmudMap map[];
	
	public static MainChar mc;
	public static Npc npc[];
	public static Skill skill[];
	public static Gesture zs[];
	public static Item wp[];
	
	
	public static boolean near(int x1,int y1,int x2,int y2)
	{
		return Math.abs(x1-x2)+Math.abs(y1-y2)==1;
	}
	
	public static int[] push_back(int[] a,int item)
	{
		int t[] = new int[a.length+1];
		for(int i=0;i< a.length;i++)
			t[i]=a[i];
		t[a.length] = item;
		return t;
	}
	
	public static int[] push_top(int[] a,int item)
	{
		int t[] = new int[a.length+1];
		t[0] = item;
		for(int i=1;i<t.length;i++)
			t[i]=a[i-1];
		return t;
	}
	
	public static int bool2int(boolean b)
	{
		if(b)
			return 1;
		else
			return 0;
	}
	
	public static boolean int2bool(int a)
	{
		if(a == 0)
			return false;
		else
			return true;
	}
	
	
	
	public static String pj[] = new String[]{
			"不堪一击",
			"毫不足虑",
			"不足挂齿",
			"初学乍练",
			"勉勉强强",
			"初窥门径",
			"初出茅庐",
			"略知一二",
			"普普通通",
			"平平常常",
			"平淡无奇",
			"粗懂皮毛",
			"半生不熟",
			"登堂入室",
			"略有小成",
			"已有小成",
			"鹤立鸡群",
			"驾轻就熟",
			"青出於蓝",
			"融会贯通",
			"心领神会",
			"炉火纯青",
			"了然於胸",
			"略有大成",
			"已有大成",
			"豁然贯通",
			"非比寻常",
			"出类拔萃",
			"罕有敌手",
			"技冠群雄",
			"神乎其技",
			"出神入化",
			"傲视群雄",
			"登峰造极",
			"无与伦比",
			"所向披靡",
			"一代宗师",
			"精深奥妙",
			"神功盖世",
			"举世无双",
			"惊世骇俗",
			"撼天动地",
			"震古铄今",
			"超凡入圣",
			"威镇寰宇",
			"空前绝后",
			"天人合一",
			"深藏不露",
			"深不可测",
			"返璞归真",
			"极轻很轻",
			"不轻不重",
			"极重很重",
			"如同馆长"
			};
	
	public static final int teachers[] = {38,43,47,56,57,58,66,73,80,87,90,94,96,97,101,122,118,110};
	
	public static final int t_faction[][] = {{},{38,43,47},{96,97,101},{56,57,58,66},{87,90,94},{73,80},{110,118,122}};
	
}


