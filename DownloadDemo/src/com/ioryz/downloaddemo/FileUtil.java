package com.ioryz.downloaddemo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.annotation.SuppressLint;
import android.os.Environment;
import android.util.Log;

@SuppressLint("SimpleDateFormat")
public class FileUtil {
	
	private String SDPATH;
	
	private static final String TAG = "FileUtil";
	
	public FileUtil() {
		SDPATH = Environment.getExternalStorageDirectory() + File.separator; 
	}
	
	public String downloadAsString(String strUrl) {
		Log.i(TAG, "Download as String => " + strUrl);
		URL url;
		InputStreamReader isr = null;
		BufferedReader br = null;
		String str = null;
		try {
			url = new URL(strUrl);
			HttpURLConnection urlConn = (HttpURLConnection) url.openConnection();
			Log.i(TAG, "Open input stream reader...");
			isr = new InputStreamReader(urlConn.getInputStream());
			Log.i(TAG, "Open buffered reader...");
			br = new BufferedReader(isr);
			String s = null;
			StringBuffer sb = new StringBuffer();
			Log.i(TAG, "Read line by line...");
			while ((s = br.readLine()) != null) {
				sb.append(s);
			}
			Log.i(TAG, "Read complete...");
			str = sb.toString();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		return str;
	}
	
	/**
	 * Create a new file on SD card with input stream to specifc path and file name
	 * 
	 * @param path	Path to save new file on sd card
	 * @param name	File name to create
	 * @param is	Input stream for new file
	 * @return	Created new file object
	 */
	public File write2SDFromInput(String path, String name, InputStream is) {
		Log.i(TAG, "Download to SD card from input stream to path =>" + path + name);
		File file = null;
		OutputStream out = null;
		try {
			Log.i(TAG, "Create directory => " + path);
			createSDDir(path);
			Log.i(TAG, "Create file => " + name);
			file = createSDFile(path + name);
			byte buffer[] = new byte[1024 * 4];
			out = new FileOutputStream(file);
			Log.i(TAG, "Read from input and write to output file...");
			while (is.read(buffer) != -1) {
				out.write(buffer);
			}
			out.flush();
			Log.i(TAG, "Write complete...");
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return file;
	}

	public String getFileByDate() {
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_kkmmssSSS");
		String filename = "Down_" + sdf.format(date) + ".txt";
		return filename;
	}
	
	public boolean isFileExist(String name) {
		File file = new File(SDPATH + name);
		return file.exists();
	}
	
	public File createSDDir(String path) {
		File dir = new File(SDPATH + path);
		dir.mkdir();
		return dir;
	}
	
	public File createSDFile(String name) {
		File file = new File(SDPATH + name);
		try {
			file.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return file;
	}
	
	public String getSDPATH() {
		return SDPATH;
	}
}
