package myspotify;

import lombok.Data;
import myspotify.model.Artist;
import myspotify.model.Song;
import myspotify.repository.ArtistRepository;
import myspotify.repository.SongRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class MySpotifyApplication {

	@Bean
	CommandLineRunner populateDatabase(SongRepository songRepository,
									   ArtistRepository artistRepository) {
		return (args) -> {
			List<SongData> songDataList = new ArrayList<>();
			songDataList.add(new SongData("Ed Sheeran", "Shape of You", "1"));
			songDataList.add(new SongData("Ofenbach", "Be Mine", "2"));
			songDataList.add(new SongData("Alle Farben", "Bad Ideas", "3"));

			songDataList.forEach(songData -> {
				Artist artist = artistRepository.save(new Artist(songData.getArtistName()));
				songRepository.save(new Song(songData.getSongName(), artist, songData.getFilename()));
			});
		};
	}

	public static void main(String[] args) {
		SpringApplication.run(MySpotifyApplication.class, args);
	}

	@Data
	private class SongData {
		private String artistName;
		private String songName;
		private String filename;

		public SongData(String artistName, String songName, String filename) {
			this.artistName = artistName;
			this.songName = songName;
			this.filename = filename;
		}
	}
}
