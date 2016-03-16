(function() {
  'use strict';

  describe('OverviewController', function() {
    var vm, TaskStub, toastrStub;
    beforeEach(module('taskApp'));
    beforeEach(inject(function($controller) {
      // Task
      TaskStub = sinon.stub();
      TaskStub.prototype.$save = sinon.stub();
      TaskStub.query = sinon.stub();

      // Toastr
      toastrStub = sinon.stub({
        success: function() {},
        error: function() {}
      });

      vm = $controller('OverviewController', {
        'Task': TaskStub,
        'toastr': toastrStub
      });
    }));

    describe('construct()', function() {
      it('Queries the task API', function() {
        expect(TaskStub.query).toHaveBeenCalledOnce();
      });
    });

    describe('getTasks()', function() {
      it('Queryies the task API', function() {
        TaskStub.query.returns([{ id: 1, description: 'Foo', completed: true }]);
        expect(vm.getTasks()).toContain({ id: 1, description: 'Foo', completed: true });
      });
    });

    describe('add()', function() {
      it('Creates a new task', function() {
        vm.add({ description: 'Foo', completed: false });
        expect(TaskStub).toHaveBeenCalledWith({ description: 'Foo', completed: false });
      });

      it('Saves the created task', function() {
        vm.add({ description: 'Foo', completed: false });
        expect(TaskStub.prototype.$save).toHaveBeenCalledOnce();
      });

      it('Shows a message if the task was successfully saved', function() {
        vm.add({ description: 'Foo', completed: false });
        TaskStub.prototype.$save.callArgWith(0, { id: 2, description: 'Foo', completed: false });
        expect(toastrStub.success).toHaveBeenCalledOnce();
      });

      it('Adds the task to the list if it was successfully saved', function() {
        vm.add({ description: 'Foo', completed: false });
        TaskStub.prototype.$save.callArgWith(0, { id: 2, description: 'Foo', completed: false });
        expect(vm.tasks).toContain({ id: 2, description: 'Foo', completed: false });
      });

      it('Resets the form field if the task was successfully saved', function() {
        vm.task.description = 'Foo';
        vm.add({ description: 'Foo', completed: false });
        TaskStub.prototype.$save.callArgWith(0, { id: 2, description: 'Foo', completed: false });
        expect(vm.task.description).toBeNull();
      });

      it('Shows an error message if the task could not be saved', function() {
        vm.add({ description: 'Foo', completed: false });
        TaskStub.prototype.$save.callArgWith(1, { data: { description: 'Errormessage' }});
        expect(toastrStub.error).toHaveBeenCalledWith('Errormessage', 'Error');
      });
    });

    describe('update()', function() {
      it('Updates the given task', function() {
        var task = { id: 3, description: 'Foo', completed: true, $update: sinon.stub() };
        vm.update(task);
        expect(task.$update).toHaveBeenCalledOnce();
      });

      it('Shows a message if the task was successfully updated', function() {
        var task = { id: 3, description: 'Foo', completed: true, $update: sinon.stub() };
        vm.update(task);
        task.$update.callArgWith(0, { id: 3, description: 'Foo', completed: true });
        expect(toastrStub.success).toHaveBeenCalledOnce();
      });

      it('Shows an error message if the task could not be updated', function() {
        var task = { id: 3, description: 'Foo', completed: true, $update: sinon.stub() };
        vm.update(task);
        task.$update.callArgWith(1, { data: { description: 'Errormessage' }});
        expect(toastrStub.error).toHaveBeenCalledWith('Errormessage', 'Error');
      });

      it('Inverts the completed state if the task could not be updated', function() {
        var task = { id: 3, description: 'Foo', completed: true, $update: sinon.stub() };
        vm.update(task);
        task.$update.callArgWith(1, { data: { description: 'Errormessage' }});
        expect(task.completed).toBeFalsy();
      });
    });

    describe('remove()', function() {
      it('Deletes the given task', function() {
        var task = { id: 1, description: 'First', completed: false, $delete: sinon.stub() };
        vm.remove(task);
        expect(task.$delete).toHaveBeenCalledOnce();
      });

      it('Shows a message if the task was successfully deleted', function() {
        var task = { id: 1, description: 'First', completed: false, $delete: sinon.stub() };
        vm.remove(task);
        task.$delete.callArg(0);
        expect(toastrStub.success).toHaveBeenCalledOnce();
      });

      it('Removes the task from the list', function() {
        var task = { id: 1, description: 'First', completed: false, $delete: sinon.stub() };
        vm.tasks = [task, { id: 2, description: 'Test', completed: false, $delete: function() {} }];
        vm.remove(task);
        task.$delete.callArg(0);
        expect(vm.tasks).not.toContain(task);
      });

      it('Shows an error message if the task could not be deleted', function() {
        var task = { id: 1, description: 'First', completed: false, $delete: sinon.stub() };
        vm.remove(task);
        task.$delete.callArgWith(1, { data: { description: 'Errormessage' }});
        expect(toastrStub.error).toHaveBeenCalledWith('Errormessage', 'Error');
      });
    });
  });
}());