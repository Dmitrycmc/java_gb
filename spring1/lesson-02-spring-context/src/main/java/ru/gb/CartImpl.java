package ru.gb;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
@Scope("prototype")
public class CartImpl implements Cart {
    private final HashMap<Integer, Integer> data;

    public CartImpl() {
        data = new HashMap<>();
    }

    @Override
    public void add(int id) {
        if (data.containsKey(id)) {
            data.put(id, data.get(id) + 1);
        } else {
            data.put(id, data.containsKey(id) ? data.get(id) + 1 : 1);
        }
    }

    @Override
    public void sub(int id) throws Exception {
        if (data.containsKey(id) && data.get(id) > 0) {
            data.put(id, data.get(id) - 1);
        } else {
            throw new Exception("Not enough");
        }
    }

    @Override
    public Map<Integer, Integer> getProducts() {
        return data;
    }
}
