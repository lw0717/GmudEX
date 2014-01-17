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

		//是否可装备
		ItemGenerator equipable(boolean b)
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

		//装备效果
		ItemGenerator equip_effect(EquipEffect e)
		{
			//TODO: 未实现
			return this;
		}

		//TODO: 更多。。。。。。

	}

	interface EquipEffect {
		void equip(Npc person);
		void unequip(Npc person);
	}
	
	

	void exec()
	{
		// 把编辑器生成的代码放到这里


		//example:
		item()
		.name("三角石板")
		.type(6)
		.desc("一块年代久远的石板")
		.dropable(false);
		
		
		item()
		.name("大留之剑")
		.type(2)
		.desc("大留使用的宝剑")
		.equipable(true)
		.equip_effect(new EquipEffect() {

			@Override
			public void equip(Npc person) {
				person.atk += 10;
			}

			@Override
			public void unequip(Npc person) {
				person.atk -= 10;
			}
			
		})
		.subtype(6)
		.dropable(true);
		
		
	}





	/**
	 * 
	 */
	public Room() {
		// TODO 自动生成的构造函数存根
	}

}
