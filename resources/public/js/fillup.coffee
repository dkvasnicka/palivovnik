Palivovnik = angular.module 'Palivovnik', ['Resources', 'Services', 'ui.date']

Palivovnik.controller "FillupController", ($scope, Fillup, GMap) ->

    $scope.errors = []
    cleanFillup = new Fillup { phases: [{ fuel: "petrol", volume: null, perunit: null }] }

    fillupId = $("#fid").attr("value")
    $scope.fillup = if fillupId == "" then cleanFillup else Fillup.get({ fid: fillupId },
        ->
            if $scope.fillup.loc
                GMap.setCoords $scope.fillup.loc)

    $scope.addPhase = ->
        $scope.fillup.phases.push {}

    $scope.removePhase = (idx) ->
        $scope.fillup.phases.splice idx, 1

    $scope.save = ->
        $("#fillupsubmit").attr("disabled", "disabled")
        $scope.fillup.$save(
            (->
                $scope.errors = []
                window.location = "/dashboard"),
            ((errors) ->
                $scope.errors = presentErrors(errors)
                $("#fillupsubmit").removeAttr("disabled")))
        false

    null

Palivovnik.controller "MapController", ($scope, GMap) ->

    $scope.address = ""

    $scope.geocode = ->
        locPromise = GMap.geocode($scope.address)
        locPromise.then (status, data) ->
            if status == "OK"
                $scope.fillup.loc = data
            else
                alert 'Vyhledávání se nezdařilo: ' + status
        false

    null
