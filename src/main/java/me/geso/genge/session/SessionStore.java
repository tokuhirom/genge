package me.geso.genge.session;

import me.geso.genge.storage.KeyValueStorage;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

public class SessionStore {
    @Autowired
    private KeyValueStorage<Map<String, String>> storage;

    public void save(String mid, Map<String, String> values) {
        storage.save(getClass().getPackage(), mid, values);
    }

    public Map<String, String> load(String mid) {
        return storage.load(getClass().getPackage(), mid);
    }
}
