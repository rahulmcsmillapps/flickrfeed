package com.millapps.flickrfeed.viewmodel;

import android.databinding.BaseObservable;

import com.millapps.flickrfeed.BR;
import com.millapps.flickrfeed.model.PublicFeedDataModel;
import com.millapps.flickrfeed.model.entity.PublicFeed;
import com.millapps.flickrfeed.model.entity.PublicFeedItem;
import com.millapps.flickrfeed.view.PublicFeedListAdapter;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.verification.VerificationMode;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.TestScheduler;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class PublicFeedListViewModelTest {

    private static final String MOCK_MODIFIED_DATE = "mockdate";

    @Mock
    private PublicFeedDataModel mockDataModel;

    @Mock
    private PublicFeed mockFeed;

    @Mock
    private PublicFeedListAdapter mockListAdapter;

    @Mock
    private BaseObservable.OnPropertyChangedCallback mockOnPropertyChangedCallback;
    
    private TestScheduler testScheduler;

    private CompositeDisposable compositeDisposable;

    private PublicFeedListViewModel viewModel;
    private List<PublicFeedItem> feedItems;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        testScheduler = new TestScheduler();
        compositeDisposable = new CompositeDisposable();

        feedItems = new ArrayList<>();
        feedItems.add(new PublicFeedItem());
        feedItems.add(new PublicFeedItem());

        viewModel = spy(new PublicFeedListViewModel(mockListAdapter, mockDataModel, testScheduler, testScheduler, compositeDisposable));
        viewModel.addOnPropertyChangedCallback(mockOnPropertyChangedCallback);
    }

    @Test
    public void test_getAdapter() {
        assertEquals(viewModel.getAdapter(), mockListAdapter);
    }

    @Test
    public void test_loadPublicFeed() {
        assertEquals(viewModel.getAdapter(), mockListAdapter);

        when(mockDataModel.publicFeed()).thenReturn(Observable.just(mockFeed));
        when(mockFeed.getItems()).thenReturn(feedItems);
        when(mockFeed.getModified()).thenReturn(MOCK_MODIFIED_DATE);

        viewModel.loadPublicFeed();

        testScheduler.triggerActions();

        verify(mockDataModel, times(1)).publicFeed();
        verifyPropertyChanged(BR.loading, times(2));
        verifyPropertyChanged(BR.syncTime, times(1));

        assertEquals(viewModel.getLoading(), false);
        assertEquals(viewModel.getSyncTime(), MOCK_MODIFIED_DATE);
    }

    private final void verifyPropertyChanged(int propertyId, VerificationMode verificationMode) {
        verify(mockOnPropertyChangedCallback, verificationMode)
                .onPropertyChanged(any(android.databinding.Observable.class), eq(propertyId));
    }

}
