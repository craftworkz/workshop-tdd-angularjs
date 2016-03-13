(function(module) {
  'use strict';

  module.exports = function(config) {
    config.set({
      frameworks: ['jasmine'],
      files: [
        'node_modules/angular/angular.js',
        'node_modules/angular-resource/angular-resource.js',
        'node_modules/angular-mocks/angular-mocks.js',
        'node_modules/sinon/lib/sinon.js',
        'node_modules/jasmine-sinon/lib/jasmine-sinon.js',
        'app/application.module.js',
        'app/**/*.js'
      ],
      reporters: ['progress'],
      autoWatch: false,
      browsers: ['PhantomJS'],
      singleRun: true,
      plugins: ['karma-phantomjs-launcher', 'karma-jasmine']
    });
  };
}(module));