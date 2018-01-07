package base.activitymeter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.*;

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

	@GetMapping("similar/{inputTag}")
	public List<Tag> findSimilarTags(@PathVariable String inputTag) {
		
		final float minScore = 0.5f; //0.69f
		int maxCountOfProposedTags = 5;
		
		String[] tagWords = inputTag.toLowerCase().split("[" + Tag.SEPARATOR_CHARS + "]+");
		
		return StreamSupport.stream(tagRepository.findAll().spliterator(), false)
				.map(tag-> Pair.of(tag, tag.getName().toLowerCase().replaceAll("["+Tag.SEPARATOR_CHARS+"]+", "")))
				.map(tag -> Pair.of(tag.getFirst(), Stream.of(tagWords)
						.filter(str -> tag.getSecond().contains(str))
						.collect(Collectors.summingDouble(str -> Math.log1p(str.length())))
						/ (float)tagWords.length
				))
				.filter(pair -> pair.getSecond() >= minScore)
				.sorted(Comparator.comparing(pair -> -pair.getSecond()))
				.limit(maxCountOfProposedTags)
				.map(Pair::getFirst)
				.collect(Collectors.toList());
	}

}
