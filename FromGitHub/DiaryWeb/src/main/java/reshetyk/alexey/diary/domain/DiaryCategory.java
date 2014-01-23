package reshetyk.alexey.diary.domain;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author Alexey
 */
@Entity
@Table(name = "CATEGORIES")
public class DiaryCategory implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID_CATEGORY")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "ID_PARENT")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private DiaryCategory parent;

    @ManyToOne //(cascade= CascadeType.ALL)
    @JoinColumn(name = "ID_USER", nullable = false)
    private DiaryUser user;

    @Column(name = "NAME", nullable = false)
    private String name;

    public DiaryCategory() {
    }

    public DiaryCategory(Integer id) {
        this.id = id;
    }

    public DiaryCategory(Integer id, DiaryUser user, String name) {
        this.id = id;
        this.user = user;
        this.name = name;
    }

    public DiaryCategory getParent() {
        return parent;
    }

    public void setParent(DiaryCategory parent) {
        this.parent = parent;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public DiaryUser getUser() {
        return user;
    }

    public void setUser(DiaryUser user) {
        this.user = user;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DiaryCategory)) {
            return false;
        }
        DiaryCategory other = (DiaryCategory) object;
        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
    }

    @Override
    public String toString() {
        return "diary.Group[ id=" + id + " ]";
    }
}
