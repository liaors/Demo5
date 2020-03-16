package com.maoye.demo5;

public enum  ListenerManager {
    INSTANCE;
    public  CallBackLister callBackLister ;

    public interface  CallBackLister{
        void  onCallBack(PersionBean persionBean);
    }

    public void setCallBack(CallBackLister callBackLister){
        this.callBackLister = callBackLister;
    }


}
