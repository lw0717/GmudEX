/**
 * 安卓白金英雄坛制作组 <p>
 * 文件名：GameConstants.java <p>
 * 创建时间：2013-5-20 上午11:42:15 <p>
 * 所属项目：GmudTest <p>
 * @author 12548 <p>
 */
package lostland.gumd.platinum12548;

/**
 * 类名：GameConstants <p>
 * 说明：
 * @author 12548
 */
public interface GameConstants {
	int FBWIDTH  = MapScreen.C_ROWS * 32;
	int FBHEIGHT = MapScreen.C_COLUMNS * 32;
	
	int BG_COLOR = 0x90B057;
	
	
	
	/**
	 * 基础基本命中率。
	 */
	double BASE_BASIC_HIT_RATE=0.85;
	/**
	 * 先天敏捷回避比例常数。
	 */
	double INNATE_AGI_EVADE_RATE=0.02;
	/**
	 * 基本命中率下限常数
	 */
	double BASIC_HIT_RATE_LOWERLIMIT=0.6;
	/**
	 * 命中敏捷加成比例常数
	 */
	double HIT_RATE_AGI_ADDITION_RATE=0.008;
	/**
	 * 回避敏捷加成比例常数
	 */
	double EVADE_RATE_AGI_ADDITION_RATE=0.012;
	/**
	 * 基本武学命中压制比例缩小常数
	 */
	double BASIC_ART_HIT_RATE_SUPPRESS_REDUCE_RATE=2.0;
	/**
	 * 额外命中压制比例缩小常数
	 */
	double ADDITIONAL_HIT_RATE_SUPPRESS_REDUCE_RATE=1.5;
	/**
	 * 基本膂力压制形成常数
	 */
	double BASIC_STR_SUPPRESS_FORM=20.0;
	/**
	 * 基本碾压伤害系数常数
	 */
	double BASIC_SUPPRESS_DAMAGE_QUO=2.0;
	/**
	 * 基础基本格挡率常数
	 */
	double BASE_BASIC_BLOCK_RATE=0.09;
	/**
	 * 基本属性格挡压制比例缩小常数
	 */
	double BASIC_ABILITY_BLOCK_RATE_SUPPRESS_REDUCE_RATE=2.25;
	/**
	 * 基本武学格挡压制比例缩小常数
	 */
	double BASIC_ART_BLOCK_RATE_SUPPRESS_REDUCE_RATE=1.75;
	/**
	 * 根骨减伤上限常数
	 */
	double BONE_DAMAGE_REDUCE_UPPERLIMIT=0.80;
	/**
	 * 先天根骨减伤上限系数常数
	 */
	double INNATE_BONE_DAMAGE_REDUCE_RATE_UPPERLIMIT=0.80;
	/**
	 * 基础根骨减伤系数常数
	 */
	double BASIC_BONE_DAMAGE_REDUCE_QUO=0.01;
	/**
	 * 基础先天根骨减伤系数常数
	 */
	double BASIC_INNATE_BONE_DAMAGE_REDUCE_QUO=1.0;
	/**
	 * 基础膂力伤害系数常数
	 */
	double BASIC_STR_DAMAGE_QUO=1.10;
	/**
	 * 基础先天膂力伤害系数常数
	 */
	double BASIC_INNATE_STR_DAMAGE_QUO=1.10;
	/**
	 * 伤势判断系数常数
	 */
	double DAMAGE_SEVERITY_JUDGEMENT_QUO=1.75;
	
	double ADVANCED_QINGGONG_HIT_QUE=2.0;
	
	double ADVANCED_QINGGONG_EVADE_QUE=3.0;
	
	String faction_text[] = new String[]{
		"[江湖小虾]","[八卦门]","[太极门]","[花间派]","[尹贺谷]","[红莲教]","[雪山派]","[逍遥派]"
	};

	int master_faction[][] = new int[][]{{},{38,43,47},{96,97,101},{56,57,66,58},{87,90,94},{73,80},{110,118,122}};
	
	float TICK_TIME = 0.05f;


}
