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
package ManagedBeans;

import DB_Entities.Log;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Queue;
import javax.jms.Session;

/**
 *
 * Message client to oversee creation of logging connections and message objects
 */
@Named(value = "messageClient")
@RequestScoped
public class MessageClient {

    @Resource(mappedName = "jms/NewMessageFactory")
    private ConnectionFactory connectionFactory;

    @Resource(mappedName = "jms/MessageQueue")
    private Queue queue;
    
    /**
     * Log message to Log table on database
     * @param message
     * @param username
     * @throws JMSException
     */
    public void logMessage(String message, String username) throws JMSException {
       
        try (Connection connection = connectionFactory.createConnection()) {
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            MessageProducer messageProducer = session.createProducer(queue);
            ObjectMessage messageObj = session.createObjectMessage();

            Log log = new Log();
            Date date = new Date();
            log.setMessage(message);
            log.setUsername(username);
            log.setTime(date);

            messageObj.setObject(log);
            messageProducer.send(messageObj);

        } catch (JMSException ex) {
            Logger.getLogger(EnterLogin.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * Create a new instance of MessageClient
     */
    public MessageClient() {
    }
    
}
