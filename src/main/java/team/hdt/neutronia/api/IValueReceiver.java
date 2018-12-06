package team.hdt.neutronia.api;

@FunctionalInterface
public interface IValueReceiver<T> {
    public void setValue(T value);
}