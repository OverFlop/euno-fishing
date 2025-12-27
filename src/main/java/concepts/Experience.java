package Concepts;

import Entity.Fish;

/**
 * {@summary}
 */
public class Experience {
  private int experienceLevel;
  private float expToNextLevel;
  private int currentExp;

  /**
   * {@summary}
   */
  public Experience() {
    this.experienceLevel = 1;
    this.expToNextLevel = 25;
    this.currentExp = 0;
  }

  /**
   * {@summary}
   * 
   * @return this.experienceLevel
   */
  public int getExperienceLevel() {
    return this.experienceLevel;
  }

  /**
   * {@summary}
   * 
   * @return this.expToNextLevel
   */
  public float getExpToNextLevel() {
    return this.expToNextLevel;
  }

  /**
   * {@summary}
   * 
   * @param exp
   */
  public void setExpToNextLevel(int exp) {
    this.expToNextLevel = exp;
  }

  /**
   * {@summary}
   * 
   * @param expLevel
   */
  public void setExperienceLevel(int expLevel) {
    this.expToNextLevel = expLevel;
  }

  /**
   * {@summary}
   * 
   * @param caughtFish
   */
  public void addExp(Fish caughtFish) {
    switch (caughtFish.getRarity()) {
      case COMMON:
        this.currentExp += 5;
        break;

      case UNCOMMON:
        this.currentExp += 10;
        break;

      case RARE:
        this.currentExp += 25;
        break;

      case UNIQUE:
        this.currentExp += 50;
        break;

      case LEGENDARY:
        this.currentExp += 70;
        break;

      case UNREAL:
        this.currentExp += 150;
        break;

      default:
        break;
    }

  }

  /**
   * {@summary}
   */
  public void levelUp() {
    if (this.currentExp >= this.expToNextLevel) {
      this.currentExp = 0;
      this.expToNextLevel *= Math.round(Math.pow(this.experienceLevel, 1.9) + 25);
    }
  }



}
