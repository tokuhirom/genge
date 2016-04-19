package me.geso.genge.storage;

import org.springframework.stereotype.Component;

import java.util.concurrent.ConcurrentHashMap;
import java.util.function.BiFunction;

@Component
public class InMemoryKeyValueStorage<V> implements KeyValueStorage<V> {
    private ConcurrentHashMap<String, V> storage;

    public InMemoryKeyValueStorage() {
        this.storage = new ConcurrentHashMap<>();
    }

    @Override
    public void save(Package pkg, String key, V i) {
        this.storage.put(pkg.getName() + "#" + key, i);
    }

    @Override
    public V load(Package pkg, String key) {
        return this.storage.get(pkg.getName() + "#" + key);
    }

    @Override
    public V compute(Package pkg, String key,
                     BiFunction<? super String, ? super V, ? extends V> remappingFunction) {
        return this.storage.compute(pkg.getName() + "#" + key,
                remappingFunction);
    }
}
