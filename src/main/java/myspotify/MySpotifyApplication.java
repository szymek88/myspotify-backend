package myspotify;

import myspotify.model.Album;
import myspotify.model.Artist;
import myspotify.model.Song;
import myspotify.service.Service;
import org.apache.solr.client.solrj.SolrServerException;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class MySpotifyApplication {

	@Bean
	CommandLineRunner populateDatabase(Service<Song, Long> songService,
									   Service<Artist, Long> artistService,
									   Service<Album, Long> albumService) {
		return (args) -> {
			List<SongData> songDataList = getSongDataList();
			songDataList.forEach(songData -> {
				try {
					Artist artist = artistService.save(new Artist(songData.artistName, "4"));
					Album album = albumService.save(new Album(songData.albumName,
							artist, songData.imageFilename));
					songService.save(new Song(songData.songName, artist, album,
							songData.audioFilename, songData.imageFilename));
				} catch (IOException | SolrServerException e) {
					e.printStackTrace();
				}
			});
		};
	}

	private List<SongData> getSongDataList() {
		List<SongData> songDataList = new ArrayList<>();
		songDataList.add(new SongData("Ed Sheeran", "Shape of You", "Divide", "1", "1"));
		songDataList.add(new SongData("Ofenbach", "Be Mine", "Be Mine", "2", "2"));
		songDataList.add(new SongData("Alle Farben", "Bad Ideas", "Best Friend", "3", "3"));
		songDataList.add(new SongData("Beyonce", "Blue", "Lemonade", "1", "1"));
		songDataList.add(new SongData("Bruno Mars", "That's What I Like", "Magic", "2", "2"));
		songDataList.add(new SongData("Bob Marley", "No Woman, No Cry", "Legend", "3", "3"));
		songDataList.add(new SongData("Travis Scott", "The End", "Birds", "1", "1"));
		songDataList.add(new SongData("The Weekend", "Can't Feel My Face", "Beauty", "2", "2"));
		return songDataList;
	}

	public static void main(String[] args) {
		SpringApplication.run(MySpotifyApplication.class, args);
	}

	private class SongData {
		public final String artistName;
		public final String songName;
		public final String albumName;
		public final String audioFilename;
		public final String imageFilename;

		public SongData(String artistName, String songName, String albumName,
						String audioFilename, String imageFilename) {
			this.artistName = artistName;
			this.songName = songName;
			this.albumName = albumName;
			this.audioFilename = audioFilename;
			this.imageFilename = imageFilename;
		}
	}
}
