# PublishersApi

All URIs are relative to *http://localhost/v2*

Method | HTTP request | Description
------------- | ------------- | -------------
[**addPublishers**](PublishersApi.md#addPublishers) | **POST** /publishers | Add a new publishers to the store
[**deletePublishers**](PublishersApi.md#deletePublishers) | **DELETE** /publishers/{publisherId} | Deletes a publishers
[**getpublisherById**](PublishersApi.md#getpublisherById) | **GET** /publishers/{publisherId} | Find publishers by ID
[**updatePublishers**](PublishersApi.md#updatePublishers) | **PUT** /publishers | Update an existing publishers
[**updatePublishersWithForm**](PublishersApi.md#updatePublishersWithForm) | **POST** /publishers/{publisherId} | Updates a publishers in the store with form data


<a name="addPublishers"></a>
# **addPublishers**
> addPublishers(body)

Add a new publishers to the store

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.PublishersApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost/v2");

    PublishersApi apiInstance = new PublishersApi(defaultClient);
    Publisher body = new Publisher(); // Publisher | publishers object that needs to be added to the store
    try {
      apiInstance.addPublishers(body);
    } catch (ApiException e) {
      System.err.println("Exception when calling PublishersApi#addPublishers");
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
 **body** | [**Publisher**](Publisher.md)| publishers object that needs to be added to the store |

### Return type

null (empty response body)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: Not defined

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**405** | Invalid input |  -  |

<a name="deletePublishers"></a>
# **deletePublishers**
> deletePublishers(publisherId)

Deletes a publishers

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.PublishersApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost/v2");

    PublishersApi apiInstance = new PublishersApi(defaultClient);
    Long publisherId = 56L; // Long | Author id to delete
    try {
      apiInstance.deletePublishers(publisherId);
    } catch (ApiException e) {
      System.err.println("Exception when calling PublishersApi#deletePublishers");
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
 **publisherId** | **Long**| Author id to delete |

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

<a name="getpublisherById"></a>
# **getpublisherById**
> Publisher getpublisherById(publisherId)

Find publishers by ID

Returns a single pet

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import org.openapitools.client.api.PublishersApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost/v2");
    
    // Configure API key authorization: api_key
    ApiKeyAuth api_key = (ApiKeyAuth) defaultClient.getAuthentication("api_key");
    api_key.setApiKey("YOUR API KEY");
    // Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
    //api_key.setApiKeyPrefix("Token");

    PublishersApi apiInstance = new PublishersApi(defaultClient);
    Long publisherId = 56L; // Long | ID of publisher to return
    try {
      Publisher result = apiInstance.getpublisherById(publisherId);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling PublishersApi#getpublisherById");
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
 **publisherId** | **Long**| ID of publisher to return |

### Return type

[**Publisher**](Publisher.md)

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

<a name="updatePublishers"></a>
# **updatePublishers**
> updatePublishers(body)

Update an existing publishers

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.PublishersApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost/v2");

    PublishersApi apiInstance = new PublishersApi(defaultClient);
    Publisher body = new Publisher(); // Publisher | Publishers object that needs to be added to the store
    try {
      apiInstance.updatePublishers(body);
    } catch (ApiException e) {
      System.err.println("Exception when calling PublishersApi#updatePublishers");
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
 **body** | [**Publisher**](Publisher.md)| Publishers object that needs to be added to the store |

### Return type

null (empty response body)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: Not defined

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**400** | Invalid ID supplied |  -  |
**404** | Pet not found |  -  |
**405** | Validation exception |  -  |

<a name="updatePublishersWithForm"></a>
# **updatePublishersWithForm**
> updatePublishersWithForm(publisherId, name)

Updates a publishers in the store with form data

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.PublishersApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost/v2");

    PublishersApi apiInstance = new PublishersApi(defaultClient);
    Long publisherId = 56L; // Long | ID of publishers that needs to be updated
    String name = "name_example"; // String | Updated books of the authors
    try {
      apiInstance.updatePublishersWithForm(publisherId, name);
    } catch (ApiException e) {
      System.err.println("Exception when calling PublishersApi#updatePublishersWithForm");
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
 **publisherId** | **Long**| ID of publishers that needs to be updated |
 **name** | **String**| Updated books of the authors | [optional]

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

