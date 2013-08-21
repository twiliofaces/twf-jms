package org.twiliofaces.jms;

import java.util.Enumeration;

import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.MessageListener;

import org.jboss.ejb3.annotation.ResourceAdapter;

@ResourceAdapter("twilio-sms-ra-0.0.1-SNAPSHOT.rar")
@MessageDriven
public class SmsReceiverMDB implements MessageListener
{

   public void onMessage(Message smsMessage)
   {
      System.out.println("we received a new twilio sms message!");
      if (smsMessage instanceof MapMessage)
      {
         MapMessage mess = (MapMessage) smsMessage;
         try
         {
            Enumeration<?> e = mess.getMapNames();
            while (e.hasMoreElements())
            {
               String key = (String) e.nextElement();
               String value = mess.getString(key);
               System.out.println(key + ": " + value);
            }
         }
         catch (JMSException e1)
         {
            // TODO Auto-generated catch block
            e1.printStackTrace();
         }

      }

   }
}
