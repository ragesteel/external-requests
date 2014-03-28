package ru.gt2.external.requests;

import javax.persistence.EntityManager;

public interface RunInTransaction<T> {
    T run(EntityManager entityManager);
}
