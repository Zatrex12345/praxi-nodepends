package lol.vifez.praxi.commands.admin.arena;

import lol.vifez.praxi.Praxi;
import lol.vifez.praxi.arena.Arena;
import lol.vifez.praxi.arena.ArenaType;
import lol.vifez.praxi.arena.generator.ArenaGenerator;
import lol.vifez.praxi.arena.generator.Schematic;
import lol.vifez.praxi.arena.impl.StandaloneArena;
import lol.vifez.praxi.util.CC;
import lol.vifez.praxi.util.command.command.CommandMeta;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.scheduler.BukkitRunnable;

import java.io.File;

@CommandMeta(label = "arena generate", permission = "praxi.admin.arena", async = true)
public class ArenaGenerateCommand {

	public void execute(CommandSender sender) {
		File schematicsFolder = new File(Praxi.get().getDataFolder().getPath() + File.separator + "schematics");

		if (!schematicsFolder.exists()) {
			sender.sendMessage(CC.RED + "The schematics folder does not exist.");
			return;
		}

		for (File file : schematicsFolder.listFiles()) {
			if (!file.isDirectory() && file.getName().contains(".schematic")) {
				boolean duplicate = file.getName().endsWith("_duplicate.schematic");

				String name = file.getName()
				                  .replace(".schematic", "")
				                  .replace("_duplicate", "");

				Arena parent = Arena.getByName(name);

				if (parent != null) {
					if (!(parent instanceof StandaloneArena)) {
						System.out.println("Skipping " + name + " because it's not duplicate and an arena with that name already exists.");
						continue;
					}
				}

				new BukkitRunnable() {
					@Override
					public void run() {
						try {
							new ArenaGenerator(name, Bukkit.getWorlds().get(0), new Schematic(file), duplicate ?
									(parent != null ? ArenaType.DUPLICATE : ArenaType.STANDALONE) : ArenaType.SHARED)
									.generate(file, (StandaloneArena) parent);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}.runTask(Praxi.get());
			}
		}

		sender.sendMessage(CC.GREEN + "Generating arenas... See console for details.");
	}

}
