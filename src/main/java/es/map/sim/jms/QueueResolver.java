package es.map.sim.jms;

public class QueueResolver {
	private static final String PREMIUM = "premium";
	private static final String REFRESH_STATUS = "refresh status";
	private static final String NORMAL = "normal";
	/**
	 * @return the premiumQueueNamePrefix
	 */
	public String getPremiumQueueName() {
		return premiumQueueName;
	}
	/**
	 * @param premiumQueueNamePrefix the premiumQueueNamePrefix to set
	 */
	public void setPremiumQueueName(String premiumQueueName) {
		this.premiumQueueName = premiumQueueName;
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
	private String premiumQueueName;
	private String queueNamePrefix;
	private String refreshQueueName;
	private String fallidosQueueName;
	public String resolveQueueName(String servicio, boolean premium){
		return resolveQueueName(servicio,(premium?PREMIUM:NORMAL));
	}
	public String resolveQueueName(String servicio, String queueType){
		StringBuilder queueName =new StringBuilder();
		if(PREMIUM.equals(queueType)){
			queueName.append(premiumQueueName);
			queueName.append("." + servicio);
		}else if(REFRESH_STATUS.equals(queueType)){
			queueName.append(refreshQueueName);
		}else if(NORMAL.equals(queueType)){
			queueName.append(queueNamePrefix);
			queueName.append(servicio);
		}
		return queueName.toString();
	}
	/**
	 * @return the refreshQueueName
	 */
	public String getRefreshQueueName() {
		return refreshQueueName;
	}
	/**
	 * @param refreshQueueName the refreshQueueName to set
	 */
	public void setRefreshQueueName(String refreshQueueName) {
		this.refreshQueueName = refreshQueueName;
	}
	/**
	 * @return the fallidosQueueName
	 */
	public String getFallidosQueueName() {
		return fallidosQueueName;
	}
	/**
	 * @param fallidosQueueName the fallidosQueueName to set
	 */
	public void setFallidosQueueName(String fallidosQueueName) {
		this.fallidosQueueName = fallidosQueueName;
	}
}
