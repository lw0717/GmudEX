/**
 * 安卓白金英雄坛制作组 <p>
 * 文件名：NpcMenuScreen.java <p>
 * 创建时间：2013-7-23 下午6:00:40 <p>
 * 所属项目：GmudTest <p>
 * @author 12548 <p>
 */
package lostland.gumd.platinum12548.ui;

import lostland.gumd.platinum12548.FontSize;
import lostland.gumd.platinum12548.GameConstants;
import lostland.gumd.platinum12548.GmudGame;
import lostland.gumd.platinum12548.GmudWorld;
import lostland.gumd.platinum12548.MainCharTile;
import lostland.gumd.platinum12548.battle.proc.BattleStart;
import lostland.gumd.platinum12548.blgframework.BasicScreen;
import lostland.gumd.platinum12548.blgframework.IGame;
import lostland.gumd.platinum12548.blgframework.impl.BLGGraphics;
import lostland.gumd.platinum12548.data.Npc;
import lostland.gumd.platinum12548.ui.core.GmudWindow;
import lostland.gumd.platinum12548.ui.core.MenuScreen;

/**
 * 类名：NpcMenuScreen <p>
 * 说明：
 * @author 12548
 */
public class NpcMenuScreen extends MenuScreen {


	public static int npcid;
	static int fourth;
	static boolean havefourth=true;


	public NpcMenuScreen(IGame game,int npcid) {
		super(game,getWindows(npcid));
		NpcMenuScreen.npcid = npcid;


		this.dummyBorder = new NpcMenuBorder((GmudGame) game, havefourth);



	}

	/* （非 Javadoc）
	 * @see lostland.gumd.platinum12548.ui.MenuScreen#onClick(int)
	 */
	@Override
	protected void onClick(int index) {

		if(index == 0){
			game.setScreen(new TalkingScreen(game,npcid));
		}
		else if(index == 1){
			game.setScreen(new LookScreen(game,npcid));
		}
		else if(index == 2)
		{
			GmudWorld.bs.setStatus(new BattleStart());
			GmudWorld.bs.enemyid = npcid;
			GmudWorld.bs.eob = false;
			game.setScreen(GmudWorld.bs);
		}
		else if(index == 3 && GmudWorld.npc[npcid].trading == 101)
		{
			if(npcid == 6 && GmudWorld.mc.exp<50000)
				game.setScreen(new TalkingScreen(game,"去去去，攒够经验再来吧！",false));
			else if(!GmudWorld.npc[npcid].qingjiaoable)
			{
				if(masterTest())
				{
					TalkingScreen.ts = new TalkingScreen(game,"你跪了下来，向"+GmudWorld.npc[npcid].name+"恭恭敬敬地磕了四个响头，叫道：『师父』！",false);
					for(int i:GmudWorld.teachers)
						GmudWorld.npc[i].qingjiaoable = false;
					GmudWorld.npc[npcid].qingjiaoable = true;
				}
			}
			else
			{
				game.setScreen(new TradeScreen(game,npcid,this));
			}
		}
		else
		{
			game.setScreen(new TradeScreen(game,npcid,this));
		}
		
		BasicScreen.recheck();
	}

	/* （非 Javadoc）
	 * @see lostland.gumd.platinum12548.ui.MenuScreen#onCancel()
	 */
	@Override
	public void onCancel() {
		game.setScreen(GmudWorld.ms);
	}


	boolean facTest(int fac)
	{
		if(GmudWorld.mc.faction != fac && GmudWorld.mc.faction>0)
			if(GmudWorld.mc.faction == 7)
			{
				game.setScreen(new TalkingScreen(game,"你已自创门派，无需拜师吧？",false));
				return false;
			}
			else
			{
				game.setScreen(new TalkingScreen(game,"你已另有名师，还要来偷师学艺吗？",false));
				return false;
			}
		return true;
	}

	boolean masterTest()
	{
		if(facTest(GmudWorld.npc[npcid].faction))
			switch(npcid)
			{
			case 38:
				if(GmudWorld.mc.sex == 0){
					game.setScreen(new TalkingScreen(game,"好吧，我暂且收下你，不过我爹的功夫我只学会三成，有机会你再向他老人家请教吧。",false));
					GmudWorld.mc.faction=1;
					return true;
				}
				else{
					game.setScreen(new TalkingScreen(game,"我八卦门功夫一向传男不传女，你还是另择名师吧。",false));
					return false;
				}
			case 43:
				if(GmudWorld.mc.maxfp < 500)
				{
					game.setScreen(new TalkingScreen(game,"你内力不足，无法修习八卦门高深武功。",false));
					return false;
				}
				else if(GmudWorld.mc.skills[14]<50)
				{
					game.setScreen(new TalkingScreen(game,"你混元一气功火候不够，无法修习八卦门高深武功。",false));
					return false;
				}
				else if(GmudWorld.mc.skills[11]<50)
				{
					game.setScreen(new TalkingScreen(game,"你还是先将刀法练好再说吧。",false));
					return false;
				}
				else
				{
					game.setScreen(new TalkingScreen(game,"好吧，从今天起我亲受你功夫，你和宝震就是师兄弟了。",false));
					GmudWorld.mc.faction=1;
					return true;
				}
			case 47:
				if(GmudWorld.mc.maxfp < 800)
				{
					game.setScreen(new TalkingScreen(game,"你内力不足，无法修习八卦门高深武功。",false));
					return false;
				}
				else if(GmudWorld.mc.skills[14]<100)
				{
					game.setScreen(new TalkingScreen(game,"你混元一气功火候不够，无法修习八卦门高深武功。",false));
					return false;
				}
				else if(GmudWorld.mc.skills[11]<100)
				{
					game.setScreen(new TalkingScreen(game,"八卦刀是我派绝学，想当年老夫就凭一把八卦紫金刀闯荡天下，你还是先将刀法练好再说吧。",false));
					return false;
				}
				else
				{
					game.setScreen(new TalkingScreen(game,"很好，没想到老夫就破例收你做关门弟子。",false));
					GmudWorld.mc.faction=1;
					return true;
				}
			case 56:
				if(GmudWorld.mc.sex == 1){
					game.setScreen(new TalkingScreen(game,"我花间派文武双修，多加努力吧。",false));
					GmudWorld.mc.faction=3;
					return true;
				}
				else{
					game.setScreen(new TalkingScreen(game,"我花间派向来只收女弟子，你还是另择名师吧。",false));
					return false;
				}
			case 57:
				if(GmudWorld.mc.skills[19] < 60){
					game.setScreen(new TalkingScreen(game,"你一剪梅花手功夫尚未纯熟，兵刃功夫又怎学得？",false));
					return false;
				}
				else
				{
					game.setScreen(new TalkingScreen(game,"掌门人要求只收俊俏女子……不过……好吧，我就收下你了。",false));
					GmudWorld.mc.faction=3;
					return true;
				}
			case 66:
				if(GmudWorld.mc.skills[19] < 50){
					game.setScreen(new TalkingScreen(game,"你一剪梅花手功夫尚未纯熟，兵刃功夫又怎学得？",false));
					return false;
				}
				else
				{
					game.setScreen(new TalkingScreen(game,"掌门人要求只收俊俏女子……不过……好吧，我就收下你了。",false));
					GmudWorld.mc.faction=3;
					return true;
				}
			case 58:
				if(GmudWorld.mc.maxfp < 1000)
				{
					game.setScreen(new TalkingScreen(game,"你内力修为不足，还是下去勤修好了再来吧！",false));
					return false;
				}
				else if(GmudWorld.mc.skills[9]<100)
				{
					game.setScreen(new TalkingScreen(game,"我花间派各路武功均蕴有深意，你读书识字基础不够，焉能领会高深武功？",false));
					return false;
				}
				else if(GmudWorld.mc.getface() < 28)
				{
					game.setScreen(new TalkingScreen(game,"我的嫡传弟子莫不是聪明伶俐，美貌如花，收你入门岂不有损我花间派的名头？",false));
					return false;
				}
				else
				{
					game.setScreen(new TalkingScreen(game,"有你这样伶俐俊秀的弟子相伴左右也是乐事，为师一定好好点拨于你。",false));
					GmudWorld.mc.faction=3;
					return true;
				}
			case 73:
				game.setScreen(new TalkingScreen(game,"很好，牢记红莲教义，跟我勤修武功吧。",false));
				GmudWorld.mc.faction=5;
				return true;
			case 80:
				if(GmudWorld.mc.skills[25]<90)
				{
					game.setScreen(new TalkingScreen(game,"你普天同济心法火候不够，无法修习更高深武学。",false));
					return false;
				}
				else if(GmudWorld.mc.getStr() < 30)
				{
					game.setScreen(new TalkingScreen(game,"你膂力不足，无法领会乱披风杖法的精髓。",false));
					return false;
				}
				else
				{
					game.setScreen(new TalkingScreen(game,"很好，牢记红莲教义，跟我勤修武功吧。",false));
					GmudWorld.mc.faction=5;
					return true;
				}
			case 90:
				game.setScreen(new TalkingScreen(game,"你随我好好练吧，保你以后飞黄腾达。",false));
				GmudWorld.mc.faction=4;
				return true;
			case 87:
				if(GmudWorld.mc.skills[27]<60)
				{
					game.setScreen(new TalkingScreen(game,"一刀流技法讲究出出手狠辣，你无法拳修为尚且不够，如何能领会高深武功？",false));
					return false;
				}
				else
				{
					game.setScreen(new TalkingScreen(game,"你随我好好练吧，保你以后飞黄腾达。",false));
					GmudWorld.mc.faction=4;
					return true;
				}
			case 94:
				if(GmudWorld.mc.skills[26]<120)
				{
					game.setScreen(new TalkingScreen(game,"扶桑功夫全需忍术支持，你还是下去勤修好了再来吧！",false));
					return false;
				}
				else if(GmudWorld.mc.getStr() < 32)
				{
					game.setScreen(new TalkingScreen(game,"我派一刀流刀法以威猛著称，力气不够是难有进境的！",false));
					return false;
				}
				else
				{
					game.setScreen(new TalkingScreen(game,"你要多加努力，把我扶桑尹贺一派绝学发扬光大。",false));
					GmudWorld.mc.faction=4;
					return true;
				}
			case 96:
			case 97:
				game.setScreen(new TalkingScreen(game,"难得你有志武学，不过常言道太极三年不伤人，需得有常恒之心才能在太极门中出人头地。",false));
				GmudWorld.mc.faction=2;
				return true;
			case 101:
				if(GmudWorld.mc.maxfp < 1500)
				{
					game.setScreen(new TalkingScreen(game,"你内力不足，无法修习道家高深武功。",false));
					return false;
				}
				else if(GmudWorld.mc.skills[32]<120)
				{
					game.setScreen(new TalkingScreen(game,"你太极神功火候不够，无法修习道家高深武功。",false));
					return false;
				}
				else if(GmudWorld.mc.skills[31]<100)
				{
					game.setScreen(new TalkingScreen(game,"太极拳是我派精华，还是专心将其练好再说吧。",false));
					return false;
				}
				else if(GmudWorld.mc.getWxg() < 28)
				{
					game.setScreen(new TalkingScreen(game,"太极剑讲究意在剑先，悟性不够难于领会其理。",false));
					return false;
				}
				else
				{
					game.setScreen(new TalkingScreen(game,"很好，没想到老夫有生之年又觅到一可塑之才。",false));
					GmudWorld.mc.faction=2;
					return true;
				}
			case 122:
				if(GmudWorld.mc.getAgi() < 22)
				{
					game.setScreen(new TalkingScreen(game,"我雪山派武学主旨为招数精妙，依我看你的资质似乎不宜？",false));
					return false;
				}
				else
				{
					game.setScreen(new TalkingScreen(game,"很好,你跟著我勤加练习，日后如能和封师兄或白老爷子学上几招，那可终身受用。",false));
					GmudWorld.mc.faction=6;
					return true;
				}
			case 118:
				if(GmudWorld.mc.getAgi() < 23)
				{
					game.setScreen(new TalkingScreen(game,"我雪山派武学主旨为招数精妙，依我看你的资质似乎不宜？",false));
					return false;
				}
				else if(GmudWorld.mc.skills[36]<40)
				{
					game.setScreen(new TalkingScreen(game,"你现在的雪山内功太薄弱，无法领会精妙的雪山剑法，还是学好再来吧。",false));
					return false;
				}
				else
				{
					game.setScreen(new TalkingScreen(game,"很好，你多加努力，他日争取将雪山剑派在你手中发扬光大。",false));
					GmudWorld.mc.faction=6;
					return true;
				}
			case 110:
				if(GmudWorld.mc.maxfp < 1200)
				{
					game.setScreen(new TalkingScreen(game,"我雪山独门武功雪影擒拿手需要深厚内力才可运用，我看你还是下去勤修好了再来吧！",false));
					return false;
				}
				else if(GmudWorld.mc.getBon() < 32)
				{
					game.setScreen(new TalkingScreen(game,"擒拿手需强劲内力，根骨太差进境缓慢，你还是先打好基本功再说吧！",false));
					return false;
				}
				else
				{
					game.setScreen(new TalkingScreen(game,"很好，你多加努力，他日必有所成。",false));
					GmudWorld.mc.faction=6;
					return true;
				}
			default:
				return false;
			}
		else
			return false;
		
		
		
	}

	/* （非 Javadoc）
	 * @see lostland.gumd.platinum12548.ui.core.ButtonControlledScreen#show()
	 */
	@Override
	protected void show() {
		BLGGraphics g=(BLGGraphics) game.getGraphics();
		Npc t = GmudWorld.npc[npcid];
		final int TOP = 2;
		final int LEFT = 8;

		GmudWorld.mapTile.drawMap(GmudWorld.ms.map, GmudWorld.ms.X, GmudWorld.ms.Y);
		GmudWorld.cnm.draw(MainCharTile.currentDirection, GmudWorld.cnm.currentStep, MainCharTile.X, MainCharTile.Y);
		g.drawRect(LEFT, TOP, t.name.length() * 12, 12,GameConstants.BG_COLOR);
		g.drawText(t.name, LEFT, TOP, FontSize.FT_12PX);
		dummyBorder.draw();
		for(int i=0;i<3;i++)
		{
			buttons[i].draw();
		}
		if(havefourth)
			buttons[3].draw();
	}

	static GmudWindow[] getWindows(int npcid)
	{

		Npc t = GmudWorld.npc[npcid];
		havefourth = true;
		
		if(t.trading>100)
			if(t.qingjiaoable)
				fourth = 5;
			else
				fourth = 4;
		else if (t.trading>0)
			fourth = 3;
		else
			havefourth = false;

		NpcMenuButton[] tw = new NpcMenuButton[(havefourth?4:3)];

		for(int i = 0; i<3;i++)
		{
			tw[i] = new NpcMenuButton(GmudWorld.game, i);
		}
		if(havefourth)
			tw[3] = new NpcMenuButton(GmudWorld.game,fourth);



		return tw;

	}



}
