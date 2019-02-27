Palivovnik = angular.module 'Palivovnik', ['ngResource']

Palivovnik.controller "NewCarController", ($scope, $resource, $http) ->
    $scope.errors = []
    $scope.models = []
    $scope.data = {}

    $scope.loadModels = ->
        $http.get("/cars/models/#{$scope.data.car.marque.name}")
            .success (data) -> $scope.models = data

    Car = $resource "/car"

    $scope.save = ->
        $("button[type=submit]").attr("disabled", "disabled")
        Car.save {}, $scope.data,
            (->
                $scope.errors = []
                window.location = "/dashboard"),
            (errors) ->
                $("button[type=submit]").removeAttr("disabled")
                $scope.errors = presentErrors(errors)
        false

    null
