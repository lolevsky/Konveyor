package konveyor.generate

class ObjectResolver {
    private val customObjectResolverMap = HashMap<Class<*>, (Any)>()

    fun <C> addCustomType(clazz: Class<C>, lambda : () -> C) =
        customObjectResolverMap.put(clazz, lambda)

    internal fun resolve(clazz: Class<*>) : Any?{
        return if(customObjectResolverMap.containsKey(clazz)) {
            (customObjectResolverMap[clazz] as? () -> Any)?.invoke()
        }else{
            null
        }
    }

    internal fun merge(objectResolver: ObjectResolver): ObjectResolver {
        customObjectResolverMap.putAll(objectResolver.customObjectResolverMap)
        return this
    }
}
