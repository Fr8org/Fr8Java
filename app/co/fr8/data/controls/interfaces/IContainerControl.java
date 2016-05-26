package co.fr8.data.controls.interfaces;


import co.fr8.data.controls.AbstractControlDefinition;

/**
 * TODO: Implement
 */
public interface IContainerControl<T extends AbstractControlDefinition> {

  Iterable<T> enumerateChildren();
}
