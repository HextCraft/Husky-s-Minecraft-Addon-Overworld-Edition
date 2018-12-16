package team.hdt.neutronia_legacy.api;

@FunctionalInterface
public interface IValueReceiver<T> {
    public void setValue(T value);
}