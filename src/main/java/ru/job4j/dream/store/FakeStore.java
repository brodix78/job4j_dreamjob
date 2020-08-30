package ru.job4j.dream.store;

import ru.job4j.dream.model.Candidate;
import ru.job4j.dream.model.Post;
import ru.job4j.dream.model.User;

import java.util.Collection;
import java.util.Map;

public class FakeStore implements Store {

    public User user;

    public FakeStore() {}

    public FakeStore(User user) {
        this.user = user;
    }

    public User authUser(String email, String pass) {
        return user;
    }

    @Override
    public int addPhoto(String file) {
        return 0;
    }

    @Override
    public Map<Integer, String> findAllPhotos() {
        return null;
    }

    @Override
    public String photoById(int id) {
        return null;
    }

    @Override
    public void removePhoto(int id) {

    }

    @Override
    public void removeCandidate(int id) {

    }

    @Override
    public User createUser(User user) {
        return null;
    }

    @Override
    public void removeUser(int id) {

    }

    @Override
    public Map<Integer, String> citiesList() {
        return null;
    }

    @Override
    public Collection<Post> findAllPosts() {
        return null;
    }

    @Override
    public Collection<Candidate> findAllCandidates() {
        return null;
    }

    @Override
    public void savePost(Post post) {

    }

    @Override
    public void saveCandidate(Candidate candidate) {

    }

    @Override
    public Post postById(int id) {
        return null;
    }

    @Override
    public Candidate candidateById(int id) {
        return null;
    }
}
