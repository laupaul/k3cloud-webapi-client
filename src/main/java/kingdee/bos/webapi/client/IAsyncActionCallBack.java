package kingdee.bos.webapi.client;

public abstract interface IAsyncActionCallBack<T> {

    public abstract void CallBack(AsyncResult<?> asyncResult);
}
