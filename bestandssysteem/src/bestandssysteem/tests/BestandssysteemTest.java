package bestandssysteem.tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.jupiter.api.Test;

import bestandssysteem.Bestand;
import bestandssysteem.Directory;
import bestandssysteem.Knoop;
import bestandssysteem.KnoopUtils;

class BestandssysteemTest {

	@Test
	void test() {
		Bestand priemgetallen = new Bestand();
		assertEquals(null, priemgetallen.getOuder());
		assertArrayEquals(new byte[] {}, priemgetallen.getInhoud());
		
		priemgetallen.setInhoud(new byte[] {2, 3, 5, 7, 11});
		assertArrayEquals(new byte[] {2, 3, 5, 7, 11}, priemgetallen.getInhoud());
		
		Directory documenten = new Directory();
		assertEquals(null, documenten.getOuder());
		assertEquals(Map.of(), documenten.getIngangen());
		
		documenten.addIngang("priemgetallen", priemgetallen);
		assertEquals(Map.of("priemgetallen", priemgetallen), documenten.getIngangen());
		assertEquals(documenten, priemgetallen.getOuder());
		
		Directory jan = new Directory();
		jan.addIngang("Documenten", documenten);
		
		assertEquals(priemgetallen, jan.zoekOp(List.of("Documenten", "priemgetallen")));
		assertEquals(null, jan.zoekOp(List.of("Documenten", "onbestaand_bestand")));
		assertEquals(null, jan.zoekOp(List.of("Documenten", "priemgetallen", "fout_pad")));
		assertEquals(jan, jan.zoekOp(List.of()));
		
		assertEquals(Set.of(documenten, priemgetallen), KnoopUtils.getAfstammelingen(jan));
		
		ArrayList<Byte> bytes = new ArrayList<>();
		for (Iterator<Byte> i = KnoopUtils.bytesIterator(priemgetallen); i.hasNext(); )
			bytes.add(i.next());
		assertEquals(Arrays.asList(new Byte[] {2, 3, 5, 7, 11}), bytes);
		
		HashSet<Knoop> kinderen = new HashSet<>();
		KnoopUtils.forEachKind(jan, kind -> {
			assertFalse(kinderen.contains(kind));
			kinderen.add(kind);
		});
		assertEquals(Set.of(documenten), kinderen);
	}

}
