/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package reshetyk.alexey.diary.domain;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @author Alexey
 */
@Entity
@Table(name = "RECORDS")
public class DiaryRecord implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID_RECORD")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "ID_CATEGORY")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private DiaryCategory diaryCategory;

    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "TEXT", length = 100000, nullable = false)//columnDefinition="TEXT")//columnDefinition="VARCHAR(40)")
    private String text;

    @Column(name = "CREATED")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date created;

    @Column(name = "UPDATED")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date updated;

    public DiaryRecord() {
    }

    public DiaryRecord(Integer id, DiaryCategory diaryCategory, String name, Date created, Date updated, String text) {
        this.id = id;
        this.diaryCategory = diaryCategory;
        this.name = name;
        this.created = created;
        this.updated = updated;
        this.text = text;
    }

    public DiaryRecord(DiaryCategory diaryCategory) {
        this.diaryCategory = diaryCategory;

    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public DiaryCategory getDiaryCategory() {
        return diaryCategory;
    }

    public void setDiaryCategory(DiaryCategory diaryGroup) {
        this.diaryCategory = diaryGroup;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
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
        if (!(object instanceof DiaryRecord)) {
            return false;
        }
        DiaryRecord other = (DiaryRecord) object;
        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
    }

    @Override
    public String toString() {
        return "diary.Record[ id=" + id + " ]";
    }

}
