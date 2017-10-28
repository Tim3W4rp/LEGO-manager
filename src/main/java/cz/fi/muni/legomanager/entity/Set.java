package cz.fi.muni.legomanager.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Entity
public class Set {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(nullable=false,unique=true)
    private String description;
    
    @NotNull
    private BigDecimal price;


    public Set(Long setId) {
        this.id = setId;
    }
    public Set() {
    }

    public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public Long getId() {
        return id;
    }

    @Override
    public int hashCode() {
    	int result = 0;
    	int primeNumber = 13;
    	
    	if(this.description != null)
    		result = this.description.hashCode();
    	if(this.price != null)
    		result += this.price.hashCode() * primeNumber;
    	
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (! (obj instanceof Set))
            return false;
        Set otherObject = (Set) obj;
        
        if (this.price.equals(otherObject.price) 
        	&& this.description.equals(otherObject.description))
        	return true;
        
        return false;
    }



}
