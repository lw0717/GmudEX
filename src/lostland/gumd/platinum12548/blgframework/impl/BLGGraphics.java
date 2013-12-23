/**
 * 安卓白金英雄坛制作组 <p>
 * 文件名：BLGGraphics.java <p>
 * 创建时间：2013-5-19 下午9:58:04 <p>
 * 所属项目：GmudTest <p>
 * @author 12548 <p>
 */
package lostland.gumd.platinum12548.blgframework.impl;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import lostland.gumd.platinum12548.Assets;
import lostland.gumd.platinum12548.FontSize;
import lostland.gumd.platinum12548.blgframework.IGraphics;
import lostland.gumd.platinum12548.blgframework.IPixmap;

import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.Paint.FontMetrics;
import android.graphics.Paint.Style;
import android.graphics.Rect;
import android.util.Log;


/**
 * 类名：BLGGraphics <p>
 * 说明：
 * @author 12548
 */
public class BLGGraphics implements IGraphics {
	AssetManager assets;
	Bitmap frameBuffer;
	Canvas canvas,SecondCanvas;
	Paint paint;
	Rect srcRect=new Rect();
	Rect dstRect=new Rect();
	
	static final int TEXT_LINE_PX_LIMIT = 148;
	
	public BLGGraphics(AssetManager assets,Bitmap frameBuffer,Bitmap fb2)
	{
		this.assets= assets;
		this.frameBuffer= frameBuffer;
		this.canvas=new Canvas(frameBuffer);
		this.SecondCanvas = new Canvas(fb2);
		this.paint=new Paint();
	}

	/* （非 Javadoc）
	 * @see lostland.gumd.platinum12548.blgframework.IGraphics#newPixmap(java.lang.String, lostland.gumd.platinum12548.blgframework.IGraphics.PixmapFormat)
	 */
	@Override
	public IPixmap newPixmap(String fileName, PixmapFormat format) {
		Config config = null;
		if(format== PixmapFormat.RGB565)
			config= Config.RGB_565;
		else if(format== PixmapFormat.ARGB4444)
			config= Config.ARGB_4444;
		else
			config= Config.ARGB_8888;
		
		Options options=new Options();
		options.inPreferredConfig=config;
		InputStream in=null;
		Bitmap bitmap=null;
		try
		{
			in=assets.open(fileName);
			bitmap=BitmapFactory.decodeStream(in);
			if(bitmap==null)
				throw new RuntimeException("无法打开位图文件"+ fileName);
		}
		catch( IOException e)
		{
			throw new RuntimeException("无法打开位图文件"+ fileName);
		}
		finally
		{
			if(in!=null)
				try{
					in.close();
				}catch( IOException e)
				{
					
				}
		}
		
		if(bitmap.getConfig()==Config.RGB_565)
			format= PixmapFormat.RGB565;
		else if( bitmap.getConfig()==Config.ARGB_4444)
			format= PixmapFormat.ARGB4444;
		else
			format= PixmapFormat.ARGB8888;
		Log.i("BLGGraphics", "Pixmap Created:"+ fileName);
		return new BLGPixmap( bitmap, format);
	}

	/* （非 Javadoc）
	 * @see lostland.gumd.platinum12548.blgframework.IGraphics#clear(int)
	 */
	@Override
	public void clear(int color) {
		canvas.drawRGB((color & 0xff0000) >> 16, (color & 0xff00) >> 8, (color & 0xff));
	}

	/* （非 Javadoc）
	 * @see lostland.gumd.platinum12548.blgframework.IGraphics#drawPixel(int, int, int)
	 */
	@Override
	public void drawPixel(int x, int y, int color) {
//		paint.setColor(color);
		paint.setStyle(Style.FILL);
		canvas.drawPoint(x, y, paint);
	}

	/* （非 Javadoc）
	 * @see lostland.gumd.platinum12548.blgframework.IGraphics#drawLine(int, int, int, int, int)
	 */
	@Override
	public void drawLine(int x, int y, int x2, int y2, int color) {
		paint.setStyle(Style.FILL);
		paint.setColor(color);
		canvas.drawLine(x, y, x2, y2, paint);
	}

	/* （非 Javadoc）
	 * @see lostland.gumd.platinum12548.blgframework.IGraphics#drawRect(int, int, int, int, int)
	 */
	@Override
	public void drawRect(int x, int y, int width, int height, int color) {
		paint.setColor(color);
		paint.setStyle(Style.FILL);
		paint.setAlpha(255);
		canvas.drawRect(x, y, x+width-1, y+height-1, paint);
	}

	
	public void drawText(String text,int x,int y,int size)
	{
		paint.setColor(0);
		paint.setStyle(Style.FILL);
		paint.setAlpha(255);
		paint.setTextAlign(Align.LEFT);
		paint.setTextSize(size);
//		paint.setAntiAlias(true);
//		paint.setTypeface(Assets.songti);
//		paint.setFakeBoldText(true);

		FontMetrics f= paint.getFontMetrics();

		canvas.drawText(text, x, y-f.ascent, paint);
	}
	
	// 重写 by 教头，形参未变
	public void drawText(String text,int x,int y,FontSize size)
	{
		drawSplitedText(splitString(text, size, TEXT_LINE_PX_LIMIT), x, y, size);
	}
	
	
	public void draw2Text(String text,int x,int y,FontSize size)
	{
		paint.setColor(0xffffffff);
		drawSplitedText(splitString(text, size, TEXT_LINE_PX_LIMIT), x, y, size);
		paint.setColor(0);
	}
	
	
	// 重载 by 教头,pxLimit表示行像素数限制，即超过便换行
	public void drawText(String text,int x,int y,FontSize size,int pxLimit)
	{
		drawSplitedText(splitString(text, size, pxLimit), x, y, size);
	}
	
	// !!! 这个不会自动检测换行，只能绘制已经分割好的String!!!
	public void drawSplitedText(String text,int x,int y,FontSize size)
	{
		byte[] buffer;
		try {
			buffer = text.getBytes("GBK");
			//buffer = splitString(text, size, pxPerLine).getBytes("GBK");



			// 字符串中可能混有ascii字符，它们的宽度是汉字的一半.(如"你还是找别人CHAT去吧")
			int char_width = 0;

			// 存储一个汉字点阵信息要的字节数
			// 12px的，每行两个字节，12行, 故为24
			// 16px的，每行两个字节，16行，故为32
			int char_dataSize = 0;

			switch (size)
			{
			case FT_12PX:
				char_width = 12;
				char_dataSize = 24;
				break;
			case FT_16PX:
				char_width = 16;
				char_dataSize = 32;
				break;
			default:
				break;
			}

			int xx = x;//Nice
			int yy = y;


			paint.setColor(0);
			paint.setStyle(Style.FILL);
			paint.setAlpha(255);



			// 遍历字符缓存《--- 中英文混在一起的东西
			for (int i = 0; i < buffer.length; i++)
			{
				byte b = buffer[i];
				// 小于0xA0(160)的，是ascii字符
				if ((b & 0xFF)< 0xA0)
				{
					// Added by 教头20130729
					
					// 貌似 '\n'的Ascii码为0x0a(10)
					if(b == 0x0A)
					{
						xx = x;
						yy += char_width;// 因为都是宽=高的字体啦...
						continue;
					}
					
					int asciiCharWidth = char_width / 2;
					int asciiCharDataSize = 12;//12 * 1
					int offset= b * asciiCharDataSize;
					
					byte[] char_lattice_info = new byte[char_dataSize];
					for (int tmp = 0; tmp < asciiCharDataSize; tmp++)
					{
						char_lattice_info[tmp] = Assets.ascii12[tmp + offset];
					}
					
					int height = size.size();//高度方向的字节数
					int width = 1;//水平都两个字节

					int ptr_byte = 0;// 记录用到了第几个字节
					
					for (int ht = 0; ht < height; ht++)
					{
						for (int wd = 0; wd < width; wd++)
						{
							for (int bit = 0; bit < 8; bit++)
							{
								// 绘制与否的信息
								boolean draw = (char_lattice_info[ptr_byte] & (1 << bit)) == 0 ? false : true;

								if (draw)
								{
									// C# 没有画点函数..
									drawPixel(
											xx + (wd << 3) + 7 - bit,// X位置 = 一直记录的x + wd * 8 + 7-bit，其中7-bit因为他是从右边往左边存....
											yy + ht,// Y位置，这是画单行的函数，只需要起止绘制点加行号就好了
											0
											);
								}
							}

							// !! 处理完一个字节了...指向下一个字节
							ptr_byte++;
						}
					}
					
					xx += asciiCharWidth;//+汉字宽的一半
				}
				// 我大汉字
				else
				{
					// 区位码，这是固定的公式, 汉字内码(就是默认编码获得的字节)转换为区位码的公式
					// area - 区，pos - 位 
					// 区位码好像是按拼音的顺序，很好
					int area = (buffer[i] & 0xFF) - 0xA0;//C#里实际上没必要&0xFF.因为byte是正的
					int pos = (buffer[i+1] & 0xFF) - 0xA0;

					// 寻址的偏移 = ((区码 - 1 * 94) + 位码 - 1 ) * 存一个汉字点阵要的字节数
					int offset = ((area - 1) * 94 + pos - 1) * char_dataSize;

					// 不知道。。小于这个值的是标点，我不知道为什么汉字的偏了，标点的却没
					if (offset > char_dataSize * 564)
						offset -= char_dataSize * 564;

					// 根据偏移量从字库里把一个汉字的点阵信息复制出来 —— 确实也没啥必要...
					byte[] char_lattice_info = new byte[char_dataSize];
					for (int tmp = 0; tmp < char_dataSize; tmp++)
					{
						if (size == FontSize.FT_12PX)
							char_lattice_info[tmp] = Assets.charGBK12[tmp + offset];
						else
							char_lattice_info[tmp] = Assets.charGBK16[tmp + offset];
					}

					int height = size.size();//高度方向的字节数
					int width = 2;//水平都两个字节

					int ptr_byte = 0;// 记录用到了第几个字节

					// 上面取到了一个汉字的点阵信息(char_lattice_info)，可以画了
					//      遍历分三层
					//          最外层(最后)的是 从上往下
					//          中间层      的是 从左到右(才两个字节)
					//          最内层      的是 访问字节的逐个位，决定是否画点
					for (int ht = 0; ht < height; ht++)
					{
						for (int wd = 0; wd < width; wd++)
						{
							for (int bit = 0; bit < 8; bit++)
							{
								// 绘制与否的信息
								boolean draw = (char_lattice_info[ptr_byte] & (1 << bit)) == 0 ? false : true;

								if (draw)
								{
									// C# 没有画点函数..
									drawPixel(
											xx + (wd << 3) + 7 - bit,// X位置 = 一直记录的x + wd * 8 + 7-bit，其中7-bit因为他是从右边往左边存....
											yy + ht,// Y位置，这是画单行的函数，只需要起止绘制点加行号就好了
											0
											);
								}
							}

							// !! 处理完一个字节了...指向下一个字节
							ptr_byte++;
						}
					}

					xx += char_width;// 汉字 宽度=高度

					i++;//一个汉字花了两个字节，下一个已经被用掉了。所以跳过一个
				}
			}

		} catch (UnsupportedEncodingException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}


	}
	
	
	
	
	
	private String splitString(String src,FontSize ftSize,int lenLimit)
	{
		StringBuilder sb = new StringBuilder();
		int HanziWidth = ftSize == FontSize.FT_12PX ? 12 : 16;
		
		int currLen = 0;
		for (int i = 0; i < src.length(); i++) 
		{
			char c = src.charAt(i);
			int wid = -1;
			if( (int)c > 0xA0 )// 判断char是否为ascii的方法
				wid = HanziWidth;
			else
			{
				if(c == 0x0A)
					wid = currLen * (-1);
				else
					wid = HanziWidth / 2;
			}
			
			if (currLen + wid > lenLimit)
			{
				sb.append('\n');
				currLen = 0;
			}
			sb.append(c);
			currLen += wid;
		}
		
		return sb.toString();
	}
	
	public ArrayList<String> splitString(String src, FontSize ftSize, int lenLimit,int linePerPage)
	{
		ArrayList<String> strs = new ArrayList<String>();
		StringBuilder sb = new StringBuilder();
		int HanziWidth = ftSize == FontSize.FT_12PX ? 12 : 16;
		
		int currLen = 0;
		int reCnt = 0;// 回车的个数，太多了则要分页了
		for (int i = 0; i < src.length(); i++) 
		{
			char c = src.charAt(i);
			int wid = -1;
			if( (c & 0xFF) > 0xA0 )// 判断char是否为ascii的方法
				wid = HanziWidth;
			else
			{
				if(c == 0x0A)
				{
					wid = 0 - currLen;//avoid multiplication
					reCnt++;
				}
				else
					wid = HanziWidth / 2;
			}
			
			if (currLen + wid > lenLimit)
			{
				sb.append('\n');
				reCnt++;
				currLen = 0;
			}
			
			if(reCnt >= linePerPage)
			{
				reCnt = 0;
				strs.add(sb.toString());
				sb.delete(0, sb.length());//length不能-1 ！！！！！！！！！不知为何
			}
			
			sb.append(c);
			currLen += wid;
		}
		strs.add(sb.toString());
		
		return strs;
	}

	public ArrayList<String> splitString(String src,int linePerPage)
	{
		ArrayList<String> strs = new ArrayList<String>();
		StringBuilder sb = new StringBuilder();
		int reCnt = 0;// 回车的个数，太多了则要分页了
		for (int i = 0; i < src.length(); i++) 
		{
			char c = src.charAt(i);
			
			if(c == 0x0A)
			{
				reCnt++;
			}
			
			if(reCnt >= linePerPage)
			{
				reCnt = 0;
				strs.add(sb.toString());
				sb.delete(0, sb.length());//length不能-1 ！！！！！！！！！不知为何
			}
			
			sb.append(c);
		}
		strs.add(sb.toString());
		
		return strs;
	}

	
	
	/* （非 Javadoc）
	 * @see lostland.gumd.platinum12548.blgframework.IGraphics#drawPixmap(lostland.gumd.platinum12548.blgframework.IPixmap, int, int, int, int, int, int)
	 */
	@Override
	public void drawPixmap(IPixmap pixmap, int x, int y, int srcX, int srcY,
			int srcWidth, int srcHeight) {
		srcRect.left=srcX;
		srcRect.top=srcY;
		srcRect.right=srcX+srcWidth-1;
		srcRect.bottom=srcY+srcHeight-1;
		
		dstRect.left=x;
		dstRect.top=y;
		dstRect.right=x+srcWidth-1;
		dstRect.bottom=y+srcHeight-1;
		
		if(pixmap==null)
			Log.e("BLGGraphics", "null pixmap!");
		
		canvas.drawBitmap(((BLGPixmap)pixmap).bitmap, srcRect,dstRect, null);
	}

	/* （非 Javadoc）
	 * @see lostland.gumd.platinum12548.blgframework.IGraphics#drawPixmap(lostland.gumd.platinum12548.blgframework.IPixmap, int, int)
	 */
	@Override
	public void drawPixmap(IPixmap pixmap, int x, int y) {
		canvas.drawBitmap(((BLGPixmap)pixmap).bitmap, x, y, null);
	}

	
	public void draw2ndPixmap(IPixmap pixmap,int x,int y)
	{
		if(pixmap == null)
			return;
		drawPixmap(pixmap, x, y);
//		this.SecondCanvas.drawBitmap(((BLGPixmap)pixmap).bitmap, x, y, null);
	}
	
	/* （非 Javadoc）
	 * @see lostland.gumd.platinum12548.blgframework.IGraphics#getWidth()
	 */
	@Override
	public int getWidth() {
		return frameBuffer.getWidth();
	}

	/* （非 Javadoc）
	 * @see lostland.gumd.platinum12548.blgframework.IGraphics#getHeight()
	 */
	@Override
	public int getHeight() {
		return frameBuffer.getHeight();
	}
	
	
	// 一个	成员
	Rect orgnClipRect = new Rect();

	// ADDED BY ME - 
	public void drawRectOutline(int x, int y, int width, int height, int color)
	{
		paint.setColor(color);
		paint.setStyle(Style.STROKE);
		paint.setAlpha(255);
		canvas.drawRect(x, y, x + width - 1, y + height - 1, paint);
	}
	public void SetClipRect(Rect rect)
	{
		orgnClipRect = canvas.getClipBounds();
		canvas.clipRect(rect);
	}
	public void ResetClipRect()
	{
		canvas.clipRect(orgnClipRect);
	}

	/**
	 * @param string
	 * @param hilightBtnDrawX
	 * @param _currentY
	 * @param ft12px
	 * @param _font_color
	 */
	public void drawSplitedText(String text, int x,
			int y, FontSize size, int _font_color) {
		byte[] buffer;
		try {
			buffer = text.getBytes("GBK");
			//buffer = splitString(text, size, pxPerLine).getBytes("GBK");



			// 字符串中可能混有ascii字符，它们的宽度是汉字的一半.(如"你还是找别人CHAT去吧")
			int char_width = 0;

			// 存储一个汉字点阵信息要的字节数
			// 12px的，每行两个字节，12行, 故为24
			// 16px的，每行两个字节，16行，故为32
			int char_dataSize = 0;

			switch (size)
			{
			case FT_12PX:
				char_width = 12;
				char_dataSize = 24;
				break;
			case FT_16PX:
				char_width = 16;
				char_dataSize = 32;
				break;
			default:
				break;
			}

			int xx = x;//Nice
			int yy = y;


			paint.setColor(0);
			paint.setStyle(Style.FILL);
			paint.setAlpha(255);



			// 遍历字符缓存《--- 中英文混在一起的东西
			for (int i = 0; i < buffer.length; i++)
			{
				byte b = buffer[i];
				// 小于0xA0(160)的，是ascii字符
				if ((b & 0xFF)< 0xA0)
				{
					// Added by 教头20130729
					
					// 貌似 '\n'的Ascii码为0x0a(10)
					if(b == 0x0A)
					{
						xx = x;
						yy += char_width;// 因为都是宽=高的字体啦...
						continue;
					}
					
					int asciiCharWidth = char_width / 2;
					int asciiCharDataSize = 12;//12 * 1
					int offset= b * asciiCharDataSize;
					
					byte[] char_lattice_info = new byte[char_dataSize];
					for (int tmp = 0; tmp < asciiCharDataSize; tmp++)
					{
						char_lattice_info[tmp] = Assets.ascii12[tmp + offset];
					}
					
					int height = size.size();//高度方向的字节数
					int width = 1;//水平都两个字节

					int ptr_byte = 0;// 记录用到了第几个字节
					
					for (int ht = 0; ht < height; ht++)
					{
						for (int wd = 0; wd < width; wd++)
						{
							for (int bit = 0; bit < 8; bit++)
							{
								// 绘制与否的信息
								boolean draw = (char_lattice_info[ptr_byte] & (1 << bit)) == 0 ? false : true;

								if (draw)
								{
									drawPixel(
											xx + (wd << 3) + 7 - bit,// X位置 = 一直记录的x + wd * 8 + 7-bit，其中7-bit因为他是从右边往左边存....
											yy + ht,// Y位置，这是画单行的函数，只需要起止绘制点加行号就好了
											0
											);
								}
							}

							// !! 处理完一个字节了...指向下一个字节
							ptr_byte++;
						}
					}
					
					xx += asciiCharWidth;//+汉字宽的一半
				}
				// 我大汉字
				else
				{
					// 区位码，这是固定的公式, 汉字内码(就是默认编码获得的字节)转换为区位码的公式
					// area - 区，pos - 位 
					// 区位码好像是按拼音的顺序，很好
					int area = (buffer[i] & 0xFF) - 0xA0;//C#里实际上没必要&0xFF.因为byte是正的
					int pos = (buffer[i+1] & 0xFF) - 0xA0;

					// 寻址的偏移 = ((区码 - 1 * 94) + 位码 - 1 ) * 存一个汉字点阵要的字节数
					int offset = ((area - 1) * 94 + pos - 1) * char_dataSize;

					// 不知道。。小于这个值的是标点，我不知道为什么汉字的偏了，标点的却没
					if (offset > char_dataSize * 564)
						offset -= char_dataSize * 564;

					// 根据偏移量从字库里把一个汉字的点阵信息复制出来 —— 确实也没啥必要...
					byte[] char_lattice_info = new byte[char_dataSize];
					for (int tmp = 0; tmp < char_dataSize; tmp++)
					{
						if (size == FontSize.FT_12PX)
							char_lattice_info[tmp] = Assets.charGBK12[tmp + offset];
						else
							char_lattice_info[tmp] = Assets.charGBK16[tmp + offset];
					}

					int height = size.size();//高度方向的字节数
					int width = 2;//水平都两个字节

					int ptr_byte = 0;// 记录用到了第几个字节

					// 上面取到了一个汉字的点阵信息(char_lattice_info)，可以画了
					//      遍历分三层
					//          最外层(最后)的是 从上往下
					//          中间层      的是 从左到右(才两个字节)
					//          最内层      的是 访问字节的逐个位，决定是否画点
					for (int ht = 0; ht < height; ht++)
					{
						for (int wd = 0; wd < width; wd++)
						{
							for (int bit = 0; bit < 8; bit++)
							{
								// 绘制与否的信息
								boolean draw = (char_lattice_info[ptr_byte] & (1 << bit)) == 0 ? false : true;

								if (draw)
								{
									// C# 没有画点函数..
									drawPixel(
											xx + (wd << 3) + 7 - bit,// X位置 = 一直记录的x + wd * 8 + 7-bit，其中7-bit因为他是从右边往左边存....
											yy + ht,// Y位置，这是画单行的函数，只需要起止绘制点加行号就好了
											_font_color
											);
								}
							}

							// !! 处理完一个字节了...指向下一个字节
							ptr_byte++;
						}
					}

					xx += char_width;// 汉字 宽度=高度

					i++;//一个汉字花了两个字节，下一个已经被用掉了。所以跳过一个
				}
			}

		} catch (UnsupportedEncodingException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}

	}
}
