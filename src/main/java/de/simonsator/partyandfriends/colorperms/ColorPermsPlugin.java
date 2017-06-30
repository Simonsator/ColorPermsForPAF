package de.simonsator.partyandfriends.colorperms;

import de.simonsator.partyandfriends.api.PAFExtension;
import de.simonsator.partyandfriends.api.pafplayers.DisplayNameProvider;
import de.simonsator.partyandfriends.api.pafplayers.OnlinePAFPlayer;
import de.simonsator.partyandfriends.api.pafplayers.PAFPlayer;
import de.simonsator.partyandfriends.api.pafplayers.PAFPlayerClass;
import de.simonsator.partyandfriends.colorperms.configuration.ColorPermsConfig;
import net.md_5.bungee.config.Configuration;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Simonsator
 * @version 1.0 09.06.2017.
 */
public class ColorPermsPlugin extends PAFExtension implements DisplayNameProvider {
	private String defaultColor;
	private List<ColorPack> colors;
	private String offlineColor;

	@Override
	public void onEnable() {
		try {
			Configuration config = new ColorPermsConfig(new File(getConfigFolder(), "config.yml")).getCreatedConfiguration();
			defaultColor = config.getString("General.DefaultColor");
			offlineColor = config.getString("General.OfflineColor");
			colors = new LinkedList<>();
			for (String key : config.getSection("Colors").getKeys()) {
				colors.add(new ColorPack(config.getString("Colors." + key + ".Color"), config.getString("Colors." + key + ".Permission")));
			}
			PAFPlayerClass.setDisplayNameProvider(this);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void reload() {
		onDisable();
		onEnable();
	}

	@Override
	public String getDisplayName(PAFPlayer pPlayer) {
		return offlineColor + pPlayer.getName();
	}

	@Override
	public String getDisplayName(OnlinePAFPlayer pPlayer) {
		for (ColorPack colorPack : colors) {
			if (pPlayer.hasPermission(colorPack.PERMISSION)) {
				return colorPack.COLOR + pPlayer.getName();
			}
		}
		return defaultColor + pPlayer.getName();
	}
}
