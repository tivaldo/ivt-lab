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
    
    // Act
    boolean result = ship.fireTorpedo(FiringMode.SINGLE);

    // Assert
    verify(mockTS1, times(0)).fire(1);
    verify(mockTS2, times(1)).fire(1);
    assertEquals(true, result);
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

}
