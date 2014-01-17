/**
 * ��׿�׽�Ӣ��̳������ <p>
 * �ļ�����ItemRoom.java <p>
 * ����ʱ�䣺2014-1-15 ����10:05:27 <p>
 * ������Ŀ��GmudEX <p>
 * @author 12548 <p>
 */
package lostland.gumd.platinum12548.data;

/**
 * ������Room <p>
 * ˵����
 * @author 12548
 */
public class Room {
	

	private ItemGenerator item()
	{
		return new ItemGenerator();
	}
	
	private class ItemGenerator {

		public ItemGenerator() {
			// TODO ��Ʒ����Ԥ����
		}

		//������Ʒ��
		ItemGenerator name(String s)
		{
			//TODO: δʵ��
			return this;
		}

		//������Ʒ����
		ItemGenerator type(int id)
		{
			//TODO: δʵ��
			return this;
		}

		//������Ʒ�����ͣ�װ��λ�ã�
		ItemGenerator subtype(int id)
		{
			//TODO: δʵ��
			return this;
		}

		//�Ƿ�ɶ���
		ItemGenerator dropable(boolean b)
		{
			//TODO: δʵ��
			return this;
		}

		//�Ƿ��װ��
		ItemGenerator equipable(boolean b)
		{
			//TODO: δʵ��
			return this;
		}

		//������Ʒ����
		ItemGenerator desc(String s)
		{
			//TODO: δʵ��
			return this;
		}

		//װ��Ч��
		ItemGenerator equip_effect(EquipEffect e)
		{
			//TODO: δʵ��
			return this;
		}

		//TODO: ���ࡣ����������

	}

	interface EquipEffect {
		void equip(Npc person);
		void unequip(Npc person);
	}
	
	

	void exec()
	{
		// �ѱ༭�����ɵĴ���ŵ�����


		//example:
		item()
		.name("����ʯ��")
		.type(6)
		.desc("һ�������Զ��ʯ��")
		.dropable(false);
		
		
		item()
		.name("����֮��")
		.type(2)
		.desc("����ʹ�õı���")
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
		// TODO �Զ����ɵĹ��캯�����
	}

}
