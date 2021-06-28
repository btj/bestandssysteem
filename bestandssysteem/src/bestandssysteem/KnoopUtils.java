package bestandssysteem;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class KnoopUtils {
	
	public static Set<Knoop> getAfstammelingen(Knoop knoop) {
		Set<Knoop> result = new HashSet<>();
		if (knoop instanceof Directory) {
			for (Knoop kind : ((Directory)knoop).getIngangen().values()) {
				result.add(kind);
				result.addAll(getAfstammelingen(kind));
			}
		}
		return result;
	}
	
	public static Stream<Knoop> getAfstammelingenStream(Knoop knoop) {
		if (knoop instanceof Directory) {
			return ((Directory)knoop)
					.getIngangen().values().stream().flatMap(k ->
						Stream.concat(Stream.of(k), getAfstammelingenStream(k)));
		} else
			return Stream.of();
	}
	
	public static Set<Knoop> getAfstammelingenMetStreams(Knoop knoop) {
		return getAfstammelingenStream(knoop).collect(Collectors.toSet());
	}
	
	public static Iterator<Byte> bytesIterator(Bestand bestand) {
		return new Iterator<Byte>() {
			byte[] bytes = bestand.getInhoud();
			int index = 0;
			public boolean hasNext() { return index < bytes.length; }
			public Byte next() { return bytes[index++]; }
		};
	}
	
	public static void forEachKind(Directory directory, Consumer<? super Knoop> consumer) {
		for (Knoop kind : directory.getIngangen().values())
			consumer.accept(kind);
	}

}
