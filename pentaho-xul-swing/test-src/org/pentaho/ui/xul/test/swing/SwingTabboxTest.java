package org.pentaho.ui.xul.test.swing;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.pentaho.ui.xul.XulDomContainer;
import org.pentaho.ui.xul.components.XulMenuitem;
import org.pentaho.ui.xul.components.XulMessageBox;
import org.pentaho.ui.xul.components.XulTab;
import org.pentaho.ui.xul.components.XulTabpanel;
import org.pentaho.ui.xul.containers.XulListbox;
import org.pentaho.ui.xul.containers.XulMenu;
import org.pentaho.ui.xul.containers.XulTabbox;
import org.pentaho.ui.xul.containers.XulTabpanels;
import org.pentaho.ui.xul.containers.XulTabs;
import org.pentaho.ui.xul.dom.Document;
import org.pentaho.ui.xul.swing.SwingXulLoader;

public class SwingTabboxTest{

  Document doc = null;
  XulDomContainer container;
  XulTabbox tabbox;
  
  @Before
  public void setUp() throws Exception {
    
    container = new SwingXulLoader().loadXul("resource/documents/tabpanel.xul");
    doc = container.getDocumentRoot();
    tabbox = (XulTabbox) doc.getElementById("myTabList");
    
  }
  
  @Test
  public void selectedIndexTest() throws Exception{
    
    assertEquals(2, tabbox.getSelectedIndex());
    XulTabs tabs = (XulTabs) doc.getElementById("tabs");
    XulTab tab = (XulTab) doc.getElementById("tab1");
  }
  
  @Test
  public void disableTabTest() throws Exception{
    XulTabs tabs = (XulTabs) doc.getElementById("tabs");
    XulTab tab = (XulTab) doc.getElementById("tab1");
    
    tab.setDisabled(false);
    assertTrue(tab.isDisabled() == false);
    
  }
  
  @Test
  public void tabCountTest() throws Exception{
    XulTabs tabs = (XulTabs) doc.getElementById("tabs");
    
    assertEquals(4, tabs.getTabCount());
    
  }
  
  @Test
  public void removeTabTest() throws Exception{
    XulTabs tabs = (XulTabs) doc.getElementById("tabs");
    XulTab tab = (XulTab) doc.getElementById("tab1");
    tabs.removeChild(tab);
    assertEquals(3, tabs.getTabCount());
    
  }
  
  @Test
  public void getTabsTest() throws Exception{
    XulTabs tabs = (XulTabs) doc.getElementById("tabs");

    assertEquals(tabs, tabbox.getTabs());
    
  }
  @Test
  public void getTabPanelsTest() throws Exception{
    XulTabpanels panels = (XulTabpanels) doc.getElementById("tabpanels");

    assertEquals(panels, tabbox.getTabpanels());
    
  }
  
  @Test
  public void getSelectedPanelTest() throws Exception{
    XulTabpanel panel = (XulTabpanel) doc.getElementById("panel3");
   assertEquals(panel, tabbox.getSelectedPanel()); 
  }
  
  @Test
  public void removeTabpanelTest() throws Exception{
    XulTabpanels panels = (XulTabpanels) doc.getElementById("tabpanels");
    XulTabpanel panel = (XulTabpanel) doc.getElementById("panel3");
    panels.removeChild(panel);
    assertEquals(3, panels.getChildNodes().size());
    
  }
  
}