package cz.muni.fi.legomanager.controllers;

import cz.muni.fi.legomanager.ApiUris;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Lukáš Dvořák
 */
@RestController
public class MainController {

    final static Logger logger = LoggerFactory.getLogger(MainController.class);

    /**
     * The main entry point of the REST API
     * Provides access to all the resources with links to resource URIs
     * Can be even extended further so that all the actions on all the resources are available
     * and can be reused in all the controllers (possibly in full HATEOAS style)
     *
     * @return resources uris
     */
    @RequestMapping(value = "/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final Map<String, String> getResources() {

        Map<String, String> resourcesMap = new HashMap<>();

        resourcesMap.put("shapes_uri", ApiUris.ROOT_URI_SHAPES);
        resourcesMap.put("bricks_uri", ApiUris.ROOT_URI_BRICKS);
        resourcesMap.put("kits_uri", ApiUris.ROOT_URI_KITS);
        resourcesMap.put("sets_uri", ApiUris.ROOT_URI_SETS);
        resourcesMap.put("categories_uri", ApiUris.ROOT_URI_CATEGORIES);
        resourcesMap.put("users_uri", ApiUris.ROOT_URI_USERS);

        return Collections.unmodifiableMap(resourcesMap);

    }
}
