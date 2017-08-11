package com.millapps.flickrfeed.model;

import android.databinding.BaseObservable;

import com.millapps.flickrfeed.BR;
import com.millapps.flickrfeed.model.entity.Media;
import com.millapps.flickrfeed.model.entity.PublicFeed;
import com.millapps.flickrfeed.model.entity.PublicFeedItem;
import com.millapps.flickrfeed.model.network.RestApi;
import com.millapps.flickrfeed.view.PublicFeedListAdapter;
import com.millapps.flickrfeed.viewmodel.PublicFeedListItemViewModel;
import com.millapps.flickrfeed.viewmodel.PublicFeedListViewModel;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.TestObserver;
import io.reactivex.schedulers.TestScheduler;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.asm.tree.InsnList.check;

public class PublicFeedDataModelTest {

    private static final String MOCK_MODIFIED_DATE = "mockdate";

    @Mock
    private PublicFeed mockFeed1;

    @Mock
    private PublicFeed mockFeed2;

    @Mock
    private IOException mockException;

    @Mock
    private RestApi mockRestApi;

    private TestScheduler testScheduler;

    private PublicFeedDataModel dataModel;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        testScheduler = new TestScheduler();

        dataModel = spy(new PublicFeedDataModel(mockRestApi));
    }

    @Test
    public void test_publicFeed() {

        when(mockRestApi.fetchPublicFeed(false)).thenReturn(Observable.just(mockFeed1));
        when(mockRestApi.fetchPublicFeed(true)).thenReturn(Observable.just(mockFeed2));

        TestObserver<PublicFeed> testObserver = dataModel.publicFeed().test();
        testObserver.awaitTerminalEvent();

        testObserver.assertNoErrors();
        testObserver.assertComplete();
        testObserver.assertResult(mockFeed1, mockFeed2);
    }

    @Test
    public void test_publicFeed_Error() {

        when(mockRestApi.fetchPublicFeed(false)).thenReturn(Observable.just(mockFeed1));
        when(mockRestApi.fetchPublicFeed(true)).thenReturn(Observable.error(mockException));

        TestObserver<PublicFeed> testObserver = dataModel.publicFeed().test();
        testObserver.awaitTerminalEvent();

        testObserver.assertNotComplete();
        testObserver.assertFailure(IOException.class, mockFeed1);
    }

}
