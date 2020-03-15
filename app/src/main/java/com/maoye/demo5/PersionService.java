package com.maoye.demo5;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteCallbackList;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 *
 * 跨app,需要设置action ,以及在manifest中设置android:enabled="true"
 *             android:exported="true"
 */
public class PersionService extends Service {
    private CopyOnWriteArrayList<PersionBean> pearsionList;
    private static RemoteCallbackList<ICallBack> sCallbackList;

    Binder binder = new PersionInterService.Stub() {


        /**
         * 每次被调用时执行包名验证，有权限的包名，客户端才能连接
         * @param code
         * @param data
         * @param reply
         * @param flags
         * @return
         * @throws RemoteException
         */
        @Override
        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            String[] packages = getPackageManager().getPackagesForUid(getCallingUid());
            String pkgName = null;
            if (packages != null && packages.length > 0) {
                pkgName = packages[0];
            }
            if (TextUtils.isEmpty(pkgName) || !pkgName.startsWith("com.rs")) {
                Log.e("TAG", "invalid pkgName : " + pkgName);
                return false;
            }
            Log.e("TAG", "invalid pkgName : 有权限" );
            return super.onTransact(code, data, reply, flags);
        }

        @Override
        public List<PersionBean> getPersionList() throws RemoteException {
            return pearsionList;
        }

        @Override
        public void addPersion(PersionBean persion) throws RemoteException {
            if (pearsionList == null) {
                dispatchResult(false, "add persion failed, pearsionList = null");
            } else {
                pearsionList.add(persion);
                dispatchResult(true, "add persion successfully");
            }
        }

        @Override
        public void register(ICallBack callBack) throws RemoteException {
            if (callBack != null) {
                sCallbackList.register(callBack);
            }

        }

        @Override
        public void unRegister(ICallBack callBack) throws RemoteException {
            if (callBack != null) {
                sCallbackList.unregister(callBack);
            }
        }

    };

    @Override
    public void onCreate() {
        super.onCreate();
        init();
    }

    private void init() {
        pearsionList = new CopyOnWriteArrayList<>();
        sCallbackList = new RemoteCallbackList<>();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }

    /**
     * 分发结果
     *
     * @param result
     * @param msg
     */
    private void dispatchResult(boolean result, String msg) {
        int length = sCallbackList.beginBroadcast();
        for (int i = 0; i < length; i++) {
            ICallBack callback = sCallbackList.getBroadcastItem(i);
            try {
                if (result) {
                    callback.onSuccess(msg);
                } else {
                    callback.onFailed(msg);
                }
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        sCallbackList.finishBroadcast();
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public boolean onUnbind(Intent intent) {
        return super.onUnbind(intent);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
