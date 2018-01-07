package base.activitymeter;

import java.util.HashSet;
import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import com.google.gson.Gson;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest
public class TagControllerTest {


	@Autowired
	MockMvc mockMvc;

	public TagControllerTest() {
		
	}
	
	private static final Long ID = (long) 42;
	private static final Long NONSENSE_TEST_ID = (long) 666;
	
	private final String[] randomTitles = {
			"Informationen",
			"Nachrichten und Ankündigungen Forum",
			"Literatur Textseite",
			"Hinweise zu den Übungen im Buch Domschke/Drexl Datei",
			"Evaluierung WS 17/18 Feedback",
			"Evaluierung der Lehrveranstaltung Operations Research im WS17/18",
			"Einführung/Modellbildung",
			"Lineare Probleme",
			"Grafische Lösung linearer Optimierungsprobleme - Teil 1 Textseite",
			"Grafische Lösung linearer Optimierungsprobleme - Teil 2 Textseite",
			"Bitte bearbeiten Sie dann den folgenden Test.",
			"Test: Grafische Lösung linearer Optimierungsprobleme",
			"Beispiel 2.7: Lösung mit Zweiphasenmethode Datei",
			"Beispiel 2.9. Korrigiertes Sinmplex-Tableau und korrigierte Lösung Datei",
			"Spezielle lineare Probleme",
			"Modi-Methode: Theoretische Herleitung Textseite",
			"Modi-Verfahren zur Lösung klassischer Transportporbleme - Beispiel 3.1 Textseite",
			"Zusatzmaterial zu Lösungen der Übungsaufgaben:",
			"Video Umladeprobleme, Aufgabe 2 vom Übungsblatt Textseite",
			"Graphentheorie",
			"Tripel-Algorithmus in Python Datei",
			"Tripel-Algorithmus mit der Hand am Beispiel Textseite",
			"Netzplantechnik",
			"Dynamische Optimierung",
			"Skript Dynamische Optimierung Datei",
			"Ganzzahlige und kombinatorische Optimierung",
			"Skript: Diskrete Optimierung Datei",
			"Warteschlangentheorie",
			"Skript Kapitel 8: Warteschlagentheorie Datei",
			"Nichtlineare Optimierung",
			"Simulation",
			"Sonstiges",
			"Probeprüfung",
	};
	
	private final String[] randomTexts = {
			"Informationen",
			"Nachrichten und Ankündigungen Forum",
			"Literatur Textseite",
			"Hinweise zu den Übungen im Buch Domschke/Drexl Datei",
			"Evaluierung WS 17/18 Feedback",
			"Evaluierung der Lehrveranstaltung Operations Research im WS17/18",
			"Einführung/Modellbildung",
			"Lineare Probleme",
			"Bitte lesen Sie Kapitel 2.1 im Skript und bearbeiten Sie die dort gestellten Aufgaben. Die beiden folgenden  Videos erklären die Vorgehensweise ausführlich.",
			"Grafische Lösung linearer Optimierungsprobleme - Teil 1 Textseite",
			"Grafische Lösung linearer Optimierungsprobleme - Teil 2 Textseite",
			"Bitte bearbeiten Sie dann den folgenden Test.",
			"Test: Grafische Lösung linearer Optimierungsprobleme",
			"Beispiel 2.7: Lösung mit Zweiphasenmethode Datei",
			"Beispiel 2.9. Korrigiertes Sinmplex-Tableau und korrigierte Lösung Datei",
			"Spezielle lineare Probleme",
			"Modi-Methode: Theoretische Herleitung Textseite",
			"Modi-Verfahren zur Lösung klassischer Transportporbleme - Beispiel 3.1 Textseite",
			"Zusatzmaterial zu Lösungen der Übungsaufgaben:",
			"Video Umladeprobleme, Aufgabe 2 vom Übungsblatt Textseite",
			"Graphentheorie",
			"Tripel-Algorithmus in Python Datei",
			"Tripel-Algorithmus mit der Hand am Beispiel Textseite",
			"Netzplantechnik",
			"Dynamische Optimierung",
			"Skript Dynamische Optimierung Datei",
			"Ganzzahlige und kombinatorische Optimierung",
			"Skript: Diskrete Optimierung Datei",
			"Warteschlangentheorie",
			"Skript Kapitel 8: Warteschlagentheorie Datei",
			"Nichtlineare Optimierung",
			"Simulation",
			"Probeprüfung",
	};
	
	private final String[] randomTags = {
			"Informationen",
			"Nachrichten, und, Ankündigungen, Forum",
			"Literatur, Textseite",
			"Hinweise, zu, den, Übungen, im, Buch, Domschke/Drexl, Datei",
			"Evaluierung, WS, 17/18, Feedback",
			"Evaluierung, der, Lehrveranstaltung, Operations, Research, im, WS17/18",
			"Einführung/Modellbildung",
			"Lineare, Probleme",
			"Bitte, lesen",
			"Grafische, Lösung, linearer, Optimierungsprobleme, -, Teil, 1, Textseite",
			"Grafische, Lösung, linearer, Optimierungsprobleme, -, Teil, 2, Textseite",
			"Bitte, bearbeiten, Sie, dann, den, folgenden, Test.",
			"Test:, Grafische, Lösung, linearer, Optimierungsprobleme",
			"Beispiel, 2.7:, Lösung, mit, Zweiphasenmethode, Datei",
			"Beispiel, 2.9., Korrigiertes, Sinmplex-Tableau, und, korrigierte, Lösung, Datei",
			"Spezielle, lineare, Probleme",
			"Modi-Methode:, Theoretische, Herleitung, Textseite",
			"Modi-Verfahren, zur, Lösung, klassischer, Transportporbleme, -, Beispiel, 3.1, Textseite",
			"Zusatzmaterial, zu, Lösungen, der, Übungsaufgaben:",
			"Video, Umladeprobleme,, Aufgabe, 2, vom, Übungsblatt, Textseite",
			"Graphentheorie",
			"Tripel-Algorithmus, in, Python, Datei",
			"Tripel-Algorithmus, mit, der, Hand, am, Beispiel, Textseite",
			"Netzplantechnik",
			"Dynamische, Optimierung",
			"Skript, Dynamische, Optimierung, Datei",
			"Ganzzahlige, und, kombinatorische, Optimierung",
			"Skript:, Diskrete, Optimierung, Datei",
			"Warteschlangentheorie",
			"Skript, Kapitel, 8:, Warteschlagentheorie, Datei",
			"Nichtlineare, Optimierung",
			"Simulation",
			"Probeprüfung",
	};
	
	private Activity makeActivity(int number) {
		int hash1 = Integer.toString(number).hashCode();
		int hash2 = Integer.toString(hash1).hashCode();
		int hash3 = Integer.toString(hash2).hashCode();
		int i = hash1 % randomTexts.length;
		int j = hash2 % randomTags.length;
		int k = hash3 % randomTitles.length;
		HashSet<Tag> tags = new HashSet<Tag>();
		tags.add(new Tag(randomTags[j]));
		return new Activity(randomTexts[i], tags, randomTitles[k]);
	}

	private String makeActivityJSON(Activity activity) {
		String json = String.format("{ \"id\":\"%d\", \"text\":\"%s\", \"tags\":%s, \"title\":\"%s\" }", 
				activity.getId(), activity.getText(), "[{\"name\": \"" + activity.getTags().toArray(new Tag[1])[0] + "\"}]", activity.getTitle());
		
		return json;
	}

	private String makeActivityJSON(int number) {
		Activity activity = makeActivity(number);
		String json = new Gson().toJson(activity, Activity.class);
		return json;
	}
	
	private <T> String makeRequestBodyEmailJSON(T data) {
		String json = new Gson().toJson(new RequestBodyEmail<T>(data, "nonsenseeMailAdressTissIs@gmail.com"), RequestBodyEmail.class);
		return json;
	}
	
	private String getAnActivityID() throws Exception{
		// Get ID of that activity
		String result = mockMvc.perform(get("/activity")).andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
		int startIndex = result.indexOf("\"id\":") + 5;
		int endIndex = result.indexOf(",", startIndex);
		
		String activityID = result.substring(startIndex, endIndex);
		return activityID;
	}

	private String makeActionConfirm() {
		TestHelper.actionsRequested++; // starts at 1 (1, 2, 3, ...)
		System.out.println("TagCtrl: TestHelper.actionsRequested == " + TestHelper.actionsRequested);
		return "/actions/confirm/" + (TestHelper.actionsRequested);
		
	}
	
	private ResultActions confirmAction() throws Exception {
		return mockMvc.perform(get(makeActionConfirm())).andExpect(status().isOk());
	}
	
	@Test(timeout = 10000)
	public void testListAll_1() throws Exception {
		mockMvc.perform(get("/tag")).andExpect(status().isOk());
	}
	

	@Test(timeout = 10000)
	public void testFindSimilarTags() throws Exception {
		String result1 = mockMvc.perform(get("/tag/similar/tag")).andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
		assertTrue(result1.contains("[]"));

		Activity activity = makeActivity(0);
		activity.getTags().add(new Tag("tag1"));
		activity.getTags().add(new Tag("tag2"));
		activity.getTags().add(new Tag("tag3"));
		activity.getTags().add(new Tag("hello World"));
		activity.getTags().add(new Tag("ta_g"));

		String activityStr = makeRequestBodyEmailJSON(activity);
		mockMvc.perform(post("/activity").contentType(MediaType.APPLICATION_JSON).content(activityStr)).andExpect(status().isOk());
		confirmAction();
		

		String result2 = mockMvc.perform(get("/tag/similar/tag")).andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
		assertTrue(result2.contains("tag1"));
		assertTrue(result2.contains("tag2"));
		assertTrue(result2.contains("tag3"));
		assertFalse(result2.contains("hello World"));
	}
}
