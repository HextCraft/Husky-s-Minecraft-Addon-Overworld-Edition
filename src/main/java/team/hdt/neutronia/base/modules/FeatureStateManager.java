package team.hdt.neutronia.base.modules;

public class FeatureStateManager {

    public Feature component;
    public boolean enabled, enabledByDefault;

    public FeatureStateManager(Feature component) {
        this.component = component;
    }

    public boolean isComponentEnabled() {
        return enabled;
    }

    public void setComponentEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public boolean isComponentEnabledByDefault() {
        return enabledByDefault;
    }

    public void setComponentEnabledByDefault(boolean enabledByDefault) {
        this.enabledByDefault = enabledByDefault;
    }

}
