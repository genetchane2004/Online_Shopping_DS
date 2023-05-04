/*
* Authors
*
* Conor Egan 13138782
* Mark Dempsey 12062863
* Niall Phillips 13153382 
* Luke Robinson 13132822
* Simon Griffin 13125648
*
*/

package EJBs;

import DB_Entities.Log;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.ejb.MessageDrivenContext;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.ConstraintViolationException;

/**
* Logging bean to accomplish the following:
* Every time a customer confirms an order or cancels an order an entry is added to the database log.
* Every time an administrator adds/removes a product an entry is added to the log.
* 
* Bean logs to database table LOG.
*
*/
@MessageDriven(mappedName = "jms/MessageQueue", activationConfig
        = {
            @ActivationConfigProperty(propertyName = "acknowledgeMode", propertyValue = "Auto-acknowledge"),
            @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue")
        })
public class LogHandler implements MessageListener {
    
    public LogHandler() {
    }
    
    @Resource
    private MessageDrivenContext mdc;
    
    @PersistenceContext(unitName = "Distributed_Systems_Project-ejbPU")
    private EntityManager em;
    
    
    /**
     * Add a log message to the LOG database table
     * @param message
     */
    @Override
    public void onMessage(Message message) {
        ObjectMessage msg = null;
        try {
            
            if (message instanceof ObjectMessage) {
                msg = (ObjectMessage) message;
                Log log = (Log) msg.getObject();
                try {
                    persist(log);
                } catch (ConstraintViolationException ex) {
                    Logger.getLogger(LogManager.class.getName())
                            .log(Level.SEVERE, null, ex);
                    System.out.println(ex.getConstraintViolations());
                }

            }
        } catch (JMSException e) {
            Logger.getLogger(LogManager.class.getName()).log(Level.SEVERE, null, e);
            mdc.setRollbackOnly();
        } catch (Throwable te) {
            Logger.getLogger(LogManager.class.getName()).log(Level.SEVERE, null, te);
        }
    }

    /**
     *
     * @param object
     */
    public void persist(Object object) {
        em.persist(object);
    }
}

