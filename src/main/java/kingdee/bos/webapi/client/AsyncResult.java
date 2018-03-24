package kingdee.bos.webapi.client;

public class AsyncResult<T> {

    public Exception Exception;
    public T ReturnValue;
    public Boolean Successful;

    public static <T> AsyncResult<T> CreateSuccessfulResult(T result) {
        AsyncResult<T> resultRtn = new AsyncResult<T>();
        resultRtn.Exception = null;
        resultRtn.ReturnValue = result;
        resultRtn.Successful = Boolean.valueOf(true);
        return resultRtn;
    }

    public static <T> AsyncResult<T> CreateUnSuccessfulResult(Exception ex) {
        AsyncResult<T> result = new AsyncResult<T>();
        result.Exception = ex;
        result.Successful = Boolean.valueOf(false);
        return result;
    }
}
