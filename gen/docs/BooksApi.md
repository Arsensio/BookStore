# BooksApi

All URIs are relative to *http://localhost/v2*

Method | HTTP request | Description
------------- | ------------- | -------------
[**addBook**](BooksApi.md#addBook) | **POST** /books | Add a new book to the store
[**deleteBook**](BooksApi.md#deleteBook) | **DELETE** /books/{bookId} | Deletes a book
[**findBookByAuthor**](BooksApi.md#findBookByAuthor) | **GET** /books/findByAuthor | Finds Book by Author
[**findBooksByName**](BooksApi.md#findBooksByName) | **GET** /books/name | Finds Book by name
[**getBooktById**](BooksApi.md#getBooktById) | **GET** /books/{bookId} | Find book by ID
[**updateBook**](BooksApi.md#updateBook) | **PUT** /books | Update an existing book
[**updateBookWithForm**](BooksApi.md#updateBookWithForm) | **POST** /books/{bookId} | Updates a book in the store with form data


<a name="addBook"></a>
# **addBook**
> addBook(body)

Add a new book to the store

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import org.openapitools.client.api.BooksApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost/v2");
    
    // Configure OAuth2 access token for authorization: petstore_auth
    OAuth petstore_auth = (OAuth) defaultClient.getAuthentication("petstore_auth");
    petstore_auth.setAccessToken("YOUR ACCESS TOKEN");

    BooksApi apiInstance = new BooksApi(defaultClient);
    Book body = new Book(); // Book | Book object that needs to be added to the store
    try {
      apiInstance.addBook(body);
    } catch (ApiException e) {
      System.err.println("Exception when calling BooksApi#addBook");
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
 **body** | [**Book**](Book.md)| Book object that needs to be added to the store |

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
**405** | Invalid input |  -  |

<a name="deleteBook"></a>
# **deleteBook**
> deleteBook(bookId)

Deletes a book

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import org.openapitools.client.api.BooksApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost/v2");
    
    // Configure OAuth2 access token for authorization: petstore_auth
    OAuth petstore_auth = (OAuth) defaultClient.getAuthentication("petstore_auth");
    petstore_auth.setAccessToken("YOUR ACCESS TOKEN");

    BooksApi apiInstance = new BooksApi(defaultClient);
    Long bookId = 56L; // Long | Book id to delete
    try {
      apiInstance.deleteBook(bookId);
    } catch (ApiException e) {
      System.err.println("Exception when calling BooksApi#deleteBook");
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
 **bookId** | **Long**| Book id to delete |

### Return type

null (empty response body)

### Authorization

[petstore_auth](../README.md#petstore_auth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: Not defined

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**400** | Invalid ID supplied |  -  |
**404** | Pet not found |  -  |

<a name="findBookByAuthor"></a>
# **findBookByAuthor**
> List&lt;Book&gt; findBookByAuthor(author)

Finds Book by Author

Multiple status values can be provided with comma separated strings

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import org.openapitools.client.api.BooksApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost/v2");
    
    // Configure OAuth2 access token for authorization: petstore_auth
    OAuth petstore_auth = (OAuth) defaultClient.getAuthentication("petstore_auth");
    petstore_auth.setAccessToken("YOUR ACCESS TOKEN");

    BooksApi apiInstance = new BooksApi(defaultClient);
    List<String> author = Arrays.asList(); // List<String> | Status values that need to be considered for filter
    try {
      List<Book> result = apiInstance.findBookByAuthor(author);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling BooksApi#findBookByAuthor");
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
 **author** | [**List&lt;String&gt;**](String.md)| Status values that need to be considered for filter |

### Return type

[**List&lt;Book&gt;**](Book.md)

### Authorization

[petstore_auth](../README.md#petstore_auth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | successful operation |  -  |
**400** | Invalid status value |  -  |

<a name="findBooksByName"></a>
# **findBooksByName**
> List&lt;Book&gt; findBooksByName(name)

Finds Book by name

Multiple status values can be provided with comma separated strings

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import org.openapitools.client.api.BooksApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost/v2");
    
    // Configure OAuth2 access token for authorization: petstore_auth
    OAuth petstore_auth = (OAuth) defaultClient.getAuthentication("petstore_auth");
    petstore_auth.setAccessToken("YOUR ACCESS TOKEN");

    BooksApi apiInstance = new BooksApi(defaultClient);
    List<String> name = Arrays.asList(); // List<String> | Status values that need to be considered for filter
    try {
      List<Book> result = apiInstance.findBooksByName(name);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling BooksApi#findBooksByName");
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
 **name** | [**List&lt;String&gt;**](String.md)| Status values that need to be considered for filter |

### Return type

[**List&lt;Book&gt;**](Book.md)

### Authorization

[petstore_auth](../README.md#petstore_auth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | successful operation |  -  |
**400** | Invalid status value |  -  |

<a name="getBooktById"></a>
# **getBooktById**
> Book getBooktById(bookId)

Find book by ID

Returns a single pet

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import org.openapitools.client.api.BooksApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost/v2");
    
    // Configure API key authorization: api_key
    ApiKeyAuth api_key = (ApiKeyAuth) defaultClient.getAuthentication("api_key");
    api_key.setApiKey("YOUR API KEY");
    // Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
    //api_key.setApiKeyPrefix("Token");

    BooksApi apiInstance = new BooksApi(defaultClient);
    Long bookId = 56L; // Long | ID of pet to return
    try {
      Book result = apiInstance.getBooktById(bookId);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling BooksApi#getBooktById");
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
 **bookId** | **Long**| ID of pet to return |

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

<a name="updateBook"></a>
# **updateBook**
> updateBook(body)

Update an existing book

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import org.openapitools.client.api.BooksApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost/v2");
    
    // Configure OAuth2 access token for authorization: petstore_auth
    OAuth petstore_auth = (OAuth) defaultClient.getAuthentication("petstore_auth");
    petstore_auth.setAccessToken("YOUR ACCESS TOKEN");

    BooksApi apiInstance = new BooksApi(defaultClient);
    Book body = new Book(); // Book | Pet object that needs to be added to the store
    try {
      apiInstance.updateBook(body);
    } catch (ApiException e) {
      System.err.println("Exception when calling BooksApi#updateBook");
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
 **body** | [**Book**](Book.md)| Pet object that needs to be added to the store |

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

<a name="updateBookWithForm"></a>
# **updateBookWithForm**
> updateBookWithForm(bookId, name, price)

Updates a book in the store with form data

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import org.openapitools.client.api.BooksApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost/v2");
    
    // Configure OAuth2 access token for authorization: petstore_auth
    OAuth petstore_auth = (OAuth) defaultClient.getAuthentication("petstore_auth");
    petstore_auth.setAccessToken("YOUR ACCESS TOKEN");

    BooksApi apiInstance = new BooksApi(defaultClient);
    Long bookId = 56L; // Long | ID of book that needs to be updated
    String name = "name_example"; // String | Updated name of the book
    BigDecimal price = new BigDecimal(78); // BigDecimal | Updated price of the book
    try {
      apiInstance.updateBookWithForm(bookId, name, price);
    } catch (ApiException e) {
      System.err.println("Exception when calling BooksApi#updateBookWithForm");
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
 **bookId** | **Long**| ID of book that needs to be updated |
 **name** | **String**| Updated name of the book | [optional]
 **price** | **BigDecimal**| Updated price of the book | [optional]

### Return type

null (empty response body)

### Authorization

[petstore_auth](../README.md#petstore_auth)

### HTTP request headers

 - **Content-Type**: application/x-www-form-urlencoded
 - **Accept**: Not defined

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**405** | Invalid input |  -  |

