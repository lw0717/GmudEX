/**
 * 安卓白金英雄坛制作组 <p>
 * 文件名：AttackStatus.java <p>
 * 创建时间：2013-8-7 下午8:22:58 <p>
 * 所属项目：GmudTest <p>
 * @author 12548 <p>
 */
package lostland.gumd.platinum12548.battle.proc;

import android.util.Log;
import lostland.gumd.platinum12548.GameConstants;
import lostland.gumd.platinum12548.GmudWorld;
import lostland.gumd.platinum12548.battle.ViewScreen;
import lostland.gumd.platinum12548.data.Gesture;
import lostland.gumd.platinum12548.data.Npc;
import lostland.gumd.platinum12548.data.Skill;

/**
 * 类名：AttackStatus <p>
 * 说明：
 * @author 12548
 */
public class AttackStatus implements Status {

	public static Status ts;
	
	public static Gesture ag;
	public Skill as;
	
	/**
	 * 
	 */
	public AttackStatus(Status s) {
		Log.d("AttackStatus","constructing");
		ts = s;
		as = GmudWorld.bs.zdp.getAttackSkill();
		Log.d("AttackStatus","constructed");
	}

	/* （非 Javadoc）
	 * @see lostland.gumd.platinum12548.battle.proc.Status#execute()
	 */
	@Override
	public boolean execute() {
		
		if(evd())
			ViewScreen.setText(GmudWorld.bs.bsp(GmudWorld.bs.bdp.getEquipedSkill(2).chooseAGesture().c));
		else if(blk())
			ViewScreen.setText(GmudWorld.bs.bsp(GmudWorld.skill[Skill.KIND_ZHAOJIA].chooseAGesture().c));
		else if(GmudWorld.bs.bdp.buff[4]>0 && GmudWorld.rand.nextBoolean())
		{
			ViewScreen.setText(GmudWorld.bs.bsp("$N没料到这一击却打空，原来打中的只是个影子"));
		}
		else
		{
			int a;
			if(GmudWorld.bs.zdp.fp>0 && GmudWorld.bs.zdp.ads>0){
				a = (int) ((GmudWorld.bs.zdp.getStr() + GmudWorld.bs.zdp.ads/2 + GmudWorld.bs.zdp.getAtk() + Math.min(GmudWorld.bs.zdp.fp/20.0, 150)) *
					(ag.force1 / 100.0) + (GmudWorld.bs.zdp.getStr() + GmudWorld.bs.zdp.ads + GmudWorld.bs.zdp.getAtk() + 
							Math.min(GmudWorld.bs.zdp.fp/20.0, 150)) *
					(ag.force2 / 100.0 + 0.8));
				GmudWorld.bs.zdp.fp -= GmudWorld.bs.zdp.ads;
				if(GmudWorld.bs.zdp.fp<0)GmudWorld.bs.zdp.fp=0;
			}
			else
				a = (int) ((GmudWorld.bs.zdp.getStr()  + GmudWorld.bs.zdp.getAtk()) * ((ag.force1+ag.force2) / 100.0 + 0.8));
			
			int dmg = (int) (a/2.0 + Math.random()*a/2.0);
			
			
			GmudWorld.bs.bdp.dmg(dmg,ag.dmgfix);
			
			GmudWorld.bs.zdp.sp += GmudWorld.bs.zdp.skills[41]*dmg / 100;
			if(GmudWorld.bs.zdp.sp>GmudWorld.bs.zdp.hp)GmudWorld.bs.zdp.sp=GmudWorld.bs.zdp.hp;
			
			ViewScreen.setText(GmudWorld.bs.bsp(get_damage_string(ag.dmg_type,dmg))+shfj(GmudWorld.bs.bdp) + "#hit#");
			
			
		}
		
		
		
		GmudWorld.bs.setStatus(new DummyStatus());
		GmudWorld.game.setScreen(new ViewScreen(GmudWorld.game));
		return false;
	}

	
	boolean evade()
	{
		double basic_hit_rate;
		double hit_agility_addition;
		double evade_agility_addition;
		double basic_art_suppress_rate;
		double advanced_art_suppress_rate;
		double hit_rate;

		basic_hit_rate=GameConstants.BASE_BASIC_HIT_RATE-GmudWorld.bs.bdp.agi*GameConstants.INNATE_AGI_EVADE_RATE;
		Log.i("attack_process", "基本命中率="+basic_hit_rate);
		if(basic_hit_rate<GameConstants.BASIC_HIT_RATE_LOWERLIMIT)
			basic_hit_rate=GameConstants.BASIC_HIT_RATE_LOWERLIMIT;
		hit_agility_addition=GmudWorld.bs.zdp.getAgi()*GameConstants.HIT_RATE_AGI_ADDITION_RATE;
		Log.i("attack_process", "命中敏捷加成值="+hit_agility_addition);
		evade_agility_addition=GmudWorld.bs.bdp.getAgi()*GameConstants.EVADE_RATE_AGI_ADDITION_RATE;
		Log.i("attack_process", "回避敏捷加成值="+evade_agility_addition);
		basic_art_suppress_rate=Math.pow((1.0*
				GmudWorld.bs.zdp.skills[as.kind + as.subkind]+1.0)/(1.0+GmudWorld.bs.bdp.skills[Skill.KIND_QINGGONG]),
				1.0/GameConstants.BASIC_ART_HIT_RATE_SUPPRESS_REDUCE_RATE);
		Log.i("attack_process", "基本武学命中压制="+basic_art_suppress_rate);
		advanced_art_suppress_rate=
				((1.0+GmudWorld.bs.zdp.skills[as.id]+
						GmudWorld.bs.zdp.skills[GmudWorld.bs.zdp.getEquipedSkill(2).id])*
						GameConstants.ADVANCED_QINGGONG_HIT_QUE)/
				((1.0+GmudWorld.bs.bdp.skills[GmudWorld.bs.zdp.getEquipedSkill(2).id])*  
						GameConstants.ADVANCED_QINGGONG_EVADE_QUE);
		Log.i("attack_process", "进阶武学命中压制="+advanced_art_suppress_rate);
		double extra_hit_rate_suppress_rate=Math.pow((GmudWorld.bs.zdp.hit+100+ag.hitfix)*1.0/(100+GmudWorld.bs.bdp.evd), 
				1.0/GameConstants.ADDITIONAL_HIT_RATE_SUPPRESS_REDUCE_RATE);
		hit_rate=basic_hit_rate*(1+hit_agility_addition)*(1-evade_agility_addition)*
				basic_art_suppress_rate*advanced_art_suppress_rate*extra_hit_rate_suppress_rate;
		Log.i("attack_process", "命中率="+hit_rate);
		boolean isHit=Math.random()<=hit_rate;
		Log.i("attack_process", "命中="+isHit);

		return !isHit;
	}
	
	boolean block()
	{
		
		
		double basic_block_rate=GameConstants.BASE_BASIC_BLOCK_RATE;
		Log.i("attack_process", "基本格挡率："+basic_block_rate);
		double basic_attributes_block_suppress=Math.pow((GmudWorld.bs.bdp.getAgi()+GmudWorld.bs.bdp.getBon()+GmudWorld.bs.bdp.getStr())*1.0/
				(GmudWorld.bs.zdp.getAgi()+GmudWorld.bs.zdp.getBon()+GmudWorld.bs.zdp.getStr()),1.0/GameConstants.BASIC_ABILITY_BLOCK_RATE_SUPPRESS_REDUCE_RATE);
		Log.i("attack_process", "基本属性格挡压制："+basic_attributes_block_suppress);
		double basic_art_block_suppress=
				Math.pow(1.0*GmudWorld.bs.bdp.skills[Skill.KIND_ZHAOJIA]/GmudWorld.bs.zdp.skills[as.getBasicSkill().id],1.0/GameConstants.BASIC_ART_BLOCK_RATE_SUPPRESS_REDUCE_RATE);
		Log.i("attack_process", "基本武学格挡压制："+basic_art_block_suppress);
		double debug11=GmudWorld.bs.bdp.skills[GmudWorld.bs.bdp.getAttackSkill().id];
		double debug221=GmudWorld.bs.zdp.skills[as.id];
		double debug22=debug221;
		double advanced_art_block_suppress=debug11/debug22;
		Log.i("attack_process", "进阶武学格挡压制："+advanced_art_block_suppress);
		double block_rate= basic_block_rate* basic_attributes_block_suppress* basic_art_block_suppress* advanced_art_block_suppress;
		Log.i("attack_process", "格挡率："+block_rate);
		
		
		boolean isBlocked=Math.random()<=block_rate;
		Log.i("attack_process", "格挡："+isBlocked);

		
		return isBlocked;
	}
	
	
	public static String get_damage_string(int damage_type,int dmg)
	{
		String s[];
		s=new String[9];
		s[0] = "结果没有造成伤害";
		
		switch(damage_type)
		{
		case 2:
			s[1] = "只轻轻地刺破$n皮肉";
			s[2] = "在$n身上刺出一个创口";
			s[3]="结果$n被刺入了寸许";
			s[4]="结果刺得$n退了几步";
			s[5]="结果刺出一个血肉模糊的伤口";
			s[6]="结果$n被刺了个透明窟窿，鲜血飞溅";
			s[7]="结果$n被刺了个透明窟窿，鲜血飞溅";
			s[8]="结果$n被刺了个透明窟窿，鲜血飞溅";
			break;
		case 3:
			s[1] = "只是轻轻地碰到$n";
			s[2] = "在$n的伤处造成一处瘀青";
			s[3]="结果一击命中，$n被打肿了一块老高";
			s[4]="结果一击命中，$n痛苦地闷哼了一声";
			s[5]="结果『砰』的一声，$n退了两步";
			s[6]="结果$n连退了好几步，差一点摔倒";
			s[7]="结果重重的击中，$n吐出一口鲜血";
			s[8]="结果一声巨响，$n像捆稻草般飞了出去";
			break;
		case 5:
			s[1] = "只是抓破$n的一点皮";
			s[2]="结果$n被抓出了五条淡淡的血痕";
			s[3]="结果一爪命中，$n被抓出了五条血沟";
			s[4]="结果抓下了$n一小块皮肉";
			s[5]="结果$n皮开肉破，鲜血流了下来";
			s[6]="结果$n被抓出了五个血洞，鲜血急喷";
			s[7]="结果$n被连皮带肉抓下了一大块，露出了骨头";
			s[8]="结果『喀嚓』一声，$n的骨头被抓得粉碎";
			break;
		case 1:
			s[1] = "只轻轻地划破$n的皮肉";
			s[2] = "划出一道细长的血痕";
			s[3]="结果$n被割出了一道伤口";
			s[4]="结果$n被划出了一道血淋淋的伤口";
			s[5]="结果$n被划出了一道又长又深的伤口";
			s[6]="结果$n被砍出了一道深及见骨的可怕伤口";
			s[7]="结果$n被砍出了一道深及见骨的可怕伤口";
			s[8]="结果$n被砍出了一道深及见骨的可怕伤口";
			break;
		case 4:
			s[1] = "$n退了半步，毫发无损";
			s[2] = "给$n造成一处瘀伤";
			s[3]="结果一击命中，$n痛得弯下腰";
			s[4]="结果$n痛苦地闷哼了一声，显然受了点内伤";
			s[5]="结果$n摇摇晃晃，一跤摔倒在地";
			s[6]="结果$n脸色一下变得惨白，连退了好几步";
			s[7]="结果『轰』的一声，$n口中鲜血狂喷而出";
			s[8]="结果$n一声惨叫，像滩软泥般塌了下去";
			break;
		case 0:
		default:
			s[1] = "只对$n勉强造成轻微的伤害";
			s[2]="结果对$n造成了轻微的伤害";
			s[3]="结果对$n造成了一处伤害";
			s[4]="结果对$n造成了颇为严重的伤害";
			s[5]="结果对$n造成了相当严重的伤害";
			s[6]="结果对$n造成了十分严重的伤害";
			s[7]="结果对$n造成了极其严重的伤害";
			s[8]="结果对$n造成了非常可怕的严重伤害";
		}
		
		if(dmg==0)
			return s[0];
		else if(dmg<10)
			return s[1];
		else if(dmg<20)
			return s[2];
		else if(dmg<40)
			return s[3];
		else if(dmg<80)
			return s[4];
		else if(dmg<120)
			return s[5];
		else if(dmg<160)
			return s[6];
		else if(dmg<200)
			return s[7];
		else
			return s[8];
	}

	String shfj(Npc p)
	{
		String t;
		String s1[] = new String[]{"看起来充满活力，一点也不累",
				"似乎有些疲惫，但是十分有活力",
				"看起来可能有些累了",
				"动作似乎开始有点不太灵光",
				"气喘嘘嘘，看起来状况并不太好",
				"十分疲惫，看来需要好好休息了",
				"已经头重脚轻，正在勉力支撑",
				"看起来已经力不从心了",
				"摇头晃脑，眼看就要倒在地上",
				"已经陷入半昏迷状态"
				};
		String s2[] = new String[]{"看起来气血充盈，并没有受伤",
				"似乎受了点轻伤，不过看不出来",
				"看起来可能受了点轻伤",
				"受了几处伤，不过似乎并不碍事",
				"受伤不轻，看起来状况并不太好",
				"动作开始散乱，看来受的伤不轻",
				"已经伤痕累累，正在勉力支撑",
				"受伤相当重，只怕有生命危险",
				"伤重之下已经难以支撑",
				"受伤过重，已经命在旦夕了",
				"受伤过重，随时都可能断气"
				};
		int t1 = p.maxhp - p.sp;
		int t2 = p.maxhp - p.hp;
		
		if(t2>=p.maxhp)
			t = s2[s2.length-1];
		else if(t1>=t2)
		{
			if(t1>=p.maxhp)
				t=s1[s1.length-1];
			else
				t=s1[(int) (1.0*t1*s1.length/p.maxhp)];
		}
		else
		{
			t=s2[(int) (1.0*t2*s2.length/p.maxhp)];
		}
		return GmudWorld.bs.bsp("（$n"+t+"）");

	}

	
	boolean evd()
	{
		Log.w("Attack", "正在获取命中率");
		double hr = GmudWorld.bs.zdp.getBattleHit() / GmudWorld.bs.bdp.getBattleEvd() * 0.5;
		Log.i("attack_process", "命中率="+hr);
		boolean isHit=Math.random()<=hr;
		Log.i("attack_process", "命中="+isHit);
		return !isHit;
	}
	
	boolean blk()
	{
		double hr = GmudWorld.bs.bdp.getBattleBLK() / GmudWorld.bs.zdp.getBattleHit() * 0.1;
		Log.i("attack_process", "格挡率："+hr);
		
		boolean isBlocked=Math.random()<=hr;
		Log.i("attack_process", "格挡："+isBlocked);
		return isBlocked;
	}

}
