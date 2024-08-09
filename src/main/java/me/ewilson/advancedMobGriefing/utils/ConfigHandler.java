package me.ewilson.advancedMobGriefing.utils;


public final class ConfigHandler {

    public static String createConfigKey(String[] args) {

        StringBuilder key = new StringBuilder();

        for (int i = 0; i < args.length; i++) {
            key.append(args[i].toLowerCase());

            if (i < args.length - 1)
                key.append('.');
        }

        return key.toString();
    }

}
