package base.activitymeter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/tag")
public class TagController {

	@Autowired
	private TagRepository tagRepository;

	@GetMapping
	public List<Tag> listAll() {
		ArrayList<Tag> tags = new ArrayList<>();
		tagRepository.findAll().forEach(tags::add);
		return tags;
	}

	@GetMapping("{id}")
	public Tag find(@PathVariable String id) {
		return tagRepository.findOne(id);
	}

	@PostMapping
	public Tag create(@RequestBody Tag input) {
		return tagRepository.save(new Tag(input.getName()));
	}

	// No delete, because tags are shared:
	//@DeleteMapping("{id}")
	//public void delete(@PathVariable Long id) {
	//	tagRepository.delete(id);
	//}


}
