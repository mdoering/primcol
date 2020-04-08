package org.gbif.primcol;

import com.carrotsearch.hppc.IntHashSet;
import com.carrotsearch.hppc.IntIntHashMap;
import com.carrotsearch.hppc.IntIntMap;
import com.koloboke.collect.map.hash.HashIntIntMap;
import com.koloboke.collect.map.hash.HashIntIntMaps;
import com.koloboke.collect.set.hash.HashIntSet;
import com.koloboke.collect.set.hash.HashIntSets;
import it.unimi.dsi.fastutil.ints.Int2IntMap;
import it.unimi.dsi.fastutil.ints.Int2IntOpenHashMap;
import it.unimi.dsi.fastutil.ints.IntOpenHashSet;
import it.unimi.dsi.fastutil.ints.IntSet;
import org.agrona.collections.Int2IntHashMap;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.IntConsumer;

public class Benchmarks {
  private final int testSize;

  public Benchmarks(int testSize) {
    this.testSize = testSize;
  }

  void report(Object obj) {
    System.out.println("--- " + obj.getClass().getName() + " ---");
    System.out.println("Shallow size: " + MemoryCounterAgent.sizeOf(obj));
    System.out.println("Deep size: " + MemoryCounterAgent.deepSizeOf(obj) + "\n");
  }

  void test(Object obj, IntConsumer add) {
    for (int i=0; i<testSize; i++) {
      add.accept(i);
    }
    report(obj);
  }

  void test(Object obj, BiIntConsumer add) {
    for (int i=0; i<testSize; i++) {
      add.accept(i, i);
    }
    report(obj);
  }

  void sets(){
    javaSet();
    fastSet();
    agronaSet();
    hppcSet();
    kolobokeSet();
  }

  void javaSet(){
    Set<Integer> set = new HashSet<>();
    test(set, set::add);
  }
  void fastSet(){
    IntSet set = new IntOpenHashSet();
    test(set, set::add);
  }
  void agronaSet(){
    org.agrona.collections.IntHashSet set = new org.agrona.collections.IntHashSet();
    test(set, set::add);
  }
  void hppcSet(){
    IntHashSet set = new IntHashSet();
    test(set, set::add);
  }

  void kolobokeSet(){
    HashIntSet set = HashIntSets.newMutableSet(testSize);
    test(set, set::add);
  }

  private void maps() {
    javaMap();
    fastMap();
    agronaMap();
    hppcMap();
    kolobokeMap();
  }

  private void kolobokeMap() {
    HashIntIntMap map = HashIntIntMaps.newMutableMap(testSize);
    test(map, map::put);
  }

  private void javaMap() {
    HashMap<Integer, Integer> map = new HashMap<>();
    test(map, map::put);
  }

  private void fastMap() {
    Int2IntOpenHashMap map = new Int2IntOpenHashMap();
    test(map, map::put);
  }

  private void agronaMap() {
    Int2IntHashMap map = new Int2IntHashMap(-1);
    test(map, map::put);
  }

  private void hppcMap() {
    IntIntMap map = new IntIntHashMap();
    test(map, map::put);
  }

  void all(){
    sets();
    maps();
  }


  public static void main(String [] args) {
    Benchmarks b = new Benchmarks(1000000);
    b.all();
  }
}