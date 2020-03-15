package com.maoye.demo5;

import android.support.annotation.IntDef;
import android.support.annotation.IntRange;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
@Retention(RetentionPolicy.RUNTIME)
@IntDef(value = {1,2,3})
@Target({ElementType.METHOD,ElementType.FIELD,ElementType.TYPE,})
@Inherited
public @interface Intype {

}
