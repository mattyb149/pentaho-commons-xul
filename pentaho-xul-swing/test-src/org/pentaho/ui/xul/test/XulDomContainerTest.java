package org.pentaho.ui.xul.test;

import java.util.Map;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.pentaho.ui.xul.XulComponent;
import org.pentaho.ui.xul.XulDomContainer;
import org.pentaho.ui.xul.XulLoader;
import org.pentaho.ui.xul.components.XulTextbox;
import org.pentaho.ui.xul.impl.XulEventHandler;
import org.pentaho.ui.xul.samples.SampleEventHandler;
import org.pentaho.ui.xul.samples.TreeHandler;
import org.pentaho.ui.xul.swing.SwingXulLoader;

import static org.junit.Assert.*;

public class XulDomContainerTest {
  
  @Test
  public void testOnLoad() throws Exception{

    XulLoader loader = new SwingXulLoader();
    XulDomContainer container = loader.loadXul("resource/documents/testEventHandlers.xul");
    XulTextbox testComponent = (XulTextbox) container.getDocumentRoot().getElementById("textbox1");
    assertEquals("initial value", testComponent.getValue());
    container.initialize();
    assertEquals("new value", testComponent.getValue());
  }
  
  @Test
  public void testGetHandlers() throws Exception{

    XulLoader loader = new SwingXulLoader();
    XulDomContainer container = loader.loadXul("resource/documents/testEventHandlers.xul");
    Map<String, XulEventHandler> handlers  = container.getEventHandlers();
    assertTrue(handlers.size() == 3);
    assertNotNull(handlers.get("handler1"));
    assertNotNull(handlers.get("handler2"));
    assertNotNull(handlers.get("handler5"));
  }
  
  @Test
  public void testGetHandler() throws Exception{

    XulLoader loader = new SwingXulLoader();
    XulDomContainer container = loader.loadXul("resource/documents/testEventHandlers.xul");
    assertNotNull(container.getEventHandler("handler1"));
    assertNotNull(container.getEventHandler("handler2"));
  }
  
  @Test
  public void testEventHandlerParams() throws Exception{
    XulLoader loader = new SwingXulLoader();
    XulDomContainer container = loader.loadXul("resource/documents/testEventHandlers.xul");
    container.initialize();
    SampleEventHandler handler = (SampleEventHandler) container.getEventHandler("handler2");
    assertEquals(5, handler.testNumber);
    assertEquals(5.2, handler.testDouble, .01);
    assertEquals("hello", handler.testString);
    assertTrue(handler.testBool);
  }
  
  @Test
  public void testMergeContainers() throws Exception{
    XulLoader loader = new SwingXulLoader();
    XulDomContainer container = loader.loadXul("resource/documents/testEventHandlers.xul");
    XulDomContainer container2 = container.loadFragment("resource/documents/testEventHandlers2.xul");
    container.mergeContainer(container2);
    assertNotNull(container.getEventHandler("handler3"));
    
  }
  
  @Test
  public void testIsRegistered() throws Exception {

    XulLoader loader = new SwingXulLoader();
    XulDomContainer container = loader.loadXul("resource/documents/testEventHandlers.xul");
    assertTrue(container.isRegistered("BUTTON"));
  }
  
  @Test
  public void testRegisterHandler() throws Exception {

    XulLoader loader = new SwingXulLoader();
    XulDomContainer container = loader.loadXul("resource/documents/testEventHandlers.xul");
    TreeHandler handler = new TreeHandler();
    handler.setName("handler1");
    container.addEventHandler(handler);
    TreeHandler outHandler = (TreeHandler) container.getEventHandler("handler1");
    assertNotNull(outHandler);
    assertSame(handler, outHandler);
  }

  @Test
  public void testBindings() throws Exception {

    XulLoader loader = new SwingXulLoader();
    XulDomContainer container = loader.loadXul("resource/documents/testEventHandlers.xul");
    //container.createBinding(source, sourceAttr, targetId, targetAttr)
  }

}

  