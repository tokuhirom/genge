package me.geso.genge.storage;

import java.util.function.BiFunction;

public interface KeyValueStorage<V> {
    void save(Package pkg, String key, V i);

    V load(Package pkg, String key);

    V compute(Package pkg, String key,
              BiFunction<? super String, ? super V, ? extends V> remappingFunction);
}
