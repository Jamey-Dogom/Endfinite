package com.jd.endfinite.models;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity
@Table(name = "stages")
public class Stage {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Size(min = 1, message = "Cannot be empty")
	private String description;
	@Column(updatable = false)
	private Date createdAt;
	private Date updatedAt;

	// Many stages belong to One story
	@ManyToOne(fetch = FetchType.LAZY,  cascade = {CascadeType.ALL})
	@JoinColumn(name = "story_id")
	private Story story;
	
	// Many stages can belong to many paths
	@ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
    @JoinTable(
            name = "stages_paths", 
            joinColumns = @JoinColumn(name = "stage_id"), 
            inverseJoinColumns = @JoinColumn(name = "path_id")
        )
        private List<Path> paths;

	// constructor
	public Stage() {

	}

	// Getters and Setters
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	public Story getStory() {
		return story;
	}

	public void setStory(Story story) {
		this.story = story;
	}

	public List<Path> getPaths() {
		return paths;
	}

	public void setPaths(List<Path> paths) {
		this.paths = paths;
	}

	// other getters and setters removed for brevity
	@PrePersist
	protected void onCreate() {
		this.createdAt = new Date();
	}

	@PreUpdate
	protected void onUpdate() {
		this.updatedAt = new Date();
	}

}
