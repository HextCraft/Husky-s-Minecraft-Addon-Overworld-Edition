package team.hdt.neutronia.base.client.gui.listener;

@FunctionalInterface
public interface IValueChangedListener<T> extends IListenerBase {
    public void valueChanged(T value);
}