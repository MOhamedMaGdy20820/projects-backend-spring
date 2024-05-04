package com.global.book.repository;

import java.util.ArrayList;
import java.util.List;

import com.global.book.entity.AutherSearch;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.global.book.entity.Auther;
import com.global.book.entity.Book;

public class AutherSpec implements Specification<Auther> {

//    private String autherName;
//
//    public AutherSpec(String autherName) {
//        this.autherName = autherName;
//    }
//
//    @Override
//    public Predicate toPredicate(Root<Auther> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
//
//        List<Predicate> predicates = new ArrayList<>();
//
//        // name
//        if (autherName != null || !autherName.isEmpty()) {
//            predicates.add(cb.like(root.get("name"), autherName));
//        }
//        return null;
//    }

    private AutherSearch search;

    public AutherSpec(AutherSearch search) {
        super();
        this.search = search;
    }

    @Override
    public Predicate toPredicate(Root<Auther> root, CriteriaQuery<?> query, CriteriaBuilder cb) {

        List<Predicate> predicates = new ArrayList<>();

        Join<Auther, Book> bookJoin = root.join("books", JoinType.LEFT);

        // auther name
        if (search.getName() != null && !search.getName().isEmpty()) {
            predicates.add(cb.like(root.get("name"), "%"+search.getName() +"%"));
        }

        // email
        if (search.getEmail() != null && !search.getEmail().isEmpty()) {

            predicates.add(cb.like(root.get("email"), "%"+search.getEmail() +"%"));
        }

        // email
        if (search.getIpAddress() != null && !search.getIpAddress().isEmpty()) {

            predicates.add(cb.like(root.get("ipAddress"), "%"+ search.getIpAddress()+"%"  )); // "%" it contains
        }

        if (search.getBookName()!=null && !search.getBookName().isEmpty()) {
            predicates.add(cb.like(bookJoin.get("name"),  search.getBookName() ));
        }

        if (search.getPrice()!=null ) {
            predicates.add(cb.greaterThanOrEqualTo(bookJoin.get("price"), search.getPrice()));
        }

        query.orderBy(cb.desc(root.get("id")));

        return cb.and(predicates.toArray(new Predicate[0]));

    }

}