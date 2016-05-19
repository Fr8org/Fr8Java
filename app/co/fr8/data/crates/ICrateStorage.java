package co.fr8.data.crates;

/**
 * TODO: Implement
 */
abstract public class ICrateStorage implements Iterable<Crate> {
  abstract public Crate getFirstCrateOrDefault();
}
