package MockData;

import sjohnsoncf.picfeed.PhotoFeedActivity;

/**
 * Created by steven on 10/9/17.
 */

public class MockData {
    public static String url1 = "https://images.unsplash.com/photo-1451187580459-43490279c0fa?dpr=1&auto=compress,format&fit=crop&w=1352&h=&q=80&cs=tinysrgb&crop=";
    public static String url2 = "https://images.unsplash.com/photo-1417577097439-425fb7dec05e?dpr=1&auto=compress,format&fit=crop&w=1489&h=&q=80&cs=tinysrgb&crop=";
    public static String url3 = "https://images.unsplash.com/photo-1500877015165-e1fb7f2db007?dpr=1&auto=compress,format&fit=crop&w=634&h=&q=80&cs=tinysrgb&crop=";
    public static String url4 = "https://images.unsplash.com/photo-1442917377311-e0c8b1476d42?dpr=1&auto=compress,format&fit=crop&w=1416&h=&q=80&cs=tinysrgb&crop=";

    public static void mockUrls(){
        PhotoFeedActivity.mFeedUrls.add(url1);
        PhotoFeedActivity.mFeedUrls.add(url2);
        PhotoFeedActivity.mFeedUrls.add(url3);
        PhotoFeedActivity.mFeedUrls.add(url4);
    }
}
