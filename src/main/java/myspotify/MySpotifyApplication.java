package myspotify;

import myspotify.model.*;
import myspotify.repository.PlaylistRepository;
import myspotify.repository.UserRepository;
import myspotify.service.Service;
import myspotify.service.SongService;
import org.apache.solr.client.solrj.SolrServerException;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class MySpotifyApplication {

	@Bean
	CommandLineRunner populateDatabase(SongService songService,
									   Service<Artist, Long> artistService,
									   Service<Album, Long> albumService,
									   UserRepository userRepository,
									   BCryptPasswordEncoder passwordEncoder,
									   PlaylistRepository playlistRepository) {
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
			Artist edSheeran = artistService.findOne(1l);
			Album divideAlbum = albumService.findOne(1l);
			fillAlbumWithSongs(getAlbumSongs(), edSheeran, divideAlbum, songService, "1");

			User user = userRepository.save(new User("user", passwordEncoder.encode("user")));

			savePlaylist(songService, playlistRepository, user);
		};
	}

	private void savePlaylist(SongService songService, PlaylistRepository playlistRepository,
							  User user) throws IOException, SolrServerException {
		Playlist playlist = playlistRepository.save(new Playlist("My Playlist", user));
		for (long songId = 4; songId <= 8; songId++) {
			songService.addSongToPlaylist(songId, playlist.getId());
		}
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

	private void fillAlbumWithSongs(List<SongData> songDataList, Artist artist, Album album,
									SongService songService, String imageFilename) {
		songDataList.forEach(songData -> {
			try {
				songService.save(new Song(songData.songName, artist, album,
                        songData.audioFilename, imageFilename));
			} catch (IOException | SolrServerException e) {
				e.printStackTrace();
			}
		});
	}

	private List<SongData> getAlbumSongs() {
		List<SongData> songDataList = new ArrayList<>();
		songDataList.add(new SongData("Eraser", "2"));
		songDataList.add(new SongData("Castle on the Hill", "3"));
		songDataList.add(new SongData("Dive", "1"));
		songDataList.add(new SongData("Perfect", "2"));
		songDataList.add(new SongData("Galway Girl", "3"));
		songDataList.add(new SongData("Happier", "1"));
		songDataList.add(new SongData("New Man", "2"));
		songDataList.add(new SongData("Hearts Dont't Break Around Here", "3"));
		songDataList.add(new SongData("What Do I Know?", "1"));
		songDataList.add(new SongData("How Would You Feel", "2"));
		songDataList.add(new SongData("Supermarket Flowers", "3"));
		return songDataList;
	}

	public static void main(String[] args) {
		SpringApplication.run(MySpotifyApplication.class, args);
	}

	class SongData {
		String artistName;
		String songName;
		String albumName;
		String audioFilename;
		String imageFilename;

		SongData(String artistName, String songName, String albumName,
						String audioFilename, String imageFilename) {
			this.artistName = artistName;
			this.songName = songName;
			this.albumName = albumName;
			this.audioFilename = audioFilename;
			this.imageFilename = imageFilename;
		}

		SongData(String songName, String audioFilename) {
			this.songName = songName;
			this.audioFilename = audioFilename;
		}
	}
}
