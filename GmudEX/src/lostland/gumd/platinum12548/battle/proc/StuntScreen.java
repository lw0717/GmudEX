/**
 * 安卓白金英雄坛制作组 <p>
 * 文件名：StuntScreen.java <p>
 * 创建时间：2013-8-12 上午10:31:26 <p>
 * 所属项目：GmudTest <p>
 * @author 12548 <p>
 */
package lostland.gumd.platinum12548.battle.proc;

import java.util.ArrayList;

import android.util.Log;

import lostland.gumd.platinum12548.FontSize;
import lostland.gumd.platinum12548.GmudGame;
import lostland.gumd.platinum12548.GmudWorld;
import lostland.gumd.platinum12548.battle.ControlScreen;
import lostland.gumd.platinum12548.battle.ViewScreen;
import lostland.gumd.platinum12548.battle.proc.stunt.缠字诀;
import lostland.gumd.platinum12548.battle.proc.stunt.JiZiJue;
import lostland.gumd.platinum12548.battle.proc.stunt.LiuLangWenYing;
import lostland.gumd.platinum12548.battle.proc.stunt.LiuXingFeiZhi;
import lostland.gumd.platinum12548.battle.proc.stunt.LuanHuanJue;
import lostland.gumd.platinum12548.battle.proc.stunt.LuoYingBinFen;
import lostland.gumd.platinum12548.battle.proc.stunt.SanHuanTaoYue;
import lostland.gumd.platinum12548.battle.proc.stunt.ShenDaoGuiDie;
import lostland.gumd.platinum12548.battle.proc.stunt.XueHuaLiuChu;
import lostland.gumd.platinum12548.battle.proc.stunt.YinYangJue;
import lostland.gumd.platinum12548.battle.proc.stunt.ZhenZiJue;
import lostland.gumd.platinum12548.battle.proc.stunt.迎风一刀斩;
import lostland.gumd.platinum12548.blgframework.IGame;
import lostland.gumd.platinum12548.blgframework.impl.BLGGraphics;
import lostland.gumd.platinum12548.data.Skill;
import lostland.gumd.platinum12548.ui.core.ArrowButton;
import lostland.gumd.platinum12548.ui.core.GmudWindow;
import lostland.gumd.platinum12548.ui.core.MenuScreen;

/**
 * 类名：StuntScreen <p>
 * 说明：
 * @author 12548
 */
public class StuntScreen extends MenuScreen{

	public static final String name[] = new String[]{
		"震字诀","挤字诀","阴阳诀","乱环诀","缠字诀","连字诀","三环套月","流星飞掷","雪花六出","神倒鬼跌",
		"落英缤纷","柳浪闻莺","化掌为刀","八卦刀影掌","八阵刀影掌","化掌为刀","旋风三连斩","迎风一刀斩","雷动九天","红莲出世",
		"冰心诀","三花","忍术烟幕","忍法影分身"
	};

	public static final int sklNeeded[][] = new int[][]{
		{1,31,0,32},{60,60,60,60},//震字诀
		{1,31,0,32},{70,70,70,70},//挤字诀
		{1,31,0,32},{120,120,120,120},//阴阳
		{1,31,0,32},{100,100,100,100},//乱环
		{2,30,0,32},{80,80,80,80},//缠字诀
		{2,30,0,32},{80,80,80,80},//连字诀
		{2,30,0,32},{120,120,120,120},//三环套月
		{0,25,5,23},{80,80,80,80},//流星飞掷
		{2,38,0,36},{60,60,60,60},//雪花六出
		{1,39,0,36},{80,80,100,100},//神倒鬼跌
		{6,17,0,20},{80,80,80,80},//落英缤纷
		{3,18,0,20},{100,100,100,100},//柳浪闻莺
		{12,1,0,14},{70,70,70,70},//化掌为刀
		{11,0,14},{90,70,70},
		{11,0,14},{90,70,70},
		{0,1,13},{90,90,90},//化掌为刀
		{0,26,3,29},{80,80,60,60},
		{0,26,3,29},{80,80,80,80},
		{0,25},{60,60},
		{0,25},{80,80},
		{0,36},{60,60},
		{0,20},{60,60},
		{0,26},{60,60},
		{0,26},{80,80}
	};

	public static final int cost[] = new int[]{
		200,350,300,500,250,350,400,850,600,350,
		400,400,200,450,150,400,350,550,150,350,
		250,350,300,550
	};

	public static final int cd[] = new int[]{
		2,2,6,9,6,9,5,5,4,9,
		4,4,4,4,4,4,7,7,5,5
		,5,5,5,5
	};

	static int cnt = 0;
	static int[] t = new int[10];
	static ArrayList<String> s = new ArrayList<String>();
	public static int i;
	/**
	 * @param game
	 */
	public StuntScreen(IGame game) {
		super(game,getWindows());


		this.dummyBorder = new GmudWindow((GmudGame) game, 48, 44, 70, 12*cnt+2){
			@Override
			public void draw() {
				this.drawBackground();
			}
		};
	}

	public static boolean canuse(int index)
	{
		boolean flag = false;
		if(GmudWorld.bs.zdp.getAttackSkill().kind == Skill.KIND_BINGREN)
		{
			int t[] ={30,23,38,17,18,11,29};
			for(int i:t)
				if(i == GmudWorld.bs.zdp.skillsckd[1])
				{
					switch(i)
					{
					case 30:
						switch(index)
						{
						case 4:
						case 5:
						case 6:
							flag = true;
							break;
						default:
							break;
						}
						break;
					case 23:
						switch(index)
						{
						case 7:
							flag = true;
							break;
						default:
							break;
						}
						break;
					case 38:
						switch(index)
						{
						case 8:
							flag = true;
							break;
						default:
							break;
						}
						break;
					case 17:
						switch(index)
						{
						case 10:
							flag = true;
							break;
						default:
							break;
						}
						break;
					case 18:
						switch(index)
						{
						case 11:
							flag = true;
							break;
						default:
							break;
						}
						break;
					case 11:
						if(index == 13 && GmudWorld.bs.zdp.skillsckd[0] == 12)
							flag = true;
						else if(index == 14 && GmudWorld.bs.zdp.skillsckd[0] == 13)
							flag = true;
						
						break;
					case 29:
						switch(index)
						{
						case 16:
						case 17:
							flag = true;
							break;
						default:
							break;
						}
						
						break;
					default:
						break;
					}
				}
		}
		else
		{
			int t[] ={31,39,12,13};
			for(int i:t)
				if(i == GmudWorld.bs.zdp.skillsckd[0])
				{
					switch(i)
					{
					case 31:
						switch(index)
						{
						case 0:
						case 1:
						case 2:
						case 3:
							flag = true;
							break;
						default:
							break;
						}
						break;
					case 39:
						switch(index)
						{
						case 9:
							flag = true;
							break;
						default:
							break;
						}
						break;
					case 12:
						switch(index)
						{
						case 12:
							flag = true;
							break;
						default:
							break;
						}
						break;
					case 13:
						switch(index)
						{
						case 15:
							flag = true;
							break;
						default:
							break;
						}
						break;
					}
				}
		}
		int t[] ={25,26,20,36};
		for(int i:t)
			if(i == GmudWorld.bs.zdp.skillsckd[3])
			{
				switch(i)
				{
				case 25:
					switch(index)
					{
					case 18:
					case 19:
						flag = true;
						break;
					default:
						break;
					}
					break;
				case 26:
					switch(index)
					{
					case 22:
					case 23:
						flag = true;
						break;
					default:
						break;
					}
					break;
				case 20:
					switch(index)
					{
					case 21:
						flag = true;
						break;
					default:
						break;
					}
					break;
				case 36:
					switch(index)
					{
					case 20:
						flag = true;
						break;
					default:
						break;
					}
					break;
				}
			}




		if(GmudWorld.bs.zdp.sz[index]>0)
			return false;

		if(GmudWorld.bs.zdp.fp < cost[index])
			return false;

		for(int i=0;i<sklNeeded[index*2].length;i++)
		{
			if(GmudWorld.bs.zdp.skills[sklNeeded[index*2][i]]<sklNeeded[index*2+1][i])
			{
				return false;
			}
		}

		if(index == 7 && GmudWorld.bs.zdp.getStr()<32)
			return false;

		return flag;
	}


	/* （非 Javadoc）
	 * @see lostland.gumd.platinum12548.ui.MenuScreen#onClick(int)
	 */
	@Override
	protected void onClick(int index) {
		boolean flag = false;

		if(GmudWorld.bs.zdp.sz[t[index]]>0){
			ViewScreen.setText("你刚用完外功，还是先歇歇吧");
			flag = true;
		}else if(GmudWorld.bs.zdp.fp < cost[t[index]]){
			ViewScreen.setText("你的内力不足");
			flag = true;
		}else{
			for(int i=0;i<sklNeeded[t[index]*2].length;i++)
			{
				if(GmudWorld.bs.zdp.skills[sklNeeded[t[index]*2][i]]<sklNeeded[t[index]*2+1][i])
				{
					ViewScreen.setText("你的"+GmudWorld.skill[sklNeeded[t[index]*2][i]].name+"修为不足");
					flag = true;
					break;
				}
			}
		}


		if(!flag){
			process(t[index]);
		}

		game.setScreen(new ViewScreen(game));

	}


	@Override
	public void onCancel() {
		game.setScreen(new ControlScreen(game));

	}

	public static void StuntOver()
	{
		AttackStatus.ts = new RoundOverStatus();
		GmudWorld.bs.setStatus(new DummyStatus());

		GmudWorld.game.setScreen(new ViewScreen(GmudWorld.game));
	}


	/* （非 Javadoc）
	 * @see lostland.gumd.platinum12548.ui.core.ButtonControlledScreen#show()
	 */
	@Override
	protected void show() {
		GmudWorld.bs.present(0.01f);
		dummyBorder.draw();
		for(int i = 0; i < cnt; i++)
			buttons[i].draw();
	}


	static GmudWindow[] getWindows()
	{
		cnt = 0;
		t = new int[10];
		s = new ArrayList<String>();

		if(GmudWorld.bs.zdp.getAttackSkill().kind == Skill.KIND_BINGREN)
			switch(GmudWorld.bs.zdp.skillsckd[1]){
			case 30:
				t[s.size()] = 4;
				s.add(name[t[s.size()]]);
				t[s.size()] = 5;
				s.add(name[t[s.size()]]);
				t[s.size()] = 6;
				s.add(name[t[s.size()]]);
				cnt+=3;
				break;
			case 23:
				t[s.size()] = 7;
				s.add(name[t[s.size()]]);

				cnt++;
				break;
			case 38:
				t[s.size()] = 8;
				s.add(name[t[s.size()]]);
				cnt++;
				break;
			case 17:
				t[s.size()] = 10;
				s.add(name[t[s.size()]]);
				cnt++;
				break;
			case 18:
				t[s.size()] = 11;
				s.add(name[t[s.size()]]);
				cnt++;
				break;
			case 11:
				if(GmudWorld.bs.zdp.getEquipedSkill(0).id ==12)
				{
					t[s.size()] = 13;
					s.add(name[t[s.size()]]);
					cnt++;
				}
				else if (GmudWorld.bs.zdp.getEquipedSkill(0).id ==13)
				{
					t[s.size()] = 14;
					s.add(name[t[s.size()]]);
					cnt++;
				}
				break;
			case 29:
				t[s.size()] = 16;
				s.add(name[t[s.size()]]);
				t[s.size()] = 17;
				s.add(name[t[s.size()]]);
				cnt +=2;
				break;
			default:
				break;
			}
		else
		{
			switch(GmudWorld.bs.zdp.getEquipedSkill(0).id)
			{
			case 31:
				t[s.size()] = 0;
				s.add(name[t[s.size()]]);
				t[s.size()] = 1;
				s.add(name[t[s.size()]]);
				t[s.size()] = 2;
				s.add(name[t[s.size()]]);
				t[s.size()] = 3;
				s.add(name[t[s.size()]]);
				cnt +=4;
				break;
			case 39:
				t[s.size()] = 9;
				s.add(name[t[s.size()]]);
				cnt++;
				break;
			case 12:
				t[s.size()] = 12;
				s.add(name[t[s.size()]]);
				cnt++;
				break;
			case 13:
				t[s.size()] = 15;
				s.add(name[t[s.size()]]);
				cnt++;
				break;
			default:
				break;
			}
		}

		switch(GmudWorld.bs.zdp.getEquipedSkill(3).id)
		{
		case 25:
			t[s.size()] = 18;
			s.add(name[t[s.size()]]);
			t[s.size()] = 19;
			s.add(name[t[s.size()]]);
			cnt+=2;
			break;
		case 26:
			t[s.size()] = 22;
			s.add(name[t[s.size()]]);
			t[s.size()] = 23;
			s.add(name[t[s.size()]]);
			cnt+=2;
			break;
		case 20:
			t[s.size()] = 21;
			s.add(name[t[s.size()]]);
			cnt++;
			break;
		case 36:
			t[s.size()] = 20;
			s.add(name[t[s.size()]]);
			cnt++;
			break;
		default:
			break;
		}

		ArrowButton[] tw = new ArrowButton[cnt];
		for(i=0;i<cnt;i++)
		{
			tw[i] = new ArrowButton((GmudGame) GmudWorld.game, 49, 45+12*i, 60, 12){
				final int a = i;
				@Override
				public void draw() {
					this.drawBackground();
					BLGGraphics g = (BLGGraphics) game.getGraphics();
					g.drawText(name[t[a]], x+8, y, FontSize.FT_12PX);
				}
			};
		}

		return tw;
	}

	public static void process(int index)
	{
		switch(index)
		{
		case 0://震字诀
			ViewScreen.setText(GmudWorld.bs.bsp("突然$N双手左右连划，一个圆圈已将$n套住，太极拳的震字诀随即使出！"));
			GmudWorld.bs.setStatus(new ZhenZiJue());
			break;
		case 1://挤字诀
			ViewScreen.setText(GmudWorld.bs.bsp("$N右脚实，左脚虚，运起 [挤] 字诀，粘连粘随，右掌翻出已搭住$n左手！"));
			GmudWorld.bs.setStatus(new JiZiJue());
			break;
		case 2://阴阳诀
			ViewScreen.setText(GmudWorld.bs.bsp("[太极阴阳少人修，吞吐开合问刚柔] ，这阴阳决使用时刚柔并济，委时变幻莫测！"));
			GmudWorld.bs.setStatus(new YinYangJue());
			break;
		case 3://乱环诀
			ViewScreen.setText(GmudWorld.bs.bsp("$N将一个个太极圆圈发出，以四两之微力，拨动$n的千斤巨力 ！"));
			GmudWorld.bs.setStatus(new LuanHuanJue());
			break;
		case 4://缠字诀
			ViewScreen.setText(GmudWorld.bs.bsp("$N每一招均是以弧形刺出，弧形收回，绵绵不绝，逐步向$n收紧！"));
			GmudWorld.bs.setStatus(new 缠字诀());
			break;
		case 5://连字诀
			ViewScreen.setText(GmudWorld.bs.bsp("$N手中剑不断划圈，剑光圈一个未消另一个再生，这光圈剑阵一出，真是守御滴水不漏！"));
			GmudWorld.bs.zdp.evd_bouns += GmudWorld.bs.zdp.skills[30] / 10;
			GmudWorld.bs.zdp.hit_bouns += 10;
			GmudWorld.bs.zdp.buff[0] = 8;
			GmudWorld.bs.setStatus(new RoundOverStatus());
			break;
		case 6://三环套月
			ViewScreen.setText(GmudWorld.bs.bsp("$N剑尖斜指，使出太极剑绝学三环套月，连环三招直向$n攻来！"));
			GmudWorld.bs.setStatus(new SanHuanTaoYue(98));
			break;
		case 7://流星飞掷

			if(GmudWorld.bs.zdp.getStr()<32)
			{
				ViewScreen.setText(GmudWorld.bs.bsp("你膂力不够，不能使用流星飞掷！"));
				GmudWorld.game.setScreen(new ViewScreen(GmudWorld.game));
				return;
			}
			ViewScreen.setText(GmudWorld.bs.bsp("$N运足内力长啸一声，将手中$w向$n猛掷过来，去势急劲，当真快若闪电！"));

			GmudWorld.bs.setStatus(new LiuXingFeiZhi());
			break;
		case 8://雪花六出

			ViewScreen.setText(GmudWorld.bs.bsp("$N长啸一声，使出雪山神技雪花六出，依照雪花六角之型，一瞬间向$n刺出数剑！"));
			GmudWorld.bs.setStatus(new XueHuaLiuChu());
			break;
		case 9://神倒鬼跌
			ViewScreen.setText(GmudWorld.bs.bsp("$N使出绝技神倒鬼跌三连环，一揪一抓一拌接连三招，据说神仙也要摔个跟头！"));
			GmudWorld.bs.setStatus(new ShenDaoGuiDie());
			break;
		case 10://落英缤纷
			ViewScreen.setText(GmudWorld.bs.bsp("$N手中$w轻舞，划出一个又一个圈子，$n跟著鞭圈一绕，登时眼花缭乱！"));
			GmudWorld.bs.setStatus(new LuoYingBinFen());
			break;
		case 11://柳浪闻莺
			ViewScreen.setText(GmudWorld.bs.bsp("$N使出柳浪闻莺绝技，向$n同时击出两掌一刀，一时间刀浪如柳，掌风如莺！"));
			GmudWorld.bs.setStatus(new LiuLangWenYing());
			break;
		case 12://化掌为刀

			ViewScreen.setText(GmudWorld.bs.bsp("$N呼喝一声，将混元一气功逼到双掌，掌心闪出奇异的光芒，掌缘锋利竟如钢刀一般！"));
			GmudWorld.bs.zdp.hit_bouns += (GmudWorld.bs.zdp.skills[1]+GmudWorld.bs.zdp.skills[12])/20;
			GmudWorld.bs.zdp.buff[2]+=4;
			GmudWorld.bs.setStatus(new RoundOverStatus());
			break;
		case 13://八卦刀影掌
			ViewScreen.setText(GmudWorld.bs.bsp("$N使出八卦门刀影掌绝学，一时间刀光闪闪，掌风呼啸，刀光掌影直向$n扑来！"));
			GmudWorld.bs.setStatus(new LiuLangWenYing());
			break;
		case 14://八卦刀影掌
			ViewScreen.setText(GmudWorld.bs.bsp("$N使出八卦门刀影掌绝学，一时间刀光闪闪，掌风呼啸，刀光掌影直向$n扑来！"));
			GmudWorld.bs.setStatus(new LiuLangWenYing());
			break;
		case 15://化掌为刀

			ViewScreen.setText(GmudWorld.bs.bsp("$N呼喝一声，将混元一气功逼到双掌，掌心闪出奇异的光芒，掌缘锋利竟如钢刀一般！"));
			GmudWorld.bs.zdp.str_bouns += GmudWorld.bs.zdp.skills[13]/5;
			GmudWorld.bs.zdp.hit_bouns += GmudWorld.bs.zdp.skills[13]/10;
			GmudWorld.bs.zdp.dz +=3;
			GmudWorld.bs.zdp.buff[6]+=7;
			GmudWorld.bs.setStatus(new RoundOverStatus());
			break;
		case 16://旋风三连斩
			ViewScreen.setText(GmudWorld.bs.bsp("$N刀指苍穹蓄势发力，忽地使出旋风三连斩绝技，身形如风连续劈出三刀！"));
			GmudWorld.bs.setStatus(new SanHuanTaoYue(87));
			break;
		case 17://迎风一刀斩
			ViewScreen.setText(GmudWorld.bs.bsp("$N反手握刀纵声长啸，霎时间天地为之变色，这一刀之势虽然平平，却威力惊人！"));
			GmudWorld.bs.setStatus(new 迎风一刀斩());
			break;
		case 18://雷动九天
			ViewScreen.setText(GmudWorld.bs.bsp("$N默念红莲教义，周身啪啪做响，真气流转间，好像手上的力道加强了！"));
			GmudWorld.bs.zdp.str_bouns += 3+(GmudWorld.bs.zdp.skills[0]+GmudWorld.bs.zdp.skills[25])/10;
			GmudWorld.bs.setStatus(new RoundOverStatus());
			break;
		case 19://红莲出世
			ViewScreen.setText(GmudWorld.bs.bsp("$N合眼默诵红莲教义：去残除恶，唯我光明！忽地睁开双眼，似乎增加了战斗力！"));
			GmudWorld.bs.zdp.hit_bouns += -3+(GmudWorld.bs.zdp.skills[0]+GmudWorld.bs.zdp.skills[25])/10;
			GmudWorld.bs.zdp.dz++;
			GmudWorld.bs.setStatus(new RoundOverStatus());
			break;
		case 20://冰心诀
			ViewScreen.setText(GmudWorld.bs.bsp("$N深吸一口气，全身真气流转，周身竟隐隐发出一片如冰雪般的晶莹之色！"));
			GmudWorld.bs.zdp.def_bouns += (GmudWorld.bs.zdp.skills[0]+GmudWorld.bs.zdp.skills[36]*2)/8;
			GmudWorld.bs.zdp.buff[5]+=9;
			GmudWorld.bs.setStatus(new RoundOverStatus());
			break;
		case 21://三花
			ViewScreen.setText(GmudWorld.bs.bsp("$N凝神运功，周身泛出一片七彩绚烂之色，似乎披了一见五彩霓裳，身法骤然变快！"));
			GmudWorld.bs.zdp.agi_bouns += GmudWorld.bs.zdp.skills[20] / 10;
			GmudWorld.bs.zdp.evd_bouns += (GmudWorld.bs.zdp.skills[0]+GmudWorld.bs.zdp.skills[20]*2)/10 -5;
			GmudWorld.bs.zdp.buff[2]+=(GmudWorld.bs.zdp.skills[0]+GmudWorld.bs.zdp.skills[20])/40;
			GmudWorld.bs.setStatus(new RoundOverStatus());
			break;
		case 22://忍术烟幕
			ViewScreen.setText(GmudWorld.bs.bsp("$N双手连挥，一掌击在地上，腾的一声爆起一阵烟幕，$n眼前顿时一片昏暗！"));

			double hit_rate = 0.8 + 0.2 * (((double)GmudWorld.bs.zdp.fp - GmudWorld.bs.bdp.fp) / (double)(GmudWorld.bs.zdp.fp + GmudWorld.bs.bdp.fp + 1));
			
			Log.i("忍术烟幕","命中率=" + hit_rate);
			
			boolean hit = Math.random() < hit_rate;
			
			
			if(hit)
			{
				GmudWorld.bs.zdp.buff[3]+=(GmudWorld.bs.zdp.skills[0]+GmudWorld.bs.zdp.skills[26])/40;
				GmudWorld.bs.bdp.hit_bouns -= 20;
				GmudWorld.bs.setStatus(new RoundOverStatus());
			}
			else
			{
				GmudWorld.bs.setStatus(new Status(){
					@Override
					public boolean execute() {
						ViewScreen.setText(GmudWorld.bs.bsp("没想到$n内力深厚，当即猛推一掌，震散了烟雾！"));
						GmudWorld.bs.zdp.dz+=3;
						StuntOver();
						return false;
					}
				});
			}

			break;
		case 23://忍法影分身
			ViewScreen.setText(GmudWorld.bs.bsp("$N双手合掌身形急转，一声低喝中，身影一分为二，$n一时竟分辩不出真假！"));
			GmudWorld.bs.zdp.buff[4]+=(GmudWorld.bs.zdp.skills[0]+GmudWorld.bs.zdp.skills[26])/40;
			GmudWorld.bs.setStatus(new RoundOverStatus());
			break;
		}
		GmudWorld.bs.zdp.fp -= cost[index];
		GmudWorld.bs.zdp.sz[index] += cd[index];
	}

}
