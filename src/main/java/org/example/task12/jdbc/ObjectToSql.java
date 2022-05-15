package org.example.task12.jdbc;

import org.example.task12.annotation.Mapped;
import org.example.task12.annotation.MappedClass;
import org.example.task12.customException.ClassNotAnnotatedException;
import java.lang.reflect.Field;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@MappedClass
public class ObjectToSql {
    private final DataBaseConnection dataBaseConnection;

    public ObjectToSql() {
        this.dataBaseConnection = new DataBaseConnection();
    }


    private void checkIfInsertable(Object object) {
        if (Objects.isNull(object)) {
            throw new ClassNotAnnotatedException("Can't insert inside the database a null object");
        }

        Class<?> clazz = object.getClass();
        if (!clazz.isAnnotationPresent(MappedClass.class)) {
            throw new ClassNotAnnotatedException("The class " + clazz.getSimpleName() + " is not annotated with MappedClass");
        }
    }

    public void insert(Object object) {
        try {
            checkIfInsertable(object);
            Field[] fields = object.getClass().getDeclaredFields();
            Class<?> clazz = object.getClass();
            List<String> fieldsValue = new ArrayList<>();
            List<String> fieldColumn = new ArrayList<>();
            for (Field fieldName : fields) {
                if (fieldName.isAnnotationPresent(Mapped.class)) {
                    fieldColumn.add(fieldName.getName());
                    Field field = clazz.getDeclaredField(fieldName.getName());
                    field.setAccessible(true);
                    String value = (String) field.get(object);
                    fieldsValue.add(value);
                }
            }
            String queryColumnName = String.join(", ", fieldColumn);
            String queryColumnValue = String.join("', '", fieldsValue);
            PreparedStatement preparedStatement = dataBaseConnection.connect().prepareStatement("INSERT INTO " + clazz.getSimpleName() + "(" + queryColumnName + ")" + " VALUES ('" + queryColumnValue + "')");
            preparedStatement.execute();
            System.out.println( queryColumnName + " inserted successfully inside the table");
        } catch (SQLException | NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }


    }


}
