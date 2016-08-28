'use strict';

angular.module('myApp').factory('NewsFeedService', ['$http', '$q', function($http, $q){

    var REST_SERVICE_URI = 'http://localhost:8080/NewsFeedApp/newsfeed/';

    var factory = {
        fetchAllNewsFeeds: fetchAllNewsFeeds,
        createNewsFeed: createNewsFeed,
        updateNewsFeed:updateNewsFeed,
        deleteNewsFeed:deleteNewsFeed
    };

    return factory;

    function fetchAllNewsFeeds() {
        var deferred = $q.defer();
        $http.get(REST_SERVICE_URI)
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while fetching NewsFeeds');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }

    function createNewsFeed(newsfeed) {
        if (newsfeed === null) {
            alert("Data is null");
        }
        var deferred = $q.defer();
        $http.post(REST_SERVICE_URI, newsfeed)
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while creating NewsFeed');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }


    function updateNewsFeed(newsFeed, id) {
        var deferred = $q.defer();
        $http.put(REST_SERVICE_URI+id, newsFeed)
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while updating NewsFeed');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }

    function deleteNewsFeed(id) {
        var deferred = $q.defer();
        $http.delete(REST_SERVICE_URI+id)
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while deleting NewsFeed');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }

}]);
