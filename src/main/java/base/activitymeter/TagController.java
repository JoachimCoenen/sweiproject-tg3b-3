package base.activitymeter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

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

	// public Tag findSimilarTags(@RequestBody Tag input) {
	@GetMapping("similar/{id}")
		public List<Tag> findSimilarTags(@PathVariable String id) {
		List<Tag> tags = new ArrayList<>();
		tagRepository.findAll().forEach(tags::add);
		
		List<Tag> tagList = tags.stream().filter(tag -> tag.getName().replaceAll("[\\- ]", "").contains(id.replaceAll("[\\- ]", ""))).collect(Collectors.toList());
		
		return tags;
	}

	// No delete, because tags are shared:
	//@DeleteMapping("{id}")
	//public void delete(@PathVariable Long id) {
	//	tagRepository.delete(id);
	//}


}
