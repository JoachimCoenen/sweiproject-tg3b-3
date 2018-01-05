/**
 * 
 */
package base.activitymeter;

import base.activitymeter.RequestedAction.ActionID;

/**
 * @author Joachim Coenen
 *
 */
public class RequestBodyEmail<T> {
	private T data;
	private String emailAddress;


	RequestBodyEmail() {
	};
	
	RequestBodyEmail(T data, String emailAddress) {
		this();
		this.setData(data);
		this.setEmailAddress(emailAddress);
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
}
