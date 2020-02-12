package com.yyrz.SpeechCognition.common.util;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Objects;

public class GsonAdapter<T> extends TypeAdapter<T> {

    @Override
    public void write(JsonWriter out, T value) throws IOException {
        Field[] fileds=value.getClass().getDeclaredFields();
        Arrays.sort(fileds, new Comparator<Field>() {
            @Override
            public int compare(Field field, Field t1) {
                return Objects.requireNonNull(field.getAnnotation(BeanFieldAnnotation.class)).order()- Objects.requireNonNull(t1.getAnnotation(BeanFieldAnnotation.class)).order();
            }
        });
        out.beginObject();
        for(Field field:fileds){
            String fieldName=field.getName();
            out.name(field.getName());
            String getMethod="get"+fieldName.substring(0, 1).toUpperCase()+fieldName.substring(1);
            try {
                String temp=String.valueOf(value.getClass().getMethod(getMethod).invoke(value));
                out.value(temp);
            } catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
                e.printStackTrace();
            }
        }
        out.endObject();
    }

    @Override
    public T read(JsonReader in) {
        return null;
    }
}
