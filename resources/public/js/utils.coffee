presentErrors = (errorReponse) ->
    if errorReponse.status in [500, 404] then ["V aplikaci se vyskytla chyba. Kontaktujte správce."] else errorReponse.data



