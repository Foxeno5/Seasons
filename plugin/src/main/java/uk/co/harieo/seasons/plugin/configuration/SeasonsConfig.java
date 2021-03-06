package uk.co.harieo.seasons.plugin.configuration;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;
import java.util.List;
import uk.co.harieo.seasons.plugin.actionbar.SeasonsActionBar;

/**
 * Loads the plugin's settings from the configuration file
 */
public class SeasonsConfig implements ConfigurationProvider {

	private double currentVersion;
	private int daysPerSeason; // Days that must go by before the world moves to the next season
	private int secondsPerDamage; // Whether to activate the effects of the seasonal weathers
	private int roofHeight; // The height of the roof that would prevent an effect from the sky
	private boolean enableEffects; // How many ticks per damage dealt to a player on a harmful weather
	private List<String> disabledWorlds;
	private List<String> disabledWeathers;
	private List<String> disabledEffects;

	@Override
	public boolean load(JavaPlugin plugin) {
		FileConfiguration config;
		try {
			config = getConfiguration(plugin);
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}

		currentVersion = config.getDouble("version");
		daysPerSeason = config.getInt("DaysPerSeason");
		secondsPerDamage = config.getInt("SecondsOfDamage");
		roofHeight = config.getInt("RoofHeight");
		enableEffects = config.getBoolean("CustomWeathers");
		if (config.getBoolean("ActionBar")) {
			SeasonsActionBar.start();
		} else {
			SeasonsActionBar.stop();
		}
		disabledWorlds = config.getStringList("disabled-worlds");
		disabledWeathers = config.getStringList("disabled-weathers");
		disabledEffects = config.getStringList("disabled-effects");
		plugin.getLogger().info(disabledWorlds.size() + " worlds have been disabled, " + disabledWeathers.size()
				+ " weathers have been disabled and " + disabledEffects.size() + " have been disabled");
		verifyVersion();

		return true;
	}

	@Override
	public String getFileName() {
		return "config.yml";
	}

	@Override
	public double getLatestVersion() {
		return 3.0;
	}

	@Override
	public double getCurrentVersion() {
		return currentVersion;
	}

	public int getDaysPerSeason() {
		return daysPerSeason;
	}

	public int getSecondsPerDamage() {
		return secondsPerDamage;
	}

	public int getRoofHeight() {
		return roofHeight;
	}

	public boolean hasEnabledEffects() {
		return enableEffects;
	}

	public List<String> getDisabledWorlds() {
		return disabledWorlds;
	}

	public List<String> getDisabledWeathers() {
		return disabledWeathers;
	}

	public List<String> getDisabledEffects() {
		return disabledEffects;
	}

}
