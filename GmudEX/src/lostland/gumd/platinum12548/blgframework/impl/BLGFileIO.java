/**
 * 安卓白金英雄坛制作组 <p>
 * 文件名：BLGFileIO.java <p>
 * 创建时间：2013-5-30 下午6:17:31 <p>
 * 所属项目：GmudTest <p>
 * @author 12548 <p>
 */
package lostland.gumd.platinum12548.blgframework.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import lostland.gumd.platinum12548.blgframework.IFileIO;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.AssetManager;
import android.preference.PreferenceManager;


/**
 * 类名：BLGFileIO <p>
 * 说明：
 * @author 12548
 */
public class BLGFileIO implements IFileIO {
	
	Context context;
	AssetManager assets;
	public String externalStoragePath;
	
	public BLGFileIO(Context context)
	{
		this.context= context;
		this.assets = context.getAssets();
//		this.externalStoragePath= Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator;
		this.externalStoragePath= context.getExternalFilesDir(null).getAbsolutePath() + File.separator;
	}

	/* （非 Javadoc）
	 * @see lostland.gumd.platinum12548.blgframework.IFileIO#readAsset(java.lang.String)
	 */
	@Override
	public InputStream readAsset(String fileName) throws IOException {
		return assets.open(fileName);
	}

	/* （非 Javadoc）
	 * @see lostland.gumd.platinum12548.blgframework.IFileIO#readFile(java.lang.String)
	 */
	@Override
	public InputStream readFile(String fileName) throws IOException {
		return new FileInputStream( externalStoragePath + fileName);
	}

	/* （非 Javadoc）
	 * @see lostland.gumd.platinum12548.blgframework.IFileIO#writeFile(java.lang.String)
	 */
	@Override
	public OutputStream writeFile(String fileName) throws IOException {
		return new FileOutputStream(externalStoragePath + fileName);
	}
	
	@Override
	public SharedPreferences getPreferences()
	{
		return PreferenceManager.getDefaultSharedPreferences(context);
	}
	
}
