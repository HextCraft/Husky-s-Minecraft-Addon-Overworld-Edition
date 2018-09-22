package net.hdt.neutronia.groups.decoration.features;

import net.hdt.neutronia.base.groups.Component;

public class VariedBarrels extends Component {

	@Override
	public String getDescription() {
		return "Adds barrels made out of wood and that can be used for decoration";
	}

	@Override
	public boolean requiresMinecraftRestartToEnable() {
		return true;
	}

}
