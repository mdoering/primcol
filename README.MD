## Results

1.000.000 unique int primitives in a Set or a int2int Map using the following library versions:

Java 11
fastutil 8.3.1
HPPC 0.8.1
Agrona 1.4.1
Koloboke 1.0.0

```
--- java.util.HashSet ---
Shallow size: 16
Deep size: 56386656

--- it.unimi.dsi.fastutil.ints.IntOpenHashSet ---
Shallow size: 48
Deep size: 8388680

--- org.agrona.collections.IntHashSet ---
Shallow size: 40
Deep size: 8388664

--- com.carrotsearch.hppc.IntHashSet ---
Shallow size: 48
Deep size: 8388720

--- com.koloboke.collect.impl.hash.MutableLHashIntSet ---
Shallow size: 40
Deep size: 8388848

--- java.util.HashMap ---
Shallow size: 48
Deep size: 72384576

--- it.unimi.dsi.fastutil.ints.Int2IntOpenHashMap ---
Shallow size: 64
Deep size: 16777328

--- org.agrona.collections.Int2IntHashMap ---
Shallow size: 48
Deep size: 16777280

--- com.carrotsearch.hppc.IntIntHashMap ---
Shallow size: 56
Deep size: 16777360

--- com.koloboke.collect.impl.hash.MutableLHashParallelKVIntIntMap ---
Shallow size: 40
Deep size: 16777456
```