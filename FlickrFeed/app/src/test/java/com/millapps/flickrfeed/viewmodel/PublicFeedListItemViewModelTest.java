package com.millapps.flickrfeed.viewmodel;

import com.millapps.flickrfeed.model.entity.Media;
import com.millapps.flickrfeed.model.entity.PublicFeedItem;

import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.assertEquals;

public class PublicFeedListItemViewModelTest {

    private static final String MEDIA_URL_M = "https:\\farm5.staticflickr.com_4379_36489106675_865642ecb0_m.jpg";
    private static final String IMAGE_URL = "https:\\farm5.staticflickr.com_4379_36489106675_865642ecb0_q.jpg";
    private static final String DATE_TAKEN = "2017-07-23T19:22:23-08:00";
    private static final String DATE_PUBLISHED = "2017-07-23T19:22:23-08:00";
    private static final String AUTHOR = "nobody@flickr.com";
    private PublicFeedListItemViewModel viewModel;
    private PublicFeedItem feedItem;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);

        feedItem = new PublicFeedItem();
        feedItem.setMedia(new Media(MEDIA_URL_M));
        feedItem.setDateTaken(DATE_TAKEN);
        feedItem.setPublished(DATE_PUBLISHED);
        feedItem.setAuthor(AUTHOR);

        viewModel = new PublicFeedListItemViewModel(feedItem);
    }

    @Test
    public void test_validateValues() {
        assertEquals(viewModel.getAuthor(), AUTHOR);
        assertEquals(viewModel.getDateTaken(), DATE_TAKEN);
        assertEquals(viewModel.getDatePublished(), DATE_PUBLISHED);
        assertEquals(viewModel.getImageUrl(), IMAGE_URL); //IMAGE_URL 'and not' MEDIA_URL_M
    }
}
