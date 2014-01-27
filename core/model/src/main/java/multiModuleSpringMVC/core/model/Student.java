package multiModuleSpringMVC.core.model;

import java.util.Date;
import java.util.Set;

import javax.persistence.*;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Type;


@Entity
@Table(name="STUDENT")
public class Student {
	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID", unique=true, nullable=false)
    private int id;

    @Embedded
    private Name name;

	@Column(name="YEAR_LEVEL")
    private int level;
	
	@OneToMany(mappedBy="student", cascade=CascadeType.ALL)
	private Set<Subject> subjects;
	
	@Column(name="STATUS")
	@Type(type="yes_no")
	private Boolean status;
	
	@Column(name="GENDER")
	@Enumerated(EnumType.STRING)
	private Gender gender;

    @Temporal(value = TemporalType.DATE)
    @Column(name="BIRTHDAY")
    private Date birthday;

    @Temporal(value = TemporalType.TIMESTAMP)
    @Column(name="CREATE_TIME")
    private Date createTime;

    @Temporal(value = TemporalType.TIMESTAMP)
    @Column(name="UPDATE_TIME")
    private Date updateTime;

	public Student() {
		
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public Gender getGender() {
		return gender;
	}
	
	public void setGender(Gender gender) {
		this.gender = gender;
	}
	
	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public Set<Subject> getSubjects() {
		return subjects;
	}

	public void setSubjects(Set<Subject> subjects) {
		this.subjects = subjects;
	}

    public Name getName() {
        return name;
    }

    public void setName(Name name) {
        this.name = name;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
