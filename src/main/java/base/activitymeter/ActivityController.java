package base.activitymeter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

import javax.mail.internet.AddressException;
import javax.servlet.http.HttpServletRequest;

import java.util.*;

@RestController
@RequestMapping("/activity")
public class ActivityController {

	@Autowired
	private RequestedActionsRepository requestedActionsRepository;
	
	@Autowired
	private ActivityRepository activityRepository;
	
	@Autowired
	private ActivityEmailService mailService;

	@GetMapping
	public List<Activity> listAll() {
		ArrayList<Activity> activities = new ArrayList<>();
		activityRepository.findAll().forEach(activities::add);
		return activities;
	}

	@GetMapping("{id}")
	public Activity find(@PathVariable Long id) {
		return activityRepository.findOne(id);
	}
	
	@PostMapping()
	public String addActivity(@RequestBody RequestBodyEmail<Activity> input, HttpServletRequest request) {

		requestAction(request, input.getEmailAddress(), new RequestedAction(input.getData(), RequestedAction.ActionID.POST));
		
		return "CheckYourMail.html";
	}

	@DeleteMapping()
	public void delete(@RequestBody RequestBodyEmail<Activity> input, HttpServletRequest request) {
		Activity activity = input.getData();
		activity = activityRepository.findOne(activity.getId());
		requestAction(request, input.getEmailAddress(), new RequestedAction(activity, RequestedAction.ActionID.DELETE));
	}

	@PutMapping("{id}")
	public Activity update(@PathVariable Long id, @RequestBody RequestBodyEmail<Activity> input, HttpServletRequest request) {
		Activity oldActivity = activityRepository.findOne(id);
		if (oldActivity == null) {
			return null;
		} else {
			Activity newActivity = input.getData();
			newActivity.setId(oldActivity.getId());

			requestAction(request, input.getEmailAddress(), 
					new RequestedAction(newActivity, RequestedAction.ActionID.POST)
					);
			return newActivity;
		}
	}

	


	public RequestedAction[] requestAction(HttpServletRequest request, String emailAddress, RequestedAction... actions) {
		String hostAddress = request.getRequestURL().toString().replaceFirst("(?<!\\/)\\/(?!\\/).*", "/");
		StringBuilder ids = new StringBuilder();
		RequestedAction[] result = Arrays.stream(actions)
				.map(action -> requestedActionsRepository.save(action))
				.peek(action -> ids.append("&").append(action.getId()))
				.toArray(RequestedAction[]::new);
		ids.delete(0, 1); // remove the first & character, it is not needed!
		
		try {
			mailService.sendConfirmationEmail(emailAddress, hostAddress + "actions/confirm/" + ids.toString());
		} catch (AddressException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
}
