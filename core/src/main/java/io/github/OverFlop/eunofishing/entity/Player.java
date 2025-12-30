package io.github.OverFlop.eunofishing.entity;

import com.badlogic.gdx.graphics.Texture;

import io.github.OverFlop.eunofishing.concepts.Experience;
import io.github.OverFlop.eunofishing.concepts.FishingPow;


/**
 * {@summary}
 */
public class Player {
  private String name;
  private Experience exp;
  private FishingRod rod;
  private FishingPow overallPow;

  private Texture testPlayerTexture;

  /**
   * {@summary}
   * 
   * @param name - Name of the player
   */
  public Player(String name,Texture testPlayerTexture) {
    this.name = name;
    this.exp = new Experience();
    this.testPlayerTexture = testPlayerTexture;
  }

  public FishingPow getOverallPow() {
    return overallPow;
  }

  public void setOverallPow() {
    this.overallPow = rod.getFishingPow();
  }

  /**
   * {@summary}
   * 
   * @return
   */
  public String getName() {
    return this.name;
  }

  /**
   * {@summary}
   * 
   * @return
   */
  public Experience getExp() {
    return this.exp;
  }

  /**
   * @return
   */
  public FishingRod getRod() {
    return this.rod;
  }

  /**
   * @param name
   */
  public void setName(String name) {
    this.name = name;
  }


  /**
   * @param rod
   */
  public void setRod(FishingRod rod) {
    this.rod = rod;
  }
}
