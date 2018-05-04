package com.panda.ReflectDemo.xicheng;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * 〈一句话功能简述〉<br>
 *
 * @author 18048474
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public class ReflectUtil {

    public static List fieldToList(Class c, String fix, Object instance) throws IllegalAccessException {
        List<String> result = new ArrayList<>();
        Field[] declaredFields = c.getDeclaredFields();
        for (Field declaredField : declaredFields) {
            if (Collection.class.isAssignableFrom(declaredField.getType())) {
                Type genericType = declaredField.getGenericType();
                ParameterizedType genericType1 = (ParameterizedType) genericType;
                Type[] actualTypeArguments = genericType1.getActualTypeArguments();
                Collection collectionValue = (Collection) getObjectValue(instance, declaredField);
                Object[] objects = collectionValue.toArray();
                for (int i = 0; i < actualTypeArguments.length; i++) {
                    Class aClass = (Class) actualTypeArguments[i];
                    if (Collection.class.isAssignableFrom(aClass) || isObejct(aClass)){
                        result.addAll(fieldToList(aClass, fix + "." + declaredField.getName(), objects[i]));
                    } else {
                        declaredField.setAccessible(true);
                        String name = declaredField.getName();
                        result.add(fix + "." + name + ":" + getObjectValue(instance, declaredField));
                    }
                }
            } else if (isObejct(declaredField)) {
                result.addAll(fieldToList(declaredField.getType(), fix + "." + declaredField.getName(), getObjectValue(instance, declaredField)));
            } else {
                declaredField.setAccessible(true);
                String name = declaredField.getName();
                result.add(fix + "." + name + ":" + getObjectValue(instance, declaredField));
            }
        }
        return result;
    }

    private static Boolean isObejct(Field declaredField) {
        Class<?> declaringClass = declaredField.getDeclaringClass();
        if (declaredField.getType().getTypeName().equalsIgnoreCase("java.lang.String")) {
            return false;
        }
        if (!declaredField.getType().isInterface()) {
            return true;
        }
        if (!declaredField.getType().isPrimitive()) {
            return true;
        }
        return false;
    }
    private static Boolean isObejct(Class type) {
        if (type.getTypeName().equalsIgnoreCase("java.lang.String")) {
            return false;
        }
        if (!type.isInterface()) {
            return true;
        }
        if (!type.isPrimitive()) {
            return true;
        }
        return false;
    }


    public static Object getObjectValue(Object obj, Field name)
            throws SecurityException,IllegalArgumentException, IllegalAccessException {

        // Field f = getSpecifiedField(obj.getClass(), name.getName());
        if (name == null) {
            System.out.println("[ReflectHWUtils.getObjectValue]"
                    + obj.getClass().getName() + " does not has field " + name);
            return null;
        }
        name.setAccessible(true);
        return name.get(obj);
    }

}
