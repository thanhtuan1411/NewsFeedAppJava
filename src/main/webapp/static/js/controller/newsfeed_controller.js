'use strict';

angular.module('myApp').controller('NewsFeedController', ['$scope', 'NewsFeedService', function($scope, NewsFeedService) {
    var self = this;
    self.newsFeed={id:null,newsFeedText:'',publicationDate:''};
    self.newsFeeds=[];

    self.submit = submit;
    self.edit = edit;
    self.remove = remove;
    self.reset = reset;


    fetchAllNewsFeeds();

    function fetchAllNewsFeeds(){
        NewsFeedService.fetchAllNewsFeeds()
            .then(
            function(d) {
                self.newsFeeds = d;
            },
            function(errResponse){
                console.error('Error while fetching NewsFeeds');
            }
        );
    }

    function createNewsFeed(newsFeed){
        NewsFeedService.createNewsFeed(newsFeed)
            .then(
            fetchAllNewsFeeds,
            function(errResponse){
                console.error('Error while creating NewsFeed');
            }
        );
    }

    function updateNewsFeed(newsFeed, id){
        NewsFeedService.updateNewsFeed(newsFeed, id)
            .then(
            fetchAllNewsFeeds,
            function(errResponse){
                console.error('Error while updating NewsFeed');
            }
        );
    }

    function deleteNewsFeed(id){
        NewsFeedService.deleteNewsFeed(id)
            .then(
            fetchAllNewsFeeds,
            function(errResponse){
                console.error('Error while deleting NewsFeed');
            }
        );
    }

    function submit() {
        if(self.newsFeed.id===null){
            console.log('Saving New NewsFeed', self.newsFeed);
            createNewsFeed(self.newsFeed);
        }else{
            updateNewsFeed(self.newsFeed, self.newsFeed.id);
            console.log('NewsFeed updated with id ', self.mewsFeed.id);
        }
        reset();
    }

    function edit(id){
        console.log('id to be edited', id);
        for(var i = 0; i < self.newsFeeds.length; i++){
            if(self.newsFeeds[i].id === id) {
                self.newsFeed = angular.copy(self.NewsFeeds[i]);
                break;
            }
        }
    }

    function remove(id){
        console.log('id to be deleted', id);
        if(self.newsFeed.id === id) {//clean form if the NewsFeed to be deleted is shown there.
            reset();
        }
        deleteNewsFeed(id);
    }


    function reset(){
        self.newsFeed={id:null,newsFeedText:'',publicationDate:''};
        $scope.myForm.$setPristine(); //reset Form
    }

}]);
