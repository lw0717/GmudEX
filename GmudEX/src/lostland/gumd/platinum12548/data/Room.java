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
		//TODO: 未实现
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
			//TODO: 未实现
		}
		
		//设置物品子类型（装备位置）
		void subtype(int id)
		{
			//TODO: 未实现
		}
		
		//是否可丢弃
		void dropable(boolean b)
		{
			//TODO: 未实现
		}
		
		//设置物品描述
		void desc(String s)
		{
			//TODO: 未实现
		}
		
		//TODO: 更多。。。。。。
		
		
		abstract void setup();
	}
	
	
	void exec()
	{
		// 把编辑器生成的代码放到这里
		
		
		//例如：
		item(new ItemGenerator() {

			@Override
			void setup() {
				name("三角石板");
				type(6);
				desc("一块年代久远的石板");
				dropable(false);
			}
			
			
		});
		
	}
	

	
	
	
	/**
	 * 
	 */
	public Room() {
		// TODO 自动生成的构造函数存根
	}

}
