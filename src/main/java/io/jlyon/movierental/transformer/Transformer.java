package io.jlyon.movierental.transformer;

@FunctionalInterface
public interface Transformer<InputType, OutputType> {
	OutputType transform(InputType inputType);
}
