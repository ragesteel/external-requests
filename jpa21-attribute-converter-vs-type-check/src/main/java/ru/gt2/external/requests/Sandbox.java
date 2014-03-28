package ru.gt2.external.requests;

import ru.gt2.external.requests.entity.Person;
import ru.gt2.external.requests.type.CustomConverted;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class Sandbox {

    private static EntityManagerFactory entityManagerFactory;

    public static void main(String[] args) {
        entityManagerFactory = Persistence.createEntityManagerFactory("external-requests");

        final Person person = run(new RunInTransaction<Person>() {
            @Override
            public Person run(EntityManager entityManager) {
                Person person = new Person();
                person.setCustomConverted(new CustomConverted(2));
                entityManager.persist(person);
                return person;
            }
        });

        run(new RunInTransaction<Void>() {
            @Override
            public Void run(EntityManager entityManager) {
                Integer savedId = person.getId();
                System.out.println("Saved persons with id = " + savedId);

                Person localPerson = entityManager.find(Person.class, savedId);
                System.out.println("Found person, value = " + person.getCustomConverted().value);
                return null;
            }
        });

        entityManagerFactory.close();
    }

    private static <T> T run(RunInTransaction<T> runInTransaction) {
        T result = null;
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        try {
            result = runInTransaction.run(entityManager);
            transaction.commit();
        } catch (Throwable e) {
            transaction.rollback();
        }
        entityManager.close();
        return result;
    }
}
