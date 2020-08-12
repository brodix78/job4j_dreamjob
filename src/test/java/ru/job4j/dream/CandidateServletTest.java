package ru.job4j.dream;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import ru.job4j.dream.model.Candidate;
import ru.job4j.dream.servlet.CandidateServlet;
import ru.job4j.dream.store.FakeStore;
import ru.job4j.dream.store.PsqlStore;
import ru.job4j.dream.store.Store;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RunWith(PowerMockRunner.class)
@PrepareForTest(PsqlStore.class)

public class CandidateServletTest {

    @Test
    public void whenAddingNewCandidateWhenUpdateStore() throws IOException {
        Candidate candidate = new Candidate(5, "Vova");
        candidate.setPhotoId(6);
        Store store = Mockito.spy(FakeStore.class);
        PowerMockito.mockStatic(PsqlStore.class);
        HttpServletRequest request = Mockito.spy(HttpServletRequest.class);
        HttpServletResponse response = Mockito.spy(HttpServletResponse.class);
        Mockito.when(PsqlStore.instOf()).thenReturn(store);
        Mockito.when(request.getParameter("name")).thenReturn("Vova");
        Mockito.when(request.getParameter("id")).thenReturn("5");
        Mockito.when(request.getParameter("photoId")).thenReturn("6");
        new CandidateServlet().doPost(request, response);
        Mockito.verify(store).saveCandidate(candidate);
    }

    @Test
    public void whenDeleteCandidateWhenDoRemove() throws IOException {
        Candidate candidate = new Candidate(5, "");
        candidate.setPhotoId(6);
        Store store = Mockito.spy(FakeStore.class);
        PowerMockito.mockStatic(PsqlStore.class);
        HttpServletRequest request = Mockito.spy(HttpServletRequest.class);
        HttpServletResponse response = Mockito.spy(HttpServletResponse.class);
        Mockito.when(PsqlStore.instOf()).thenReturn(store);
        Mockito.when(request.getParameter("action")).thenReturn("delete");
        Mockito.when(request.getParameter("id")).thenReturn("5");
        new CandidateServlet().doPost(request, response);
        Mockito.verify(store).removeCandidate(5);
    }
}
