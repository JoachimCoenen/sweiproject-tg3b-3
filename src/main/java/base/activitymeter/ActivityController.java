package base.activitymeter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.*;

@RestController
@RequestMapping("/activity")
public class ActivityController {

	@Autowired
	private ActivityRepository activityRepository;
	
	@Autowired
	private TagRepository tagRepository;

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

	@PostMapping
	public Activity create(@RequestBody Activity input) {
		saveTags(input.getTags());
		return activityRepository.save(new Activity(input.getText(), input.getTags(), input.getTitle()));
	}

	@DeleteMapping("{id}")
	public void delete(@PathVariable Long id) {
		activityRepository.delete(id);
	}

	@PutMapping("{id}")
	public Activity update(@PathVariable Long id, @RequestBody Activity input) {
		Activity activity = activityRepository.findOne(id);
		if (activity == null) {
			return null;
		} else {
			saveTags(input.getTags());
			activity.setText(input.getText());
			activity.setTags(input.getTags());
			activity.setTitle(input.getTitle());
			return activityRepository.save(activity);
		}
	}

	
	private void saveTags(Set<Tag> tags) {
		Iterable<Tag> newTags = tagRepository.save(tags);
		tags.clear();
		newTags.forEach(tag -> { if(Tag.isValidTag(tag)) {tags.add(tag);} } );
	}
	

}
