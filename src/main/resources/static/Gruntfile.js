(function(module) {
  'use strict';

  module.exports = function(grunt) {
    grunt.initConfig({

      // Linting/hinting JavaScript files
      jshint: {
        options: {
          reporter: require('jshint-stylish'),
          jshintrc: true
        },
        dist: ['app/**/*.js']
      },

      // Executing Karma test runner
      karma: {
        dist: {
          options: {
            configFile: 'karma.config.js'
          }
        },
        continuous: {
          options: {
            configFile: 'karma.config.js',
            singleRun: false
          }
        }
      },

      // Injecting source files (CSS, JavaScript) into HTML pages
      injector: {
        options: {
          relative: true,
          addRootSlash: false
        },
        dist: {
          files: {
            'index.html': [
              'app/**/*.module.js',
              'app/**/*.factory.js',
              'app/**/*.service.js',
              'app/**/*.config.js',
              'app/**/*.controller.js',
              'app/**/*.filter.js',
              'app/**/*.directive.js'
            ]
          }
        }
      },

      // Execute tasks by watching files
      watch: {
        options: {
          atBegin: true
        },
        js: {
          files: ['app/**/*.js', '!app/**/*.spec.js'],
          tasks: ['injector:dist']
        },
        lint: {
          files: ['app/**/*.js'],
          tasks: ['jshint:dist']
        },
        test: {
          files: ['app/**/*.js'],
          tasks: ['karma:dist']
        }
      }
    });

    // Default task, automatically executed on build and file changes
    grunt.registerTask('default', ['jshint:dist', 'karma:dist']);

    // Watch task, necessary when developing in an environment that doesn't support Maven
    grunt.registerTask('watch', ['jshint:dist', 'watch:js', 'watch:lint', 'watch:test']);

    require('load-grunt-tasks')(grunt);
    require('time-grunt')(grunt);
  };
}(module));