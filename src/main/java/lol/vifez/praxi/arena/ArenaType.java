package lol.vifez.praxi.arena;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ArenaType {
	STANDALONE,
	SHARED,
	DUPLICATE
}