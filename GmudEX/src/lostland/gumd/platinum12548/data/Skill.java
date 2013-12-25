/**
 * 安卓白金英雄坛制作组 <p>
 * 文件名：Skill.java <p>
 * 创建时间：2013-7-24 上午9:45:13 <p>
 * 所属项目：GmudTest <p>
 * @author 12548 <p>
 */
package lostland.gumd.platinum12548.data;

import lostland.gumd.platinum12548.GmudWorld;

/**
 * 类名：Skill <p>
 * 说明：
 * @author 12548
 */
public class Skill {

	public static final int SKILL_COUNT = 42;
	
	public static final String skill_name[] = new String[] {
			"基本内功","基本拳脚","基本剑术","基本刀法","基本棍法","基本杖法","基本鞭法","基本轻功","基本招架","读书识字",
	        "驻颜术","八卦刀","八卦游身掌","八阵八卦掌","混元一气功","游龙身法","飞蝶身法","花团鞭法","柳叶刀法","一剪梅花手",
	        "三花聚顶","鹤翔身法","红莲教义","乱披风杖法","太祖长拳","普天同济","扶桑忍术","无法拳","无影遁形","川枫一刀流",
	        "太极剑","太极拳","太极神功","万流归一","玄虚刀法","踏雪无痕","雪山内功","入门十三式","雪山剑法","雪影擒拿手",
	        "猛虎拳","吸血大法"
		};
	
	public static final int skill_zs[] = new int[]{
		0,0,191,195,198,199,196,198,200,202,200,202,202,202,208,212,203,207,0,0,
		0,0,0,7,8,13,14,22,0,0,23,27,28,33,34,40,41,47,48,55,
		0,0,56,61,0,0,62,69,70,75,0,0,0,0,76,80,81,86,90,97,
		101,120,123,140,0,0,141,145,146,151,152,157,0,0,158,161,162,171,172,179,
		180,190,0,0
	};
	
	public static final int skill_kind[] = new int[]{
		0,1,2,2,2,2,2,7,8,9,
		9,2,1,1,0,7,7,2,2,1,
		0,7,9,2,1,0,0,1,7,2,
		2,1,0,7,2,7,0,2,2,1,
		1,9
	};
	
	public static final int skill_subkind[] = new int[]{
		0,0,0,1,2,3,4,0,0,0,
		0,1,0,0,0,0,0,4,1,0,
		0,0,0,3,0,0,0,0,0,1,
		0,0,0,0,1,0,0,0,0,0,
		0,0
	};
	
	public static final int equip_bias[] = new int[] {
		1,1,5,-3,4,4
	};
	
	public static final int eqpbias2[] = new int []{
		0,3,0,0,0,0,2,5,4,6
	};
	
	public static final int equip_pos[] = new int[] {
		3,0,1,1,1,1,1,2,4,5
	};
	
	public static final int KIND_QUANJIAO = 1;
	public static final int KIND_BINGREN = 2;
	public static final int KIND_ZHAOJIA = 8;
	public static final int KIND_QINGGONG = 7;
	public static final int KIND_ZHISHI = 9;
	public static final int KIND_NEIGONG = 0;
	
	public static final int KIND_DAOFA = 1;
	public static final int KIND_JIANFA = 0;
	public static final int KIND_BIANFA = 4;
	public static final int KIND_ZHANGFA = 3;
	public static final int KIND_GUNFA = 2;
	
	public int id;                       //ID
	public int kind;                   //类型
	public int subkind=0;          //子类型
	public String name;             //名称
	public boolean checkable;  //可勾选
	
	public int zss;                    //招式编号起始
	public int zse;                    //招式编号结束
	
	
	public int pos;
	
	public Skill getBasicSkill()
	{
		return GmudWorld.skill[kind + subkind];
	}
	
	
	public static int getBasicSkill(int index)
	{
		return GmudWorld.skill[index].kind +GmudWorld.skill[index].subkind;
	}
	
	
	public Skill() {
		// TODO 自动生成的构造函数存根
	}
	
	public static void init()
	{
		for(int i=0;i<SKILL_COUNT;i++)
		{
			GmudWorld.skill[i] = new Skill();
			GmudWorld.skill[i].id = i;
			GmudWorld.skill[i].name = skill_name[i];
			GmudWorld.skill[i].kind = skill_kind[i];
			GmudWorld.skill[i].subkind = skill_subkind[i];
			GmudWorld.skill[i].checkable = GmudWorld.skill[i].id>9 && GmudWorld.skill[i].kind !=9;
			GmudWorld.skill[i].zss = skill_zs[2*i];
			GmudWorld.skill[i].zse = skill_zs[2*i+1];
			
			GmudWorld.skill[i].pos = equip_pos[GmudWorld.skill[i].kind];
		}
	}
	
	public Gesture chooseAGesture()
	{
		Skill t = this;
		
		int i = t.zse;
		int s = i -t.zss;
		
		int r = t.zss + (int)(Math.random() * s);
		
		return GmudWorld.zs[r];
	}
	
	
}
