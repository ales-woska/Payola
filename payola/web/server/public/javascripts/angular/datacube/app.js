'use strict';

// Declare app level module which depends on filters, and services
angular.module('dataCube', [
        'ngRoute',
        'ngResource',
        'dataCube.filters',
        'dataCube.services',
        'dataCube.runtimeServices',
        'dataCube.directives',
        'dataCube.controllers',
        "highcharts-ng"
    ]).
    config(['$routeProvider', function ($routeProvider) {
        $routeProvider.when('/',
            {templateUrl: '/assets/javascripts/angular/datacube/partials/datacube.html', controller: 'DataCube'});
        $routeProvider.otherwise({redirectTo: '/'});
    }]);
