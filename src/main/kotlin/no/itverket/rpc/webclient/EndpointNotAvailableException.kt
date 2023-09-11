package no.itverket.rpc.webclient

import java.io.IOException

class EndpointNotAvailableException(
    path: String,
    team: String
): IOException("Every second that $team does not create the endpoint $path, a kitten loses its mother")