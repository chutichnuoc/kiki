package com.hung.kiki;

import java.util.List;
import java.util.Map;

public class KikiClass implements KikiCallable {

    final String name;
    private final Map<String, KikiFunction> methods;

    KikiClass(String name, Map<String, KikiFunction> methods) {
        this.name = name;
        this.methods = methods;
    }

    KikiFunction findMethod(String name) {
        if (methods.containsKey(name)) {
            return methods.get(name);
        }

        return null;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public Object call(Interpreter interpreter, List<Object> arguments) {
        KikiInstance instance = new KikiInstance(this);
        KikiFunction initializer = findMethod("init");
        if (initializer != null) {
            initializer.bind(instance).call(interpreter, arguments);
        }

        return instance;
    }

    @Override
    public int arity() {
        KikiFunction initializer = findMethod("init");
        if (initializer == null) {
            return 0;
        }
        return initializer.arity();
    }
}
