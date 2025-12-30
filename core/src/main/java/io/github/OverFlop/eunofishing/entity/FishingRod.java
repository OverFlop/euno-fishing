package io.github.OverFlop.eunofishing.entity;

import com.badlogic.gdx.graphics.Texture;

import io.github.OverFlop.eunofishing.concepts.FishingPow;
/**
 * 
 */
public class FishingRod {

  private String name;
  private FishingPow fishingPow;
  private Bait bait;

  private Texture testRodTexture ;
  private Texture testRodFishingTexture;
  private Texture testRodCastingTexture;

  /**
   * @param name - Name of the fishing rod
   * @param fishingPow - The fishing power the rod gives the player
   * @param bait - The bait that is attached to the rod
   * @param rodCastImg - Name of the rodCast image
   * @param rodImg - Name of the rod image
   * @param rodFishingImg - Name of the rodFishing image
   */
  public FishingRod(String name, FishingPow fishingPow, Bait bait, Texture rodCastImg, Texture rodImg, Texture rodFishingImg) {
    this.name = name;
    this.fishingPow = fishingPow;
    this.bait = bait;

    this.testRodCastingTexture = rodCastImg;
    this.testRodFishingTexture = rodFishingImg;
    this.testRodTexture = rodFishingImg;
  }

  public Texture getTestRodTexture() {
    return testRodTexture;
  }

  public void setTestRodTexture(Texture testRodTexture) {
    this.testRodTexture = testRodTexture;
  }

  public Texture getTestRodFishingTexture() {
    return testRodFishingTexture;
  }

  public void setTestRodFishingTexture(Texture testRodFishingTexture) {
    this.testRodFishingTexture = testRodFishingTexture;
  }

  public Texture getTestRodCastingTexture() {
    return testRodCastingTexture;
  }

  public void setTestRodCastingTexture(Texture testRodCastingTexture) {
    this.testRodCastingTexture = testRodCastingTexture;
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
