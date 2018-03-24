package kingdee.bos.webapi.client;

public abstract interface ISerializerProxy {

    public abstract String Serialize(Object paramObject);

    public abstract Object Deserialize(String paramString, Class<?> paramClass);
}
