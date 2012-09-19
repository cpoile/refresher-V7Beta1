package com.github.wolfie.refresher;

import com.vaadin.server.WrappedRequest;
import com.vaadin.ui.*;
import com.vaadin.ui.Button.ClickEvent;
import java.util.Timer;
import java.util.TimerTask;


@SuppressWarnings("serial")
public class WidgetTestApplication extends UI {
   private Label counter;
   Timer timer;

   @Override
   protected void init(WrappedRequest request) {
      Refresher refresher = new Refresher();
      refresher.setRefreshInterval(500);
      addComponent(refresher);
      Button btn2 = new Button("Click Me");
      addComponent(btn2);

      btn2.addClickListener(new Button.ClickListener() {
         public void buttonClick(ClickEvent event) {
            event.getButton().getUI().addComponent(new Label("Thank you for clicking."));
            counter = new Label("1");
            event.getButton().getUI().addComponent(counter);
            timer = new Timer();
            timer.schedule(new MyTask(counter) ,1, 1000);
         }
      });
   }

   class MyTask extends TimerTask {
      int count = 0;
      Label toUpdate;

      public MyTask(Label label) {
         toUpdate = label;
      }
      public void run() {
         if (count==100) count=0;
         count++;
         toUpdate.setValue(String.valueOf(count));
      }
   }
}

