package co.fr8.data.interfaces.manifests;

import java.util.Iterator;
import java.util.Spliterator;
import java.util.function.Consumer;

/**
 * TODO: DOCUMENT
 */
public class ActivityCallStack implements Iterable<StackFrame> {
  @Override
  public Iterator<StackFrame> iterator() {
    return null;
  }

  @Override
  public void forEach(Consumer<? super StackFrame> action) {

  }

  @Override
  public Spliterator<StackFrame> spliterator() {
    return null;
  }
}
