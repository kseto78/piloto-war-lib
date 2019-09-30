package es.map.sim.jms.sender;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsOperations;
import org.springframework.jms.core.MessageCreator;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import es.map.sim.jms.QueueResolver;
import es.map.sim.negocio.modelo.MensajeJMS;

/**
 * @author vbazagad
 *
 */
@Component("messageSender")
public class SIMMessageSender {

	private String maxRetriesProperty;
	
	@Autowired
	private JmsOperations template;
	
	private String queueNamePrefix;
	
	private Object premiumQueueNamePrefix;
	
	@Autowired
	private QueueResolver qResolver;
	
	private MessageConverter converter;

	private String deliveryDelayProperty;

	private Long deliveryDelay;

	/**
	 * Mensaje que permite enviar un mensaje a la cola refreshStatus
	 * @param message Mensaje a enviar.
	 * @param maxRetries Numero máximo de reintentos (<0= Sin limite)
	 */
	public void sendRefresh(MensajeJMS message, final long maxRetries){
		sendByQueueName(message, maxRetries, qResolver.getRefreshQueueName(),deliveryDelay);
	}
	
	/**
	 * Metodo que permite enviar un mensaje a una cola estandar asociado a un servicio o bien a la cola premium
	 * @param message Mensaje a Enviar
	 * @param maxRetries Número máximo de mensajes (<0 = Limitados por el broker)
	 * @param serviceName servicio.aplicacion Sufijo para la cola a la que se enviará el mensaje
	 * @param premium Booleano indicando si el mensaje es o no de tipo premium
	 */
	public void send(final MensajeJMS message, final long maxRetries, String serviceName, boolean premium) {		
		sendByQueueName(message, maxRetries, qResolver.resolveQueueName(serviceName,premium),null);
	}
	public void sendByQueueName(final MensajeJMS message, final long maxRetries,
			String queueName, Long deliveryDelay) {
		template.send(queueName, new SIMMessageCreator(message, maxRetries, maxRetriesProperty,converter,queueName, deliveryDelayProperty, deliveryDelay));
	}
	class SIMMessageCreator implements MessageCreator{
		private MensajeJMS message;
		private Long maxRetries;
		private String maxRetriesProperty;
		private String deliveryDelayProperty;
		private MessageConverter converter;
		private String queue;
		private Long deliveryDelay;
		public SIMMessageCreator(MensajeJMS message, Long maxRetries, String maxRetriesProperty, MessageConverter converter, String queue, String deliveryDelayProperty, Long deliveryDelay){
			this.message=message;
			this.maxRetries=maxRetries;
			this.maxRetriesProperty=maxRetriesProperty;
			this.converter=converter;
			this.queue=queue;
			this.deliveryDelayProperty = deliveryDelayProperty;
			this.deliveryDelay=deliveryDelay;
		}
		public Message createMessage(Session session) throws JMSException {
			Message msg = converter.toMessage(message, session);
//			if(!StringUtils.isEmpty(maxRetriesProperty)&&maxRetries!=null){			
			if(!(maxRetriesProperty == null) || ("".equals(maxRetriesProperty))&&maxRetries!=null){
				msg.setLongProperty(maxRetriesProperty, maxRetries);			
			}			
			msg.setStringProperty("SIM_QUEUE_NAME", queue);
			if((deliveryDelayProperty == null) || ("".equals(deliveryDelayProperty))&&deliveryDelay!=null) {
//			if(!StringUtils.isEmpty(deliveryDelayProperty)&&deliveryDelay!=null) {
				msg.setLongProperty(deliveryDelayProperty, deliveryDelay);
			}
			return msg;
		}
	}
	/**
	 * @return the maxRetriesProperty
	 */
	public String getMaxRetriesProperty() {
		return maxRetriesProperty;
	}
	/**
	 * @param maxRetriesProperty the maxRetriesProperty to set
	 */
	public void setMaxRetriesProperty(String maxRetriesProperty) {
		this.maxRetriesProperty = maxRetriesProperty;
	}
	/**
	 * @return the template
	 */
	public JmsOperations getTemplate() {
		return template;
	}
	/**
	 * @param template the template to set
	 */
	public void setTemplate(JmsOperations template) {
		this.template = template;
	}
	/**
	 * @return the queueNamePrefix
	 */
	public String getQueueNamePrefix() {
		return queueNamePrefix;
	}
	/**
	 * @param queueNamePrefix the queueNamePrefix to set
	 */
	public void setQueueNamePrefix(String queueNamePrefix) {
		this.queueNamePrefix = queueNamePrefix;
	}
	/**
	 * @return the premiumQueueNamePrefix
	 */
	public Object getPremiumQueueNamePrefix() {
		return premiumQueueNamePrefix;
	}
	/**
	 * @param premiumQueueNamePrefix the premiumQueueNamePrefix to set
	 */
	public void setPremiumQueueNamePrefix(Object premiumQueueNamePrefix) {
		this.premiumQueueNamePrefix = premiumQueueNamePrefix;
	}
	/**
	 * @return the converter
	 */
	public MessageConverter getConverter() {
		return converter;
	}
	/**
	 * @param converter the converter to set
	 */
	public void setConverter(MessageConverter converter) {
		this.converter = converter;
	}

	/**
	 * @return the deliveryDelayProperty
	 */
	public String getDeliveryDelayProperty() {
		return deliveryDelayProperty;
	}

	/**
	 * @param deliveryDelayProperty the deliveryDelayProperty to set
	 */
	public void setDeliveryDelayProperty(String deliveryDelayProperty) {
		this.deliveryDelayProperty = deliveryDelayProperty;
	}
	
	/**
	 * @return the deliveryDelay
	 */
	public Long getDeliveryDelay() {
		return deliveryDelay;
	}

	/**
	 * @param deliveryDelay the deliveryDelay to set
	 */
	public void setDeliveryDelay(Long deliveryDelay) {
		this.deliveryDelay = deliveryDelay;
	}
	
}
