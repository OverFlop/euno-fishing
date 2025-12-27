package entity;

import concepts.FishingPow;

public class Bait {
  FishingPow baitStrength;
  int baitLength;

  /**
   * @param baitStrength - This is the fishing power the bait gives the player
   * @param baitLength - This is how many fishes the bait lasts
   */
  public Bait(FishingPow baitStrength, int baitLength) {
    this.baitLength = baitLength;
    this.baitStrength = baitStrength;
  }

  public FishingPow getBaitStrength() {
    return baitStrength;
  }

  public void setBaitStrength(FishingPow baitStrength) {
    this.baitStrength = baitStrength;
  }

  public int getBaitLength() {
    return baitLength;
  }

  public void setBaitLength(int baitLength) {
    this.baitLength = baitLength;
  }
}
