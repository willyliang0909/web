package com.willy.repository;

import com.willy.model.Staff;
import org.hibernate.query.NativeQuery;
import org.hibernate.transform.Transformers;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.*;
import javax.xml.transform.Transformer;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class StaffRepositoryCustomImpl implements StaffRepositoryCustom{

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Staff> findByEmails(Set<String> emails) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Staff> query = cb.createQuery(Staff.class);

        Root<Staff> staff = query.from(Staff.class);
        Path<String> emailPath = staff.get("email");

        List<Predicate> predicates = new ArrayList<>();
        for (String email : emails) {
            predicates.add(cb.like(emailPath, email));
        }

        query.select(staff)
                .where(cb.or(predicates.toArray(new Predicate[predicates.size()])));

        return entityManager.createQuery(query)
                .getResultList();
    }


}
