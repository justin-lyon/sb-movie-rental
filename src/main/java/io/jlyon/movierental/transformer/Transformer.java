package io.jlyon.movierental.transformer;

@FunctionalInterface
public interface Transformer<T, R> {
	R transform(T t);
}
