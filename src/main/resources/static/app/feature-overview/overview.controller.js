(function(angular) {
  'use strict';

  function OverviewController(Task) {
    var vm = this;
    vm.tasks = getTasks();

    ////////

    function getTasks() {
      return Task.query();
    }
  }

  angular.module('taskApp.features.overview').controller('OverviewController', OverviewController);
}(angular));