/*
 * BoockStore
 * Special API for BookStore
 *
 * The version of the OpenAPI document: 1.0.0
 * Contact: arsenulykbekov9@gmail.com
 *
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */


package org.openapitools.client.api;

import org.openapitools.client.ApiException;
import org.openapitools.client.model.Author;
import org.openapitools.client.model.Book;
import org.junit.Test;
import org.junit.Ignore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * API tests for AuthorsApi
 */
@Ignore
public class AuthorsApiTest {

    private final AuthorsApi api = new AuthorsApi();

    
    /**
     * Create auhores
     *
     * 
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void createAuhoresTest() throws ApiException {
        Author body = null;
        api.createAuhores(body);

        // TODO: test validations
    }
    
    /**
     * Deletes a authors
     *
     * 
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void deleteAuthorsTest() throws ApiException {
        Long authorsId = null;
        api.deleteAuthors(authorsId);

        // TODO: test validations
    }
    
    /**
     * Find Authors by ID
     *
     * Returns a single pet
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void getAuthortByIdTest() throws ApiException {
        Long authorsId = null;
        Book response = api.getAuthortById(authorsId);

        // TODO: test validations
    }
    
    /**
     * Updates a authors in the store with form data
     *
     * 
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void updateAuthorsWithFormTest() throws ApiException {
        Long authorsId = null;
        List<String> books = null;
        api.updateAuthorsWithForm(authorsId, books);

        // TODO: test validations
    }
    
    /**
     * Update an existing authors
     *
     * 
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void updateauthorsTest() throws ApiException {
        Author body = null;
        api.updateauthors(body);

        // TODO: test validations
    }
    
}
