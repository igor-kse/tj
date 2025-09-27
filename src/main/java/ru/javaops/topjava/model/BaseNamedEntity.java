package ru.javaops.topjava.model;

public class BaseNamedEntity extends BaseEntity {

    protected final String name;

    public BaseNamedEntity(Long id, String name) {
        super(id);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "NamedEntity{" +
                "name='" + name + '\'' +
                '}';
    }
}
