package no.itverket.rpc.sign.exception

import no.itverket.rpc.sign.Sign
import kotlin.reflect.KClass

class UnknownSignClassException(
    signClass: KClass<out Sign>
): RuntimeException("There is a new class $signClass that is unknown")