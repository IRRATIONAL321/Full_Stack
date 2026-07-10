package com.resume.portal.repository.impl;

import com.resume.portal.repository.UserRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

public class UserRepositoryImpl implements UserRepository {
    private static final ConcurrentHashMap<Long, User> database = new ConcurrentHashMap<>();
    private static final AtomicLong idCounter = new AtomicLong(1);

    @Override
    public User save(User user) {
        if (user.getId() == null) {
            user.setId(idCounter.getAndIncrement());
        }
        database.put(user.getId(), user);
        return user;
    }

    @Override
    public Optional<User> findById(Long id) {
        return Optional.ofNullable(database.get(id));
    }

    @Override
    public List<User> findAll() {
        return new ArrayList<>(database.values());
    }

    @Override
    public User update(User user) {
        if (user.getId() != null && database.containsKey(user.getId())) {
            database.put(user.getId(), user);
            return user;
        }
        return null;
    }

    @Override
    public void delete(Long id) {
        database.remove(id);
    }
}
