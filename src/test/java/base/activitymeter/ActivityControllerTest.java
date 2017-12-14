package base.activitymeter;


import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.boot.autoconfigure.jdbc.*;
//org/springframework/boot/autoconfigure/jdbc/DataSourceConfiguration$Tomcat.class
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import base.Application;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;


@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest
public class ActivityControllerTest {

	@Autowired
	MockMvc mockMvc;

	public ActivityControllerTest() {
		
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
		return new Activity(randomTexts[i], randomTags[j], randomTitles[k]);
	}

	private String makeActivityJSON(Activity activity) {
		String json = String.format("{ \"id\":\"%d\", \"text\":\"%s\", \"tags\":\"%s\", \"title\":\"%s\" }", 
				activity.getId(), activity.getText(), activity.getTags(), activity.getTitle());
		
		return json;
	}

	private String makeActivityJSON(int number) {
		Activity activity = makeActivity(number);
		String json = makeActivityJSON(activity);
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

	@Test(timeout = 10000)
	public void testListAll_1() throws Exception {
		mockMvc.perform(get("/activity")).andExpect(status().isOk());
	}
	
	@Test(timeout = 10000)
	public void testFind() throws Exception {
		mockMvc.perform(get("/activity/0")).andExpect(status().isOk());
	}

	@Test(timeout = 10000)
	public void testDelete() throws Exception {
		String activity = makeActivityJSON(0);
		// Add an activity
		mockMvc.perform(post("/activity").contentType(MediaType.APPLICATION_JSON).content(activity)).andExpect(status().isOk());

		// Get ID of that activity
		String activityID = getAnActivityID();
		
		// Delete the activity
		mockMvc.perform(delete("/activity/" + activityID)).andExpect(status().isOk());
		
		// make sure it got deleted
		String result2 = mockMvc.perform(get("/activity")).andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
		assertFalse(result2.contains(activityID));
	}

	@Test(timeout = 10000)
	public void testUpdate_1() throws Exception {
		String activity = makeActivityJSON(7);
		String newActivity = makeActivityJSON(9);
		// Add an activity
		mockMvc.perform(post("/activity").contentType(MediaType.APPLICATION_JSON).content(activity)).andExpect(status().isOk());

		// Get ID of that activity
		String activityID = getAnActivityID();

		// Update an activity
		mockMvc.perform(put("/activity/" + activityID).contentType(MediaType.APPLICATION_JSON).content(newActivity)).andExpect(status().isOk());
		
	}

	@Test(timeout = 10000)
	public void testUpdate_2() throws Exception {
		String newActivity = makeActivityJSON(6);

		// Update an activity that does not exist
		mockMvc.perform(put("/activity/" + "420024").contentType(MediaType.APPLICATION_JSON).content(newActivity)).andExpect(status().isOk());
		
	}
	
//	@Test(expected = NullPointerException.class)
	public void testCreate() throws Exception {
		String activity = makeActivityJSON(-42);
		mockMvc.perform(post("/activity").contentType(MediaType.APPLICATION_JSON).content(activity)).andExpect(status().isOk());
	}

}
