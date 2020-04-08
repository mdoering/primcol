package org.gbif.primcol;

@FunctionalInterface
public interface BiIntConsumer {

  /**
   * Performs this operation on the given argument.
   *
   * @param t the first input argument
   * @param u the second input argument
   */
  void accept(int t, int u);

}
