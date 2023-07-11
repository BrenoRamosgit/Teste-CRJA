package com.example.demo.repository;

import com.example.demo.model.Person;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.nonNull;

@Repository
public class PersonFilterRepositoryImpl implements PersonFilterRepository{

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Page<Person> findAll(Pageable pageable, LocalDate startTime, LocalDate finishTime, String name) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Person> criteria = builder.createQuery(Person.class);
        Root<Person> root = criteria.from(Person.class);

        List<Predicate> predicates = new ArrayList<>();
        if (!StringUtils.isBlank(name)) {
            builder.like(builder.lower(root.get("nome").as(String.class)),
                    builder.lower(builder.literal("%" + name + "%")));
        }
        if(nonNull(startTime) && nonNull(finishTime)){
            Predicate date =  builder.between(root.get("date"), startTime, finishTime);
            predicates.add(date);
        }
        criteria.where(predicates.toArray(new Predicate[0]));


        TypedQuery<Person> query = entityManager.createQuery(criteria)
                .setFirstResult((pageable.getPageNumber()) * pageable.getPageSize());
        query.setMaxResults(pageable.getPageSize());


        Long total = getTotalCount(builder, predicates.toArray(new Predicate[0]));
        return new PageImpl<>(query.getResultList(), pageable, total);
    }

    private Long getTotalCount(CriteriaBuilder criteriaBuilder, Predicate[] predicateArray) {
        CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);
        Root<Person> root = criteriaQuery.from(Person.class);

        criteriaQuery.select(criteriaBuilder.count(root));
        criteriaQuery.where(predicateArray);

        return entityManager.createQuery(criteriaQuery).getSingleResult();
    }
}
