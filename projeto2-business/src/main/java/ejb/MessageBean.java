/*
package ejb;

import data.Car;

import javax.annotation.Resource;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.EJB;
import javax.ejb.MessageDriven;
import javax.ejb.MessageDrivenContext;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import org.apache.log4j.Logger;

@MessageDriven(
        name = "Handler",
        activationConfig = {
                @ActivationConfigProperty( propertyName = "destinationType",
                        propertyValue = "javax.jms.Topic"),
                @ActivationConfigProperty( propertyName = "destination",
                        propertyValue ="jms/Topic/TopicExample")
        }
)
public class MessageBean implements MessageListener {

    final static Logger logger = Logger.getLogger(MessageBean.class);

    @Resource
    private MessageDrivenContext mdctx;

    @EJB
    CarsEJBRemote ejbRemote;

    public MessageBean(){
    }

    public void onMessage(Message message) {
        ObjectMessage objectMessage = null;
        try {
            objectMessage = (ObjectMessage) message;
            Car car = (Car) objectMessage.getObject();
            if(logger.isDebugEnabled()){
                logger.debug("DEBUG: Received car " + car.getCustomer());
            }
            ejbRemote.addCarSV(car);

        } catch (JMSException ex) {
            mdctx.setRollbackOnly();
        }
    }
}*/
