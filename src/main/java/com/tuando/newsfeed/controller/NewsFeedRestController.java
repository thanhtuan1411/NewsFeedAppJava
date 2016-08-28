package com.tuando.newsfeed.controller;

import com.tuando.newsfeed.model.NewsFeed;
import com.tuando.newsfeed.service.NewsFeedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
public class NewsFeedRestController {
 
    @Autowired
    NewsFeedService newsFeedService;  //Service which will do all data retrieval/manipulation work
 
    
    //-------------------Retrieve All NewsFeeds--------------------------------------------------------
     
    @RequestMapping(value = "/newsfeed/", method = RequestMethod.GET)
    public ResponseEntity<List<NewsFeed>> listAllNewsFeed() {
        List<NewsFeed> newsFeeds = newsFeedService.findAllNewsFeeds();
        if(newsFeeds.isEmpty()){
            return new ResponseEntity<List<NewsFeed>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<List<NewsFeed>>(newsFeeds, HttpStatus.OK);
    }
 
 
    
    //-------------------Retrieve Single NewsFeed--------------------------------------------------------
     
    @RequestMapping(value = "/newsfeed/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<NewsFeed> getNewsFeed(@PathVariable("id") long id) {
        System.out.println("Fetching NewsFeed with id " + id);
        NewsFeed newsFeed = newsFeedService.findById(id);
        if (newsFeed == null) {
            System.out.println("NewsFeed with id " + id + " not found");
            return new ResponseEntity<NewsFeed>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<NewsFeed>(newsFeed, HttpStatus.OK);
    }
 
     
     
    //-------------------Create a NewsFeed--------------------------------------------------------
     
    @RequestMapping(value = "/newsfeed/", method = RequestMethod.POST)
    public ResponseEntity<Void> createNewsFeed(@RequestBody NewsFeed newsFeed, UriComponentsBuilder ucBuilder) {
        System.out.println("Creating NewsFeed " + newsFeed.getNewsFeedText());
 
        if (newsFeedService.isNewsFeedExist(newsFeed)) {
            System.out.println("A NewsFeed with name " + newsFeed.getNewsFeedText() + " already exist");
            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }
 
        newsFeedService.saveNewsFeed(newsFeed);
 
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/newsFeed/{id}").buildAndExpand(newsFeed.getId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }
 
    
     
    //------------------- Update a NewsFeed --------------------------------------------------------
     
    @RequestMapping(value = "/newsfeed/{id}", method = RequestMethod.PUT)
    public ResponseEntity<NewsFeed> updateNewsFeed(@PathVariable("id") long id, @RequestBody NewsFeed newsFeed) {
        System.out.println("Updating NewsFeed " + id);
         
        NewsFeed currentNewsFeed = newsFeedService.findById(id);
         
        if (currentNewsFeed ==null) {
            System.out.println("NewsFeed with id " + id + " not found");
            return new ResponseEntity<NewsFeed>(HttpStatus.NOT_FOUND);
        }
 
        currentNewsFeed.setNewsFeedText(newsFeed.getNewsFeedText());
        currentNewsFeed.setPublicationDate(newsFeed.getPublicationDate());
//        currentNewsFeed.setEmail(newsFeed.getEmail());
         
        newsFeedService.updateNewsFeed(currentNewsFeed);
        return new ResponseEntity<NewsFeed>(currentNewsFeed, HttpStatus.OK);
    }
 
    
    
    //------------------- Delete a NewsFeed --------------------------------------------------------
     
    @RequestMapping(value = "/newsfeed/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<NewsFeed> deleteNewsFeed(@PathVariable("id") long id) {
        System.out.println("Fetching & Deleting NewsFeed with id " + id);
 
        NewsFeed newsFeed = newsFeedService.findById(id);
        if (newsFeed == null) {
            System.out.println("Unable to delete. NewsFeed with id " + id + " not found");
            return new ResponseEntity<NewsFeed>(HttpStatus.NOT_FOUND);
        }
 
        newsFeedService.deleteNewsFeedById(id);
        return new ResponseEntity<NewsFeed>(HttpStatus.NO_CONTENT);
    }
 
     
    
    //------------------- Delete All NewsFeed --------------------------------------------------------
     
    @RequestMapping(value = "/newsfeed/", method = RequestMethod.DELETE)
    public ResponseEntity<NewsFeed> deleteAllNewsFeed() {
        System.out.println("Deleting All NewsFeed");
 
        newsFeedService.deleteAllNewsFeed();
        return new ResponseEntity<NewsFeed>(HttpStatus.NO_CONTENT);
    }
 
}