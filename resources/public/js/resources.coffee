Resources = angular.module 'Resources', ['ngResource']

Resources.factory "Fillup", ($resource, $http) ->
    $resource "/car/fillup/:fid", { fid: "@_id" },
        {
            "save": {
                method : "POST", transformRequest: [(f) ->
                        f.date = f.date.getTime()
                        f
                ].concat $http.defaults.transformRequest
            },

            "get": {
                method: "GET", transformResponse: $http.defaults.transformResponse.concat(
                    [(f) ->
                        f.date = new Date f.date
                        f])
            }
        }
