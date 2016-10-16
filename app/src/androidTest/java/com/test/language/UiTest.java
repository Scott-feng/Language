package com.test.language;

import android.app.Instrumentation;
import android.os.RemoteException;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.support.test.uiautomator.By;
import android.support.test.uiautomator.UiDevice;
import android.support.test.uiautomator.UiObject2;
import android.support.test.uiautomator.UiObjectNotFoundException;
import android.support.test.uiautomator.UiScrollable;
import android.support.test.uiautomator.UiSelector;
import android.util.Log;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by 90969 on 2016/10/09/0009.
 */
@RunWith(AndroidJUnit4.class)
public class UiTest {
    Instrumentation instrumentation;
    UiDevice device;
    //set global variable
    ArrayList stores=new ArrayList();
    ArrayList receiver=new ArrayList();
    Set set =new HashSet();



    @Before
    public void setUp() throws UiObjectNotFoundException {
        instrumentation = InstrumentationRegistry.getInstrumentation();
        device = UiDevice.getInstance(instrumentation);

    }

    @Test
    public void testUi() throws UiObjectNotFoundException {
        for (int k = 81; k < 100; k++) {
            Log.d("k",String.valueOf(k));
            //chrome is launched
            device.pressHome();
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            try {
                device.pressRecentApps();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //switch settings
            device.click(600,600);
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            //click menu
            device.click(600, 350);
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            //select language
            if (k>9) {
                //j scrollable times
                for (int j=0;j<k/9;j++) {
                    UiScrollable scroll = new UiScrollable(new UiSelector().className("android.widget.ListView"));
                    scroll.scrollForward();
                }
                Click(k);
            } else {
                Click(k);
            }

            //return chrome
            try {
                device.pressRecentApps();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            device.click(600,600);
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

    @Test
    public void getLanguage() throws UiObjectNotFoundException {

            getName();
            UiScrollable scroll = new UiScrollable(new UiSelector().className("android.widget.ListView"));
            for (int j=0;j<11;j++) {
                scroll.scrollForward();
                getName();
            }
        //add to ArrayList receiver
        for (int i=0;i<stores.size();i++){
            if (! receiver.contains(stores.get(i))){
                receiver.add(stores.get(i));
            }
        }

        //print ArrayList receiver
        for (int k=0;k<receiver.size();k++){
            Log.d("receiver",String.valueOf(receiver.get(k)));

        }
        Log.d("receiverNum",String.valueOf(receiver.size()));

        //List add to set ,remove duplicate elements

//        set.addAll(stores);
//
//        for(Iterator it = set.iterator(); it.hasNext(); )
//        {
//            Log.d("set",it.next().toString());
//        }
//        Log.d("setNum",String.valueOf(set.size()));


        UiScrollable view = new UiScrollable(new UiSelector().className("android.widget.ListView"));
        //scroll target language
        for (int m=0;m<receiver.size();m++) {

            view.scrollIntoView(new UiSelector().text(String.valueOf(receiver.get(m))));
            //click target language
            device.findObject(new UiSelector().text(String.valueOf(receiver.get(m)))).click();
            Log.d("LanguageName",String.valueOf(receiver.get(m)));
            Log.d("Num",String.valueOf(m));
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //return language & input setting
            device.click(500,350);
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

    }

    @Test
    public void scrollable() throws UiObjectNotFoundException{
        List<UiObject2> uiObject2s = device.findObjects(By.res("android:id/locale"));
        int num = uiObject2s.size();

//        Log.d("num", String.valueOf(num));

        String[] store = new String[num];
        for (int i = 0; i < num; i++) {
            store[i] = uiObject2s.get(i).getText();
            Log.d("languageName", store[i]);
        }
        UiScrollable scroll = new UiScrollable(new UiSelector().className("android.widget.ListView"));
        for (int j=0;j<100;j++) {
            scroll.scrollIntoView(new UiSelector().text(store[j]));
        }

    }

    private void Click(int k) throws UiObjectNotFoundException {
        List<UiObject2> uiObject2s = device.findObjects(By.res("android:id/locale"));
        int num = uiObject2s.size();
        int mod = k % 9;
        Log.d("num", String.valueOf(num));
        Log.d("mod",String.valueOf(mod));
        String[] store = new String[num];
        for (int i = 0; i < num ; i++) {
            store[i] = uiObject2s.get(i).getText();
            Log.d("languageName", store[i]);
        }
        device.findObject(new UiSelector().text(store[mod])).click();
    }

    private void getName() {
        List<UiObject2> uiObject2s = device.findObjects(By.res("android:id/locale"));
        int num = uiObject2s.size();

        Log.d("num", String.valueOf(num));


        for (int i = 0; i < num; i++) {
            //add elements to ArrayList
            stores.add(i, uiObject2s.get(i).getText());

        }

    }

}
