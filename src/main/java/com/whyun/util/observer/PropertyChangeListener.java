package com.whyun.util.observer;

import java.util.ArrayList;

public class PropertyChangeListener {
	private static final ArrayList<Changeable> PROPERTY_CONFIGS
		= new ArrayList<Changeable>();

	public static void addToListener(Changeable config) {
		PROPERTY_CONFIGS.add(config);
	}
	
	public static void removeFromListener(Changeable config) {
		PROPERTY_CONFIGS.remove(config);
	}

	public static void reloadAll() {
		if (PROPERTY_CONFIGS.size() > 0) {
			for (Changeable config : PROPERTY_CONFIGS) {
				config.reload();
			}
		}
	}
}
