package com.tuando.newsfeed.service;

import com.tuando.newsfeed.model.NewsFeed;

import java.util.List;


public interface NewsFeedService {
	
	NewsFeed findById(long id);
	
	NewsFeed findByName(String name);
	
	void saveNewsFeed(NewsFeed newsFeed);
	
	void updateNewsFeed(NewsFeed newsFeed);
	
	void deleteNewsFeedById(long id);

	List<NewsFeed> findAllNewsFeeds();
	
	void deleteAllNewsFeed();
	
	public boolean isNewsFeedExist(NewsFeed newsFeed);
	
}
