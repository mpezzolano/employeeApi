package com.repository.emp;

import com.model.emp.Gender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Repository
public class GenderRepositoryImpl implements GenderRepository {

    private final EntityManager entityManager;

    @Autowired
    public GenderRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Optional<Gender> findByName(String name) {
        TypedQuery<Gender> query = entityManager.createQuery("SELECT g FROM Gender g WHERE g.name = :name", Gender.class);
        query.setParameter("name", name);
        try {
            return Optional.ofNullable(query.getSingleResult());
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }

    @Override
    public void flush() {

    }

    @Override
    public <S extends Gender> S saveAndFlush(S entity) {
        return null;
    }

    @Override
    public <S extends Gender> List<S> saveAllAndFlush(Iterable<S> entities) {
        return null;
    }

    @Override
    public void deleteAllInBatch(Iterable<Gender> entities) {

    }

    @Override
    public void deleteAllByIdInBatch(Iterable<Long> longs) {

    }

    @Override
    public void deleteAllInBatch() {

    }

    @Override
    public Gender getOne(Long aLong) {
        return null;
    }

    @Override
    public Gender getById(Long aLong) {
        return null;
    }

    @Override
    public Gender getReferenceById(Long aLong) {
        return null;
    }

    @Override
    public <S extends Gender> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends Gender> List<S> findAll(Example<S> example) {
        return null;
    }

    @Override
    public <S extends Gender> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    @Override
    public <S extends Gender> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends Gender> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends Gender> boolean exists(Example<S> example) {
        return false;
    }

    @Override
    public <S extends Gender, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return null;
    }

    @Override
    public <S extends Gender> S save(S entity) {
        return null;
    }

    @Override
    public <S extends Gender> List<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<Gender> findById(Long aLong) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public List<Gender> findAll() {
        return null;
    }

    @Override
    public List<Gender> findAllById(Iterable<Long> longs) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(Long aLong) {

    }

    @Override
    public void delete(Gender entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {

    }

    @Override
    public void deleteAll(Iterable<? extends Gender> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public List<Gender> findAll(Sort sort) {
        return null;
    }

    @Override
    public Page<Gender> findAll(Pageable pageable) {
        return null;
    }



}