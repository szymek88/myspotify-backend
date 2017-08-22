package myspotify.hateoas;

import org.springframework.hateoas.ResourceSupport;

interface ResourceMapper<Entity, Resource extends ResourceSupport> {
    Resource mapToResource(Entity entity);
}
