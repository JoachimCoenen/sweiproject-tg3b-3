/**
 * 
 */
package base.activitymeter;

import java.util.Arrays;
import java.util.Set;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;


/**
 * @author Joachim Coenen
 *
 */
@RestController
@RequestMapping("/actions")
public class RequestedActionsController {

	@Autowired
	private RequestedActionsRepository requestedActionsRepository;

	@Autowired
	private ActivityRepository activityRepository;
	
	@Autowired
	private TagRepository tagRepository;
	
	@GetMapping("confirm/{idsString}")
	public String confirmAction(@PathVariable String idsString) {
		boolean isSuccess = Arrays.stream(idsString.split("&"))
		.mapToLong(Long::decode)
		.allMatch(id -> {
			RequestedAction action = requestedActionsRepository.findOne(id);
			return (action != null && executeAction(action));			
		});
		
		if (isSuccess) {
			return "confirmActionSuccessfull.html";
		}
		return "confirmActionFailed.html";
	}
	
	private boolean executeAction(RequestedAction action) {
		if (action.isExpired()) {
			requestedActionsRepository.delete(action);
			return false;
		}
		
		Gson gson=new Gson();
		Activity activity = gson.fromJson(action.getData(), Activity.class);
		
		
		boolean isSuccess = false;
		switch (action.getActionID()) {
		case NONE:
			isSuccess = true;
			break;
		case POST: {
			saveTags(activity.getTags());
			activity = activityRepository.save(activity);
			isSuccess = true;
			break;
		}
		case DELETE:
			activityRepository.delete(activity);
			isSuccess = true;
			break;
		default:
			isSuccess = false;
			break;
		}

		requestedActionsRepository.delete(action);
		return isSuccess;
	}

	private void saveTags(Set<Tag> tags) {
		Iterable<Tag> newTags = tagRepository.save(tags);
		tags.clear();
		newTags.forEach(tag -> { if(Tag.isValidTag(tag)) {tags.add(tag);} } );
	}


}
