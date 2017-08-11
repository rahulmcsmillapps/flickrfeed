# Flickr Feed Android application

Basic Implementation of the public Flickr Feed API allowing users to view the latest public feed. The app has been implemented using a reactive Model View ViewModel architecture (MVVM) and uses RxJava and DataBinding to achieve the same.

Currently, the app displays the basic feed list along with the images (using Glide as the underlying image download and caching library). It also caches the feed locally using the underlying OkHttp response cache so that next time the app is launched, a stale copy of the feed is dsplayed while the feed is retrieved from the Flickr Feed server.

# Future Enhancements
1. Use a reactive DB such as Realm to perform feed caching and using that to perform sort operations.
2. Periodic refresh of the feed
