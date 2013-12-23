/**
 * 安卓白金英雄坛制作组 <p>
 * 文件名：GameEvent.java <p>
 * 创建时间：2013-5-27 下午5:40:49 <p>
 * 所属项目：GmudTest <p>
 * @author 12548 <p>
 */
package lostland.gumd.platinum12548;

import java.util.ArrayList;

import lostland.gumd.platinum12548.battle.proc.BattleStart;
import lostland.gumd.platinum12548.blgframework.BasicScreen;
import lostland.gumd.platinum12548.blgframework.IFileIO;
import lostland.gumd.platinum12548.data.Item;
import lostland.gumd.platinum12548.ui.EventScreen;
import lostland.gumd.platinum12548.ui.NotificationScreen;
import lostland.gumd.platinum12548.ui.RopeScreen;
import lostland.gumd.platinum12548.ui.SignScreen;
import lostland.gumd.platinum12548.ui.TalkingScreen;
import lostland.gumd.platinum12548.ui.WateringScreen;
import lostland.gumd.platinum12548.ui.core.YesNoScreen;

/**
 * 类名：GameEvent <p>
 * 说明：事件处理类
 * @author 12548
 */
public class GameEvent {
	public static final int DRINK=1;
	public static final int SIGN = 5;
	public static final int ROPE = 0;
	
	public static void callEvent(int eventid)
	{
		switch(eventid)
		{
		case ROPE:
			if(!GmudWorld.mc.have(Item.ROPE_INDEX))
				GmudWorld.game.setScreen(new RopeScreen(GmudWorld.game));
			break;
		case DRINK:
			GmudWorld.game.setScreen(new WateringScreen(GmudWorld.game));
			break;
		case SIGN:
			GmudWorld.game.setScreen(new SignScreen(GmudWorld.game));
			break;
		case 2:
		case 3:
		case 4:
			if(TalkingScreen.mpp != eventid)return;
			int cost[] = {0,0,50,60,40};
			if(GmudWorld.mc.sp < cost[eventid]){
				GmudWorld.game.setScreen(new TalkingScreen(GmudWorld.game,"婆婆啊！手都软了，我们老板都没你狠！",false));
				TalkingScreen.mpp = 0;
				return;
			}
			GmudWorld.mc.sp-=cost[eventid];
			GmudWorld.mc.setExp(GmudWorld.mc.exp + 20);
			GmudWorld.mc.setPotential(GmudWorld.mc.potential + 10);
			GmudWorld.mc.gold += 50;
			String s[] = {"","","","",
					"挑水挑水我挑水\n倒水进缸水花飞","一桶两桶三四桶\n反正不用交水费",
					"劈柴劈柴我劈柴\n抡起斧子劈起来","保护树木讲环保\n婆婆没事别乱烧",
					"扫地扫地我扫地\n地上还有西瓜皮","婆婆家中欠打扫\n尘土满天难呼吸"};
			
			ArrayList<String> a = new ArrayList<String>();
			
			a.add(s[2*eventid]);
			a.add(s[2*eventid+1]);
			
			a.add("费了好大力气，总算干完了");
			a.add("你被奖励了：\n20实战经验10潜能50金钱");
			TalkingScreen.mpp = 0;
			GmudWorld.game.setScreen(new EventScreen(GmudWorld.game,a,true));
			
			break;
			
		case 6:
			a = new ArrayList<String>();

			if(!GmudWorld.mc.have(73)){
				a.add("看着池塘里的鱼，你摸了摸肚皮，心想：要是有个钓竿多好啊！");
			}
			else if(GmudWorld.mc.sp<50){
				a.add("现在日头这么毒，以你的体质，恐怕没钓到鱼就已经……");
			}
			else{
				a.add("你在土里刨了刨，抓了只小蚯蚓，穿在吊钩上，一挥手，钓线划出一道优美的弧线扎进了水里，你往地上");
				a.add("一蹲，开始钓鱼。");
				GmudWorld.mc.dmg(50, -50);
				if(GmudWorld.rand.nextBoolean())
				{
					a.add("浮子猛然沉了下去，你手疾眼快，一提钓竿，有了！");
					if(GmudWorld.mc.have(76))
					{
						a.add("你把鱼放进鱼篓，心里了开了花，今天的零花钱有着" +
								"落了！");
						GmudWorld.mc.give(74);
					}
					else
					{
						a.add("你把鱼从钩上摘下来，猛然想起：坏了，没带鱼篓。一" +
								"分神，鱼一下子滑到了水里，到手的鱼跑了！");
					}
				}
				else
				{
					a.add("过了很久也没有鱼上钩，你一收钓竿，到树下乘凉去了。");
				}
			}
			GmudWorld.game.setScreen(new EventScreen(GmudWorld.game,a,false));
			break;
			
		case 7:

			GmudWorld.game.setScreen(new TalkingScreen(GmudWorld.game,"活着真没意思，真想找根绳子上吊自杀",false));
			if(GmudWorld.mc.have(Item.ROPE_INDEX))
			{
				TalkingScreen.ts = new YesNoScreen(GmudWorld.game,"如果你自杀，资料就删除了，请考虑清楚！"){

					@Override
					protected void onYes() {
						IFileIO f = GmudWorld.game.getFileIO();
						while(!f.getPreferences().edit().putBoolean("newgame", true).commit())
							;
						GmudWorld.game.oo();
					}

					@Override
					protected void onNo() {
						GmudWorld.game.setScreen(GmudWorld.ms);
					}
					
				};
			}
			
			break;
		case 8:
			if(GmudWorld.mc.sex == 0 && GmudWorld.mc.equips(40) && GmudWorld.mc.age == 14 && GmudWorld.mc.fame < 128)
			{
				GmudWorld.mc.give(81);
				GmudWorld.game.setScreen(new TalkingScreen(GmudWorld.game,"发现菜花宝典",false));
			}
			
			break;
			
		case 9:
			if(GmudWorld.mc.inventory[77] >=6)
			{
				ArrayList<String> ts = new ArrayList<String>();
				int t;
				if(GmudWorld.mc.fame >= 160)
				{
					t = 125;
					ts.add("东方求败：哈哈----------！没想到吧。");
					ts.add(GmudWorld.mc.name+"：难道神秘人是……");
					ts.add("东方求败：不错。十年前，自诩为名门正派的六大门派合力偷袭，把我关在这里……");
					ts.add(GmudWorld.mc.name+"：我会把你再次封印。");
					ts.add("东方求败：做梦吧，以你的实力，十万年还早！");
				}
				else if(GmudWorld.mc.fame < 128 && GmudWorld.npc[11].dead)
				{
					t = 126;
					ts.add("道德和尚：苦海无边，回头是岸。");
					ts.add(GmudWorld.mc.name+"：不可能！难道你没有死？");
					ts.add("道德和尚：施主罪孽深重，老衲是不会让你回到原来的世界再次危害天下的。");
				}
				else
				{
					t = 124;
					ts.add("我是谁:在每个故事的结尾,通常都会有一个最终BOSS。遇上我，是你的不幸，要怪就怪那个幕后的导演吧！");
				}
				
				GmudWorld.bs.enemyid = t;
				GmudWorld.bs.setStatus(new BattleStart());
				GmudWorld.bs.eob = false;
				EventScreen.ts = GmudWorld.bs;
				GmudWorld.game.setScreen(new EventScreen(GmudWorld.game,ts,false));
			}
			
			break;
			
		case 10:

			GmudWorld.game.setScreen(new YesNoScreen(GmudWorld.game, "一座高山，山顶终年积雪，相传山顶有神仙居住，想上去看看吗？"){

				@Override
				protected void onYes() {
					game.setScreen(new NotificationScreen(game,GmudWorld.ms,"游戏厅尚未开启！"));
				}

				@Override
				protected void onNo() {
					GmudWorld.game.setScreen(GmudWorld.ms);
				}});
			
			
			break;
			
		case 11:
			
			break;
			
		case 13:
			if(GmudWorld.mc.have(78)){
				GmudWorld.game.setScreen(new TalkingScreen(GmudWorld.game,"zzz,好困啊~",false));
				GmudWorld.mc.drop(78,1);
				BasicScreen.timeFlow(900);
			}
			
			break;
		}
		
			
	}
}
