package com.demo.repositories.projections;

import java.math.BigDecimal;

import com.demo.models.Category;

public interface ProductProjection {

	 Long getId();
	    String getTitle();
	    String getDescription();
	    BigDecimal getPrice();
	    Category getCategory();
	    String getImageUrl();
	
}
