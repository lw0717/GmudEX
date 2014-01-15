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

	private void item(ItemGenerator g)
	{
		
	}
	
	private abstract class ItemGenerator {
		
		//设置物品名
		void name(String s)
		{
			//TODO: 未实现
		}
		
		//设置物品类型
		void type(int id)
		{
			
		}
		
		//设置物品子类型（装备位置）
		void subtype(int id)
		{
			
		}
		
		//是否可掉落
		void dropable(boolean b)
		{
			
		}
		
		
		
	}
	
	
	public abstract class EquipEffect {
		
	}
	
	public abstract class Buff {
		
	}
	
	public abstract class Trigger {
		
	}
	
	
	
	/**
	 * 
	 */
	public Room() {
		// TODO 自动生成的构造函数存根
	}

}
