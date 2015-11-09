package com.linciping.util.inject;

import android.app.Activity;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by Administrator on 2015/11/9 0009.
 */
public class MyInject {

    public static void init(Activity activity)
    {
        MyInject inject=new MyInject();
        inject.injectMethod(activity);
    }

    public void injectMethod(Activity activity)
    {
        Class<?> tagetClass= activity.getClass();
        Field[] fields=tagetClass.getDeclaredFields();//获取属于这个activity的字段
        for(int i=0;i<fields.length;i++)
        {
            InjectView inject=fields[i].getAnnotation(InjectView.class);
            if (inject!=null)
            {
                int id=inject.id();
                try {
                    Method method=tagetClass.getMethod("findViewById",int.class);
                    Object view=method.invoke(activity,id);
                    fields[i].setAccessible(true);
                    fields[i].set(activity,view);
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
