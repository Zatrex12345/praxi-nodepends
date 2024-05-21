package lol.vifez.praxi.profile.meta;

import lol.vifez.praxi.kit.Kit;
import lol.vifez.praxi.kit.KitLoadout;
import lombok.Getter;
import lombok.Setter;

public class ProfileKitEditorData {

	@Getter @Setter private boolean active;
	@Setter private boolean rename;
	@Getter @Setter private Kit selectedKit;
	@Getter @Setter private KitLoadout selectedKitLoadout;

	public boolean isRenaming() {
		return this.active && this.rename && this.selectedKit != null;
	}

}
