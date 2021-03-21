package com.github.andrelugomes.model

/**
 * open ==
 * org.springframework.data.redis.serializer.SerializationException: Could not read JSON: Missing type id when trying to resolve subtype of [simple type, class java.lang.Object]: missing type id property '@class'
 *
 *
 * Default Nullable Values ==
 * org.springframework.data.redis.serializer.SerializationException: Could not read JSON: Cannot construct instance of `com.github.andrelugomes.model.Client` (no Creators, like default constructor, exist): cannot deserialize from Object value (no delegate- or property-based Creator)
 */
open class Client(var id: Int? = null, var token: String? = null, var name: String? = null)