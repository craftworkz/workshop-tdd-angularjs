(function(angular) {
  'use strict';
  angular.module('taskApp.core', ['ngResource', 'ngAnimate', 'toastr']);
  angular.module('taskApp.features.overview', ['taskApp.core']);
  angular.module('taskApp', [
    'taskApp.features.overview'
  ]);
}(angular));