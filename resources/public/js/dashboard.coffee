Palivovnik = angular.module 'Palivovnik', ['Resources', 'ui.bootstrap']

Palivovnik.controller "FillupListController", ($scope, Fillup, $http) ->
    $scope.cars = []
    $scope.currentCar = null

    $scope.fillups = []

    # defaults
    $scope.noOfPages = 1
    $scope.currentPage = 1
    $scope.maxSize = 10

    $scope.$watch "currentPage", (oldv, newv) ->
        $scope.loadFillups(oldv)
        true

    carId = parseInt $("#carname").attr("car-id")

    $scope.loadFillups = (p) ->
        $http.get("/car/#{carId}/fillups/p/#{p}").success((data) -> $scope.fillups = data)

    $scope.deleteFillup = (fid) ->
        if confirm "Opravdu chcete tento záznam vymazat?"
            Fillup.delete({ fid: fid }, -> location.reload(false))

    # init
    $http.get("/car/#{carId}/fillups/count").success((data) -> $scope.noOfPages = Math.ceil(data.count / 10))
    $http.get("/cars").success(
        (data) ->
            $scope.cars = data
            $scope.currentCar = _.find data, (obj) -> obj.id == carId
            null)
    $scope.loadFillups(1)

    null

moment.lang("cs")
extractMonths = (data) ->
    data.map((mo) -> moment(mo.firstdate).format("MMMM YYYY"))

extractPlotData = (rawData, yaxis) ->
    rawData.map((mo) ->
        if mo.avgcons is 0 then null else mo[yaxis])

Palivovnik.controller "DashboardChartController", ($scope, $http) ->
    $http.get("/stats/#{$("#carname").attr("car-id")}/avg-cons-months").success(
        (data) ->
            if data.length == 0
                $("#chart").remove()
            else
                conses = extractPlotData data, 'avgcons'
                kms = extractPlotData data, 'distance'

                chart = c3.generate {
                    data: {
                        columns: [
                            ['data1'].concat(conses),
                            ['data2'].concat(kms)
                        ],
                        names: {
                            data1: 'l / 100 km',
                            data2: 'km / měsíc'
                        }
                        axes: {
                            data1: 'y',
                            data2: 'y2'
                        },
                        colors: {
                            data1: "#3498db"
                            data2: "#1abc9c"
                        }
                    },
                    axis: {
                        x : {
                            type : 'categorized'
                            categories: extractMonths data
                        }
                        y2: {
                            show: true
                        }
                    }
                }
    )

    null

fuelAmount = (phases) -> phases.map((x) -> x.volume).reduce((a, b) -> a + b)

Palivovnik.filter "totalFuelAmount", -> fuelAmount

Palivovnik.filter "avgPricePerUnit", ->
    (phases) ->
        (phases.map((x) -> x.perunit * x.volume)
               .reduce((a, b) -> a + b) / fuelAmount phases).toFixed 2
