package me.Rokaz.AutoPicker.core.utils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class AnnotationProcessor {
    public static List<Method> getAnnotatedMethods(Class<?> clz, Class<? extends Annotation> annotation) {
        final List<Method> methods = new ArrayList<>();
        for (Method m: clz.getMethods()) {
            if (m.isAnnotationPresent(annotation)) methods.add(m);
        }
        return methods;
    }
}
