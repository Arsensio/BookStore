# AuthorsApi

All URIs are relative to *http://localhost/v2*

Method | HTTP request | Description
------------- | ------------- | -------------
[**createAuhores**](AuthorsApi.md#createAuhores) | **POST** /authors | Create auhores
[**deleteAuthors**](AuthorsApi.md#deleteAuthors) | **DELETE** /authors/{authorsId} | Deletes a authors
[**getAuthortById**](AuthorsApi.md#getAuthortById) | **GET** /authors/{authorsId} | Find Authors by ID
[**updateAuthorsWithForm**](AuthorsApi.md#updateAuthorsWithForm) | **POST** /authors/{authorsId} | Updates a authors in the store with form data
[**updateauthors**](AuthorsApi.md#updateauthors) | **PUT** /authors | Update an existing authors


<a name="createAuhores"></a>
# **createAuhores**
> createAuhores(body)

Create auhores

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.AuthorsApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost/v2");

    AuthorsApi apiInstance = new AuthorsApi(defaultClient);
    Author body = new Author(); // Author | Created user object
    try {
      apiInstance.createAuhores(body);
    } catch (ApiException e) {
      System.err.println("Exception when calling AuthorsApi#createAuhores");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **body** | [**Author**](Author.md)| Created user object |

### Return type

null (empty response body)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: Not defined

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**0** | successful operation |  -  |

<a name="deleteAuthors"></a>
# **deleteAuthors**
> deleteAuthors(authorsId)

Deletes a authors

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.AuthorsApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost/v2");

    AuthorsApi apiInstance = new AuthorsApi(defaultClient);
    Long authorsId = 56L; // Long | Author id to delete
    try {
      apiInstance.deleteAuthors(authorsId);
    } catch (ApiException e) {
      System.err.println("Exception when calling AuthorsApi#deleteAuthors");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **authorsId** | **Long**| Author id to delete |

### Return type

null (empty response body)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: Not defined

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**400** | Invalid ID supplied |  -  |
**404** | Pet not found |  -  |

<a name="getAuthortById"></a>
# **getAuthortById**
> Book getAuthortById(authorsId)

Find Authors by ID

Returns a single pet

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import org.openapitools.client.api.AuthorsApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost/v2");
    
    // Configure API key authorization: api_key
    ApiKeyAuth api_key = (ApiKeyAuth) defaultClient.getAuthentication("api_key");
    api_key.setApiKey("YOUR API KEY");
    // Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
    //api_key.setApiKeyPrefix("Token");

    AuthorsApi apiInstance = new AuthorsApi(defaultClient);
    Long authorsId = 56L; // Long | ID of pet to return
    try {
      Book result = apiInstance.getAuthortById(authorsId);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling AuthorsApi#getAuthortById");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **authorsId** | **Long**| ID of pet to return |

### Return type

[**Book**](Book.md)

### Authorization

[api_key](../README.md#api_key)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | successful operation |  -  |
**400** | Invalid ID supplied |  -  |
**404** | Pet not found |  -  |

<a name="updateAuthorsWithForm"></a>
# **updateAuthorsWithForm**
> updateAuthorsWithForm(authorsId, books)

Updates a authors in the store with form data

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.AuthorsApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost/v2");

    AuthorsApi apiInstance = new AuthorsApi(defaultClient);
    Long authorsId = 56L; // Long | ID of authors that needs to be updated
    List<String> books = "books_example"; // List<String> | Updated books of the authors
    try {
      apiInstance.updateAuthorsWithForm(authorsId, books);
    } catch (ApiException e) {
      System.err.println("Exception when calling AuthorsApi#updateAuthorsWithForm");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **authorsId** | **Long**| ID of authors that needs to be updated |
 **books** | [**List&lt;String&gt;**](String.md)| Updated books of the authors | [optional]

### Return type

null (empty response body)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/x-www-form-urlencoded
 - **Accept**: Not defined

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**405** | Invalid input |  -  |

<a name="updateauthors"></a>
# **updateauthors**
> updateauthors(body)

Update an existing authors

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import org.openapitools.client.api.AuthorsApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost/v2");
    
    // Configure OAuth2 access token for authorization: petstore_auth
    OAuth petstore_auth = (OAuth) defaultClient.getAuthentication("petstore_auth");
    petstore_auth.setAccessToken("YOUR ACCESS TOKEN");

    AuthorsApi apiInstance = new AuthorsApi(defaultClient);
    Author body = new Author(); // Author | Authors object that needs to be added to the store
    try {
      apiInstance.updateauthors(body);
    } catch (ApiException e) {
      System.err.println("Exception when calling AuthorsApi#updateauthors");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **body** | [**Author**](Author.md)| Authors object that needs to be added to the store |

### Return type

null (empty response body)

### Authorization

[petstore_auth](../README.md#petstore_auth)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: Not defined

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**400** | Invalid ID supplied |  -  |
**404** | Pet not found |  -  |
**405** | Validation exception |  -  |

