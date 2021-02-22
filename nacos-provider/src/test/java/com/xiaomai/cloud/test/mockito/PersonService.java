package com.xiaomai.cloud.test.mockito;

/**
 * @author Madison
 * @date 2021/1/27
 */
public class PersonService {
    private final PersonDao personDao;
    public PersonService(PersonDao personDao) {
        this.personDao = personDao;
    }
    public boolean update(int id, String name) {
        Person person = personDao.getPerson(id);
        if (person == null)
        { return false; }
        Person personUpdate = new Person(person.getId(), name);
        return personDao.update(personUpdate);
    }
}