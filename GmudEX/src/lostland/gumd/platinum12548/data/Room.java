/**
 * 安卓白金英雄坛制作组 <p>
 * 文件名：ItemRoom.java <p>
 * 创建时间：2014-1-15 上午10:05:27 <p>
 * 所属项目：GmudEX <p>
 * @author 12548 <p>
 */
package lostland.gumd.platinum12548.data;

/**
 * 类名：Room <p>
 * 说明：
 * @author 12548
 */
public class Room {


	private ItemGenerator item()
	{
		return new ItemGenerator();
	}

	final int ITMTYPE_FOOD = 0;
	final int ITMTYPE_MEDIC = 1;
	final int ITMTYPE_WEAPON = 2;
	final int ITMTYPE_EQUIPMENT = 3;
	final int ITMTYPE_OTHER = 4;

	private class ItemGenerator {

		public ItemGenerator() {
			// TODO 物品生成预处理
		}

		//设置物品名
		ItemGenerator name(String s)
		{
			//TODO: 未实现
			return this;
		}

		//设置物品类型
		ItemGenerator type(int id)
		{
			//TODO: 未实现
			return this;
		}

		//设置物品子类型（装备位置）
		ItemGenerator subtype(int id)
		{
			//TODO: 未实现
			return this;
		}

		//是否可丢弃
		ItemGenerator dropable(boolean b)
		{
			//TODO: 未实现
			return this;
		}

		//设置物品描述
		ItemGenerator desc(String s)
		{
			//TODO: 未实现
			return this;
		}

		//设置物品售价
		ItemGenerator cost(int value)
		{
			//TODO: 未实现
			return this;
		}

		//装备效果
		ItemGenerator equip_effect(EffectGenerator e)
		{
			//TODO: 未实现
			return this;
		}

		//使用效果
		ItemGenerator use_effect(EffectGenerator e)
		{
			//TODO: 未实现
			return this;
		}

		//携带效果
		ItemGenerator carry_effect(EffectGenerator e)
		{
			//TODO: 未实现
			return this;
		}

		//TODO: 更多。。。。。。

	}


	private EffectGenerator effect(int effectid)
	{
		return new EffectGenerator(effectid);
	}

	final int EFFECT_RESERVED = 0;                                        //保留，无效果
	final int EFFECT_ADDTO_ATTR = 1;                                  //增加属性
	final int EFFECT_ADDTO_ATTR_BOUNS = 2;                   //增加临时属性（正常以上难度下战斗结束后消失）
	final int EFFECT_RECOVER_SP = 3;                                  //恢复体力
	final int EFFECT_CURE = 4;                                                //治疗伤势
	final int EFFECT_RECOVER_FP = 5;                                  //恢复内力
	final int EFFECT_ADDTO_XATTR = 6;                               //增加特殊属性（只对主角有效的属性）
	final int EFFECT_SEE_SOMETHING_CLEAR = 7;              //能看见葵花宝典（老花镜）
	final int EFFECT_FISHING = 8;                                            //可以用来钓鱼（鱼竿）
	final int EFFECT_HOLD_FISH = 9;                                     //可以用来装鱼（鱼篓）


	//效果作用目标
	enum EffectTarget{ 
		SELF,
		ENEMY
	}

	final int ATTR_STR = 0;
	final int ATTR_AGI = 1;
	final int ATTR_WXG = 2;
	final int ATTR_BON = 3;
	final int ATTR_ATK = 4;
	final int ATTR_DEF = 5;
	final int ATTR_HIT = 6;
	final int ATTR_EVD = 7;
	final int ATTR_EXP = 8;
	final int ATTR_ADS = 9;
	final int ATTR_MAXHP = 10;
	final int ATTR_MAXFP = 11;

	final int XATTR_FACE = 0;
	final int XATTR_POT = 1;
	final int XATTR_FOOD = 2;
	final int XATTR_WATER = 3;


	private class EffectGenerator {
		EffectGenerator(int effectid)
		{

		}

		//效果值
		EffectGenerator value(int value)
		{
			//TODO: 未实现
			return this;
		}

		//效果比例值
		EffectGenerator value(float rate)
		{
			//TODO: 未实现
			return this;
		}

		//效果作用属性
		EffectGenerator attr(int attr)
		{
			//TODO: 未实现
			return this;
		}

		// 效果作用目标
		EffectGenerator target(EffectTarget value)
		{
			//TODO: 未实现
			return this;
		}


	}


	void exec()
	{
		// 把编辑器生成的代码放到这里


		//example:
		item()
		.name("三角石板")
		.type(ITMTYPE_OTHER)
		.desc("一块年代久远的石板")
		.dropable(false);

		//例子：大留之剑，加10点攻击力
		item()
		.name("大留之剑")
		.type(ITMTYPE_WEAPON)
		.desc("大留使用的宝剑")
		.subtype(6)
		.dropable(true) //可丢弃，可以省略，默认可丢弃
		.cost(100000)
		.equip_effect(
				effect(EFFECT_ADDTO_ATTR)
				.attr(ATTR_ATK)
				.value(10)
				.target(EffectTarget.SELF) //效果作用目标自身，也可以省略，默认为SELF
				);

		//生肌膏恢复50%血量
		item()
		.name("生肌膏")
		.type(ITMTYPE_MEDIC)
		.desc("黑黝黝的药膏")
		.cost(3000)
		.use_effect(
				effect(EFFECT_CURE)
				.value(0.5f)
				);

		//大留秘药，吃了之后永久降低10血量，提升5点相貌
		item()
		.name("大留秘药")
		.type(ITMTYPE_MEDIC)
		.desc("大留秘制的药丸")
		.cost(1000000000)
		.use_effect(
				effect(EFFECT_ADDTO_ATTR)
				.attr(ATTR_MAXHP)
				.value(-10)
				)
		.use_effect(
				effect(EFFECT_ADDTO_XATTR)
				.attr(XATTR_FACE)
				.value(5)
				);

		
		//老花镜
		item()
		.name("老花镜")
		.type(ITMTYPE_EQUIPMENT)
		.subtype(0)
		.desc("一副老花镜,戴上去试试看,一些东西变得清楚了")
		.cost(100)
		.equip_effect(effect(EFFECT_SEE_SOMETHING_CLEAR));
		
		
		//鱼篓
		item()
		.name("鱼篓")
		.type(ITMTYPE_OTHER)
		.desc("一个草编的鱼篓,专门用来装鱼的")
		.cost(100)
		.carry_effect(effect(EFFECT_HOLD_FISH));

	}





	/**
	 * 
	 */
	public Room() {
		// TODO 自动生成的构造函数存根
	}

}
