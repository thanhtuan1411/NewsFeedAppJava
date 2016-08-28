<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
  <head>  
    <title>NewsFeed App</title>

     <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
     <link href="<c:url value='/static/css/app.css' />" rel="stylesheet">
  </head>
  <body ng-app="myApp" class="ng-cloak">
      <div class="generic-container" ng-controller="NewsFeedController as ctrl">
          <div class="panel panel-default">
                <!-- Default panel contents -->
              <div class="panel-heading"><span class="lead">List of Newsfeed </span></div>
              <div class="tablecontainer">
                  <table class="table table-hover">
                      <thead>
                          <tr>
                              <th>ID.</th>
                              <th>NewsFeed Text</th>
                              <th>Publication Date</th>
                              <th width="20%"></th>
                          </tr>
                      </thead>
                      <tbody>
                          <tr ng-repeat="u in ctrl.newsFeeds | orderBy:'publicationDate':true">
                              <td><span ng-bind="u.id"></span></td>
                              <td><span ng-bind="u.newsFeedText"></span></td>
                              <td><span ng-bind="u.publicationDate | date:'MM/dd/yyyy'"></span></td>
                          </tr>
                      </tbody>
                  </table>
              </div>
          </div>

          <div class="panel panel-default">
              <div class="panel-heading"><span class="lead">Add Newsfeed</span></div>
              <div class="formcontainer">
                  <form ng-submit="ctrl.submit()" name="myForm" class="form-horizontal">
                      <input type="hidden" ng-model="ctrl.newsFeed.id" />
                      <div class="row">
                          <div class="form-group col-md-12">
                              <label class="col-md-2 control-lable">Newsfeed Text</label>
                              <div class="col-md-7">
                                  <input type="text" ng-model="ctrl.newsFeed.newsFeedText"/>
                              </div>
                          </div>
                      </div>

                      <div class="row">
                          <div class="form-group col-md-12">
                              <label class="col-md-2 control-lable">Publication Date</label>
                              <div class="col-md-7">
                                  <input type="date" ng-model="ctrl.newsFeed.publicationDate"/>
                              </div>
                          </div>
                      </div>

                      <div class="row">
                          <div class="form-actions floatRight">
                              <input type="submit"  value="Add" class="btn btn-primary btn-sm">
                          </div>
                      </div>
                  </form>
              </div>
          </div>
      </div>
      
      <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.5.8/angular.js"></script>
      <script src="<c:url value='/static/js/app.js' />"></script>
      <script src="<c:url value='/static/js/service/newsfeed_service.js' />"></script>
      <script src="<c:url value='/static/js/controller/newsfeed_controller.js' />"></script>
  </body>
</html>