package kingdee.bos.webapi.client;

import org.apache.http.client.CookieStore;
import org.json.JSONObject;

public class ApiClient {

	private String _serverUrl;
	private CookieStore _cookieStore;

	public ApiClient(String serverUrl) {
		this._serverUrl = serverUrl;
	}

	public <T> ApiRequest<T> createRequest(String servicename, Object[] parameters, Class<T> returnType) {
		return new ApiServiceRequest<T>(this._serverUrl, this._cookieStore, servicename, parameters, returnType);
	}

	public <T> T execute(String servicename, Object[] parameters, Class<T> returnType) throws Exception {
		ApiRequest<T> request = createRequest(servicename, parameters, returnType);
		ApiHttpClient<T> httpClient = new ApiHttpClient<T>();
		request.setListener(httpClient);
		return (T) httpClient.Send(request, returnType);
	}

	public <T> ApiRequest<T> executeAsync(String servicename, Object[] parameters, Class<T> returnType, IAsyncActionCallBack<T> callback) throws Exception {
		ApiRequest<T> request = createRequest(servicename, parameters, returnType);
		ApiHttpClient<T> httpClient = new ApiHttpClient<T>(callback);
		request.setListener(httpClient);
		httpClient.syncSend(request);
		return request;
	}

	public Boolean login(String dbId, String userName, String password, int lcid) throws Exception {
		Object[] loginInfo = { dbId, userName, password, Integer.valueOf(lcid) };
		ApiRequest<String> request = createRequest("Kingdee.BOS.WebApi.ServicesStub.AuthService.ValidateUser", loginInfo, String.class);
		ApiHttpClient<String> httpClient = new ApiHttpClient<String>();
		request.setListener(httpClient);
		String ret = httpClient.Send(request, String.class);
		int result = new JSONObject(ret).getInt("LoginResultType");
		if (result == 1) {
			this._cookieStore = request.getCookieStore();
			return Boolean.valueOf(true);
		}
		return Boolean.valueOf(false);
	}
}
