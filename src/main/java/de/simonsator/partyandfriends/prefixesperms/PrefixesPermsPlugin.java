package de.simonsator.partyandfriends.prefixesperms;

import de.simonsator.partyandfriends.api.PAFExtension;
import de.simonsator.partyandfriends.api.pafplayers.DisplayNameProvider;
import de.simonsator.partyandfriends.api.pafplayers.OnlinePAFPlayer;
import de.simonsator.partyandfriends.api.pafplayers.PAFPlayer;
import de.simonsator.partyandfriends.api.pafplayers.PAFPlayerClass;
import de.simonsator.partyandfriends.prefixesperms.configuration.PrefixesPermsConfig;
import net.md_5.bungee.config.Configuration;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Simonsator
 * @version 1.0 09.06.2017.
 */
public class PrefixesPermsPlugin extends PAFExtension implements DisplayNameProvider {
	private String defaultPrefix;
	private List<PrefixPack> prefixes;
	private String offlinePrefix;

	@Override
	public void onEnable() {
		try {
			Configuration config = new PrefixesPermsConfig(new File(getConfigFolder(), "config.yml"), this).getCreatedConfiguration();
			defaultPrefix = config.getString("General.DefaultPrefix");
			offlinePrefix = config.getString("General.OfflinePrefix");
			prefixes = new LinkedList<>();
			for (String key : config.getSection("Prefixes").getKeys()) {
				prefixes.add(new PrefixPack(config.getString("Prefixes." + key + ".Prefix"), config.getString("Prefixes." + key + ".Permission")));
			}
			PAFPlayerClass.setDisplayNameProvider(this);
			registerAsExtension();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public String getDisplayName(PAFPlayer pPlayer) {
		return offlinePrefix.replace("[PLAYER_NAME]", pPlayer.getName());
	}

	@Override
	public String getDisplayName(OnlinePAFPlayer pPlayer) {
		if(pPlayer.getSettingsWorth(3)==1)
			return getDisplayName((PAFPlayer) pPlayer);
		for (PrefixPack prefixPack : prefixes) {
			if (pPlayer.hasPermission(prefixPack.PERMISSION)) {
				return prefixPack.PREFIX.replace("[PLAYER_NAME]", pPlayer.getName());
			}
		}
		return defaultPrefix.replace("[PLAYER_NAME]", pPlayer.getName());
	}
}
