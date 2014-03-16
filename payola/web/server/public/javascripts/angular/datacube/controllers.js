/* Controllers */

angular.module('dataCube.controllers', []).
    controller('DataCube',
        ['$scope', 'DataCubeService', 'analysisId', 'evaluationId', '$q', '$location', function ($scope, DataCubeService,
            analysisId, evaluationId, $q, $location) {

            const URI_rdfType = "http://www.w3.org/1999/02/22-rdf-syntax-ns#type";
            const URI_dsd = "http://purl.org/linked-data/cube#DataStructureDefinition";
            const URI_component = "http://purl.org/linked-data/cube#component";
            const URI_dimension = "http://purl.org/linked-data/cube#dimension";
            const URI_measure = "http://purl.org/linked-data/cube#measure";
            const URI_QB_ORDER = "http://purl.org/linked-data/cube#order";
            const URI_attribute = "http://purl.org/linked-data/cube#attribute";
            const URI_label = "http://www.w3.org/2000/01/rdf-schema#label";
            const URI_sparqlResultValue = "http://www.w3.org/2005/sparql-results#value";
            const URI_binding = "http://www.w3.org/2005/sparql-results#binding";
            const URI_variable = "http://www.w3.org/2005/sparql-results#variable";
            const URI_value = "http://www.w3.org/2005/sparql-results#value";

            $scope.initDone = false;
            $scope.dataStructures = [];
            $scope.evaluationId = evaluationId;
            $scope.selectedDataStructure = null;
            $scope.XAxisDimension = null;
            $scope.activeMeasure = null;
            $scope.error = null;
            $scope.filtersString = "";

            $scope.highcharts = {
                options: {
                    chart: {
                        type: 'line',
                        height: 650
                    }
                },
                series: [
                ],
                title: {
                    text: 'DataCube'
                },
                yAxis: {
                    title: {
                        text: ""
                    }
                },
                loading: false
            };

            $scope.labelsMap = {};

            $scope.setDimensionsValuesEnabled = function (map) {
                angular.forEach($scope.dataStructures[$scope.selectedDataStructure].dimensions, function (dim, dimUri) {
                    var negative = dim.order == 1;
                    if (map[dim.uri]) {
                        angular.forEach(dim.values, function (val) {
                            var ptr = (val.uri ? "<" + val.uri + ">" : "'"+val.value+"'");

                            if (negative) {
                                val.active = map[dim.uri][ptr] !== false;
                            } else {
                                val.active = map[dim.uri][ptr];
                            }
                        });
                    }
                });
            };

            $scope.setActiveValue = function (dimUri, v) {
                var dimension = $scope.dataStructures[$scope.selectedDataStructure].dimensions[dimUri];
                var currentValue = v.active;

                if (dimension.order > 2) {
                    if (currentValue == false) {
                        v.active = true;
                        return;
                    }
                    angular.forEach(dimension.values, function (value) {
                        value.active = value == v;
                    });
                }
            };

            $scope.applyFilters = function (callback) {
                return function () {
                    if ($scope.filtersString) {
                        var filters = decodeURIComponent($scope.filtersString);
                        var components = filters.split(";;");

                        var map = {};

                        angular.forEach(components, function (c) {
                            var plusMinus = c.substring(0, 1);
                            var rest = c.substring(1);
                            var v = rest.split("$:$:$");

                            map[v[0]] = map[v[0]] || {};
                            map[v[0]][v[1]] = plusMinus == "+";
                        });

                        $scope.setDimensionsValuesEnabled(map);
                        $scope.filtersString = "";
                    }
                    callback();
                };
            };

            DataCubeService.get({queryName: "list-cubes", analysisId: analysisId, evaluationId: evaluationId},
                function (data) {

                    angular.forEach(data, function (node, uri) {
                        if (uri.substr(0, 1) != '$') {
                            if (isDSD(node)) {
                                parseDSD(node, data, uri);
                            }
                        }
                    });

                    $scope.loadingDataDone();
                });

            $scope.buildUI = function (callback) {

                var promises = [];

                $scope.labelsMap = {};

                angular.forEach([
                    $scope.dataStructures[$scope.selectedDataStructure].dimensions,
                    $scope.dataStructures[$scope.selectedDataStructure].attributes
                ],
                    function (container) {

                        angular.forEach(container, function (def, uri) {

                            def.values = [];

                            promises.push(DataCubeService.get({queryName: "distinct-values", evaluationId: evaluationId, property: uri, isDate: def.isDate},
                                function (data) {

                                    angular.forEach(data, function (value) {

                                        if (value[URI_binding]) {
                                            var res = {labels: []};
                                            angular.forEach(value[URI_binding], function (b) {
                                                var solution = data[b.value];
                                                if (solution[URI_variable][0].value == "o") {
                                                    var o = solution[URI_value][0];
                                                    if (o.type == 'uri') {
                                                        res.uri = o.value;
                                                    } else if (o.type == 'literal') {
                                                        res.value = o.value;
                                                        res.datype = o.datatype;
                                                    }
                                                    res.o = solution[URI_value][0];
                                                }
                                                if (solution[URI_variable][0].value == "date") {
                                                    var o = solution[URI_value][0];
                                                    res.uri = o.value;
                                                }
                                                if (solution[URI_variable][0].value == "spl") {
                                                    res.prefLabel = solution[URI_value][0].value;
                                                }
                                                if (solution[URI_variable][0].value == "l") {
                                                    res.label = solution[URI_value][0].value;
                                                }
                                                if (solution[URI_variable][0].value == "sn") {
                                                    res.notion = solution[URI_value][0].value;
                                                }
                                            });
                                            res.active = (!def.isDimension || (def == $scope.XAxisDimension));
                                            def.values.push(res);

                                            $scope.labelsMap[res.uri] = res.prefLabel || res.label || res.notion || res.uri;
                                        }
                                    });

                                    if (def.values[0]) {
                                        def.values[0].active = true;
                                    }
                                })
                            );
                        });
                    });

                $q.all(promises.map(function (x) {
                        return x['$promise'];
                    })).then(function () {
                    callback();
                });
            };

            $scope.switchDSD = function ($index, force, setUrl) {

                if (force || $scope.selectedDataStructure != $index) {
                    $scope.selectedDataStructure = $index;
                    $scope.XAxisDimension = $scope.dataStructures[$index].dimensionsOrdered[0] || $scope.dataStructures[$index].dimensionsOrdered[1];
                    $scope.activeMeasure = $scope.dataStructures[$index].measuresOrdered[0] || $scope.dataStructures[$index].measuresOrdered[1];

                    $scope.highcharts.title.text = $scope.dataStructures[$index].label;
                    $scope.highcharts.yAxis.title.text = $scope.activeMeasure.label.substring(0, 25) + "...";

                    angular.forEach($scope.dataStructures, function (dsd, i) {
                        if (i != $index) {
                            dsd.active = false;
                        }
                    });
                    $scope.dataStructures[$index].active = true;

                    $scope.buildUI($scope.applyFilters($scope.loadData));

                    if (setUrl) {
                        $location.search("dsd", encodeURIComponent($scope.dataStructures[$index].uri));
                    }
                }
            };

            $scope.loadData = function () {

                var measureUri = $scope.activeMeasure.uri;

                var filters = [];

                filters = filters.concat(computeFilters($scope.dataStructures[$scope.selectedDataStructure].attributes));
                var filtersFrom = []
                var dimCount = $scope.dataStructures[$scope.selectedDataStructure].dimensionsOrdered.length;
                if (dimCount > 3) {
                    filtersFrom = $scope.dataStructures[$scope.selectedDataStructure].dimensionsOrdered.slice(-(dimCount - 3));
                }
                filters = filters.concat(computeFilters(filtersFrom, true));
                filters = filters.concat(computeFilters([$scope.XAxisDimension]));

                $scope.highcharts.series = [];
                $scope.highcharts.xAxis = {categories: []};

                var cycleDim = 1;
                if (!$scope.dataStructures[$scope.selectedDataStructure].dimensionsOrdered[0]) {
                    cycleDim = 2;
                }

                var globalFilters = [];

                var dim = $scope.dataStructures[$scope.selectedDataStructure].dimensionsOrdered[cycleDim];
                angular.forEach(dim.values, function (v, k) {

                    if (!v.active) return;

                    var localFilters = filters.concat(computeFilters([
                        {uri: dim.uri, isDate: dim.isDate, values: [v]}
                    ], true));

                    globalFilters = globalFilters.concat(localFilters);

                    DataCubeService.get({queryName: "data", evaluationId: evaluationId, measure: measureUri, dimension: $scope.XAxisDimension.uri, filters: localFilters.map(function (x) {
                        return (x.positive ? "+" : "-") + x.component + "$:$:$" + x.value + "$:$:$" + x.isDate;
                    })}, function (data) {

                        var serie = {name: v.prefLabel || v.uri || v.value, data: []};
                        $scope.highcharts.series.push(serie);

                        angular.forEach(data, function (val, key) {
                            if (key.substr(0, 1) != '$') {
                                if (val[URI_binding]) {
                                    var res = {};
                                    angular.forEach(val[URI_binding], function (b) {
                                        var solution = data[b.value];
                                        if (solution[URI_variable][0].value == "m") {
                                            res.m = solution[URI_value][0];
                                        }
                                        if (solution[URI_variable][0].value == "d") {
                                            res.d = solution[URI_value][0];
                                        }
                                    });

                                    serie.data.push({name: $scope.labelsMap[res.d.value] || res.d.value, y: parseInt(res.m.value) });
                                    $scope.highcharts.xAxis.categories.push($scope.labelsMap[res.d.value]);
                                }
                            }
                        });
                    });
                });

                persistFilterState(globalFilters);

            };

            function persistFilterState(filters) {
                var f = filters.map(function (x) {
                    return (x.positive ? "+" : "-") + x.component + "$:$:$" + x.value;
                });

                $location.search("filters", f.join(";;"));
            }

            $scope.refresh = function () {
                $scope.loadData();
            };

            $scope.loadingDataDone = function () {
                if ($scope.dataStructures.length < 1) {
                    $scope.error = "No DSDs found.";
                    return;
                }

                if ($location.search().dsd) {
                    angular.forEach($scope.dataStructures, function (val, key) {
                        if (val.uri == decodeURIComponent($location.search().dsd)) {
                            $scope.switchDSD(key, true);
                        }
                    });
                } else {
                    $scope.switchDSD(0, true);
                }

                if ($location.search().chartType) {
                    $scope.switchChart($location.search().chartType);
                }

                if ($location.search().polarChart) {
                    $scope.switchPolar($location.search().polarChart);
                }

                if ($location.search().filters) {
                    $scope.filtersString = $location.search().filters;
                }

                $scope.initDone = true;
            };

            $scope.switchChart = function (chartType, setUrl) {
                $scope.highcharts.options.chart.type = chartType;
                if (setUrl) {
                    $location.search("chartType", chartType);
                }
            };

            $scope.switchPolar = function (isPolar, setUrl) {
                $scope.highcharts.options.chart.polar = isPolar === true;
                if (setUrl) {
                    $location.search("isPolar", isPolar === true);
                }
            };

            function computeFilters(components, positive) {
                positive = positive || false;

                var filters = [];
                angular.forEach(components, function (component) {
                    angular.forEach(component.values, function (componentVal) {
                        if ((positive && componentVal.active) || (!positive && !componentVal.active)) {
                            var val = (componentVal.uri ? "<" + componentVal.uri + ">" : "'" + componentVal.value + "'" + (componentVal.datatype ? "^^<" + componentVal.datatype + ">" : ""));
                            filters.push({component: component.uri, value: val, positive: positive, isDate: component.isDate && !componentVal.uri});
                        }
                    });
                });
                return filters;
            }

            function merge_options(obj1, obj2) {
                var obj3 = {};
                for (var attrname in obj1) {
                    obj3[attrname] = obj1[attrname];
                }
                for (var attrname in obj2) {
                    obj3[attrname] = obj2[attrname];
                }
                return obj3;
            }

            function isDSD(node) {
                return node[URI_rdfType] && node[URI_rdfType][0] && node[URI_rdfType][0].type == "uri" && node[URI_rdfType][0].value == URI_dsd;
            }

            function parseDSD(node, data, uri) {

                var dsdRef = {label: "Unlabeled DSD", uri: uri, dimensions: {}, measures: {}, attributes: {}, dimensionsOrdered: [], measuresOrdered: [], attributesOrdered: []};

                var queue = [
                    {uri: URI_measure, field: 'measures', arrayIdx: 'measuresOrdered'},
                    {uri: URI_dimension, field: 'dimensions', arrayIdx: 'dimensionsOrdered'},
                    {uri: URI_attribute, field: 'attributes', arrayIdx: 'attributesOrdered'}
                ];

                if (node[URI_component]) {

                    if (node[URI_label]) {
                        dsdRef.label = node[URI_label][0].value;
                    }

                    angular.forEach(node[URI_component], function (component) {
                            if (component.type == "bnode" || component.type == "uri") {
                                var componentValue = data[component.value];

                                angular.forEach(queue, function (queueItem) {
                                        if (componentValue[queueItem.uri]) {
                                            var c = {
                                                uri: componentValue[queueItem.uri][0].value,
                                                label: componentValue[URI_label][0].value,
                                                values: [],
                                                isDimension: queueItem.uri == URI_dimension,
                                                isDate: (componentValue[queueItem.uri][0].value.substr(-6).toLowerCase() == "period"),
                                                order: parseInt((componentValue[URI_QB_ORDER] || [
                                                    {value: dsdRef[queueItem.arrayIdx].length + 1}
                                                ])[0].value)
                                            };

                                            dsdRef[queueItem.field][componentValue[queueItem.uri][0].value] = c;
                                            dsdRef[queueItem.arrayIdx][c.order] = c; //TODO: a QB DSD with some orders defined and some not
                                        }
                                    }
                                );
                            }
                        }
                    );
                }

                $scope.dataStructures.push(dsdRef);
            }

        }])
;