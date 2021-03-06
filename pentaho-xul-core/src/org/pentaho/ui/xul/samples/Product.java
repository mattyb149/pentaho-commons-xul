package org.pentaho.ui.xul.samples;

import org.pentaho.ui.xul.XulEventSourceAdapter;

public class Product extends XulEventSourceAdapter {
  private String name;
  private String description;
  
  public Product(String name, String descr){
    this.name = name;
    this.description = descr;
  }

  public String getName() {
  
    return name;
  }

  public void setName(String name) {
    this.name = name;
    firePropertyChange("name", null, name);
  }

  public String getDescription() {
  
    return description;
  }

  public void setDescription(String description) {
  
    this.description = description;
  }
  
}

  