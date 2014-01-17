/**
 * 安卓白金英雄坛制作组 <p>
 * 文件名：Npc.java <p>
 * 创建时间：2013-7-23 上午11:14:02 <p>
 * 所属项目：GmudTest <p>
 * @author 12548 <p>
 */
package lostland.gumd.platinum12548.data;

import java.util.Arrays;

import android.util.Log;
import lostland.gumd.platinum12548.GmudWorld;

/**
 * 类名：Npc <p>
 * 说明：
 * @author 12548
 */
public class Npc {

	public static final int NPC_COUNT = 127;
	
	
	public int id;                 // id
	public String name;       // 名字
	public int faction = 0;         //阵营
	public int sex = 0;              //性别
	public int age = 14;              //年龄
	public int fame = 0;            //声望
	
	public String des;          //描述
	
	public int hit=0;                //命中
	public int evd=0;              //闪避
	public int atk=0;              //攻击
	public int def=0;               //防御
	public int exp=0;              //经验
	public int ads=0;             //加力
	
	public int hit_bouns=0;
	public int evd_bouns=0;
	public int atk_bouns=0;
	public int def_bouns=0;
	
	public int str = 20;             //膂力
	public int agi = 20;            //敏捷
	public int wxg = 20;            //悟性
	public int bon = 20;            //根骨
	
	public int str_bouns=0,agi_bouns=0,wxg_bouns=0,bon_bouns=0; //额外膂力、敏捷、悟性、根骨
	
	
	public int maxhp = 100;       //生命上限
	public int maxfp = 100;       //内力上限
	
	public int gold = 0;          // 金钱
	
	public int skills[] = new int[42];       //技能等级（所有技能）
	public int items[]  = new int[0];     //物品
	
	public int skillsckd[] = new int[]{-1,-1,-1,-1,-1};  //勾选技能
	public int itemsckd[]  = new int[] {0}; //勾选物品
	
	public int trading;      //交易列表id
	
	public int sells[]  = new int[0];
	
	/////////////////////////////////////////////////////////////////////////
	
	public boolean qingjiaoable;
	public boolean dead;
	
	public int sp;       //体力
	public int hp;        //生命
	public int fp;          //内力
	
	
	/////////////////////////////////////////////////////////////////////////
	
	public int sz[] = new int[24],dz=0;
	
	public int buff[] = new int[]{0,0,0,0,0,0,0,0,0,0};
	
	
	
	public double temp_dmg_multiplier = 1.0;
	
	public Npc() {
		Arrays.fill(sz, 0);
	}
	
	public void badman()
	{
		str = sex==0?24:14;
		agi = sex==0?18:30;
		wxg = sex==0?22:26;
		bon = sex==0?26:20;
		
		Log.e("badman", "a");
		
		atk = (int) (Math.random() * 20);
		def = (int) (Math.random() * 20);
		
		int skl[][] ={
				{} , {0,1,7,8,3,15,11,13,14},{0,1,7,8,2,33,30,31,32},{0,1,7,8,6,16,17,19,20},{0,1,7,8,3,28,29,27,26},{0,1,7,8,5,21,23,24,25},{0,1,7,8,2,35,38,39,36}
		};
		
		exp = GmudWorld.mc.exp;
		
		this.skills = new int[GmudWorld.skill.length];
		
		Arrays.fill(skills, 0);
		
		int s=0;
		
		Log.e("badman", "b");
		
		for(int i :GmudWorld.mc.skills)
		{
			if(i>s)
			{
				s=i;
			}
		}
		int a;
		

		a = s;
		
		
		for(int b:skl[faction])
		{
			skills[b] = (int) (a + 1 + Math.random()*3);
		}
		
		Log.e("badman", "d");
		
		int ckd[][] ={
				{},{13,11,15,14,11},{31,30,33,32,30},{19,17,16,20,17},{27,29,28,26,29},{24,23,21,25,23},{39,38,35,36,38}
		};
		this.skillsckd = ckd[faction];
		
		this.maxfp = this.getMaxmaxfp();
		this.maxhp = this.getMaxhp();
		
		Log.e("badman", "e");
		
		int f[] = {0,0,200,160,140,100,120};
		int h[] = {0,50,0,10,20,40,30};
		
		this.maxfp += f[faction];
		this.maxhp += h[faction];
		
		ads = skillsckd[3]/2+skills[Skill.KIND_NEIGONG]/4;
		
		this.gold = 100;
		
		Log.e("badman", "f");
	}
	
	public Gesture cag(int wg)
	{
		int t = chooseAGesture(wg);
		Log.w("choosing gesture",""+t);
		return GmudWorld.zs[t];
	}
	
	public Gesture  cg()
	{
		return cag(getAttackSkill().id);
	}
	
	
	public int chooseAGesture(int wg)
	{
		int l = skills[wg];
		Skill t = GmudWorld.skill[wg];
		
//		Log.w("choosing from",""+t.name);
		
//		if(Gesture.b)
//		{
//			Gesture.zsc = 0;
//			GmudWorld.zs = new Gesture[222];
//			Gesture.init();
//		}
		
		
		int i = t.zse;
		
//		Log.w("testing from",""+i+"in"+GmudWorld.zs.length);
		
		while(GmudWorld.zs[i].llimit>l){
//			Log.w("testing llimit:",""+i);
			i--;
		}
		
//		Log.w("choosing by",""+i);
		
		int s = i - t.zss + 1;
		
		int r = t.zss + (int)(Math.random() * s);
		
//		Log.w("chose:",""+r);
		return r;
	}
	
	
	public void copy(int index)
	{
		this.str = GmudWorld.npc[index].str;
		this.agi = GmudWorld.npc[index].agi;
		this.bon = GmudWorld.npc[index].bon;
		this.wxg = GmudWorld.npc[index].wxg;
		
		this.hit = GmudWorld.npc[index].hit;
		this.evd = GmudWorld.npc[index].evd;
		this.atk = GmudWorld.npc[index].atk;
		this.def = GmudWorld.npc[index].def;
		
		this.maxfp = GmudWorld.npc[index].maxfp;
		this.maxhp = GmudWorld.npc[index].maxhp;
		
		this.setExp(GmudWorld.npc[index].exp);
		
		this.ads = GmudWorld.npc[index].ads;
		
		this.skills = GmudWorld.npc[index].skills;
		this.skillsckd = GmudWorld.npc[index].skillsckd;
		
		this.items = GmudWorld.npc[index].items;
		this.itemsckd = GmudWorld.npc[index].itemsckd;
		
		this.refresh();
	}
	
	public void cure(int val)
	{
		hp+=val;
		if(hp>maxhp)hp=maxhp;
	}
	
	
	public void dmg(int dmg,int fix)
	{
		dmg *= temp_dmg_multiplier;
		temp_dmg_multiplier = 1.0;
		
		
		sp -= dmg;
		if(sp<0)sp=0;
		
		int dd = dmg + fix - getDef() - getBon()/2;
		
		if(dd<0)dd=0;
		
		Log.w("体力伤害：",""+dmg);
		Log.w("受伤伤害：",""+dd);
		
		hp -= dd;
		if(hp<0)hp=0;
	}
	
	public boolean equips(int itmid)
	{
		for(int i : itemsckd)
			if(itmid == i)
				return true;
		return false;
	}
	
	
	
	public boolean expcanlearn(int lv){
		return lv*lv*lv*0.1 - lv*lv*0.2 + lv*0.4 - 2 <exp;
	}
	
	public int getAdsLimit()
	{
		if(skillsckd[3]<10)
			return 0;
		else
			return skills[Skill.KIND_NEIGONG]/4 + skills[skillsckd[3]] / 2;
	}
	
	public int getAgi()
	{
		return agi + skills[Skill.KIND_QINGGONG] / 10+agi_bouns;
	}
	
	public int getAtk()
	{
		int t=0;
		for(int i=0;i<itemsckd.length;i++)
		{
			if(GmudWorld.wp[itemsckd[i]].kind == 2)
				t+=GmudWorld.wp[itemsckd[i]].a3;
		}
		return atk+t + atk_bouns;
	}
	
	public Skill getAttackSkill()
	{
		if(GmudWorld.wp[itemsckd[0]].kind == 2)
			if(skillsckd[1]>-1 && GmudWorld.skill[Skill.eqpbias2[GmudWorld.wp[itemsckd[0]].subkind]].subkind == GmudWorld.skill[skillsckd[1]].subkind)
			{
				Log.i("获取攻击技能（武器对应）：",GmudWorld.skill[skillsckd[1]].name);
				return GmudWorld.skill[skillsckd[1]];
				
			}
			else
			{
				Log.i("获取攻击技能（武器不对应）：", GmudWorld.skill[Skill.eqpbias2[GmudWorld.wp[itemsckd[0]].subkind]].name);
				return GmudWorld.skill[Skill.eqpbias2[GmudWorld.wp[itemsckd[0]].subkind]];
			}
		else
			if(skillsckd[0]>-1)
			{
				Log.i("获取攻击技能（有拳脚）：", GmudWorld.skill[skillsckd[0]].name);
				return GmudWorld.skill[skillsckd[0]];
			}
			else
			{
				Log.i("获取攻击技能（无拳脚）：", GmudWorld.skill[Skill.KIND_QUANJIAO].name);
				return GmudWorld.skill[Skill.KIND_QUANJIAO];
			}

	}
	
	public double getBattleBLK()
	{
		double t = getWC() + getAgi() + getStr() + getBon() + skills[Skill.KIND_ZHAOJIA];
		if(getAttackSkill().id>9)t+=skills[getAttackSkill().id]*2.0/3.0;
		Log.w(name, "战场格挡="+t);
		return t;
	}
	
	public double getBattleEvd()
	{
		double t=getWC()+getAgi()*2+skills[getEquipedSkill(2).getBasicSkill().id]/3.0;
		if(getEquipedSkill(2).id>9)t+=skills[getEquipedSkill(2).id]*2.0/3.0;
		t *= getEvd()*0.01+1.0;
		Log.w(name, "战场回避="+t);
		return t;
	}
	
	
	public double getBattleHit()
	{
		Log.w("Npc", "正在获取战场命中");
		double t=0;
		Log.w("Npc", "正在获取武常");
		t+=getWC();
		Log.w("Npc", "正在获取膂力");
		t+=getStr()*2;
		if(getAttackSkill().id>9)t+=skills[getAttackSkill().id]*2.0/3.0;
		t+=skills[getAttackSkill().getBasicSkill().id]/3.0;
		t*=getHit()*0.01+1.0;
		Log.w(name, "战场命中="+t);
		return t;
	}
	
	
	
	public int getBon()
	{
		return bon + skills[Skill.KIND_NEIGONG] /10+bon_bouns;
	}

	public String getcs()
	{
		
		String s[] = new String[]{"极轻","很轻","不轻","不重","很重","极重"}; 
		
		int t=ads/2+getStr();
		if(GmudWorld.wp[itemsckd[0]].kind == 2)
			t+=GmudWorld.wp[itemsckd[0]].a3;
		
		Log.i("获取出手", "出手等级为"+t);
		
		if(t<20)  
			return s[0];
		else if(t<40)
			return s[1];
		else if(t<60)
			return s[2];
		else if(t<80)
			return s[3];
		else if(t<100)
			return s[4];
		else return s[5];
	}
	
	public int getDef()
	{
		int t=0;
		for(int i=0;i<itemsckd.length;i++)
		{
			if(GmudWorld.wp[itemsckd[i]].kind == 3)
				t+=GmudWorld.wp[itemsckd[i]].a3;
		}
		return def+t+def_bouns;
	}
	
	/**
	 * 获得指定类型的装备技能（不适用于兵刃）
	 * @param eqpkind 装备技能类型（0拳脚1兵刃2轻功3内功4招架）
	 * @return
	 */
	public Skill getEquipedSkill(int eqpkind)
	{
		return this.skillsckd[eqpkind]>-1?GmudWorld.skill[skillsckd[eqpkind]]:GmudWorld.skill[eqpkind+Skill.equip_bias[eqpkind]];
	}
	
	public int getEvd()
	{
		int t=0;
		for(int i=0;i<itemsckd.length;i++)
		{
			if(GmudWorld.wp[itemsckd[i]].kind == 2 || GmudWorld.wp[itemsckd[i]].kind == 3)
				t+=GmudWorld.wp[itemsckd[i]].a5;
		}
		return evd+t+evd_bouns;
	}
	
	
	
	/**
	 * @return exp
	 */
	public int getExp() {
		return exp;
	}
	
	public int getHit()
	{
		int t=0;
		for(int i=0;i<itemsckd.length;i++)
		{
			if(GmudWorld.wp[itemsckd[i]].kind == 2 || GmudWorld.wp[itemsckd[i]].kind == 3)
				t+=GmudWorld.wp[itemsckd[i]].a4;
		}
		return hit+t+hit_bouns;
	}
	
	
	public int getMaxhp()
	{
		int age = this.age<30?this.age:29;
		return ( age - 14 ) * 20 + 100 + maxfp / 4;
	}
	
	
	public int getMaxmaxfp()
	{
		if(skillsckd[3] == -1) return 0;
		int agee = Math.min(this.age, 60);
		return exp / 1000 +skills[Skill.KIND_NEIGONG] / 2 * 10 + skills[skillsckd[3]] * 10 + ( agee - 14 ) * 20;
	}
	
	public int getpj()
	{
		int t=skills[Skill.KIND_ZHAOJIA]+skills[Skill.KIND_QINGGONG];
		if(this.getEquipedSkill(2).id>10)
			t+=skills[this.getEquipedSkill(2).id]*2;
		
		if(GmudWorld.wp[itemsckd[0]].kind == Skill.KIND_BINGREN)
		{
			  t+=skills[this.getEquipedSkill(1).getBasicSkill().id]*2;
			  if(skillsckd[1]>-1 && GmudWorld.skill[Skill.eqpbias2[GmudWorld.wp[itemsckd[0]].subkind]].subkind == GmudWorld.skill[skillsckd[1]].subkind)
				  t+=skills[skillsckd[1]]*4;
			  
			  if(skillsckd[1] == skillsckd[4] && skillsckd[4]>-1)
				  t+=skills[skillsckd[4]]*2;
		}
		else
		{
			 t+=skills[this.getEquipedSkill(0).getBasicSkill().id]*2;
			 if(skillsckd[0]>-1)
				 t+=skills[skillsckd[0]]*4;
			 
			 if(skillsckd[0] == skillsckd[4] && skillsckd[4]>-1)
				  t+=skills[skillsckd[4]]*2;
		}
		
		Log.i("获取评价", "总评等级为"+t/12);
		
		return t/60;
	}
	public int getStr()
	{
		return str + skills[Skill.KIND_QUANJIAO] / 10 +str_bouns;
		
	}
	
	public int getWC()
	{
		double t = 0.0d;
		for(int i=0;i<Skill.SKILL_COUNT;i++)
		{
			t += skills[i];
		}
		t *= 0.1;
		
		t+=exp/400.0;
		
		Log.w(name, "武常="+t);
		return (int) t;
	}
	
	public int getWxg()
	{
		return wxg + skills[Skill.KIND_ZHISHI] / 10+wxg_bouns;
	}
	
	public void give(int index)
	{
		if(this.have(index))return;
		items = GmudWorld.push_back(items, index);
	}
	
	
	public boolean have(int index)
	{
		int i,n = this.items.length;
		for(i=0;i<n;i++)
		{
			if(this.items[i] == index)
				return true;
		}
		return false;
	}
	
	public void rec(int val)
	{
		sp+=val;
		if(sp>hp)sp=hp;
	}
	
	
	public void refresh()
	{
		fp = maxfp;
		hp = maxhp;
		sp = hp;
		ref0();
	}
	
	public void ref0()
	{
		str_bouns = 0;
		agi_bouns = 0;
		wxg_bouns = 0;
		bon_bouns = 0;
		
		hit_bouns = 0;
		evd_bouns = 0;
		atk_bouns = 0;
		def_bouns = 0;
		
		dz = 0;
		
		temp_dmg_multiplier = 1.0;
		
		Arrays.fill(sz, 0);
		Arrays.fill(buff, 0);

	}
	
	///////////////////////////////////////////////////////////////////////////////////////////////
	public void setDifficulty(float multiplier)
	{
		this.str *= multiplier;
		this.agi *= multiplier;
		this.wxg *= multiplier;
		this.bon *= multiplier;
		
		this.atk *= multiplier;
		this.def *= multiplier;
		this.hit *= multiplier;
		this.evd *= multiplier;
		
		this.exp *= multiplier;
		
		this.maxfp *= multiplier;
		this.maxhp *= multiplier;
		
		for(int i=0;i<skills.length;i++)
		{
			skills[i] *= multiplier;
		}

		this.refresh();
	}

	/**
	 * @param exp 要设置的 exp
	 */
	public void setExp(int exp) {
		this.exp = exp;
	}

	public void xiqi()
	{
		int t = Math.min(fp, hp - sp);
		fp-=t;
		sp+=t;
	}
	
}
