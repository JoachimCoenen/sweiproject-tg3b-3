package base.activitymeter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.DoubleSummaryStatistics;
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

	// public List<Tag> findSimilarTags(@RequestBody Tag input) {
	@GetMapping("similar/{inputTag}")
	public List<Tag> findSimilarTags(@PathVariable String inputTag) {
		
		//String[] inputs = input.split("&");

		//String inputTag = inputs[0];
		//inputs.length >= 2 ? Float.parseFloat(inputs[1]) / 100f : 0.99f;
		final float minScore = 0.69f;//
		
		List<String> tagWords = Arrays.asList(inputTag.toLowerCase().split("[" + Tag.separatorChars + "]+"));
		
		// the double is the score:
		List<Pair<Tag, Float>> filteredTags = new ArrayList<>();
		tagRepository.findAll().forEach(tag -> {
			String name = tag.getName().toLowerCase();

			DoubleSummaryStatistics finalSummary = tagWords.stream()
					.filter(str -> name.contains(str))
					.collect(Collectors.summarizingDouble(str -> {
				return Math.log1p(str.length());
			}));
			
			float finalScore = (float) (finalSummary.getSum() / (float)tagWords.size());
			
			
			if (finalScore >= minScore) {
				filteredTags.add(Pair.of(tag, -finalScore)); // a negative finalScore makes the list be ordered the right way round
			}
			
		});
		
		int maxCountOfProposedTags = 5;
		return filteredTags.stream()
				.sorted(Comparator.comparing(Pair::getSecond))
				.limit(maxCountOfProposedTags)
				.map(pair -> pair.getFirst())
				.collect(Collectors.toList());
		
		//List<Tag> tagList = tags.stream().filter(tag -> tag.getName().replaceAll("[\\- ]", "").contains(id.replaceAll("[\\- ]", ""))).collect(Collectors.toList());
		
	}

	// No delete, because tags are shared:
	//@DeleteMapping("{id}")
	//public void delete(@PathVariable Long id) {
	//	tagRepository.delete(id);
	//}


}
