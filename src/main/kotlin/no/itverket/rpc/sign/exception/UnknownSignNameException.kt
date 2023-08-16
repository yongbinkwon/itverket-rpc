package no.itverket.rpc.sign.exception

class UnknownSignNameException(
    name: String
): RuntimeException("There is no sign with name $name")