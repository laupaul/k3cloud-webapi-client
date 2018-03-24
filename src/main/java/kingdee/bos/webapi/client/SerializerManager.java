package kingdee.bos.webapi.client;

public class SerializerManager {

    public static ISerializerProxy Create()
            throws Exception {
        return new JsonSerializerProxy();
    }
}
