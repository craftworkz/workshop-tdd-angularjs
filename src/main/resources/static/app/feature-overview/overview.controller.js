(function(angular) {
  'use strict';

  function OverviewController(Task, toastr) {
    var vm = this;
    vm.getTasks = getTasks;
    vm.add = add;
    vm.update = update;
    vm.remove = remove;
    vm.task = { completed: false };

    construct();

    ////////

    function getTasks() {
      return Task.query();
    }

    function add(task) {
      new Task(task).$save(function(savedTask) {
        toastr.success('The task was successfully saved');
        if (vm.tasks == null) {
          vm.tasks = [savedTask];
        } else {
          vm.tasks.push(savedTask);
        }
        vm.task.description = null;
      }, function(err) {
        toastr.error(err.data.description, 'Error');
      });
    }

    function update(task) {
      task.$update(function() {
        toastr.success('The task was successfully updated');
      }, function(err) {
        toastr.error(err.data.description, 'Error');
        task.completed = !task.completed;
      });
    }

    function remove(task) {
      task.$delete(function() {
        toastr.success('The task was successfully removed');
        if (vm.tasks != null) {
          var idx = vm.tasks.indexOf(task);
          if (idx >= 0) {
            vm.tasks.splice(idx, 1);
          }
        }
      }, function(err) {
        toastr.error(err.data.description, 'Error');
      });
    }

    function construct() {
      vm.tasks = getTasks();
    }
  }

  OverviewController.$inject = ['Task', 'toastr'];

  angular.module('taskApp.features.overview').controller('OverviewController', OverviewController);
}(angular));