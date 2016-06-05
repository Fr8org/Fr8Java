package co.fr8.terminal.base;

/**
 * TODO: Implement
 */
abstract public class TerminalActivityFactory<T extends AbstractTerminalActivity> {

  abstract public T getTerminalActivity();
}
