package team.hdt.neutronia_legacy.api;

@FunctionalInterface
public interface IValueProvider<T> {
    public T getValue();
}