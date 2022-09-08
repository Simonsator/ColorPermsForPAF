package de.simonsator.partyandfriends.velocity.prefixesperms;

import de.simonsator.partyandfriends.velocity.api.PAFExtension;
import de.simonsator.partyandfriends.velocity.api.pafplayers.DisplayNameProvider;
import de.simonsator.partyandfriends.velocity.api.pafplayers.OnlinePAFPlayer;
import de.simonsator.partyandfriends.velocity.api.pafplayers.PAFPlayer;
import de.simonsator.partyandfriends.velocity.api.pafplayers.PAFPlayerClass;
import de.simonsator.partyandfriends.velocity.friends.settings.OfflineSetting;
import de.simonsator.partyandfriends.velocity.prefixesperms.configuration.PrefixesPermsConfig;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.LinkedList;
import java.util.List;

public class PrefixesPermsPlugin extends PAFExtension implements DisplayNameProvider {
	private String defaultPrefix;
	private List<PrefixPack> prefixes;
	private String offlinePrefix;

	public PrefixesPermsPlugin(Path folder) {
		super(folder);
	}

	@Override
	public void onEnable() {
		try {
			PrefixesPermsConfig config = new PrefixesPermsConfig(new File(getConfigFolder(),
					"config.yml"), this);
			defaultPrefix = config.getString("General.DefaultPrefix");
			offlinePrefix = config.getString("General.OfflinePrefix");
			prefixes = new LinkedList<>();
			for (String key : config.getSectionKeys("Prefixes")) {
				prefixes.add(new PrefixPack(config.getString("Prefixes." + key + ".Prefix"),
						config.getString("Prefixes." + key + ".Permission")));
			}
			PAFPlayerClass.setDisplayNameProvider(this);
			registerAsExtension();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public String getName() {
		return "PrefixesPerms-For-PAF";
	}

	@Override
	public String getDisplayName(PAFPlayer pPlayer) {
		return offlinePrefix.replace("[PLAYER_NAME]",
				pPlayer.getName());
	}

	@Override
	public String getDisplayName(OnlinePAFPlayer pPlayer) {
		if (pPlayer.getSettingsWorth(OfflineSetting.SETTINGS_ID) ==
				OfflineSetting.FRIENDS_ALWAYS_SEE_PLAYER_AS_OFFLINE_STATE)
			return getDisplayName((PAFPlayer) pPlayer);
		for (PrefixPack prefixPack : prefixes) {
			if (pPlayer.hasPermission(prefixPack.PERMISSION)) {
				return prefixPack.PREFIX.replace("[PLAYER_NAME]", pPlayer.getName());
			}
		}
		return defaultPrefix.replace("[PLAYER_NAME]", pPlayer.getName());
	}
}