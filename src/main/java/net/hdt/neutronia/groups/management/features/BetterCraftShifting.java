package net.hdt.neutronia.groups.management.features;

import net.hdt.neutronia.base.groups.Feature;
import net.hdt.neutronia.base.groups.GroupLoader;

public class BetterCraftShifting extends Feature {

	public static int getInventoryBoundary(int curr) {
		if(!GroupLoader.isFeatureEnabled(BetterCraftShifting.class))
			return curr;
		
		return curr == 37 ? 0 : 10;
	}
	
}
