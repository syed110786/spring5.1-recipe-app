package ali.springframework.spring5.recipeapp.domain;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(exclude= {"recipes"})
public class Category {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	private String description;
	
	@ManyToMany(mappedBy="categories")
	private Set<Recipe> recipes;

//	public Long getId() {
//		return id;
//	}
//
//	public void setId(Long id) {
//		this.id = id;
//	}
//
//	public String getDepartmentName() {
//		return description;
//	}
//
//	public void setDepartmentName(String departmentName) {
//		this.description = departmentName;
//	}
//
//	public Set<Recipe> getRecipes() {
//		return recipes;
//	}
//
//	public void setRecipes(Set<Recipe> recipes) {
//		this.recipes = recipes;
//	}
//	
	
}
