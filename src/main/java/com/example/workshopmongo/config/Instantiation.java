package com.example.workshopmongo.config;

import com.example.workshopmongo.domain.Post;
import com.example.workshopmongo.domain.User;
import com.example.workshopmongo.dto.AuthorDTO;
import com.example.workshopmongo.dto.CommentDTO;
import com.example.workshopmongo.repository.PostRepository;
import com.example.workshopmongo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;
import java.util.TimeZone;

@Configuration
public class Instantiation implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;

    @Override
    public void run(String... args) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        sdf.setTimeZone(TimeZone.getTimeZone("GMT"));

        userRepository.deleteAll();
        postRepository.deleteAll();

        User walter = new User(null, "Walter White", "walter@white.com");
        User jesse = new User(null, "Jesse Pinkman", "jesse@pinkman.com");
        User gus = new User(null, "Gus Fring", "gus@fring.com");

        List<User> users = Arrays.asList(walter, jesse, gus);
        userRepository.saveAll(users);

        Post post1 = new Post(null, sdf.parse("21/03/2018"), "Partiu viagem", "Vou viajar para São Paulo, abraços",new AuthorDTO(walter));
        Post post2 = new Post(null, sdf.parse("23/03/2018"), "Bom dia", "Acordei feliz hoje", new AuthorDTO(walter));

        CommentDTO c1 = new CommentDTO("Boa viagem mano", sdf.parse("21/03/2018"), new AuthorDTO(jesse));
        CommentDTO c2 = new CommentDTO("Aproveite", sdf.parse("22/03/2018"), new AuthorDTO(gus));
        CommentDTO c3 = new CommentDTO("Tenha um otimo dia", sdf.parse("23/03/2018"), new AuthorDTO(jesse));

        post1.getComments().addAll(Arrays.asList(c1, c2));
        post2.getComments().add(c3);

        postRepository.saveAll(Arrays.asList(post1, post2));

        walter.getPosts().addAll(Arrays.asList(post1, post2));
        userRepository.save(walter);

    }
}
