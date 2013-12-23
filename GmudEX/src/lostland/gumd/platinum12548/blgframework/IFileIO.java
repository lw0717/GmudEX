/**
 * 安卓白金英雄坛制作组 <p>
 * 文件名：IFileIO.java <p>
 * 创建时间：2013-5-19 下午7:09:55 <p>
 * 所属项目：GmudTest <p>
 * @author 12548 <p>
 */
package lostland.gumd.platinum12548.blgframework;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import android.content.SharedPreferences;

/**
 * 类名：IFileIO <p>
 * 说明：
 * @author 12548
 */
public interface IFileIO {
	public InputStream readAsset(String fileName) throws IOException;
	
	public InputStream readFile(String FileName) throws IOException;
	
	public OutputStream writeFile(String fileName) throws IOException;
	
	public SharedPreferences getPreferences();
}
