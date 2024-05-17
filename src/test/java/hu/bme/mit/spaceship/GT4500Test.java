package hu.bme.mit.spaceship;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

public class GT4500Test {

  private GT4500 ship;
  private TorpedoStore mockTS1;
  private TorpedoStore mockTS2;

  @BeforeEach
  public void init(){
    this.mockTS1 = mock(TorpedoStore.class);
    this.mockTS2 = mock(TorpedoStore.class);
    this.ship = new GT4500(mockTS1, mockTS2);
  }

  @Test
  public void TestCase1(){
    // Arrange
    when(mockTS1.fire(1)).thenReturn(true);
    when(mockTS2.fire(1)).thenReturn(true);

    // Act
    boolean result = ship.fireTorpedo(FiringMode.SINGLE);

    // Assert
    verify(mockTS1, times(1)).fire(1);
    verify(mockTS2, times(0)).fire(1);
    assertEquals(true, result);
  }

  @Test
  public void TestCase2(){
    // Arrange
    when(mockTS1.fire(1)).thenReturn(true);
    when(mockTS2.fire(1)).thenReturn(true);

    // Act
    boolean result = ship.fireTorpedo(FiringMode.SINGLE);
    boolean result2 = ship.fireTorpedo(FiringMode.SINGLE);

    // Assert
    verify(mockTS1, times(1)).fire(1);
    verify(mockTS2, times(1)).fire(1);
    assertEquals(true, result);
    assertEquals(true, result2);
  }

  @Test
  public void TestCase3(){
    // Arrange
    when(mockTS1.fire(1)).thenReturn(true);
    when(mockTS2.fire(1)).thenReturn(true);
    when(mockTS1.isEmpty()).thenReturn(true);
    
    // Act
    boolean result = ship.fireTorpedo(FiringMode.SINGLE);

    // Assert
    verify(mockTS1, times(0)).fire(1);
    verify(mockTS2, times(1)).fire(1);
    assertEquals(true, result);
  }

  @Test
  public void TestCase4(){
    // Arrange
    when(mockTS1.fire(1)).thenReturn(false);
    when(mockTS2.fire(1)).thenReturn(true);
    
    // Act
    boolean result = ship.fireTorpedo(FiringMode.SINGLE);
    boolean result2 = ship.fireTorpedo(FiringMode.SINGLE);

    // Assert
    verify(mockTS1, times(1)).fire(1);
    verify(mockTS2, times(0)).fire(1);
    assertEquals(false, result);
    assertEquals(false, result2);
  }

  @Test
  public void TestCase5(){
    // Arrange
    when(mockTS1.fire(1)).thenReturn(true);
    when(mockTS2.fire(1)).thenReturn(true);

    // Act
    boolean result = ship.fireTorpedo(FiringMode.ALL);

    // Assert
    verify(mockTS1, times(1)).fire(1);
    verify(mockTS2, times(1)).fire(1);
    assertEquals(true, result);
  }

  @Test
  public void TestCase6(){
    // Arrange
    when(mockTS1.fire(1)).thenReturn(false);
    when(mockTS2.fire(1)).thenReturn(true);

    // Act
    boolean result = ship.fireTorpedo(FiringMode.ALL);

    // Assert
    verify(mockTS1, times(1)).fire(1);
    verify(mockTS2, times(0)).fire(1);
    assertEquals(false, result);
  }

  @Test
  public void TestCoverage1(){
    // Arrange
    when(mockTS1.fire(1)).thenReturn(true);
    when(mockTS2.fire(1)).thenReturn(true);
    when(mockTS2.isEmpty()).thenReturn(true);
    
    // Act
    boolean result = ship.fireTorpedo(FiringMode.SINGLE);
    boolean result2 = ship.fireTorpedo(FiringMode.SINGLE);

    // Assert
    verify(mockTS1, times(2)).fire(1);
    verify(mockTS2, times(0)).fire(1);
    assertEquals(true, result);
    assertEquals(true, result2);
  }

  @Test
  public void TestCoverage2(){
    // Arrange
    when(mockTS1.fire(1)).thenReturn(true);
    when(mockTS2.fire(1)).thenReturn(false);
    
    // Act
    boolean result = ship.fireTorpedo(FiringMode.SINGLE);
    boolean result2 = ship.fireTorpedo(FiringMode.SINGLE);
    boolean result3 = ship.fireTorpedo(FiringMode.SINGLE);

    // Assert
    verify(mockTS1, times(1)).fire(1);
    verify(mockTS2, times(1)).fire(1);
    assertEquals(true, result);
    assertEquals(false, result2);
    assertEquals(false, result3);
  }

  @Test
  public void TestCoverage3(){
    // Arrange
    when(mockTS1.fire(1)).thenReturn(true);
    when(mockTS2.fire(1)).thenReturn(true);
    when(mockTS1.isEmpty()).thenReturn(true);
    when(mockTS2.isEmpty()).thenReturn(true);
    
    // Act
    boolean result = ship.fireTorpedo(FiringMode.SINGLE);
    boolean result2 = ship.fireTorpedo(FiringMode.SINGLE);

    // Assert
    verify(mockTS1, times(0)).fire(1);
    verify(mockTS2, times(0)).fire(1);
    assertEquals(false, result);
    assertEquals(false, result2);
  }

  @Test
  public void TestCoverage4(){
    // Arrange
    when(mockTS1.fire(1)).thenReturn(true);
    when(mockTS2.fire(1)).thenReturn(true);
    when(mockTS2.isEmpty()).thenReturn(true);
    
    // Act
    boolean result = ship.fireTorpedo(FiringMode.SINGLE);

    when(mockTS1.isEmpty()).thenReturn(true);

    boolean result2 = ship.fireTorpedo(FiringMode.SINGLE);
    
    // Assert
    verify(mockTS1, times(1)).fire(1);
    verify(mockTS2, times(0)).fire(1);
    assertEquals(true, result);
    assertEquals(false, result2);
  }

  @Test
  public void TestCoverage5(){
    // Arrange
    when(mockTS1.fire(1)).thenReturn(true);
    when(mockTS2.fire(1)).thenReturn(true);
    when(mockTS1.isEmpty()).thenReturn(true);
    when(mockTS2.isEmpty()).thenReturn(true);
    
    // Act
    boolean result = ship.fireTorpedo(FiringMode.ALL);
    
    // Assert
    verify(mockTS1, times(0)).fire(1);
    verify(mockTS2, times(0)).fire(1);
    assertEquals(false, result);
  }

  @Test
  public void TestCoverage6(){
    // Arrange
    when(mockTS1.fire(1)).thenReturn(true);
    when(mockTS2.fire(1)).thenReturn(true);
    when(mockTS1.isEmpty()).thenReturn(true);
    when(mockTS2.isEmpty()).thenReturn(true);
    
    // Act
    boolean result = ship.fireTorpedo(FiringMode.valueOf("ALL"));
    
    // Assert
    verify(mockTS1, times(0)).fire(1);
    verify(mockTS2, times(0)).fire(1);
    assertEquals(false, result);
  }







}
