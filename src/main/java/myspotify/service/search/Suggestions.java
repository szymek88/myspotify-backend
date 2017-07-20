package myspotify.service.search;

import lombok.Data;

import java.util.List;

@Data
public class Suggestions {

    private List<String> songSuggestions;

    public Suggestions() {}
}
