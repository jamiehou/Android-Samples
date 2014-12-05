package com.ioryz.servicedemo;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import android.util.Log;

public class BindService extends Service {
	
	private static final String TAG = "BindService";

	@Override
	public IBinder onBind(Intent intent) {
		Log.i(TAG, "===>onBind...");
		return new MyBinder();
	}
	
	class MyBinder extends Binder {
		
		@Override
		protected boolean onTransact(int code, Parcel data, Parcel reply,
				int flags) throws RemoteException {
			Log.i(TAG, "===>transact...");
			Log.i(TAG, "Read string=>" + data.readString());
			Log.i(TAG, "Read int=>" + data.readInt());
			reply.writeString("Zack");
			reply.writeInt(35);
			return super.onTransact(code, data, reply, flags);
		}
	}
}
