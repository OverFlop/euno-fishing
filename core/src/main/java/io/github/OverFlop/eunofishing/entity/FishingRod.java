package io.github.OverFlop.eunofishing.entity;

import io.github.OverFlop.eunofishing.concepts.FishingPow;
/**
 * 
 */
public class FishingRod {

  private String name;
  private FishingPow fishingPow;
  private Bait bait;

  /**
   * @param name - Name of the fishing rod
   * @param fishingPow - The fishing power the rod gives the player
   * @param bait - The bait that is attached to the rod
   */
  public FishingRod(String name, FishingPow fishingPow, Bait bait) {
    this.name = name;
    this.fishingPow = fishingPow;
    this.bait = bait;
  }

  /**
   * @return name
   */
  public String getName() {
    return name;
  }

  /**
   * @param name - New name of fishing rod
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * @return fishingPow
   */
  public FishingPow getFishingPow() {
    return fishingPow;
  }

  /**
   * @param fishingPow - New fishingPow to be set
   */
  public void setFishingPow(FishingPow fishingPow) {
    this.fishingPow = fishingPow;
  }

  /**
   * @return bait
   */
  public Bait getBait() {
    return bait;
  }

  /**
   * @param bait - New bait to be set
   */
  public void setBait(Bait bait) {
    this.bait = bait;
  }



}
