package entity;

import concepts.Experience;
import concepts.FishingPow;


/**
 * {@summary}
 */
public class Player {
  private String name;
  private Experience exp;
  private FishingRod rod;
  private FishingPow overallPow;

  /**
   * {@summary}
   * 
   * @param name - Name of the player
   */
  public Player(String name) {
    this.name = name;
    this.exp = new Experience();
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
