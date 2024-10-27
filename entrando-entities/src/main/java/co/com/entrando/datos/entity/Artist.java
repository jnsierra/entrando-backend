package co.com.entrando.datos.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "artist")
public class Artist {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "artist_id_gen")
    @SequenceGenerator(name = "artist_id_gen", sequenceName = "artist_seq", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description", nullable = false)
    private String description;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "musicband_id", nullable = false)
    private MusicBand musicband;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public MusicBand getMusicband() {
        return musicband;
    }

    public void setMusicband(MusicBand musicband) {
        this.musicband = musicband;
    }

}