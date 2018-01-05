package base.activitymeter;

import javax.mail.internet.InternetAddress;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

import com.google.gson.Gson;

@Entity
public class RequestedAction {
	public static final long defaultExpirationTime = 86400000 * 3; // 1 day has 86400000 ms;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(length=10000)
	@Lob
	private String data;
	
	private final long expirationDate;
	private ActionID actionID = ActionID.NONE;

	RequestedAction() {
		expirationDate = System.currentTimeMillis() + defaultExpirationTime;
	};
	
	RequestedAction(String data, ActionID actionID) {
		this();
		this.data = data;
		this.actionID = actionID;
	}
	
	RequestedAction(Activity activity, ActionID actionID) {
		this(new Gson().toJson(activity, Activity.class), actionID);
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getData() {
		return data;
	}
	
	public void setData(String data) {
		this.data = data;
	}
	
	public ActionID getActionID() {
		return actionID;
	}
	
	public void setActionID(ActionID actionID) {
		this.actionID = actionID;
	}

	public boolean isExpired() {
		return System.currentTimeMillis() > expirationDate;
	}
	
	public enum ActionID {
		NONE, POST, DELETE;
	}
}
