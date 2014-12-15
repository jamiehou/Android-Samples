package com.ioryz.downloaddemo;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import android.util.Log;

public class HttpDownloader {
	
	private static final String TAG = "HttpDownloader";
	public static final int DOWNLOAD_SUCCESS = 0;
	public static final int DOWNLOAD_FAIL = -1;
	public static final int FILE_EXIST = 1;

	/**
	 * Download from give url and print to console
	 * 
	 * @param url	url string for download
	 * @return	-1: Download failed; 0: Download successful;
	 */
	public int download(String url) {
		FileUtil fUtil = new FileUtil();
		String str = fUtil.downloadAsString(url);
		if (str == null) {
			return DOWNLOAD_FAIL;
		}
		Log.i(TAG, "***Print downloaded file as below...***");
		Log.i(TAG, str);
		return DOWNLOAD_SUCCESS;
	}
	
	/**
	 * Download from give url and save to SD card with specified path and file name
	 * 
	 * @param url	url string for download
	 * @param path	path on sd card to save downloaded file
	 * @param name	file name to save as
	 * @return
	 */
	public int download(String url, String path, String name) {
		FileUtil fUtil = new FileUtil();
		if (name == null)
			name = fUtil.getFileByDate();
		String fileName = path + name;
		if (fUtil.isFileExist(fileName))
			return FILE_EXIST;
		File file = fUtil.write2SDFromInput(path, name, getInputStreamFromUrl(url));
		if (file == null) 
			return DOWNLOAD_FAIL;
		return DOWNLOAD_SUCCESS;
	}
	
	private InputStream getInputStreamFromUrl(String url) {
		InputStream is = null;
		try {
			HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
			is =  conn.getInputStream();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return is;
	}
}
