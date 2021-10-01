package no.totenkode.memory.table;

import no.totenkode.memory.table.annotation.Column;
import no.totenkode.memory.table.annotation.Table;
import no.totenkode.memory.table.find.PredicateBuilder;
import no.totenkode.memory.table.repository.Repository;
import org.reflections.Reflections;
import org.reflections.scanners.FieldAnnotationsScanner;
import org.reflections.scanners.SubTypesScanner;
import org.reflections.scanners.TypeAnnotationsScanner;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class Main {

    public static void main(String[] args) throws Exception {
        final Repository repository = create();

        Random random = new Random();
        for (long i = 0; i < 100; i++) {
            repository.save(new TestObject(i, true, new Date(), new BigDecimal("100"), random.nextInt(100)));
        }


//        repository.save(new TestObject(1L, true, new Date(), new BigDecimal("100"), 111));
//        repository.save(new TestObject(2L, true, new Date(), new BigDecimal("200"), 111));
//        repository.save(new TestObject(3L, true, new Date(), new BigDecimal("100"), 111));
//        repository.save(new TestObject(4L, false, new Date(), new BigDecimal("200"), 222));
//        repository.save(new TestObject(5L, true, new Date(), new BigDecimal("100"), 222));
//        repository.save(new TestObject(6L, true, new Date(), new BigDecimal("200"), 222));

        final PredicateBuilder builder = new PredicateBuilder(TestObject.class);
        builder.equal("quantity", 94);

        final List<Long> result = repository.find(builder, 10);

        System.out.println("resutl");
        for (Long id : result) {
            System.out.println(id);
        }

    }

    private static Repository create() throws NoSuchMethodException {
        // TODO create a memory table
        // index tree

        final Repository repository = new Repository();

        final Reflections reflections = new Reflections("no.totenkode", new FieldAnnotationsScanner(), new TypeAnnotationsScanner(), new SubTypesScanner());

        final Set<Class<?>> types = reflections.getTypesAnnotatedWith(Table.class);

        for (Class<?> type : types) {
            final Table tableAnnotation = type.getAnnotation(Table.class);
            final TableDefinition tableDefinition = new TableDefinition(tableAnnotation.value(), type);
            for (Field field : reflections.getFieldsAnnotatedWith(Column.class)) {
                field.setAccessible(true);
                final Column annotation = field.getAnnotation(Column.class);

                // TODO base this on methods instead of field

                final Method method;
                if (field.getType().getName().equals("boolean")) {
                    method = type.getMethod(getterNameBoolean(field.getName()));
                } else {
                    method = type.getMethod(getterName(field.getName()));
                }

                tableDefinition.getColumnDefinitions().add(new ColumnDefinition(annotation.value(), annotation.indexed(), method));
            }
            tableDefinition.getColumnDefinitions();
            repository.construct(tableDefinition);
        }

        return repository;
    }

    private static String getterName(String fieldName) {
        return "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
    }

    private static String getterNameBoolean(String fieldName) {
        return "is" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
    }
}
