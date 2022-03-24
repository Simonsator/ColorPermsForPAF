package de.simonsator.partyandfriends.prefixesperms.configuration;

import de.simonsator.partyandfriends.api.PAFExtension;
import de.simonsator.partyandfriends.utilities.ConfigurationCreator;

import java.io.File;
import java.io.IOException;

/**
 * @author 00pfl
 * @version 1.0 09.06.2017.
 */
public class PrefixesPermsConfig extends ConfigurationCreator {
	public PrefixesPermsConfig(File pFile, PAFExtension pafExtension) throws IOException {
		super(pFile, pafExtension, true);
		copyFromJar();
		readFile();
		loadDefaults();
		saveFile();
		process();
	}

	private void loadDefaults() {
		set("General.OfflinePrefix", "&c[Offline] &c[PLAYER_NAME]");
		set("General.DefaultPrefix", "&e[PLAYER_NAME]");
		if (get("Prefixes") == null) {
			set("Prefixes.Admin.Prefix", "&4[Admin] &4[PLAYER_NAME]");
			set("Prefixes.Admin.Permission", "de.simonsator.partyandfriends.prefixesperms.admin");
			set("Prefixes.Premium.Prefix", "&9[PLAYER_NAME]");
			set("Prefixes.Premium.Permission", "de.simonsator.partyandfriends.prefixesperms.premium");
			set("Prefixes.AddMorePrefixRanksIfYouWant.Prefix", "&e[PLAYER_NAME]");
			set("Prefixes.AddMorePrefixRanksIfYouWant.Permission", "perm.xyz");
		}
	}
}
