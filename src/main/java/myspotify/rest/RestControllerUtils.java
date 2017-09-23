package myspotify.rest;

import org.springframework.hateoas.ResourceSupport;
import org.springframework.hateoas.Resources;

import java.util.List;
import java.util.function.Function;

import static java.util.stream.Collectors.toList;

class RestControllerUtils {

    static <Entity, Resource extends ResourceSupport> Resources<Resource>
    mapToResources(List<Entity> entities, Function<Entity, Resource> mappingFunction) {
        List<Resource> resources = entities
                .stream()
                .map(mappingFunction::apply)
                .collect(toList());

        return new Resources<>(resources);
    }

    static void imitateNetworkDelay() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
