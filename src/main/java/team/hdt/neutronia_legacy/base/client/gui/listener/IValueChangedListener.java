package team.hdt.neutronia_legacy.base.client.gui.listener;

@FunctionalInterface
public interface IValueChangedListener<T> extends IListenerBase {
    public void valueChanged(T value);
}