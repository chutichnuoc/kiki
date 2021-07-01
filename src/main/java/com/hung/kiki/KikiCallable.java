package com.hung.kiki;

import java.util.List;

interface KikiCallable {

    int arity();

    Object call(Interpreter interpreter, List<Object> arguments);
}
