package interfaces;

import exceptions.NotFoundException;

public interface Container {
    void add(Object object);
    Object get(Object object) throws NotFoundException;

    void explore();
}