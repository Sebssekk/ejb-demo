package seb.course.mdb;

import jakarta.ejb.ActivationConfigProperty;
import jakarta.ejb.MessageDriven;
import jakarta.jms.JMSException;
import jakarta.jms.Message;
import jakarta.jms.MessageListener;
import jakarta.jms.ObjectMessage;

@MessageDriven(activationConfig = {
    @ActivationConfigProperty(
        propertyName = "destinationLookup",
        propertyValue = "java:app/jms/OrderQueue"
    ),
    @ActivationConfigProperty(
        propertyName = "destinationType",
        propertyValue = "jakarta.jms.Queue"
    )
})
public class Consumer implements MessageListener {
    @Override
    public void onMessage(Message message) {
        try {
            if (message instanceof ObjectMessage objMsg) {
                ProducerResource.MessageRequest msg = (ProducerResource.MessageRequest) objMsg.getObject();
                System.out.println("[*] Message Received. Start processing...");
                // Fake processing
                for (int i = 0; i < 5; i++) {
                    System.out.println("... Processing " + msg.key + " ...");
                    Thread.sleep(2000);
                }
                System.out.println("[*] Message " + msg.key + " processed.");
                System.out.println("*********** MSG ************");
                System.out.println(msg.message);
                System.out.println("*****************************");
            }else{
                System.out.println("Unknown message type received -> " + message.getClass().getName());
            }
        }
        catch(JMSException | InterruptedException e){
                e.printStackTrace();
        }
    }
}
