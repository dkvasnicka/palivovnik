Palivovnik = angular.module 'Palivovnik', []

# ------------------ Facebook
window.fbAsyncInit = () ->
    FB.init {
        appId      : '',
        channelUrl : '//localhost:3000/channel.htm',
        status     : true, # check login status
        cookie     : true, # enable cookies to allow the server to access the session
        xfbml      : true  # parse XFBML
    }

    # checks fb login after page load
    FB.getLoginStatus (response) ->
        if response.status == 'connected'
            setupSession response.authResponse.accessToken, "facebook"
        else if response.status == 'not_authorized'
            console.log "Not authorized"
        else
            console.log response

$(document).ready ->
    $("#fblogin").click ->
        FB.login ((response) ->
            if response.authResponse
                setupSession response.authResponse.accessToken, "facebook"
            else
                console.log "cancelled"),
            { scope: 'email' }


# ------------------ Google
signinCallback = (authResult) ->
    token = authResult.access_token
    if token
        # Successfully authorized
        setupSession token, "google"
    else if authResult.error
        # Possible error codes:
        # "access_denied" - User denied access to your app
        # "immediate_failed" - Could not automatially log in the user
        console.log authResult.error

# ------------------- misc.
setupSession = (token, service) ->
    $.post "/setup-session/#{service}", { "token" : token },
        (data) ->
            console.log "Logged in using #{service}"
            window.location = if data.userExists then "/dashboard" else "/new-user"
