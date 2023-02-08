package com.willy.repository;

import com.willy.model.Staff;
import org.hibernate.query.NativeQuery;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;
import java.util.Map;

@Repository
public class WillyTest {

    @PersistenceContext
    private EntityManager entityManager;

    public List<Map<String, Object>> findOther() {
        Query q = entityManager.createNativeQuery("select s.* from staffinfo s join memberaccount2019 m");
        q.unwrap(NativeQuery.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        return q.getResultList();
    }

    public List<Staff> findOther2() {
        Query q = entityManager.createNativeQuery("select * from staffinfo s"
                , Staff.class);
        return q.getResultList();
    }
}
