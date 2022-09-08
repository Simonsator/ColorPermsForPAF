package de.simonsator.partyandfriends.velocity.prefixesperms;

import com.google.inject.Inject;
import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.event.proxy.ProxyInitializeEvent;
import com.velocitypowered.api.plugin.Dependency;
import com.velocitypowered.api.plugin.Plugin;
import de.simonsator.partyandfriends.velocity.VelocityExtensionLoadingInfo;
import de.simonsator.partyandfriends.velocity.main.PAFPlugin;

import java.nio.file.Path;

@Plugin(id = "prefixesperms-for-paf", name = "PrefixesPerms-For-PAF", version = "1.0.3-SNAPSHOT",
		url = "https://www.spigotmc.org/resources/prefixes-for-party-and-friends.43105/",
		description = "An add-on for party and friends to add prefixes to the names"
		, authors = {"JT122406", "Simonsator"}, dependencies = {@Dependency(id = "partyandfriends")})
public class PrefixPackLoader {

	private final Path folder;

	@Inject
	public PrefixPackLoader(Path pFolder) {
		folder = pFolder;
	}

	@Subscribe
	public void onProxyInitialization(ProxyInitializeEvent event) {
		PAFPlugin.loadExtension(new VelocityExtensionLoadingInfo(new PrefixesPermsPlugin(folder),
				"prefixesperms-for-paf",
				"PrefixesPerms-For-PAF",
				"1.0.3-RELEASE", "JT122406, Simonsator"));
	}
}