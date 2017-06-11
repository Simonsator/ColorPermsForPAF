package de.simonsator.partyandfriends.colorperms.configuration;

import de.simonsator.partyandfriends.utilities.ConfigurationCreator;

import java.io.File;
import java.io.IOException;

/**
 * @author 00pfl
 * @version 1.0 09.06.2017.
 */
public class ColorPermsConfig extends ConfigurationCreator {
    public ColorPermsConfig(File pFile) throws IOException {
        super(pFile);
        readFile();
        loadDefaults();
        saveFile();
        process(configuration);
    }

    private void loadDefaults() {
        set("General.OfflineColor", "&c [Offline] &c");
        set("General.DefaultColor", "&e");
        set("Colors.Admin.Color", "&4 [Admin] &4");
        set("Colors.Admin.Permission", "de.simonsator.partyandfriends.colorperms.premium");
        set("Colors.Premium.Color", "&9");
        set("Colors.Premium.Permission", "de.simonsator.partyandfriends.colorperms.admim");
        set("Colors.AddMoreColorRanksIfYouWant.Color", "&e");
        set("Colors.AddMoreColorRanksIfYouWant.Permission", "perm.xyz");
    }

    @Override
    public void reloadConfiguration() throws IOException {

    }
}
