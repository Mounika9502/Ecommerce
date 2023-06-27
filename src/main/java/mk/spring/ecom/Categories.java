package mk.spring.ecom;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="Categories")
public class Categories {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column(name="catname")
	private String catname;
	
	
	
	 @OneToMany(targetEntity = Product.class,cascade = CascadeType.ALL)
	 @JoinColumn(name="catid",referencedColumnName = "id")
	 private List<Product> prodlist;
	 public List<Product> getProdlist() {
			return prodlist;
		}
		public void setProdlist(List<Product> prodlist) {
			this.prodlist = prodlist;
		}
	
	public Categories(Long id, String catname) {
		super();
		this.id = id;
		this.catname = catname;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getCatname() {
		return catname;
	}
	public void setCatname(String catname) {
		this.catname = catname;
	}
	
	@Override
	public String toString() {
		return "Categories [id=" + id + ", catname=" + catname + "]";
	}
	public Categories() {
		
	}
	
	   

}