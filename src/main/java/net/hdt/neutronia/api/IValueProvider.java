package net.hdt.neutronia.api;

@FunctionalInterface
public interface IValueProvider<T> {
	public T getValue();
}