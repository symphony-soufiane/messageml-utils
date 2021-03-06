package org.symphonyoss.symphony.messageml.bi;

import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class BiContextTest {

  public BiContext biContext;

  @Before
  public void setUp(){
    biContext = new BiContext();
  }

  @Test
  public void testUpdateItemInContextWhenItemNotFound() {
    biContext.updateItem("Link", "href");

    assertEquals(1, biContext.getItems().size());

    BiItem biItem = biContext.getItems().get(0);
    assertEquals("Link", biItem.getName());
    assertEquals("{href=1}", biItem.getAttributes().toString());
  }

  @Test
  public void testUpdateItemInContextWhenItemAndAttributeFound() {
    Map<String, Object> linkAttrs = new HashMap<>();
    linkAttrs.put("href", 1);
    BiItem headerItem = new BiItem("Link", linkAttrs);
    biContext.addItem(headerItem);

    biContext.updateItem("Link", "href");

    assertEquals(1, biContext.getItems().size());

    BiItem biItem = biContext.getItems().get(0);
    assertEquals("Link", biItem.getName());
    assertEquals("{href=2}", biItem.getAttributes().toString());
  }

  @Test
  public void testUpdateItemInContextWhenMultipleAttrsItemFound() {
    Map<String, Object> headerAttrs = new HashMap<>();
    headerAttrs.put("h1", 2);
    headerAttrs.put("h3", 1);
    BiItem headerItem = new BiItem("Header", headerAttrs);
    biContext.addItem(headerItem);

    biContext.updateItem("Header", "h1");

    assertEquals(1, biContext.getItems().size());

    BiItem biItem = biContext.getItems().get(0);
    assertEquals("Header", biItem.getName());
    assertEquals(expectedMap(), biItem.getAttributes());

  }

  private Map<String, Object> expectedMap() {
    Map<String, Object> expected = new HashMap<>();
    expected.put("h1", 3);
    expected.put("h3", 1);
    return expected;
  }

}