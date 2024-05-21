package lol.vifez.praxi.kit.meta;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class KitEditRules {

	@Getter @Setter private boolean allowPotionFill;
	@Getter private final List<ItemStack> editorItems = new ArrayList<>();

}
