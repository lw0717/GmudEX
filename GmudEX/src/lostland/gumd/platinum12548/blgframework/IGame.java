/**
 * 安卓白金英雄坛制作组 <p>
 * 文件名：IGame.java <p>
 * 创建时间：2013-5-19 下午7:26:04 <p>
 * 所属项目：GmudTest <p>
 * @author 12548 <p>
 */
package lostland.gumd.platinum12548.blgframework;

/**
 * 类名：IGame <p>
 * 说明：
 * @author 12548
 */
public interface IGame {
	public IInput getInput();
	
	public IFileIO getFileIO();
	
	public IGraphics getGraphics();
	
	//TODO：没抄的……
	
	public void  setScreen(CScreen screen);
	
	public CScreen getCurrentScreen ();
	public CScreen getStartScreen();

	/**
	 * 
	 */
	public void oo();

}
