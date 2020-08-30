package ru.job4j.dream.store;

import ru.job4j.dream.model.Candidate;
import ru.job4j.dream.model.Post;
import ru.job4j.dream.model.User;

import java.util.Collection;
import java.util.Map;

public interface Store {
    Collection<Post> findAllPosts();

    Collection<Candidate> findAllCandidates();

    void savePost(Post post);

    void saveCandidate(Candidate candidate);

    Post postById(int id);

    Candidate candidateById(int id);

    User authUser(String email, String pass);

    int addPhoto(String file);

    Map<Integer, String> findAllPhotos();

    String photoById(int id);

    void removePhoto(int id);

    void removeCandidate(int id);

    User createUser(User user);

    void removeUser(int id);

    Map<Integer, String> citiesList();
}
