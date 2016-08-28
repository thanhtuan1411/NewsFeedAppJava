package com.tuando.newsfeed.service;

import com.tuando.newsfeed.model.NewsFeed;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Service("newsFeedService")
public class NewsFeedServiceImpl implements NewsFeedService {

	private static final String DB_FILENAME = "newsfeeddb.xml";
	
	private static final AtomicLong counter = new AtomicLong();
	
	private static List<NewsFeed> newsFeeds;
	
	static{
		newsFeeds = readNewsFeed();
	}

	public List<NewsFeed> findAllNewsFeeds() {
		return newsFeeds;
	}
	
	public NewsFeed findById(long id) {
		for(NewsFeed newsFeed : newsFeeds){
			if(newsFeed.getId() == id){
				return newsFeed;
			}
		}
		return null;
	}
	
	public NewsFeed findByName(String name) {
		for(NewsFeed newsFeed : newsFeeds){
			if(newsFeed.getNewsFeedText().equalsIgnoreCase(name)){
				return newsFeed;
			}
		}
		return null;
	}
	
	public void saveNewsFeed(NewsFeed newsFeed) {
		newsFeed.setId(counter.incrementAndGet());
		newsFeeds.add(newsFeed);
		addNewsFeedtoXml(newsFeed);
	}

	public void updateNewsFeed(NewsFeed newsFeed) {
		int index = newsFeeds.indexOf(newsFeed);
		newsFeeds.set(index, newsFeed);
	}

	public void deleteNewsFeedById(long id) {
		
		for (Iterator<NewsFeed> iterator = newsFeeds.iterator(); iterator.hasNext(); ) {
		    NewsFeed newsFeed = iterator.next();
		    if (newsFeed.getId() == id) {
		        iterator.remove();
		    }
		}
	}

	public boolean isNewsFeedExist(NewsFeed newsFeed) {
		return findByName(newsFeed.getNewsFeedText())!=null;
	}
	
	public void deleteAllNewsFeed(){
		newsFeeds.clear();
	}

	private static void addNewsFeedtoXml(NewsFeed newsFeed) {
		try {

			DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
			Document doc = documentBuilder.parse(DB_FILENAME);
			Element rootElement = doc.getDocumentElement();
			Element element = doc.createElement("newsfeed");
			counter.incrementAndGet();
			element.setAttribute("id", counter.toString());
			element.setAttribute("newsFeedText", newsFeed.getNewsFeedText());
			element.setAttribute("publicationDate", newsFeed.getPublicationDate().toString());
			rootElement.appendChild(element);

			TransformerFactory transformerFactory = TransformerFactory.newInstance();

			Transformer transformer = transformerFactory.newTransformer();
			DOMSource domSource = new DOMSource(doc);
			StreamResult streamResult = new StreamResult(new File(DB_FILENAME));
			transformer.transform(domSource, streamResult);

		} catch (Exception ex) {
			String tt = "";
		}

	}

	private static List<NewsFeed> readNewsFeed() {
		List<NewsFeed> newsFeeds = new ArrayList<NewsFeed>();
		try {
			DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
			Document doc = documentBuilder.parse(DB_FILENAME);

			NodeList nList = doc.getElementsByTagName("newsfeed");

			for (int temp = 0; temp < nList.getLength(); temp++) {
				Node nNode = nList.item(temp);
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
					Element element = (Element) nNode;
					NewsFeed newsFeed = new NewsFeed();
					newsFeed.setId(Long.parseLong(element.getAttribute("id")));
					newsFeed.setNewsFeedText(element.getAttribute("newsFeedText"));
					SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
					Date publicationDate = formatter.parse(element.getAttribute("publicationDate"));
					newsFeed.setPublicationDate(publicationDate);
					newsFeeds.add(newsFeed);
					counter.incrementAndGet();
				}
			}
		} catch (Exception ex) {

		}
		return newsFeeds;
	}

	public static void main(String[] args) {
//		NewsFeed newsFeed = new NewsFeed(counter.incrementAndGet(), "test", new Date());
//		addNewsFeedtoXml(newsFeed);
		readNewsFeed();

	}

//	private static List<NewsFeed> populateDummyNewsFeed(){
//		List<NewsFeed> newsFeeds = new ArrayList<NewsFeed>();
//		newsFeeds.add(new NewsFeed(counter.incrementAndGet(),"Sam", new Date()));
//		newsFeeds.add(new NewsFeed(counter.incrementAndGet(),"Sam 2", new Date()));
//		return newsFeeds;
//	}

}
