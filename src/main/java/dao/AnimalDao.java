package dao;

import dao.generic.AbstractJpaDao;
import domain.animals.Animal;

public class AnimalDao extends AbstractJpaDao {
    public AnimalDao() {
        super();
        setClazz(Animal.class);
    }
}
