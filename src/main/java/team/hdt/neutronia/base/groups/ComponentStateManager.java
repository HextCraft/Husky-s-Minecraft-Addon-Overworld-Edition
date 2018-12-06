package team.hdt.neutronia.base.groups;

public class ComponentStateManager {

    public Component component;
    public boolean enabled, enabledByDefault;

    public ComponentStateManager(Component component) {
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
