(function(angular) {
  'use strict';

  function Task($resource) {
    return $resource('api/tasks/:id', { id: '@id' }, {
      update: { method: 'PUT', isArray: false }
    });
  }

  angular.$inject = ['$resource'];

  angular.module('taskApp.core').factory('Task', Task);
}(angular));