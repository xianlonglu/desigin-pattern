package com.lxl.observer.events;

import com.lxl.observer.events.mouseevent.Mouse;
import com.lxl.observer.events.mouseevent.MouseEventCallback;
import com.lxl.observer.events.mouseevent.MouseEventType;

public class MouseEventTest {
    public static void main(String[] args) {

        MouseEventCallback callback = new MouseEventCallback();

        Mouse mouse = new Mouse();

        //@谁？  @回调方法
        mouse.addLisenter(MouseEventType.ON_CLICK,callback);
        mouse.addLisenter(MouseEventType.ON_FOCUS,callback);

        mouse.click();

        mouse.focus();


    }
}
