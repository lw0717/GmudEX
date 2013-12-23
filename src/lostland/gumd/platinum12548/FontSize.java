/**
 * 安卓白金英雄坛制作组 <p>
 * 文件名：FontSize.java <p>
 * 创建时间：2013-7-12 下午2:07:39 <p>
 * 所属项目：GmudTest <p>
 * @author 12548 <p>
 */
package lostland.gumd.platinum12548;

/**
 * 类名：FontSize <p>
 * 说明：
 * @author 12548
 */
public enum FontSize {
	FT_12PX,
	FT_16PX;
	
	public int size()
	{
		if(this == FT_12PX)
			return 12;
		else
			return 16;
	}
}
