package lol.vifez.praxi.event;

import lol.vifez.praxi.event.impl.sumo.SumoEvent;
import lol.vifez.praxi.util.command.command.adapter.CommandTypeAdapter;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EventTypeAdapter implements CommandTypeAdapter {

	private final static Map<String, Class<? extends Event>> map;

	static {
		map = new HashMap<>();
		map.put("sumo", SumoEvent.class);
	}

	@Override
	public <T> T convert(String string, Class<T> type) {
		return type.cast(Event.getEvent(map.get(string.toLowerCase())));
	}

	@Override
	public <T> List<String> tabComplete(String string, Class<T> type) {
		return Arrays.asList("Sumo", "Corners");
	}

}
