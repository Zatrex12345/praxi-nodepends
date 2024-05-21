package lol.vifez.praxi;

import com.bizarrealex.aether.Aether;
import com.bizarrealex.aether.AetherOptions;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoDatabase;
import lol.vifez.praxi.adapter.AssembleScoreboardAdapter;
import lol.vifez.praxi.arena.*;
import lol.vifez.praxi.commands.admin.arena.*;
import lol.vifez.praxi.commands.admin.general.SetSpawnCommand;
import lol.vifez.praxi.commands.admin.kits.KitCreateCommand;
import lol.vifez.praxi.commands.admin.kits.KitGetLoadoutCommand;
import lol.vifez.praxi.commands.admin.kits.KitSetLoadoutCommand;
import lol.vifez.praxi.commands.admin.kits.KitsCommand;
import lol.vifez.praxi.commands.admin.match.MatchTestCommand;
import lol.vifez.praxi.commands.donater.FlyCommand;
import lol.vifez.praxi.commands.event.admin.*;
import lol.vifez.praxi.commands.event.map.*;
import lol.vifez.praxi.commands.event.user.*;
import lol.vifez.praxi.commands.event.vote.EventMapVoteCommand;
import lol.vifez.praxi.commands.user.duels.DuelAcceptCommand;
import lol.vifez.praxi.commands.user.duels.DuelCommand;
import lol.vifez.praxi.commands.user.duels.RematchCommand;
import lol.vifez.praxi.commands.user.gamer.SuicideCommand;
import lol.vifez.praxi.commands.user.match.SpectateCommand;
import lol.vifez.praxi.commands.user.match.StopSpectatingCommand;
import lol.vifez.praxi.commands.user.match.ViewInventoryCommand;
import lol.vifez.praxi.commands.user.party.*;
import lol.vifez.praxi.commands.user.settings.ToggleDuelRequestsCommand;
import lol.vifez.praxi.commands.user.settings.ToggleScoreboardCommand;
import lol.vifez.praxi.commands.user.settings.ToggleSpectatorsCommand;
import lol.vifez.praxi.essentials.Essentials;
import lol.vifez.praxi.event.Event;
import lol.vifez.praxi.event.EventTypeAdapter;
import lol.vifez.praxi.event.game.EventGameListener;
import lol.vifez.praxi.event.game.map.EventGameMap;
import lol.vifez.praxi.event.game.map.EventGameMapTypeAdapter;
import lol.vifez.praxi.kit.Kit;
import lol.vifez.praxi.kit.KitEditorListener;
import lol.vifez.praxi.kit.KitTypeAdapter;
import lol.vifez.praxi.match.Match;
import lol.vifez.praxi.match.MatchListener;
import lol.vifez.praxi.party.Party;
import lol.vifez.praxi.party.PartyListener;
import lol.vifez.praxi.profile.Profile;
import lol.vifez.praxi.profile.ProfileListener;
import lol.vifez.praxi.profile.hotbar.Hotbar;
import lol.vifez.praxi.queue.QueueListener;
import lol.vifez.praxi.queue.QueueThread;
import lol.vifez.praxi.scoreboard.ScoreboardAdapter;
import lol.vifez.praxi.util.InventoryUtil;
import lol.vifez.praxi.util.command.Honcho;
import lol.vifez.praxi.util.config.BasicConfigurationFile;
import lol.vifez.praxi.util.menu.MenuListener;
import lol.vifez.praxi.util.scoreboard.AssembleAdapter;
import lombok.Getter;
import org.bukkit.Difficulty;
import org.bukkit.Material;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Arrays;

public class Praxi extends JavaPlugin {

	private static Praxi praxi;

	@Getter private BasicConfigurationFile mainConfig;
	@Getter private BasicConfigurationFile arenasConfig;
	@Getter private BasicConfigurationFile kitsConfig;
	@Getter private BasicConfigurationFile eventsConfig;
	@Getter private BasicConfigurationFile scoreboardConfig;
	@Getter private MongoDatabase mongoDatabase;
	@Getter private Honcho honcho;
	@Getter private Essentials essentials;

	@Override
	public void onEnable() {
		praxi = this;
		getServer().getPluginManager().registerEvents(new MenuListener(), this);
		honcho = new Honcho(this);
		mainConfig = new BasicConfigurationFile(this, "config");
		arenasConfig = new BasicConfigurationFile(this, "arenas");
		kitsConfig = new BasicConfigurationFile(this, "kits");
		eventsConfig = new BasicConfigurationFile(this, "events");
		scoreboardConfig = new BasicConfigurationFile(this, "scoreboard");

		this.essentials = new Essentials(this);
		loadMongo();

		Hotbar.init();
		Kit.init();
		Arena.init();
		Profile.init();
		Match.init();
		Party.init();
		Event.init();
		EventGameMap.init();


		//TODO: Make sure assemble works. If you want to use it comment this out and uncomment whats below it


		new Aether(this, new ScoreboardAdapter(), new AetherOptions().hook(true));

		//AssembleScoreboardAdapter scoreboardAdapter = new AssembleScoreboardAdapter();




		new QueueThread().start();

		getHoncho().registerTypeAdapter(Arena.class, new ArenaTypeAdapter());
		getHoncho().registerTypeAdapter(ArenaType.class, new ArenaTypeTypeAdapter());
		getHoncho().registerTypeAdapter(Kit.class, new KitTypeAdapter());
		getHoncho().registerTypeAdapter(EventGameMap.class, new EventGameMapTypeAdapter());
		getHoncho().registerTypeAdapter(Event.class, new EventTypeAdapter());


		Arrays.asList(
				new ArenaAddKitCommand(),
				new ArenaRemoveKitCommand(),
				new ArenaCreateCommand(),
				new ArenaDeleteCommand(),
				new ArenaGenerateCommand(),
				new ArenaGenHelperCommand(),
				new ArenaSaveCommand(),
				new ArenasCommand(),
				new ArenaSelectionCommand(),
				new ArenaSetSpawnCommand(),
				new ArenaStatusCommand(),
				new DuelCommand(),
				new DuelAcceptCommand(),
				new EventAdminCommand(),
				new EventHelpCommand(),
				new EventCancelCommand(),
				new EventClearCooldownCommand(),
				new EventForceStartCommand(),
				new EventHostCommand(),
				new EventInfoCommand(),
				new EventJoinCommand(),
				new EventLeaveCommand(),
				new EventSetLobbyCommand(),
				new EventMapCreateCommand(),
				new EventMapDeleteCommand(),
				new EventMapsCommand(),
				new EventMapSetSpawnCommand(),
				new EventMapStatusCommand(),
				new EventMapVoteCommand(),
				new EventAddMapCommand(),
				new EventRemoveMapCommand(),
				new EventsCommand(),
				new RematchCommand(),
				new SpectateCommand(),
				new StopSpectatingCommand(),
				new FlyCommand(),
				new SetSpawnCommand(),
				new PartyChatCommand(),
				new PartyCloseCommand(),
				new PartyCreateCommand(),
				new PartyDisbandCommand(),
				new PartyHelpCommand(),
				new PartyInfoCommand(),
				new PartyInviteCommand(),
				new PartyJoinCommand(),
				new PartyKickCommand(),
				new PartyLeaveCommand(),
				new PartyOpenCommand(),
				new KitCreateCommand(),
				new KitGetLoadoutCommand(),
				new KitSetLoadoutCommand(),
				new KitsCommand(),
				new ViewInventoryCommand(),
				new MatchTestCommand(),
				new ToggleScoreboardCommand(),
				new ToggleSpectatorsCommand(),
				new ToggleDuelRequestsCommand(),
				new SuicideCommand()
		).forEach(command -> getHoncho().registerCommand(command));

		Arrays.asList(
				new KitEditorListener(),
				new PartyListener(),
				new ProfileListener(),
				new PartyListener(),
				new MatchListener(),
				new QueueListener(),
				new ArenaListener(),
				new EventGameListener()
		).forEach(listener -> getServer().getPluginManager().registerEvents(listener, this));

		Arrays.asList(
				Material.WORKBENCH,
				Material.STICK,
				Material.WOOD_PLATE,
				Material.WOOD_BUTTON,
				Material.SNOW_BLOCK
		).forEach(InventoryUtil::removeCrafting);

		// Set the difficulty for each world to HARD
		// Clear the droppedItems for each world

		getServer().getWorlds().forEach(world -> {
			world.setDifficulty(Difficulty.HARD);
			getEssentials().clearEntities(world);

		});
	}

	@Override
	public void onDisable() {
		Match.cleanup();
	}

	private void loadMongo() {
		if (mainConfig.getBoolean("MONGO.AUTHENTICATION.ENABLED")) {
			mongoDatabase = new MongoClient(
					new ServerAddress(
							mainConfig.getString("MONGO.HOST"),
							mainConfig.getInteger("MONGO.PORT")
					),
					MongoCredential.createCredential(
							mainConfig.getString("MONGO.AUTHENTICATION.USERNAME"),
							mainConfig.getString("MONGO.AUTHENTICATION.ADMIN"), mainConfig.getString("MONGO.AUTHENTICATION.PASSWORD").toCharArray()
					),
					MongoClientOptions.builder().build()
			).getDatabase(mainConfig.getString("MONGO.DATABASE"));
		} else {
			mongoDatabase = new MongoClient(mainConfig.getString("MONGO.HOST"), mainConfig.getInteger("MONGO.PORT"))
					.getDatabase(mainConfig.getString("MONGO.DATABASE"));
		}
	}

	public static Praxi get() {
		return praxi;
	}

}
