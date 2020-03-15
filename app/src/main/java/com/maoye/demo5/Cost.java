package com.maoye.demo5;

import android.util.Log;

public class Cost implements AutoCloseable {
    private long start;

    public Cost(){
        this.start = System.currentTimeMillis();
    }
    @Override
    public void close() throws Exception {
        Log.e("time", "cost: "+(System.currentTimeMillis()-start) );
    }
}
