package co.com.entrando.datos.entity;

import jakarta.persistence.*;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "music_band")
public class MusicBand {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "music_band_id_gen")
    @SequenceGenerator(name = "music_band_id_gen", sequenceName = "music_band_seq", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "name", nullable = false)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "presentation_id", nullable = false)
    private Presentation presentation;

    @OneToMany(mappedBy = "musicband")
    private Set<Artist> artists = new LinkedHashSet<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Presentation getPresentation() {
        return presentation;
    }

    public void setPresentation(Presentation presentation) {
        this.presentation = presentation;
    }

    public Set<Artist> getArtists() {
        return artists;
    }

    public void setArtists(Set<Artist> artists) {
        this.artists = artists;
    }

}