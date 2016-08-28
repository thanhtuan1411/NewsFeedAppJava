package com.tuando.newsfeed.model;

import java.util.Date;

public class NewsFeed {

	private long id;
	
	private String newsFeedText;
	
	private Date publicationDate;
	
	public NewsFeed(){
		id=0;
	}
	
	public NewsFeed(long id, String newsFeedText, Date publicationDate){
		this.id = id;
		this.newsFeedText = newsFeedText;
		this.publicationDate = publicationDate;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNewsFeedText() {
		return newsFeedText;
	}

	public void setNewsFeedText(String newsFeedText) {
		this.newsFeedText = newsFeedText;
	}

	public Date getPublicationDate() {
		return publicationDate;
	}

	public void setPublicationDate(Date publicationDate) {
		this.publicationDate = publicationDate;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof NewsFeed))
			return false;
		NewsFeed other = (NewsFeed) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "NewsFeed [id=" + id + ", newsFeedText=" + newsFeedText + ", publicationDate=" + publicationDate + "]";
	}
	

	
}
