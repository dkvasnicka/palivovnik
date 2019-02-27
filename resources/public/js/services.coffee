Services = angular.module 'Services', []

Services.service "GMap", () ->

    gmap = {}

    geocoder = new google.maps.Geocoder()
    map = new google.maps.Map document.getElementById('map-canvas'), {
                    zoom: 8,
                    mapTypeId: google.maps.MapTypeId.ROADMAP,
                    center: new google.maps.LatLng(50.0788919, 14.4409467)
                }

    marker = new google.maps.Marker

    setMapCenterAndPutMarker = (loc) ->
        null

    gmap.setCoords = (coords) ->
        loc = new google.maps.LatLng coords[1], coords[0]

        map.setCenter(loc)
        marker.setMap null
        marker = new google.maps.Marker({
            map: map,
            position: loc
        })

    gmap.geocode = (address) ->
        p = new promise.Promise()

        geocoder.geocode( { 'address': address }, (results, status) ->
            p.done status, (
                if status == google.maps.GeocoderStatus.OK
                    loc = results[0].geometry.location
                    map.setCenter(loc)
                    marker.setMap null
                    marker = new google.maps.Marker({
                        map: map,
                        position: loc
                    })

                    [ loc.lng(), loc.lat() ]
                else null)
            null)
        p

    gmap
