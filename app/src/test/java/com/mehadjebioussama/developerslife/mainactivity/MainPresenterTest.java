package com.mehadjebioussama.developerslife.mainactivity;

import com.mehadjebioussama.developerslife.db.GifDbModel;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class MainPresenterTest {
    @Mock
    private MainContract$View$$State view;

    @Mock
    private MainRepository repository;

    private MainPresenter presenter;

    @Captor
    ArgumentCaptor<MainContract.OnResponseCallback> argumentCaptor;

    @Before
    public void setUp() throws Exception {
        presenter = new MainPresenter(repository);
        presenter.setViewState(view);
    }

    @Test
    public void onFirstViewAttach() {
        presenter.onFirstViewAttach();
        verify(repository, times(1)).loadGif(argumentCaptor.capture(), eq(0));
        argumentCaptor.getValue().showData(new GifDbModel("hello", "http://static.devli.ru/public/images/gifs/201704/099595df-ad11-4a7a-83bd-b65de1cc044a.gif","latest"));
        ArgumentCaptor<GifDbModel> entityArgumentCaptor = ArgumentCaptor.forClass(GifDbModel.class);
        verify(view).showGif(entityArgumentCaptor.capture());
    }

    @Test
    public void onNextClick() {
        presenter.onNextClick(0);
        verify(repository, times(1)).onNextClick(argumentCaptor.capture(), eq(0));
        argumentCaptor.getValue().showData(new GifDbModel("hello", "http://static.devli.ru/public/images/gifs/201704/099595df-ad11-4a7a-83bd-b65de1cc044a.gif","latest"));
        ArgumentCaptor<GifDbModel> entityArgumentCaptor = ArgumentCaptor.forClass(GifDbModel.class);
        verify(view).showGif(entityArgumentCaptor.capture());
    }

    @Test
    public void onPreviousClick() {
        presenter.onPreviousClick(0);
        verify(repository, times(1)).onPreviousClick(argumentCaptor.capture(), eq(0));
        argumentCaptor.getValue().showData(new GifDbModel("hello", "http://static.devli.ru/public/images/gifs/201704/099595df-ad11-4a7a-83bd-b65de1cc044a.gif","latest"));
        ArgumentCaptor<GifDbModel> entityArgumentCaptor = ArgumentCaptor.forClass(GifDbModel.class);
        verify(view).showGif(entityArgumentCaptor.capture());
    }

    @Test
    public void tryAgain() {
        presenter.tryAgain(0);
        verify(repository, times(1)).loadGif(argumentCaptor.capture(), eq(0));
        argumentCaptor.getValue().showData(new GifDbModel("hello", "http://static.devli.ru/public/images/gifs/201704/099595df-ad11-4a7a-83bd-b65de1cc044a.gif","latest"));
        ArgumentCaptor<GifDbModel> entityArgumentCaptor = ArgumentCaptor.forClass(GifDbModel.class);
        verify(view).showGif(entityArgumentCaptor.capture());
    }
}